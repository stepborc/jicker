package org.jicker.mp3;

import org.jicker.mp3.data.GetData;

public class JickerMp3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * SwingUtilities.invokeLater(new Runnable() { public void run() { new
		 * JickerMp3UI(); } });
		 */
		// Datenabfrage starten
		new GetData();

		// GUI aufbauen

	}

}
