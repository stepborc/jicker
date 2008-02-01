package org.jicker.gui.jwizz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.javaprog.ui.wizard.AbstractStep;
import net.javaprog.ui.wizard.DataModel;

import org.jicker.JickerGlobals;

public class PropertyLocationStep extends AbstractStep {
	protected DataModel data;
	protected JPanel chooseFile = new JPanel();
	private JPanel chooseDirectory;
	private JTextField dirName;
	protected JFileChooser fc = new JFileChooser();

	public PropertyLocationStep(DataModel data) {
		super("Arbeitsverzeichnis", "Einstellen des Arbeitsverzeichnis");
		this.data = data;
	}

	@Override
	protected JComponent createComponent() {
		// Top JPanel
		chooseDirectory = new JPanel();
		// BoxLayout chooseDirectoryLayout = new BoxLayout(chooseDirectory, 0);
		chooseDirectory.setLayout(new BoxLayout(chooseDirectory, BoxLayout.Y_AXIS));
		chooseDirectory.add(Box.createVerticalGlue());		
		
		// Panel mit GridLayout fuer Radiobutton
		GridLayout buttonLayout = new GridLayout(2,0);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonPanel.getPreferredSize().height));
		
		JRadioButton defaultDirectory = new JRadioButton("Standardverzeichnis " + JickerGlobals.PROP_DIR, true);
		defaultDirectory.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				chooseFile.setVisible(false);
				
			}
			
		});
		
		JRadioButton noDefaultDirectory = new JRadioButton("Freie Wahl");
		noDefaultDirectory.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				chooseFile.setVisible(true);		
			}
		});
		
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(defaultDirectory);
		buttonGroup.add(noDefaultDirectory);
		
		buttonPanel.add(defaultDirectory);
		buttonPanel.add(noDefaultDirectory);
		
		// JPanel chooseFile = new JPanel();
		chooseFile.setLayout(new BoxLayout(chooseFile, BoxLayout.X_AXIS));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		JLabel label = new JLabel("Verzeichnis");
		dirName = new JTextField();
		JButton dirButton = new JButton("Öffnen");
		
		dirButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (fc.showOpenDialog(chooseDirectory) == JFileChooser.APPROVE_OPTION) {
                    dirName.setText(fc.getSelectedFile().getPath());
                }
            }
        });
		chooseFile.add(label);
		chooseFile.add(dirName);
		chooseFile.add(dirButton);
		chooseFile.setMaximumSize(new Dimension(Integer.MAX_VALUE, chooseFile.getPreferredSize().height));
		chooseFile.setVisible(false);
		// hinzufuegen der Panel zum Top Panel
		chooseDirectory.add(buttonPanel);
		chooseDirectory.add(chooseFile);
		chooseDirectory.add(Box.createVerticalGlue());
		return chooseDirectory;
	}

	public void prepareRendering() {
		// Nothing to do

	}

}
