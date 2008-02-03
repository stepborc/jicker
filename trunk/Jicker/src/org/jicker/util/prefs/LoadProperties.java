package org.jicker.util.prefs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class LoadProperties {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filename = "/jickerprop.xml";
		try {
			FileInputStream propFile = new FileInputStream(filename);
			Properties p = new Properties();
			p.loadFromXML(propFile);
			System.out.println(p.size());

			for (Enumeration e = p.propertyNames(); e.hasMoreElements();) {
				String key = (String) e.nextElement();
				String value = p.getProperty(key);
				System.out.println(key + "=" + value);
			}
			p.list(System.out);

		} catch (FileNotFoundException e) {
			System.out.println("Fehler beim lesen der " + filename + " Datei.");
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
