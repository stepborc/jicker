package org.jicker;

import org.jicker.gui.JickerApp;
import org.jicker.gui.MainWindow;

@SuppressWarnings("serial")
public class Jicker extends JickerApp {
	// Konstruktor
	public Jicker() {
		super("Jicker", "Jicker");
		MainWindow aMainWindow = new MainWindow();
		restore(aMainWindow);
	}

	/**
	 * Startup no paramet accept
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			final Jicker jicker = new Jicker();

			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					jicker.setVisible(true);
				}
			});
		} catch (Exception e) {
			System.out.println("Exception caught in main");
			System.out.println(e.getMessage());
			System.exit(1);
		}

	}

}
