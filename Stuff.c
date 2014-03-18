I prefer eclipse kepler in Ubuntu/Linux (and I am not doing any C/C++ development on windows any more ,
I have not tried on a mac). You need to download the CDT after downloading eclipse (it's a plugin , tons of tutorials 
out there!).GCC Compiler FTW!

*type to refer to the value stored inside the memory address of the type that is pointed at.
type(pointer) to refer to the memory address of the pointed type.
&type to refer to the type's memory address.

scanf("%type", *type);
printf("%type..." , type...);

Reading a file:

  	FILE *file;//our file
	file = fopen("/home/zarkpafilis/test.txt", "r");//directory - mode (r = read) -- returns a FILE
	int cap = 200;//how big shall the buffer be?
	char buff[cap];//create the string with the preferred size

	while(fgets(buff,cap,file) != NULL){//while the string you read till a character in order to avoid crash , in the specified file is not null,
			 printf ("%s\n", buff);//print the string
	}

	fclose(file);//housekeeping , close the file!

I dont get why you should use a function pointer. It seems useless to me. Anyway , here is how I get it.

int shit();//declaration

int shit(){//just return a random number
	return 14;
}

	int (*pointer) ();//this is how you declare it - type (*name) (parameters)
	pointer = &shit;//you can do that up here too ^. Just assign the fuctions memory address the same way you 
			//would do with a simple pointer

	printf("%d", pointer());//pointer(params) is the way you call the function with a pointer!

Structures. One of my favorites in C. They are like an organized bag of data. They can even hold more structures 
inside them!

//how to: lets "define" one structure
struct name {

var x,y,z;//data - or other structures!
struct other a;//the 'other' cant be the same structure (in this case: 'name')
};

//lets make one we can use in our program
struct name var_name;
//lets assign some values!
var_name.x = somevalue;
//lets get some values!
var x = var_name.something;

---
Something about how the kernel serves us:
Acts as a "bridge" between hardware and software
Treats I/O devices as files
Treats disk storage and main memory(a.k.a. RAM) as Virtual Memory
