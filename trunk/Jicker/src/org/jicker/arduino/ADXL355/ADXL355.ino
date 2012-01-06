    /*
    ADXL335test
    ahdavidson
    */


    #define DEVELOPMENT 1  // controls serial output: 0 = off, 1 = on


    const int xPin = 2;   // x-axis of the accelerometer output - analog pin
    const int yPin = 1;   // y-axis of the accelerometer output - analog pin
    const int zPin = 0;   // z-axis of the accelerometer output - analog pin
    const int led  = 13;  // a blinky light

    const int zeroOffsetSamples = 1000;  // # of samples to average for zero offset
    const int calibrateSeconds = 10;     // number of seconds for calibration test


    long xZero = 0;  // zero offset value for each axis (values at rest)
    long yZero = 0;
    long zZero = 0;

    int xMin = 0;  // min and max values found on each axis during calibration
    int xMax = 0;
    int yMin = 0;
    int yMax = 0;
    int zMin = 0;
    int zMax = 0;

    int x, y, z;  // for reading pin values

    long startCalibration;  // timer start value for calibration



    void setup() {

    #if DEVELOPMENT
      Serial.begin (9600);
      Serial.println ("*** ADXL335 Test");
    #endif

      pinMode (led, OUTPUT);

      pinMode (xPin, INPUT);  // not really needed for analog reads, just to remind
      pinMode (yPin, INPUT); 
      pinMode (zPin, INPUT);

      // establish the zero offset values for each axis
      // by reading the pin values a bunch of times
      // and averaging them -- the board must be motionless
      // during this loop

      for (int i=0; i<zeroOffsetSamples; i++) {
        xZero += analogRead (xPin);
        yZero += analogRead (yPin);
        zZero += analogRead (zPin);
      }

      xZero /= zeroOffsetSamples;
      yZero /= zeroOffsetSamples;
      zZero /= zeroOffsetSamples;

    #if DEVELOPMENT
      Serial.println ("*** Zero offset:");
      Serial.print   ("    x: "); 
      Serial.println (xZero);
      Serial.print   ("    y: "); 
      Serial.println (yZero);
      Serial.print   ("    z: "); 
      Serial.println (zZero);
    #endif

      xMin = xZero;
      xMax = xZero;
      yMin = yZero;
      yMax = yZero;
      zMin = zZero;
      zMax = zZero;


      // now turn on the LED and set a timer for some # of seconds
      // during that time, watch for the max and min values on each
      // axis and save them (will be used in real app)
      // if the board is rotated +/- 90 degrees, we can auto-calibrate
     

      digitalWrite (led, HIGH); 
      startCalibration = millis ();

      while ((millis () - startCalibration) < long (calibrateSeconds * 1000)) {
        x = analogRead (xPin);
        y = analogRead (yPin);
        z = analogRead (zPin);
        xMin = min (xMin, x);  //  - xZero);
        xMax = max (xMax, x);  //  - xZero);
        yMin = min (yMin, y);  //  - yZero);
        yMax = max (yMax, y);  //  - yZero);
        zMin = min (zMin, z);  //  - zZero);
        zMax = max (zMax, z);  //  - zZero);
      } 

      digitalWrite (led, LOW);

    #if DEVELOPMENT
      Serial.println ("*** Minimax: ");
      Serial.print   ("    x raw : "); Serial.print (xMin); Serial.print ("\t"); Serial.println (xMax);
      Serial.print   ("    y raw : "); Serial.print (yMin); Serial.print ("\t"); Serial.println (yMax);
      Serial.print   ("    z raw : "); Serial.print (zMin); Serial.print ("\t"); Serial.println (zMax);
      Serial.print   ("    x zero: "); Serial.print (xMin - xZero); Serial.print ("\t"); Serial.println (xMax - xZero);
      Serial.print   ("    y zero: "); Serial.print (yMin - yZero); Serial.print ("\t"); Serial.println (yMax - yZero);
      Serial.print   ("    z zero: "); Serial.print (zMin - zZero); Serial.print ("\t"); Serial.println (zMax - zZero);
    #endif

    }


    void loop() {

      // nothing for now

    }

