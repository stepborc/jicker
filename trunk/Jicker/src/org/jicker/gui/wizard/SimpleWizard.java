/*
 * 
 */
package org.jicker.gui.wizard;

import java.awt.GridLayout;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jicker.JickerGlobals;
import org.netbeans.spi.wizard.WizardPage;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleWizard.
 */
public class SimpleWizard extends WizardPage {
	
	/** The dir name. */
	static File dirName = new File(JickerGlobals.PROP_DIR);
	
	/** The dir. */
	static JTextField dir;
	
	/**
	 * Instantiates a new simple wizard.
	 */
	public SimpleWizard() {
		initComponents();
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public static final String getDescription() {
		return "Stammverzeichnis festlegen";
	}

	/**
	 * Inits the components.
	 */
	private void initComponents() {
		//GridLayout gl = new GridLayout(1,1);
		//JPanel panel = new JPanel();
		setLayout(new GridLayout(3,2));

		ButtonGroup bg = new ButtonGroup();		

		JRadioButton b1 = new JRadioButton();
		bg.add(b1);
		b1.setText("Standard: " + JickerGlobals.USER_HOME + JickerGlobals.SEP + ".jicker");
		b1.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 0, 0)));
		b1.setMargin(new java.awt.Insets(0, 0, 0, 0));
		b1.setName("Standard");
		b1.setSelected(true);
		
		JRadioButton b2 = new JRadioButton();		
		bg.add(b2);
		b2.setText("Spezial");
		b2.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 0, 0)));
		b2.setMargin(new java.awt.Insets(0, 0, 0, 0));
		b2.setName("Standard");

		JLabel t1 = new JLabel("");
		JLabel t2 = new JLabel("");
 
		add(b1); add(t1);
		add(b2);add(t2);
		
		dir = new JTextField(dirName.getAbsolutePath().toString());
		dir.setName("Pfad");
		dir.setColumns(10);
	    JButton dirButton   = new JButton("Verzeichnis auswählen");
	    
	    dirButton.addActionListener(new GetDirectory());
	    add(dir);
	    add(dirButton);
		
		
	}

}
