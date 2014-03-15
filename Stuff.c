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
