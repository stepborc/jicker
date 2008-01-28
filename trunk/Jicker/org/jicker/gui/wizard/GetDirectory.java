package org.jicker.gui.wizard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

public class GetDirectory implements ActionListener {

	      public void actionPerformed(ActionEvent ae)
          {
            JFileChooser chooser = new JFileChooser(SimpleWizard.dirName);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = chooser.showOpenDialog(chooser);

      
            if(option == JFileChooser.APPROVE_OPTION)
            {
            	SimpleWizard.dirName = chooser.getSelectedFile();
              //statusbar.setText("Sie haben folgendes Verzeichnis geöffnet: " + ((chooser.getSelectedFile()!=null)?chooser.getSelectedFile().getName():"nothing"));
            }
            else
            {
              //statusbar.setText("Sie haben abgebrochen.");
            }
          }
        }
	

