/*
 * 
 */
package org.jicker.util.systemproperties;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class SysInfo.
 */
@SuppressWarnings("serial")
public class SysInfo extends JFrame {

	/**
	 * Instantiates a new sys info.
	 */
	public SysInfo(){
		super("Ein einfacher Frame");
		addWindowListener(new WindowClosingAdapter(true));
		Properties sysprops = System.getProperties();
		Enumeration propnames = sysprops.propertyNames();
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(sysprops.size(),1));

		String iProper;
		while(propnames.hasMoreElements()){
			String propname = (String)propnames.nextElement();
			iProper = propname + " = " + System.getProperty(propname);
			JLabel label = new JLabel(iProper);
			label.setHorizontalAlignment(JLabel.LEFT);
			cp.add(label);
		}

	}
	
	/**
	 * The main method.
	 * 
	 * @param args the args
	 */
	public static void main(String[] args) {
		SysInfo sysinfo = new SysInfo();
		sysinfo.setLocation(100, 100);
		sysinfo.setSize(1024,768);
		sysinfo.setVisible(true);

	}

}