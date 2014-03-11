/***Method***/
Method method = System.out.getClass().getMethod("println", String.class);//how to use a method with reflection. Get the class (With proper hierarchy) and then
method.invoke(System.out, "invoke parameters");	//invoke! Params = Method's class , parameters of the method!
/***Class***/
TO do
