package org.jicker.mp3;

import org.jicker.mp3.data.GetData;

public class JickerMp3 {

	/**
	 * @param args
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static void main(String[] args) throws SecurityException,
			NoSuchFieldException {
		/*
		 * SwingUtilities.invokeLater(new Runnable() { public void run() { new
		 * JickerMp3UI(); } });
		 */
		// Datenabfrage starten
		new GetData();
		// GUI aufbauen

	}

}
