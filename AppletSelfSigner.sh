#!/bin/bash
#  IMPORTANT: BEFORE RUNNING THIS SHELL SCRIPT FILE , MAKE SURE THAT YOU HAVE ADDED THE CORRECT DIRECTORIES AT YOUR PATH VARIABLE! IF THESE COMMANDS ARE NOT RECONGIZED YOU MIGHT WANT TO DO SOME GOOGLING. 
# (keystore,jarsigner)

#  Author: Zarkopafilis 

#  This is a very basic self-signer , just to gain full permission with a popup

#  Get the keystore name
echo -n "Keystore > "
read keystore 
#  Get the alias' value
echo -n "Alias > "
read alias
#  Just a message :D
echo Generating keystore...
#  Generate keystore
keytool -genkey -keystore $keystore -alias $alias
# Generate a self-signed certificate
keytool -selfcert -keystore $keystore -alias $alias
#  Get jarfile's title without the ".jar"
echo -n "Jarfile (Without '.jar')  > "
read jar
#  Sign all the class files in the jar with the given keystore
jarsigner -keystore $keystore $jar.jar $alias 
#  Close the command prompt after 5 seconds
read -t5 -n1 -r -p "Closing in 5 seconds..." key
exit
