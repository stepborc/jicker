/*
 * 
 */
package org.jicker.util.log;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.Priority;
import org.apache.log4j.xml.DOMConfigurator;

// TODO: Auto-generated Javadoc
/**
 * The Class Log.
 */
public class Log
{
  
  /** The Constant LOG4J_CONFIG_FILE. */
  private static final String LOG4J_CONFIG_FILE  = "org/jicker/util/log/log4j.xml";
  
  /** The Constant MEIN_LOGGER_NAME. */
  private static final String MEIN_LOGGER_NAME   = "Log";
  
  /** The Constant MESSAGES_RESBUNDLE. */
  private static final String MESSAGES_RESBUNDLE = "org/jicker/util/log/messages";
  
  /** The messages res bundle. */
  private static ResourceBundle messagesResBundle;
  
  /** The mein logger. */
  private static Log meinLogger;
  
  /** The log4j logger. */
  private static Logger log4jLogger;

  // private damit Singleton
  /**
   * Instantiates a new log.
   */
  private Log()
  {
    init();
  }

  /**
   * Inits the.
   */
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
  /**
   * Gets the single instance of Log.
   * 
   * @return single instance of Log
   */
  public static synchronized Log getInstance()
  {
    if( meinLogger == null ) meinLogger = new Log();
    return meinLogger;
  }

  /**
   * Log.
   * 
   * @param level the level
   * @param caller the caller
   * @param id the id
   * @param parms the parms
   */
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

  /**
   * Checks if is enabled for.
   * 
   * @param level the level
   * 
   * @return true, if is enabled for
   */
  public boolean isEnabledFor( Level level )
  {
     return log4jLogger.isEnabledFor( level );
  }
}