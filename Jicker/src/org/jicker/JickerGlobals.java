/*
 * 
 */
package org.jicker;


// TODO: Auto-generated Javadoc
/**
 * The Class JickerGlobals.
 */
public class JickerGlobals {
	
	// Platform specific path separator
	/** The Constant SEP. */
	public static final String SEP = System.getProperty("file.separator");
	// User's home directory
	/** The Constant USER_HOME. */
	public static final String USER_HOME = System.getProperty("user.home");
	//* Directory where app properties are stored
	/** The Constant PROP_DIR. */
	public static final String PROP_DIR = USER_HOME + SEP + ".jicker";
	// The app's property file
	/** The Constant PROP_FILE. */
	public static final String PROP_FILE = PROP_DIR + SEP + "jicker.properties";
	// The application's install dir
	/** The Constant USER_DIR. */
	public static final String USER_DIR = System.getProperty("user.dir");
	// location of default database
	/** The Constant DATA_BASE. */
	public static final String DATA_BASE = PROP_DIR + SEP + "jicker.yap";
	// location of flickr authentification store 
	//public static final String FLICKR_AUTH = PROP_FILE;
	/** The Constant FLICKR_AUTH_DIR. */
	public static final String FLICKR_AUTH_DIR = PROP_DIR;
	// Property file
	/** The Constant PROPERTY_FILE. */
	public static final String PROPERTY_FILE = "jickerprop.xml";
	
	

	/*public JickerGlobals() {
		Properties p = new Properties();
		p.setProperty("dataHome", DATA_BASE);
		p.setProperty("FlickrAuth", FLICKR_AUTH_DIR);
		final SaveProperties sp = new SaveProperties(p);
		
	}*/
	
	//p.setProperties("dataHome",DATA_BASE);
}
