::  IMPORTANT: BEFORE RUNNING THIS BATCH FILE , MAKE SURE THAT YOU HAVE ADDED THE CORRECT DIRECTORIES AT YOUR PATH VARIABLE! IF THESE COMMANDS ARE NOT RECONGIZED YOU MIGHT WANT TO DO SOME GOOGLING. 
:: (keystore,jarsigner)

::  Author: Zarkopafilis 

::  This is a very basic self-signer , just to gain full permission with a popup

::  Ensures that only output of the commands is printed to the screen
@echo off 

::  Set the title
title Simple Jar Self-Signer 
::  Set the color
color 0A 
::  Get the keystore name
set /p keystore= Keystore name: 
::  Get the alias' value
set /p alias= Alias: 
::  Just a message :D
echo Generating keystore...
::  Generate keystore
keytool -genkey -keystore %keystore% -alias %alias% 
:: Generate a self-signed certificate
keytool -selfcert -keystore %keystore% -alias %alias% 
::  Get jarfile's title without the ".jar"
set /p jar= Jarfile (Without '.jar') 
::  Sign all the class files in the jar with the given keystore
jarsigner -keystore %keystore% %jar%.jar %alias% 
::  Wait for a key to be pressed 
PAUSE 
::  Close the command prompt
exit 
