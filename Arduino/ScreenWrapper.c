#include "ScreenWrapper.h" 
 
#define cs 10
#define dc 9
#define rst 8  
  
  ScreenWrapper::ScreenWrapper(){
    
    _key = -1;
    
    _screen = TFT(cs , dc , rst);
    
    _screen.begin();
    
    _screen.background(0,0,0);
    
    _screen.stroke(255 , 255 , 255);
    
    _screen.setTextSize(2);  
  }
  
  ScreenWrapper::clrscr(){
   _screen.text(""); 
  }
  
  ScreenWrapper::setKey(int key){
   _key = key; 
  }
  
  ScreenWrapper::getKey(){
   return _key; 
  }
  
  ScreenWrapper::printf(char[] content){
   _screen.text(content); 
  } 
  
  ScreenWrapper::printfk(char[] content , int key){
   _screen.text(content);
  setKey(key); 
  }
  
  ScreenWrapper::setFontColor(int r , int g , int b){
   _screen.stroke(r , g , b); 
  }
  
  ScreenWrapper::setBackgroundColor(int r , int g , int b){
   _screen.background(r , g , b); 
  }
