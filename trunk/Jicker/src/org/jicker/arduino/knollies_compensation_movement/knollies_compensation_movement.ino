#include <TimerOne.h>


//=======================================================================
//  Reading Triple Axis Accelerometer MMA7260 using Arduino Duemilanove V2
//  (2011 July)
//  www.e-shore.com.my
//=======================================================================



//LED and Accelerometer pin
const int LED1=2;      //LED1 - LED8  
const int LED2=3;
const int LED3=4;
const int LED4=5;
const int LED5=6;
const int LED6=7;
const int LED7=8;
const int LED8=9;
const int ACC_X=A0;      //accelerometer pins
const int ACC_Y=A1;
const int ACC_Z=A2;

//Pin connected to Pin 12 of 74HC595 (Latch)
int latchPin = 8;
//Pin connected to Pin 11 of 74HC595 (Clock)
int clockPin = 12;
//Pin connected to Pin 14 of 74HC595 (Data)
int dataPin = 11;

uint8_t led[8];
uint8_t ledBlank[0];
uint8_t ledLeft1[8];
uint8_t ledLeft2[8];
uint8_t ledLeft3[8];
uint8_t ledLeft4[8];
uint8_t ledRight1[8];
uint8_t ledRight2[8];
uint8_t ledRight3[8];
uint8_t ledRight4[8];
uint8_t ledUp1[8];
uint8_t ledUp2[8];
uint8_t ledUp3[8];
uint8_t ledUp4[8];
uint8_t ledDown1[8];
uint8_t ledDown2[8];
uint8_t ledDown3[8];
uint8_t ledDown4[8];

//  setup function
//=======================================================================
void setup()
{
  //configure all LED pin as output
  pinMode(LED1,OUTPUT);
  pinMode(LED2,OUTPUT);
  pinMode(LED3,OUTPUT);
  pinMode(LED4,OUTPUT);
  pinMode(LED5,OUTPUT);
  pinMode(LED6,OUTPUT);
  pinMode(LED7,OUTPUT);
  pinMode(LED8,OUTPUT);

  //configure all accelerometer pins as input
  pinMode(ACC_X,INPUT);
  pinMode(ACC_Y,INPUT);
  pinMode(ACC_Z,INPUT);

  //off all LED by default
  write_led(0x00);

  //serial output
  Serial.begin(9600);

  //Display Ausgabe
  //Setzen Steuerung
  pinMode(latchPin, OUTPUT);
  pinMode(clockPin, OUTPUT);
  pinMode(dataPin, OUTPUT);

  ledBlank[0] = B00000000;

  ledLeft1[0] = B00000000;
  ledLeft1[1] = B00000000;
  ledLeft1[2] = B00000000;
  ledLeft1[3] = B00010000;
  ledLeft1[4] = B00010000;
  ledLeft1[5] = B00000000;
  ledLeft1[6] = B00000000;
  ledLeft1[7] = B00000000;

  ledLeft2[0] = B00000000;
  ledLeft2[1] = B00000000;
  ledLeft2[2] = B00100000;
  ledLeft2[3] = B00110000;
  ledLeft2[4] = B00110000;
  ledLeft2[5] = B00100000;
  ledLeft2[6] = B00000000;
  ledLeft2[7] = B00000000;

  ledLeft3[0] = B00000000;
  ledLeft3[1] = B01000000;
  ledLeft3[2] = B01100000;
  ledLeft3[3] = B01110000;
  ledLeft3[4] = B01110000;
  ledLeft3[5] = B01100000;
  ledLeft3[6] = B01000000;
  ledLeft3[7] = B00000000;

  ledLeft4[0] = B10000000;
  ledLeft4[1] = B11000000;
  ledLeft4[2] = B11100000;
  ledLeft4[3] = B11110000;
  ledLeft4[4] = B11110000;
  ledLeft4[5] = B11100000;
  ledLeft4[6] = B11000000;
  ledLeft4[7] = B10000000;

  ledRight1[0] = B00000000;
  ledRight1[1] = B00000000;
  ledRight1[2] = B00000000;
  ledRight1[3] = B00001000;
  ledRight1[4] = B00001000;
  ledRight1[5] = B00000000;
  ledRight1[6] = B00000000;
  ledRight1[7] = B00000000;

  ledRight2[0] = B00000000;
  ledRight2[1] = B00000000;
  ledRight2[2] = B00000100;
  ledRight2[3] = B00001100;
  ledRight2[4] = B00001100;
  ledRight2[5] = B00000100;
  ledRight2[6] = B00000000;
  ledRight2[7] = B00000000;

  ledRight3[0] = B00000000;
  ledRight3[1] = B00000010;
  ledRight3[2] = B00000110;
  ledRight3[3] = B00001110;
  ledRight3[4] = B00001110;
  ledRight3[5] = B00000110;
  ledRight3[6] = B00000010;
  ledRight3[7] = B00000000;

  ledRight4[0] = B00000001;
  ledRight4[1] = B00000011;
  ledRight4[2] = B00000111;
  ledRight4[3] = B00001111;
  ledRight4[4] = B00001111;
  ledRight4[5] = B00000111;
  ledRight4[6] = B00000011;
  ledRight4[7] = B00000001;

  ledUp1[0] = B00000000;
  ledUp1[1] = B00000000;
  ledUp1[2] = B00000000;
  ledUp1[3] = B00011000;
  ledUp1[4] = B00000000;
  ledUp1[5] = B00000000;
  ledUp1[6] = B00000000;
  ledUp1[7] = B00000000;

  ledUp2[0] = B00000000;
  ledUp2[1] = B00000000;
  ledUp2[2] = B00111100;
  ledUp2[3] = B00011000;
  ledUp2[4] = B00000000;
  ledUp2[5] = B00000000;
  ledUp2[6] = B00000000;
  ledUp2[7] = B00000000;

  ledUp3[0] = B00000000;
  ledUp3[1] = B01111110;
  ledUp3[2] = B00111100;
  ledUp3[3] = B00011000;
  ledUp3[4] = B00000000;
  ledUp3[5] = B00000000;
  ledUp3[6] = B00000000;
  ledUp3[7] = B00000000;

  ledUp4[0] = B11111111;
  ledUp4[1] = B01111110;
  ledUp4[2] = B00111100;
  ledUp4[3] = B00011000;
  ledUp4[4] = B00000000;
  ledUp4[5] = B00000000;
  ledUp4[6] = B00000000;
  ledUp4[7] = B00000000;

  ledDown1[0] = B00000000;                
  ledDown1[1] = B00000000;
  ledDown1[2] = B00000000;
  ledDown1[3] = B00000000;
  ledDown1[4] = B00011000;
  ledDown1[5] = B00000000;
  ledDown1[6] = B00000000;
  ledDown1[7] = B00000000;

  ledDown2[0] = B00000000;                
  ledDown2[1] = B00000000;
  ledDown2[2] = B00000000;
  ledDown2[3] = B00000000;
  ledDown2[4] = B00011000;
  ledDown2[5] = B00111100;
  ledDown2[6] = B00000000;
  ledDown2[7] = B00000000;

  ledDown3[0] = B00000000;                
  ledDown3[1] = B00000000;
  ledDown3[2] = B00000000;
  ledDown3[3] = B00000000;
  ledDown3[4] = B00011000;
  ledDown3[5] = B00111100;
  ledDown3[6] = B01111110;
  ledDown3[7] = B00000000;

  ledDown4[0] = B00000000;                
  ledDown4[1] = B00000000;
  ledDown4[2] = B00000000;
  ledDown4[3] = B00000000;
  ledDown4[4] = B00011000;
  ledDown4[5] = B00111100;
  ledDown4[6] = B01111110;
  ledDown4[7] = B11111111;

  //Timer initialize and calling periodicly
  Timer1.initialize(10000);
  Timer1.attachInterrupt(screenUpdate);
}

