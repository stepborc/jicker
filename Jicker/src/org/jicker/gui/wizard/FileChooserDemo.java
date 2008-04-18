/*
 * 
 */
package org.jicker.gui.wizard;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class FileChooserDemo.
 */
public class FileChooserDemo {
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory()
						|| f.getName().toLowerCase().endsWith(".txt");
			}

			@Override
			public String getDescription() {
				return "Texte";
			}
		});
		int state = fc.showOpenDialog(null);
		if (state == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			System.out.println(file.getName());
		} else
			System.out.println("Auswahl abgebrochen");
		System.exit(0);
	}
}
