package org.jicker;

public class JickerGlobals {
	
	/* Platform specific path separator */
	public static final String SEP = System.getProperty("file.separator");
	/* User's home directory */
	public static final String USER_HOME = System.getProperty("user.home");
	/* Directory where app properties are stored */
	public static final String PROP_DIR = USER_HOME + SEP + ".jicker";
	/* The app's property file */
	public static final String PROP_FILE = PROP_DIR + SEP + "jicker.properties";
	/* The application's install dir */
	public static final String USER_DIR = System.getProperty("user.dir");
	/* location of default database */
	public static final String DATA_BASE = PROP_DIR + SEP + "jicker.db4o";
	/* location of flickr authentification store */
	//public static final String FLICKR_AUTH = PROP_FILE;
	public static final String FLICKR_AUTH_DIR = PROP_DIR;
	
}
