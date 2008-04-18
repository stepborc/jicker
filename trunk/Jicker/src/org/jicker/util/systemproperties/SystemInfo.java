/*
 * 
 */
package org.jicker.util.systemproperties;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemInfo.
 */
@SuppressWarnings("serial")
public class SystemInfo extends Applet {

/* (non-Javadoc)
 * @see java.awt.Container#paint(java.awt.Graphics)
 */
public void paint(Graphics g){
	showStatus("Hello Vera");
	Properties sysprops = System.getProperties();
	Enumeration propnames = sysprops.propertyNames();
	int n = 10;
	String iProper;
	while(propnames.hasMoreElements()){
		String propname = (String)propnames.nextElement();
	//	System.out.println(propname + " = " + System.getProperty(propname));
		iProper = propname + " = " + System.getProperty(propname);
		g.drawString(iProper, 10, n);
		n = n + 10;
	}

}
}
