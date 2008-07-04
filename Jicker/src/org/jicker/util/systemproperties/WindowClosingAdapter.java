/*
 *
 */
package org.jicker.util.systemproperties;

/* WindowClosingAdapter.java */

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class WindowClosingAdapter.
 */
public class WindowClosingAdapter extends WindowAdapter {

	/** The exit system. */
	private boolean exitSystem;

	/**
	 * Erzeugt einen WindowClosingAdapter zum Schliessen des Fensters. Ist
	 * exitSystem true, wird das komplette Programm beendet.
	 *
	 * @param exitSystem
	 *            the exit system
	 */
	public WindowClosingAdapter(boolean exitSystem) {
		this.exitSystem = exitSystem;
	}

	/**
	 * Erzeugt einen WindowClosingAdapter zum Schliessen des Fensters. Das
	 * Programm wird nicht beendet.
	 */
	public WindowClosingAdapter() {
		this(true);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	 */
	public void windowClosing(WindowEvent event) {
		event.getWindow().setVisible(false);
		event.getWindow().dispose();
		if (exitSystem) {
			System.exit(0);
		}
	}
}