package org.jicker.mp3;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.jicker.mp3.data.GetData;
import org.jicker.mp3.ui.JickerMp3UI;



public class JickerMp3 {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JickerMp3UI();
			}
		});*/
		// Datenabfrage starten
		GetData gd = new GetData();
		// GUI aufbauen

	}

}
