package com.eventora.mobile.app.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.eventora.mobile.app.AppExecutors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zarkopafilis on 10/8/2017.
 */

public abstract class NetworkBoundData<DataType, ResponseType> {
    private final AppExecutors appExecutors;

    private final MediatorLiveData<DataType> result = new MediatorLiveData<>();


    public NetworkBoundData(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        LiveData<DataType> dbSource = loadFromDb();
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> setValue(newData));
            }
        });
    }

    @MainThread
    private void setValue(DataType newValue) {
        if (!Objects.equals(result.getValue(), newValue)) {
            result.setValue(newValue);
        }
    }

    private void fetchFromNetwork(final LiveData<DataType> dbSource) {
        Call<ResponseType> call = createCall();
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource, newData -> setValue(newData));

        call.enqueue(new Callback<ResponseType>() {
            @Override
            public void onResponse(Call<ResponseType> call, Response<ResponseType> response) {
                if (validateResponse(response)) {
                    MutableLiveData<DataType> networkSource = new MutableLiveData<>();
                    DataType proccessedData = processResponse(response.body());
                    networkSource.setValue(proccessedData);

                    result.addSource(networkSource, newData -> {
                        result.removeSource(networkSource);
                        result.removeSource(dbSource);
                        //noinspection ConstantConditions
                        appExecutors.diskIO().execute(() -> {
                            saveCallResult(proccessedData);
                            appExecutors.mainThread().execute(() ->
                                    // we specially request a new live data,
                                    // otherwise we will get immediately last cached value,
                                    // which may not be updated with latest results received from network.
                                    result.addSource(loadFromDb(),
                                            otherNewData -> setValue(otherNewData))
                            );
                        });
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseType> call, Throwable t) {
                onFetchFailed();
                result.addSource(dbSource,
                        newData -> setValue(newData));
            }
        });
    }

    protected void onFetchFailed() {
    }

    public LiveData<DataType> asLiveData() {
        return result;
    }

    @WorkerThread
    protected abstract DataType processResponse(ResponseType response);

    @WorkerThread
    protected abstract void saveCallResult(@NonNull DataType item);

    @MainThread
    protected abstract boolean shouldFetch(@Nullable DataType data);

    @NonNull
    @MainThread
    protected abstract LiveData<DataType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract Call<ResponseType> createCall();

    @NonNull
    @MainThread
    protected abstract boolean validateResponse(Response<ResponseType> response);
}
