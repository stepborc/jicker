#include <TimerOne.h>

//ADXL335 Part Start
//Analog read pins
const int xPin = 0;
const int yPin = 1;
const int zPin = 2;

//read the analog values from the accelerometer
//into 
int xRead, yRead, zRead;

//convert read values to degrees -90 to 90 - Needed for atan2
int xAng, yAng, zAng;

//The minimum and maximum values that came from
//the accelerometer while standing still
//You very well may need to change these
int minVal = 265;
int maxVal = 402;

//to hold the caculated values
double x;
double y;
double z;
//ADXL335 Part Ends

//Pin connected to Pin 12 of 74HC595 (Latch)
int latchPin = 8;
//Pin connected to Pin 11 of 74HC595 (Clock)
int clockPin = 12;
//Pin connected to Pin 14 of 74HC595 (Data)
int dataPin = 11;

uint8_t led[8];
uint8_t ledUp[8];
uint8_t ledDown[8];

long counter1 = 0;
long counter2 = 0;

String arrowDirection = "down";

void setup() {
  //set pins to output 
  pinMode(latchPin, OUTPUT);
  pinMode(clockPin, OUTPUT);
  pinMode(dataPin, OUTPUT);
  //digitalWrite(latchPin, HIGH);
  ledUp[0] = B00001000;
  ledUp[1] = B00011100;
  ledUp[2] = B00111110;
  ledUp[3] = B00011100;
  ledUp[4] = B00011100;
  ledUp[5] = B00011100;
  ledUp[6] = B00011100;
  ledUp[7] = B00011100;
  ledDown[7] = B00000100;
  ledDown[6] = B00001110;
  ledDown[5] = B00011111;
  ledDown[4] = B00001110;
  ledDown[3] = B00001110;
  ledDown[2] = B00001110;
  ledDown[1] = B00001110;
  ledDown[0] = B00001110;
  Timer1.initialize(10000);
  Timer1.attachInterrupt(screenUpdate);
  //Start ADXL335 output to serial port
  Serial.begin(9600);
  //End ADXL335
}

void loop() {
  //Start ADXL335 Code
  //read the analog values from the accelerometer
  xRead = analogRead(xPin);
  yRead = analogRead(yPin);
  zRead = analogRead(zPin);

  //convert read values to degrees -90 to 90 - Needed for atan2
  xAng = map(xRead, minVal, maxVal, -90, 90);
  yAng = map(yRead, minVal, maxVal, -90, 90);
  zAng = map(zRead, minVal, maxVal, -90, 90);

  //Caculate 360deg values like so: atan2(-yAng, -zAng)
  //atan2 outputs the value of -π to π (radians)
  //We are then converting the radians to degrees
  x = RAD_TO_DEG * (atan2(-yAng, -zAng) + PI);
  y = RAD_TO_DEG * (atan2(-xAng, -zAng) + PI);
  z = RAD_TO_DEG * (atan2(-yAng, -xAng) + PI);

  //Output the caculations
  //Serial.print("x: ");
  //Serial.print(x);
  //Serial.print(" | y: ");
  //Serial.print(y);
  //Serial.print(" | z: ");
  //Serial.println(z);

  //int ledPinX = 5; //whatever PWM digital pin you want
  //int ledPinY = 9; //whatever PWM digital pin you want
  //int ledPinZ = 10; //whatever PWM digital pin you want

  //pinMode(ledPinX, OUTPUT);
  //pinMode(ledPinY, OUTPUT);
  //pinMode(ledPinZ, OUTPUT);

  //int LEDx = map(x, 0, 360, 0, 255);
  //int LEDy = map(y, 0, 360, 0, 255);
  //int LEDz = map(z, 0, 360, 0, 255);

  //analogWrite(ledPinX, LEDx);
  //analogWrite(ledPinY, LEDy);
  //analogWrite(ledPinZ, LEDz);


  //  delay(100);//just here to slow down the serial output - Easier to read
  //End ADXL335 Code


  counter1++;
  if (counter1 >=100000) {
    counter2++;
  }
  if (counter2 >= 10000) {
    counter1 = 0;
    counter2 = 0;

    //for (int i=0; i<8; i++) {
    //led[i]= ~led[i];
    //}

    if (arrowDirection == "up"){
      for (int i=0;i<8;i++){
        led[i]= ledDown[i];
      }
      arrowDirection = "down";
    }
    else if (arrowDirection == "down"){
      for (int i=0;i<8;i++){
        led[i]= ledUp[i];
      }
      arrowDirection = "up";
    }
  }
}

void screenUpdate() {
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
void getX(){
  
}



