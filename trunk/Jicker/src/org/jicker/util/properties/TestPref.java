/*
 * 
 */
package org.jicker.util.properties;

import java.util.prefs.Preferences;

import org.jicker.JickerGlobals;

// TODO: Auto-generated Javadoc
/**
 * The Class TestPref.
 */
public class TestPref {

	/**
	 * The main method.
	 * 
	 * @param args the args
	 */
	public static void main(String[] args) {
		Preferences root = Preferences.userRoot();
		
		System.out.println(root.absolutePath());
		root.userRoot().node(JickerGlobals.PROP_DIR);
		System.out.println(root.absolutePath());

	}

}
