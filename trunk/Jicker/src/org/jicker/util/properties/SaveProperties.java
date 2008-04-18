/*
 * 
 */
package org.jicker.util.properties;

import java.io.*;
import java.util.*;

import org.jicker.JickerGlobals;

// TODO: Auto-generated Javadoc
/**
 * The Class SaveProperties.
 */
public class SaveProperties {
	
	/** The property file. */
	FileOutputStream propertyFile;

	/**
	 * Instantiates a new save properties.
	 * 
	 * @param property the property
	 */
	public SaveProperties(Properties property) {

		try {
			propertyFile = new FileOutputStream(JickerGlobals.PROP_DIR
					+ JickerGlobals.SEP + JickerGlobals.PROP_FILE);

			//property.setProperty("JickerHome", JickerGlobals.DATA_BASE
			//		+ JickerGlobals.SEP + ".jicker");
			property.storeToXML(propertyFile, "Jicker Properties");

		} catch (FileNotFoundException e1) {
			System.out.println("Property-Datei nicht gefunden.");
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				propertyFile.close();
			} catch (IOException e) {
			}
		}
	}
}