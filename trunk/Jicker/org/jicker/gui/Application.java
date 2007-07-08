package org.jicker.gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Application extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Container c;
	JLabel beschriftung;
	public Application() {
		c = getContentPane();
		c.setLayout(new FlowLayout());
		beschriftung = new JLabel("Test Text");
		c.add(beschriftung);
	}

}
