#ifndef ScreenWrapper_h
#define ScreenWrapper_h
 
#include "Arduino.h"
#include "TFT.h"
#include "SPI.h"
 
/* pin definition for the Uno
#define cs   10
#define dc   9
#define rst  8*/
 
class ScreenWrapper
{
public:
  ScreenWrapper();
  void printf(char[] content);
  void printfk(char[] content , int key);
  void clrscr();
  void setKey(int key);
  int getKey();
  void setFontColor(int r , int g , int b);
  void setBackgroundColor(int r , int g , int b);
private:
  int _key;
  TFT _screen;  
};
 
#endif
