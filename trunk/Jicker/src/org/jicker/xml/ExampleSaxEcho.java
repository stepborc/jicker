package org.jicker.xml;

import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class ExampleSaxEcho extends DefaultHandler
{
  static final   String       sNEWLINE   = System.getProperty( "line.separator" );
  static private Writer       out        = null;
  private        StringBuffer textBuffer = null;

  // ---- main ----
  // http://www.torsten-horn.de/techdocs/java-xml.htm#Programmierbeispiel-SAX-Echo

  public static void main( String[] argv )
  {
    if( argv.length != 1 )
    {
      System.err.println( "Usage: java ExampleSaxEcho MyXmlFile.xml" );
      System.exit( 1 );
    }
    try {
      // Use an instance of ourselves as the SAX event handler
      DefaultHandler handler = new ExampleSaxEcho();
      // Parse the input with the default (non-validating) parser
      SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
      saxParser.parse( new File( argv[0] ), handler );
      System.exit( 0 );
    } catch( Throwable t ) {
      t.printStackTrace();
      System.exit( 2 );
    }
  }

  // ---- SAX DefaultHandler methods ----

  public void startDocument()
  throws SAXException
  {
    echoString( sNEWLINE + "<?xml ...?>" + sNEWLINE + sNEWLINE );
  }

  public void endDocument()
  throws SAXException
  {
    echoString( sNEWLINE );
  }

  public void startElement( String namespaceURI,
                            String localName,   // local name
                            String qName,       // qualified name
                            Attributes attrs )
  throws SAXException
  {
    echoTextBuffer();
    String eName = ( "".equals( localName ) ) ? qName : localName;
    echoString( "<" + eName );                  // element name
    if( attrs != null )
    {
      for( int i=0; i<attrs.getLength(); i++ )
      {
        String aName = attrs.getLocalName( i ); // Attr name
        if( "".equals( aName ) )  aName = attrs.getQName( i );
        echoString( " " + aName + "=\"" + attrs.getValue( i ) + "\"" );
      }
    }
    echoString( ">" );
  }

  public void endElement( String namespaceURI,
                          String localName,     // local name
                          String qName )        // qualified name
  throws SAXException
  {
    echoTextBuffer();
    String eName = ( "".equals( localName ) ) ? qName : localName;
    echoString( "</" + eName + ">" );           // element name
  }

  public void characters( char[] buf, int offset, int len )
  throws SAXException
  {
    String s = new String( buf, offset, len );
    if( textBuffer == null )
      textBuffer = new StringBuffer( s );
    else
      textBuffer.append( s );
  }

  // ---- Helper methods ----

  // Display text accumulated in the character buffer
  private void echoTextBuffer()
  throws SAXException
  {
    if( textBuffer == null )  return;
    echoString( textBuffer.toString() );
    textBuffer = null;
  }

  // Wrap I/O exceptions in SAX exceptions, to
  // suit handler signature requirements
  private void echoString( String s )
  throws SAXException
  {
    try {
      if( null == out )
        out = new OutputStreamWriter( System.out, "UTF8" );
      out.write( s );
      out.flush();
    } catch( IOException ex ) {
      throw new SAXException( "I/O error", ex );
    }
  }
}
