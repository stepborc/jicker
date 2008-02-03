package org.jicker;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.jicker.gui.JickerApp;
import org.jicker.gui.MainWindow;
import org.jicker.util.prefs.SaveProperties;

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

		// Logger starten
		try {
			File dir = new File(JickerGlobals.PROP_DIR);
			if (!dir.exists())
				dir.mkdirs();

			Handler fh = new FileHandler(JickerGlobals.PROP_DIR
					+ "/jicker%g.log", 50000, 5, true);
			fh.setFormatter(new SimpleFormatter());
			Logger.getLogger("").addHandler(fh);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Logger logger = Logger.getLogger("org.jicker");
		logger.log(Level.INFO, "Jicker gestartet.");
		logger.info("Jicker gestartet.");
		//Property starten
		JickerGlobals jg = new JickerGlobals(); 
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
