/*
 *
 */
package org.jicker.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jicker.util.Pref;

// TODO: Auto-generated Javadoc
/**
 * The Class JickerApp.
 */
public class JickerApp extends JFrame {

	/** The Constant LOOKFEEL. */
	public static final String LOOKFEEL = "lookfeel";

	/** The Constant LOOKFEEL_CLASSNAME. */
	public static final String LOOKFEEL_CLASSNAME = "com.jgoodies.looks.plastic.PlasticLookAndFeel";
	// public static final String LOOKFEEL_CLASSNAME =
	// "com.jgoodies.looks.plastic.PlasticXPLookAndFeel";
	// public static final String LOOKFEEL_CLASSNAME =
	// "com.jgoodies.looks.windows.WindowsLookAndFeel";

	/** The Constant CODEPAGE. */
	public static final String CODEPAGE = "codepage";

	/** The a main panel. */
	private JPanel aMainPanel = null;

	/** The aa application. */
	private JickerApp aaApplication;

	/** The a default cursor. */
	private Cursor aDefaultCursor;

	/** The a wait cursor. */
	private Cursor aWaitCursor;

	/** The a operating system. */
	private String aOperatingSystem;

	/** The a app name. */
	private String aAppName;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new jicker app.
	 *
	 * @param appName
	 *            the app name
	 * @param prefName
	 *            the pref name
	 */
	public JickerApp(String appName, String prefName) {
		super(appName);
		// Task: Logging einbauen
		aaApplication = this;
		aDefaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		aWaitCursor = new Cursor(Cursor.WAIT_CURSOR);
		aOperatingSystem = System.getProperty("os.name");
		aAppName = appName;

		new Pref(prefName);
		try {
			// UIManager.setLookAndFeel(
			// "com.jgoodies.looks.windows.WindowsLookAndFeel");
			UIManager
					.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
		} catch (Exception e) {
			setLookAndFeel(false);
		}

	}

	/**
	 * Sets the look and feel.
	 *
	 * @param appIsRunning
	 *            the new look and feel
	 */
	public void setLookAndFeel(boolean appIsRunning) {
		boolean lf = false;
		try {
			String lookAndFeelName;
			if (appIsRunning) {
				lookAndFeelName = Pref.getPref(LOOKFEEL, LOOKFEEL_CLASSNAME);
			} else {
				lookAndFeelName = System.getProperty("swing.defaultlaf");

				if (lookAndFeelName == null || lookAndFeelName.length() < 10) {
					lookAndFeelName = Pref
							.getPref(LOOKFEEL, LOOKFEEL_CLASSNAME);
				}
			}
			// Log.get().addTime(1, "Java Look & Feel is set to: " +
			// lookAndFeelName);

			if (lookAndFeelName != null) {
				UIManager.setLookAndFeel(lookAndFeelName);
			}
		} catch (ClassNotFoundException e) {
			// showHtmlError("Look and feel library can't be found.",
			// e.getMessage());
			lf = true;
		} catch (InstantiationException e) {
			lf = true;
		} catch (IllegalAccessException e) {
			lf = true;
		} catch (UnsupportedLookAndFeelException e) {
			// showHtmlError(
			// "Selected \"look and feel\" is unsupported on this platform.",
			// e.getMessage());
			lf = true;
		}

		if (appIsRunning) {
			SwingUtilities.updateComponentTreeUI(this);

			for (Frame f : getFrames()) {
				SwingUtilities.updateComponentTreeUI(f);
				f.validate();

				for (Window w : f.getOwnedWindows()) {
					SwingUtilities.updateComponentTreeUI(w);
					w.validate();
				}
			}
		} else {
			try {
				if (lf) {
					UIManager.setLookAndFeel(UIManager
							.getCrossPlatformLookAndFeelClassName());
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Restore.
	 *
	 * @param panel
	 *            the panel
	 */
	public void restore(JPanel panel) {
		aMainPanel = panel;

		double w = Pref.getPref("w", 800.0);
		double h = Pref.getPref("h", 600.0);
		double x = Pref.getPref("x", 50.0);
		double y = Pref.getPref("y", 50.0);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (w < 100) {
			w = 100;
		}
		if (w > 3000) {
			w = 800;
		}
		if (h < 100) {
			h = 100;
		}
		if (h > 3000) {
			h = 600;
		}
		if (x < 0) {
			x = 0;
		}
		if (x > screenSize.getWidth()) {
			x = 50;
		}
		if (y < 0) {
			y = 0;
		}
		if (y > screenSize.getHeight()) {
			y = 50;
		}

		setContentPane(aMainPanel);
		pack();
		setSize((int) w, (int) h);
		setLocation((int) x, (int) y);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				quit();
			}

			private void quit() {
				try {
					Dimension size = getSize();
					Point pos = getLocation();

					Pref.setPref("w", Double.toString(size.getWidth()));
					Pref.setPref("h", Double.toString(size.getHeight()));
					Pref.setPref("x", Double.toString(pos.getX()));
					Pref.setPref("y", Double.toString(pos.getY()));
				} catch (Exception e) {
				}
				setVisible(false);
				dispose();
				System.exit(0);
			}

		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