//  loop function
//=======================================================================
void loop()
{
  //variable
  int acc_x_raw=0;
  int acc_y_raw=0;
  double center_x=0;
  double center_y=0;
  double acc_x=0.0;
  double acc_y=0.0;
  double acc_x_array[50];
  double acc_y_array[50];
  int i=0;

  //get the center x voltage
  center_x=(double)analogRead(ACC_X)*5.0/1024.0;

  //get the center y voltage
  center_y=(double)analogRead(ACC_Y)*5.0/1024.0;

  //get all center value
  while(1)
  {
    //Serial.println(center_x);
    //get acc_x raw data
    acc_x_raw=analogRead(ACC_X);
    //get acc_y raw data
    acc_y_raw=analogRead(ACC_Y);

    //increase i counter from value 0-49
    i+=1;          //increase i
    if(i>49)i=0;   //make sure the value is not larger than 49 

    //convert the raw data to acceleration in double and store in 
    // array at index i
    acc_x_array[i]=((double)acc_x_raw*5.0/1024.0-center_x)/0.3;
    acc_y_array[i]=((double)acc_y_raw*5.0/1024.0-center_y)/0.3;

    //get the average value of all 50 acc value in array
    acc_x=0;                  //clear acc_x value
    acc_y=0;
    for(int j=0;j<=49;j+=1)   //for loop to add all acc value in array 
    {                         // to acc_x
      acc_x+=acc_x_array[j];
      acc_y+=acc_y_array[j];
    }
    acc_x=acc_x/50.0;         //divide the total value to 50
    acc_y=acc_y/50.0;         //divide the total value to 50


      for(int i = 0;i<8;i++){
      led[i] = ledBlank[0];
    }

    //compare the acceleration value, output different LED for different 
    // range of acceleration
    if(acc_x>=0.75){
      write_led(0b10000000);      //0.75 < acc
      for (int i =0;i<8;i++){
        led[i] = ledLeft4[i];
      }
    }
    else if(acc_x>=0.5){
      write_led(0b01000000);      //0.5 < acc < 0.75
      for (int i =0;i<8;i++){
        led[i] = ledLeft3[i];
      }
    }
    else if(acc_x>=0.25){
      write_led(0b00100000);     //0.25 < acc < 0.5
      for (int i =0;i<8;i++){
        led[i] = ledLeft2[i];
      }
    }
    else if(acc_x>=0.0){
      write_led(0b00010000);      //0.0 < acc < 0.25
      for (int i =0;i<8;i++){
        led[i] = ledLeft1[i];
      }
    }
    else if(acc_x>=-0.25){
      write_led(0b00001000);    //-0.25 < acc < 0.0
      for (int i =0;i<8;i++){
        led[i] = ledRight1[i];
      }
    }
    else if(acc_x>=-0.5){
      write_led(0b00000100);     //-0.5 < acc < -0.25
      for (int i =0;i<8;i++){
        led[i] = ledRight2[i];
      }
    }
    else if(acc_x>=-0.75){
      write_led(0b00000010);    //-0.75 < acc < -0.5
      for (int i =0;i<8;i++){
        led[i] = ledRight3[i];
      }
    }
    else {
      write_led(0b00000001);                  //acc < -0.75
      for (int i =0;i<8;i++){
        led[i] = ledRight4[i];
      }
    }
    if(acc_y>=0.75){
      write_led(0b10000000);      //0.75 < acc
      for (int i =0;i<8;i++){
        led[i] = led[i] | ledUp4[i];
      }
    }
    else if(acc_y>=0.5){
      write_led(0b01000000);      //0.5 < acc < 0.75
      for (int i =0;i<8;i++){
        led[i] = led[i] | ledUp3[i];
      }
    }
    else if(acc_y>=0.25){
      write_led(0b00100000);     //0.25 < acc < 0.5
      for (int i =0;i<8;i++){
        led[i] = led[i] | ledUp2[i];
      }
    }
    else if(acc_y>=0.0){
      write_led(0b00010000);      //0.0 < acc < 0.25
      for (int i =0;i<8;i++){
        led[i] = led[i] | ledUp1[i];
      }
    }
    else if(acc_y>=-0.25){
      write_led(0b00001000);    //-0.25 < acc < 0.0
      for (int i =0;i<8;i++){
        led[i] = led[i] | ledDown1[i];
      }
    }
    else if(acc_y>=-0.5){
      write_led(0b00000100);     //-0.5 < acc < -0.25
      for (int i =0;i<8;i++){
        led[i] = led[i] | ledDown2[i];
      }
    }
    else if(acc_y>=-0.75){
      write_led(0b00000010);    //-0.75 < acc < -0.5
      for (int i =0;i<8;i++){
        led[i] = led[i] | ledDown3[i];
      }
    }
    else {
      write_led(0b00000001);                  //acc < -0.75
      for (int i =0;i<8;i++){
        led[i] = led[i] | ledDown4[i];
      }
    }
  }
}

