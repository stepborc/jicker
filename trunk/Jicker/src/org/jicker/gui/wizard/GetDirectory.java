/*
 *
 */
package org.jicker.gui.wizard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

// TODO: Auto-generated Javadoc
/**
 * The Class GetDirectory.
 */
public class GetDirectory implements ActionListener {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ae) {
		JFileChooser chooser = new JFileChooser(SimpleWizard.dirName);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int option = chooser.showOpenDialog(chooser);

		if (option == JFileChooser.APPROVE_OPTION) {
			SimpleWizard.dirName = chooser.getSelectedFile();
			SimpleWizard.dir.setText(chooser.getSelectedFile().toString());
			// statusbar.setText("Sie haben folgendes Verzeichnis geöffnet: " +
			// ((chooser.getSelectedFile()!=null)?chooser.getSelectedFile().
			// getName():"nothing"));
		} else {
			// statusbar.setText("Sie haben abgebrochen.");
		}
	}
}
