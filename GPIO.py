#Notes regarding raspberry pi's GPIO pins

#I sometimes forget some stuff

#import
import RPi.GPIO as GPIO

#numbering that should be used (important), with GPIO.BOARD you refer to the pins with the way that they are counted 
#physically (not by other weird numbers...)
GPIO.setmode(GPIO.BOARD)

#set up the way each pin works
GPIO.setup(PIN_NUMBER , GPIO.OUT/GPIO.IN )

#output 3.3 Volts
GPIO.output(PIN_NUMBER , True/False )

#before you terminate the program , use this line of code to ensure a 'clean' exit - it basically resets everything
#(stuff you have already setted up , unbinds pins reserved from the program , allowing them to be used again without 
#warnings). Just do it!
GPIO.cleanup()
