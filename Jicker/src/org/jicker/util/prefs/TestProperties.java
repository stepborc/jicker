package org.jicker.util.prefs;

import java.util.Properties;

class TestProperties
{
  public static void main( String args[] )
  {
    Properties defaultProperties = new Properties(),
               userProperties = new Properties(defaultProperties);

    defaultProperties.setProperty( "User", "Standard" );
    defaultProperties.setProperty( "Version", "" + 0.02f );
    userProperties.setProperty( "User", "Ulli Schnulli" );
    userProperties.setProperty( "MagCleverUndSmart", "" + true );

    System.out.println( "Default Properties:" );
    defaultProperties.list( System.out );

    System.out.println( "\nUser Properties:" );
    userProperties.list( System.out );

    System.out.println( "Property ’User’ is ’" +
                        userProperties.getProperty("User") + "’" );
    }
}