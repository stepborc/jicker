/*
 * 
 */
package org.jicker.util.systemproperties;

import java.util.Enumeration;
import java.util.Properties;


// TODO: Auto-generated Javadoc
/**
 * The Class sysprop.
 */
public class sysprop {

	/**
	 * The main method.
	 * 
	 * @param args the args
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
