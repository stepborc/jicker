package org.jicker.util.cobra;

import java.security.Policy;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lobobrowser.html.HttpRequest;
import org.lobobrowser.html.UserAgentContext;

/**
 * Simple implementation of {@link org.lobobrowser.html.UserAgentContext}. 
 * This class is provided for user convenience. 
 * Normally this class should be extended in order to provide appropriate
 * user agent information and more robust content loading routines.
 */
public class SimpleUserAgentContext implements UserAgentContext {
	private static final Logger logger = Logger.getLogger(SimpleUserAgentContext.class.getName());
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
	 */
	public HttpRequest createHttpRequest() {
		return new SimpleHttpRequest(this, this.getProxy());
	}	

	/**
	 * Gets the connection proxy used in requests created
	 * by {@link #createHttpRequest()} by default. This implementation returns
	 * <code>Proxy.NO_PROXY</code>, but it may be overridden.
	 */
	protected java.net.Proxy getProxy() {
		return java.net.Proxy.NO_PROXY;
	}
	
	public void warn(String message, Throwable throwable) {
		if(logger.isLoggable(Level.WARNING)) {
			logger.log(Level.WARNING, message, throwable);
		}
	}
	
	public void error(String message, Throwable throwable) {
		if(logger.isLoggable(Level.SEVERE)) {
			logger.log(Level.SEVERE, message, throwable);
		}
	}
	
	public void warn(String message) {
		if(logger.isLoggable(Level.WARNING)) {
			logger.log(Level.WARNING, message);
		}		
	}
	
	public void error(String message) {
		if(logger.isLoggable(Level.SEVERE)) {
			logger.log(Level.SEVERE, message);
		}
	}

	/**
	 * Override to provide the application "code name."
	 */
	public String getAppCodeName() {
		return "Cobra";
	}

	/**
	 * Override to provide the application's minor version.
	 */
	public String getAppMinorVersion() {
		return "0";
	}

	/**
	 * Override to provide the application name.
	 */
	public String getAppName() {
		return "Browser";
	}

	/**
	 * Override to provide the application version.
	 */
	public String getAppVersion() {
		return "1";
	}

	/**
	 * Override to provide the browser language. This
	 * implementation returns EN.
	 */
	public String getBrowserLanguage() {
		return "EN";
	}

	/**
	 * Returns the value of Java property <code>os.name</code>.
	 */
	public String getPlatform() {
		return System.getProperty("os.name");
	}

	/**
	 * Returns a simple user-agent string.
	 */
	public String getUserAgent() {
		return "Mozilla/4.0 (compatible; MSIE 6.0;) Cobra/Simple";
	}

	/**
	 * This implementation returns false. Override to give a different value.
	 */
	public boolean isCookieEnabled() {
		return false;
	}

	/**
	 * This implementation returns blank. Override if a cookie store is implemented.
	 */
	public String getCookie(java.net.URL url) {
		return "";
	}

	/**
	 * Returns <code>true</code>. Implementations wishing to
	 * disable JavaScript may override this method.
	 */
	public boolean isScriptingEnabled() {
		return true;
	}

	/**
	 * This implementation does nothing. Override if a
	 * cookie store is implemented.
	 */
	public void setCookie(java.net.URL url, String cookieSpec) {
		this.warn("setCookie(): Method not overridden.");
	}

	/**
	 * Returns <code>null</code>. This method must be overridden
	 * if JavaScript code is untrusted.
	 */
	public Policy getSecurityPolicy() {
		return null;
	}

	/**
	 * Returns -1. Override to provide a different Rhino optimization level.
	 */
	public int getScriptingOptimizationLevel() {
		return -1;
	}
}
