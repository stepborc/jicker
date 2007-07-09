package org.jicker.gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.googlepages.dronten.jtagger.gui.MainWindow;

public class JickerApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainWindow aMainWindow = null;
	Container c;
	JLabel beschriftung;
	public JickerApp(String appName, String prefName) {
		super(appName);
		c = getContentPane();
		c.setLayout(new FlowLayout());
		beschriftung = new JLabel("Test Text");
		c.add(beschriftung);
	}

}
