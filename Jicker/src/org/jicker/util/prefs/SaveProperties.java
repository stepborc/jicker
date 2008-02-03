package org.jicker.util.prefs;

import java.io.*;
import java.util.*;

import org.jicker.JickerGlobals;

class SaveProperties {
	public static void main(String args[]) {
		FileOutputStream writer = null;

		try {
			writer = new FileOutputStream("/jickerprop.xml");

			Properties p1 = new Properties(System.getProperties());
			p1.setProperty("JickerHome", JickerGlobals.DATA_BASE
					+ JickerGlobals.SEP + ".jicker");
			p1.storeToXML(writer, "Jicker Properties");

			FileInputStream reader = new FileInputStream("/jickerprop.xml");

			Properties p2 = new Properties();
			p2.loadFromXML(reader);
			p2.list(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
			}
		}
	}
}