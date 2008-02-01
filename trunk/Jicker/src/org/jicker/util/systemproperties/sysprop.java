package org.jicker.util.systemproperties;

import java.util.Enumeration;
import java.util.Properties;


public class sysprop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties sysprops = System.getProperties();
		Enumeration propnames = sysprops.propertyNames();
		while(propnames.hasMoreElements()){
			String propname = (String)propnames.nextElement();
			System.out.println(propname + " = " + System.getProperty(propname));
		}

	}

}