//  extra function
//=======================================================================

//function to write one byte value to leds, LED8 = MSB, LED1 = LSB
void write_led (unsigned char data)
{
  digitalWrite(LED1,(data&0x01));
  digitalWrite(LED2,(data&0x02));
  digitalWrite(LED3,(data&0x04));
  digitalWrite(LED4,(data&0x08));
  digitalWrite(LED5,(data&0x10));
  digitalWrite(LED6,(data&0x20));
  digitalWrite(LED7,(data&0x40));
  digitalWrite(LED8,(data&0x80));  
}

void screenUpdate(){
  uint8_t row = B00000001;
  for (byte k = 0; k < 9; k++) {
    digitalWrite(latchPin, LOW); // Open up the latch ready to receive data

    shiftIt(~row ); 
    shiftIt(led[k] ); // LED array

    digitalWrite(latchPin, HIGH); // Close the latch, sending the data in the registers out to the matrix
    row = row << 1;
  }
}

void shiftIt(byte dataOut) {
  // Shift out 8 bits LSB first, 
  // on rising edge of clock

  boolean pinState;

  //clear shift register read for sending data
  digitalWrite(dataPin, LOW);

  // for each bit in dataOut send out a bit
  for (int i=7; i>=0; i--)  {
    //set clockPin to LOW prior to sending bit
    digitalWrite(clockPin, LOW);

    // if the value of DataOut and (logical AND) a bitmask
    // are true, set pinState to 1 (HIGH)
    if ( dataOut & (1<<i) ) {
      pinState = HIGH;
    }
    else {	
      pinState = LOW;
    }

    //sets dataPin to HIGH or LOW depending on pinState
    digitalWrite(dataPin, pinState);
    //send bit out on rising edge of clock 
    digitalWrite(clockPin, HIGH);
    digitalWrite(dataPin, LOW);
  }

  //stop shifting
  digitalWrite(clockPin, LOW);
}




