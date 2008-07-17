package org.jicker.properties;

import java.io.*;
import java.util.*;

class SaveProperties
{
  public static void main( String args[] )
  {
   String filename = "gda.properties";
    try
    {
      FileOutputStream propOutFile =
         new FileOutputStream( filename );

      Properties p1 = new Properties( System.getProperties() );

      p1.setProperty( "MeinNameIst", "Forest Gump" );
      p1.store( propOutFile, "Eine Insel mit zwei Bergen" );

      FileInputStream propInFile = new FileInputStream( filename );

      Properties p2 = new Properties();
      p2.load( propInFile );

      p2.list( System.out );
     }
     catch ( FileNotFoundException e ) {
       System.err.println( "Can’t find " + filename );
     }
     catch ( IOException e ) {
       System.err.println( "I/O failed." );
     }
  }
}
