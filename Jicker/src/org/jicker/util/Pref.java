package org.jicker.util;

import java.util.prefs.Preferences;

public class Pref {

	private static Pref aaPref = null;

	private Preferences aPref = null;

	/**
	 * Create preference object.
	 * 
	 * @param prefName
	 */
	public Pref(String prefName) {
		aaPref = this;
		aPref = Preferences.userRoot().node(prefName);
	}

	/**
	 * Get preference object.
	 * 
	 * @return
	 */
	public static Preferences get() {
		assert aaPref != null;
		return aaPref.aPref;
	}

	/**
	 * Get preference value.
	 * 
	 * @return
	 */
	public static String getPref(String key, String def) {
		return aaPref.aPref.get(key, def);
	}

	/**
	 * Get preference value.
	 * 
	 * @return
	 */
	public static int getPref(String key, int def) {
		return aaPref.aPref.getInt(key, def);
	}

	/**
	 * Get preference value.
	 * 
	 * @return
	 */
	public static double getPref(String key, double def) {
		return aaPref.aPref.getDouble(key, def);
	}

	/**
	 * Get preference value.
	 * 
	 * @return
	 */
	public static boolean getPref(String key, boolean def) {
		return aaPref.aPref.getBoolean(key, def);
	}

	/**
	 * Get preference value.
	 * 
	 * @return
	 */
	public static int getPref(String key, int option, int def) {
		String key2 = String.format("%s%03d", key, option);
		return aaPref.aPref.getInt(key2, def);
	}

	/**
	 * Get preference value.
	 * 
	 * @return
	 */
	public static String getPref(String key, int option, String def) {
		String key2 = String.format("%s%03d", key, option);
		return aaPref.aPref.get(key2, def);
	}

	/**
	 * Set preference.
	 * 
	 * @param val
	 */
	public static void setPref(String key, String val) {
		aaPref.aPref.put(key, val);
	}

	/**
	 * Set preference.
	 * 
	 * @param val
	 */
	public static void setPref(String key, int val) {
		aaPref.aPref.putInt(key, val);
	}

	/**
	 * Set preference.
	 * 
	 * @param val
	 */
	public static void setPref(String key, double val) {
		aaPref.aPref.putDouble(key, val);
	}

	/**
	 * Set preference.
	 * 
	 * @param val
	 */
	public static void setPref(String key, boolean val) {
		aaPref.aPref.putBoolean(key, val);
	}

}
