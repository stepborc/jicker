package org.jicker.util.prefs;

import java.util.prefs.Preferences;

import org.jicker.JickerGlobals;

public class TestPref {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Preferences root = Preferences.userRoot();
		
		System.out.println(root.absolutePath());
		root.userRoot().node(JickerGlobals.PROP_DIR);
		System.out.println(root.absolutePath());

	}

}
