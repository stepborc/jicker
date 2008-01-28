package org.jicker.gui.wizard;

import java.awt.GridLayout;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.jicker.JickerGlobals;
import org.netbeans.spi.wizard.WizardPage;

public class SimpleWizard extends WizardPage {
	static File dirName = new File(JickerGlobals.USER_HOME);
	public SimpleWizard() {
		initComponents();
	}

	public static final String getDescription() {
		return "Stammverzeichnis festlegen";
	}

	private void initComponents() {
		//GridLayout gl = new GridLayout(1,1);
		//JPanel panel = new JPanel();
		setLayout(new GridLayout(3,1));
		ButtonGroup bg = new ButtonGroup();		
		JRadioButton b1 = new JRadioButton("Standard");
		bg.add(b1);
		b1.setText("Standard");
		b1.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 0, 0)));
		b1.setMargin(new java.awt.Insets(0, 0, 0, 0));
		b1.setName("Standard");
		add(b1);
		
		JRadioButton b2 = new JRadioButton("Spezial");		
		bg.add(b2);
		b2.setText("Spezial");
		b2.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 0, 0)));
		b2.setMargin(new java.awt.Insets(0, 0, 0, 0));
		b2.setName("Spezial");
		add(b2);
		
		JPanel fcc = new JPanel();
		JTextField dir = new JTextField(dirName.getAbsolutePath().toString());
		dir.setColumns(10);
	    JButton db   = new JButton("Verzeichnis auswählen");
	    db.addActionListener(new GetDirectory());
	    fcc.add(dir);
	    fcc.add(db);
		add(fcc);
		
		
	}

}
