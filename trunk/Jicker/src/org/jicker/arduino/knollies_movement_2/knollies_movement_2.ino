#include <math.h> 
/*
27th September 2011
Getting ADXL335 data filtered and onto the PC via python.

// Values calibrated from *my* accelerometer. Calibrate yours today!
//  

*/

const int XMAX = 408;
const int XMIN = 270;
const int YMAX = 406;
const int YMIN = 269;
const int ZMAX = 406;
const int ZMIN = 274;
const float radToDeg = 57.2957795;

const int controlChar = 119;

const int input_X = 0; 
const int input_Y = 1;
const int input_Z = 2;


int xVal, yVal, zVal;
 
float xAng, yAng, zAng;
  
float x,y,z;

int charBuffer=0;

// Variables for the moving average filter

const int filterSize = 100; // Define the filter's width here. 

float filter_X[filterSize];  // Declare it.
float filter_Y[filterSize];
float filter_Z[filterSize];

int filterUpdatePos = 0;  // This variable indicates which array position to update.

float avg_x = 0;
float avg_y = 0;
float avg_z = 0;

float floatmap(float x, float in_min, float in_max, float out_min, float out_max)
{
  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
}

void updateGlobals()
{
  xVal = analogRead(input_X);
  yVal = analogRead(input_Y);
  zVal = analogRead(input_Z);
  
  xAng = floatmap(xVal, XMIN, XMAX, -90, 90);
  yAng = floatmap(yVal, YMIN, YMAX, -90, 90);
  zAng = floatmap(zVal, ZMIN, ZMAX, -90, 90);
  
  
  x = radToDeg * (atan2(yAng, -zAng) + PI);
  
  y = radToDeg * (atan2(xAng, -zAng) + PI);
  
 
  
  if(x>180)
      x= floatmap(x, 180,360,-180,0);  
      
  if(y>180)
      y= floatmap(y, 180,360,-180,0);  
      
      
      
  updateFilter();
    
}


void updateFilter()
{
   // First, we get the values from the analog in pins and then store them in the current position.
   filter_X[filterUpdatePos] = x;
   filter_Y[filterUpdatePos] = y;
 
   // Next, update the pointer. 
   if (filterUpdatePos >= filterSize)
   {
     filterUpdatePos = 0;  
   }
   else  
   {
      filterUpdatePos++;
 
    }
  
}



void updateAverage()
{
  float xTemp;
  float yTemp;

  xTemp = yTemp = 0;
  
  for (int i=0; i<filterSize; i++)
  {
    xTemp += filter_X[i];     
  }
  for (int i=0; i<filterSize; i++)
  {
    yTemp += filter_Y[i];     
  }
 
  
  avg_x = xTemp/filterSize;
  avg_y = yTemp/filterSize;

  
  
}


void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  
  for (int i=0; i< filterSize; i++)
  {
    Serial.print("Updating Position ");
    Serial.println(filterUpdatePos);
    updateFilter();
  }  
  
  
  
  
  
}

void loop() {




  //if(Serial.available() > 0)
  //  {
  //    charBuffer = Serial.read();
      
   //   if (charBuffer == controlChar)
   //     {
          updateGlobals();
          updateAverage();
          
          Serial.print(avg_x);
          Serial.print ("\t");
          Serial.println(avg_y);

     //   }
            
      
    //}
    //else
    //{

      //updateGlobals();
     
      
    //}


}

