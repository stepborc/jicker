package org.jicker;

import org.jicker.gui.JickerApp;

public class Jicker extends JickerApp {
	//Konstruktor
	public Jicker()  {
		super("Jicker", "Jicker");
	}	

	/**
	 * Startup no paramet accept
	 * @param args
	 */
	public static void main(String[] args) {
		//Application jicker = new Application();
		//jicker.setVisible(true);
	     try {
	            final Jicker jicker = new Jicker();

	            javax.swing.SwingUtilities.invokeLater(new Runnable() {
	                public void run() {
	                    jicker.setVisible(true);
	                }
	            });
	        }
	        catch (Exception e) {
	            System.out.println("Exception caught in main");
	            System.out.println(e.getMessage());
	            System.exit(1);
	        }

	}

}
