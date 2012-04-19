#include <TimerOne.h>

//Pin connected to Pin 12 of 74HC595 (Latch)
int latchPin = 8;
//Pin connected to Pin 11 of 74HC595 (Clock)
int clockPin = 12;
//Pin connected to Pin 14 of 74HC595 (Data)
int dataPin = 11;

uint8_t led[8];
uint8_t ledP[8];
uint8_t ledF[8];
uint8_t ledUp[8];
uint8_t ledDown[8];
uint8_t ledSign[46][8];

long counter1 = 0;
long counter2 = 0;

String arrowDirection = "down";

int n = 1;

void setup() {
  //set pins to output 
  pinMode(latchPin, OUTPUT);
  pinMode(clockPin, OUTPUT);
  pinMode(dataPin, OUTPUT);
  //digitalWrite(latchPin, HIGH);
//  ledSign[46][8] = {
//    // leer
//    { B00000000, B00000000, B00000000, B00000000, B00000000, B00000000, B00000000, B00000000 },
//    //A
//    { B00000000, B00011000, B00100100, B00100100, B00111100, B00100100, B00100100, B00000000 },
//    //B
//    { B00000000, B00111000, B00100100, B00111000, B00100100, B00100100, B00111000, B00000000 },
//    //C
//    { 
//      B00000000, 
//      B00011000, 
//      B00100100, 
//      B00100000, 
//      B00100000,
//      B00100100, 
//      B00011000,
//      B00000000     }
//    ,
//        { 
//      B00000000, 
//      B00011000, 
//      B00100100, 
//      B00100000, 
//      B00100000,
//      B00100100, 
//      B00011000,
//      B00000000     }
//    ,
//  }

  ledUp[0] = B00001000;
  ledUp[1] = B00011100;
  ledUp[2] = B00111110;
  ledUp[3] = B00011100;
  ledUp[4] = B00011100;
  ledUp[5] = B00011100;
  ledUp[6] = B00011100;
  ledUp[7] = B00011100;
  ledDown[7] = B00001000;
  ledDown[6] = B00011100;
  ledDown[5] = B00111110;
  ledDown[4] = B00011100;
  ledDown[3] = B00011100;
  ledDown[2] = B00011100;
  ledDown[1] = B00011100;
  ledDown[0] = B00011100;
  Timer1.initialize(10000);
  Timer1.attachInterrupt(screenUpdate);
}

void loop() {
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

    //  if (arrowDirection == "up"){
    //    for (int i=0;i<8;i++){
    //          led[i]= ledDown[i];
    //    }
    //    arrowDirection = "down";
    //  }
    //  else if (arrowDirection == "down"){
    //    for (int i=0;i<8;i++){
    //          led[i]= ledUp[i];
    //    }
    //    arrowDirection = "up";
    //  }

    for (int i = 0;i<8;i++){
      ledF[i] = ledUp[i] << n;
      ledP[i] = ledUp[i] >> (8 - n);
      led[i] = ledF[i] | ledP[i];

    }
    n++;
    if (n > 8){
      n = 1;
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




