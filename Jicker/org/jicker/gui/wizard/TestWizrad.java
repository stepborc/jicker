package org.jicker.gui.wizard;

import javax.swing.JFrame;

public class TestWizrad {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sorting");
	    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		AnimalTypePage t = new AnimalTypePage();
		frame.setContentPane(t);
	}

}
