package org.jicker.util.prefs;

import java.io.*;
import java.util.*;

import org.jicker.JickerGlobals;

public class SaveProperties {
	FileOutputStream propertyFile;

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