/*
 * 
 */
package org.jicker.util;

import java.util.prefs.Preferences;

// TODO: Auto-generated Javadoc
/**
 * The Class Pref.
 */
public class Pref {

	/** The aa pref. */
	private static Pref aaPref = null;

	/** The a pref. */
	private Preferences aPref = null;

	/**
	 * Create preference object.
	 * 
	 * @param prefName the pref name
	 */
	public Pref(String prefName) {
		aaPref = this;
		aPref = Preferences.userRoot().node(prefName);
	}

	/**
	 * Get preference object.
	 * 
	 * @return aaPref.aPref
	 */
	public static Preferences get() {
		assert aaPref != null;
		return aaPref.aPref;
	}

	/**
	 * Get preference value.
	 * 
	 * @param key the key
	 * @param def the def
	 * 
	 * @return aaPref.aPref.get(key, def)
	 */
	public static String getPref(String key, String def) {
		return aaPref.aPref.get(key, def);
	}

	/**
	 * Get preference value.
	 * 
	 * @param key the key
	 * @param def the def
	 * 
	 * @return aaPref.aPref.getInt(key, def)
	 */
	public static int getPref(String key, int def) {
		return aaPref.aPref.getInt(key, def);
	}

	/**
	 * Get preference value.
	 * 
	 * @param key the key
	 * @param def the def
	 * 
	 * @return aaPref.aPref.getDouble(key, def)
	 */
	public static double getPref(String key, double def) {
		return aaPref.aPref.getDouble(key, def);
	}

	/**
	 * Get preference value.
	 * 
	 * @param key the key
	 * @param def the def
	 * 
	 * @return aPref.aPref.getBoolean(key, def)
	 */
	public static boolean getPref(String key, boolean def) {
		return aaPref.aPref.getBoolean(key, def);
	}

	/**
	 * Get preference value.
	 * 
	 * @param key the key
	 * @param option the option
	 * @param def the def
	 * 
	 * @return aaPref.aPref.getInt(key2, def)
	 */
	public static int getPref(String key, int option, int def) {
		String key2 = String.format("%s%03d", key, option);
		return aaPref.aPref.getInt(key2, def);
	}

	/**
	 * Get preference value.
	 * 
	 * @param key the key
	 * @param option the option
	 * @param def the def
	 * 
	 * @return aaPref.aPref.get(key2, def)
	 */
	public static String getPref(String key, int option, String def) {
		String key2 = String.format("%s%03d", key, option);
		return aaPref.aPref.get(key2, def);
	}

	/**
	 * Set preference.
	 * 
	 * @param val the val
	 * @param key the key
	 */
	public static void setPref(String key, String val) {
		aaPref.aPref.put(key, val);
	}

	/**
	 * Set preference.
	 * 
	 * @param val the val
	 * @param key the key
	 */
	public static void setPref(String key, int val) {
		aaPref.aPref.putInt(key, val);
	}

	/**
	 * Set preference.
	 * 
	 * @param val the val
	 * @param key the key
	 */
	public static void setPref(String key, double val) {
		aaPref.aPref.putDouble(key, val);
	}

	/**
	 * Set preference.
	 * 
	 * @param val the val
	 * @param key the key
	 */
	public static void setPref(String key, boolean val) {
		aaPref.aPref.putBoolean(key, val);
	}

}
