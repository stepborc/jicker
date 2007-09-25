package org.jicker.util.log;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

public class Log
{
  private static final String LOG4J_CONFIG_FILE  = "org/jicker/util/log/log4j.xml";
  private static final String MEIN_LOGGER_NAME   = "Log";
  private static final String MESSAGES_RESBUNDLE = "org/jicker/util/log/messages";
  private static ResourceBundle messagesResBundle;
  private static Log meinLogger;
  private static Logger log4jLogger;

  // private damit Singleton
  private Log()
  {
    init();
  }

  private synchronized void init()
  {
    try {
      DOMConfigurator.configureAndWatch( LOG4J_CONFIG_FILE, 60*1000 );
      log4jLogger = Logger.getLogger( MEIN_LOGGER_NAME );
      messagesResBundle = ResourceBundle.getBundle( MESSAGES_RESBUNDLE );
      log4jLogger.setResourceBundle( messagesResBundle );
    } catch( MissingResourceException ex ) {
      System.err.println( "Fehler: '" + MESSAGES_RESBUNDLE + "'-.properties-Datei fehlt!" );
    } catch( Exception ex ) {
      System.err.println( "Fehler bei Logger-Initialisierung!" );
    } 
  }

  // Singleton-Instanz
  public static synchronized Log getInstance()
  {
    if( meinLogger == null ) meinLogger = new Log();
    return meinLogger;
  }

  public synchronized static void log( Level level, Object caller, String id, String[] parms ) 
  {
    MDC.put( "clss", caller.getClass().getName() );
    MDC.put( "id", id );

    String message = id;
    if( null != messagesResBundle ) {
      try {
        message = messagesResBundle.getString( id );
      } catch( MissingResourceException ex ) {/**/}
    }
    if( null != parms )
      message = MessageFormat.format( message, parms );

    switch( level.toInt() ) {
      case Priority.ALL_INT:  
      case Priority.DEBUG_INT: log4jLogger.debug( message ); break;
      case Priority.INFO_INT:  log4jLogger.info(  message ); break;
      case Priority.WARN_INT:  log4jLogger.warn(  message ); break;
      case Priority.ERROR_INT: log4jLogger.error( message ); break;
      case Priority.FATAL_INT: log4jLogger.fatal( message ); break;                        
    }
  }

  public boolean isEnabledFor( Level level )
  {
     return log4jLogger.isEnabledFor( level );
  }
}