/*
 * 
 */
package org.jicker.util.cobra;

import java.security.Policy;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lobobrowser.html.HttpRequest;
import org.lobobrowser.html.UserAgentContext;

// TODO: Auto-generated Javadoc
/**
 * Simple implementation of {@link org.lobobrowser.html.UserAgentContext}.
 * This class is provided for user convenience.
 * Normally this class should be extended in order to provide appropriate
 * user agent information and more robust content loading routines.
 */
public class SimpleUserAgentContext implements UserAgentContext {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(SimpleUserAgentContext.class.getName());
	
	/** The Constant mediaNames. */
	private static final Set mediaNames = new HashSet();
	
	static {
		// Media names claimed by this context.
		Set mn = mediaNames;
	    mn.add("screen");	
	    mn.add("tv");	
	    mn.add("tty");	
	    mn.add("all");	
	}
	
	/**
	 * This implementation returns true for certain media names,
	 * such as <code>screen</code>.
	 * 
	 * @param mediaName the media name
	 * 
	 * @return true, if checks if is media
	 */
	public boolean isMedia(String mediaName) {
		return mediaNames.contains(mediaName.toLowerCase());
	}

	/**
	 * Creates a {@link org.lobobrowser.html.test.SimpleHttpRequest} instance.
	 * The {@link org.lobobrowser.html.HttpRequest}</code> object returned by this method is
	 * used to load images, scripts, style sheets, and to implement
	 * the Javascript XMLHttpRequest class.
	 * Override if a custom mechanism to make requests is needed.
	 * 
	 * @return the http request
	 */
	public HttpRequest createHttpRequest() {
		return new SimpleHttpRequest(this, this.getProxy());
	}	

	/**
	 * Gets the connection proxy used in requests created
	 * by {@link #createHttpRequest()} by default. This implementation returns
	 * <code>Proxy.NO_PROXY</code>, but it may be overridden.
	 * 
	 * @return the proxy
	 */
	protected java.net.Proxy getProxy() {
		return java.net.Proxy.NO_PROXY;
	}
	
	/**
	 * Warn.
	 * 
	 * @param message the message
	 * @param throwable the throwable
	 */
	public void warn(String message, Throwable throwable) {
		if(logger.isLoggable(Level.WARNING)) {
			logger.log(Level.WARNING, message, throwable);
		}
	}
	
	/**
	 * Error.
	 * 
	 * @param message the message
	 * @param throwable the throwable
	 */
	public void error(String message, Throwable throwable) {
		if(logger.isLoggable(Level.SEVERE)) {
			logger.log(Level.SEVERE, message, throwable);
		}
	}
	
	/**
	 * Warn.
	 * 
	 * @param message the message
	 */
	public void warn(String message) {
		if(logger.isLoggable(Level.WARNING)) {
			logger.log(Level.WARNING, message);
		}		
	}
	
	/**
	 * Error.
	 * 
	 * @param message the message
	 */
	public void error(String message) {
		if(logger.isLoggable(Level.SEVERE)) {
			logger.log(Level.SEVERE, message);
		}
	}

	/**
	 * Override to provide the application "code name."
	 * 
	 * @return the app code name
	 */
	public String getAppCodeName() {
		return "Cobra";
	}

	/**
	 * Override to provide the application's minor version.
	 * 
	 * @return the app minor version
	 */
	public String getAppMinorVersion() {
		return "0";
	}

	/**
	 * Override to provide the application name.
	 * 
	 * @return the app name
	 */
	public String getAppName() {
		return "Browser";
	}

	/**
	 * Override to provide the application version.
	 * 
	 * @return the app version
	 */
	public String getAppVersion() {
		return "1";
	}

	/**
	 * Override to provide the browser language. This
	 * implementation returns EN.
	 * 
	 * @return the browser language
	 */
	public String getBrowserLanguage() {
		return "EN";
	}

	/**
	 * Returns the value of Java property <code>os.name</code>.
	 * 
	 * @return the platform
	 */
	public String getPlatform() {
		return System.getProperty("os.name");
	}

	/**
	 * Returns a simple user-agent string.
	 * 
	 * @return the user agent
	 */
	public String getUserAgent() {
		return "Mozilla/4.0 (compatible; MSIE 6.0;) Cobra/Simple";
	}

	/**
	 * This implementation returns false. Override to give a different value.
	 * 
	 * @return true, if checks if is cookie enabled
	 */
	public boolean isCookieEnabled() {
		return false;
	}

	/**
	 * This implementation returns blank. Override if a cookie store is implemented.
	 * 
	 * @param url the url
	 * 
	 * @return the cookie
	 */
	public String getCookie(java.net.URL url) {
		return "";
	}

	/**
	 * Returns <code>true</code>. Implementations wishing to
	 * disable JavaScript may override this method.
	 * 
	 * @return true, if checks if is scripting enabled
	 */
	public boolean isScriptingEnabled() {
		return true;
	}

	/**
	 * This implementation does nothing. Override if a
	 * cookie store is implemented.
	 * 
	 * @param url the url
	 * @param cookieSpec the cookie spec
	 */
	public void setCookie(java.net.URL url, String cookieSpec) {
		this.warn("setCookie(): Method not overridden.");
	}

	/**
	 * Returns <code>null</code>. This method must be overridden
	 * if JavaScript code is untrusted.
	 * 
	 * @return the security policy
	 */
	public Policy getSecurityPolicy() {
		return null;
	}

	/**
	 * Returns -1. Override to provide a different Rhino optimization level.
	 * 
	 * @return the scripting optimization level
	 */
	public int getScriptingOptimizationLevel() {
		return -1;
	}
}
