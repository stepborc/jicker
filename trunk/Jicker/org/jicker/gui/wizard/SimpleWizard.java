package org.jicker.gui.wizard;

import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.netbeans.spi.wizard.WizardPage;

public class SimpleWizard extends WizardPage {
	private static final long serialVersionUID = 1L;
	private ButtonGroup buttonGroup1;
	private JRadioButton jRadioButton1;
	private JRadioButton jRadioButton2;
	
	private JTextField jTextField;

	public SimpleWizard() {
		initComponents();
	}

	public static final String getDescription() {
		return "Wähle Datenverzeichnis aus ";
	}

	private void initComponents() {
		buttonGroup1 = new ButtonGroup();
		jRadioButton1 = new JRadioButton();
		jRadioButton2 = new JRadioButton();
		jTextField = new JTextField();
		//jRadioButton3 = new javax.swing.JRadioButton();

		//setLayout(new java.awt.GridLayout(2,2));
		GridLayout directoryChoose = new GridLayout(2,2);

		buttonGroup1.add(jRadioButton1);
		jRadioButton1.setText("Standard Userhome:\t" + System.getProperty("user.home"));
		jRadioButton1.setBorder(new javax.swing.border.EmptyBorder(
				new java.awt.Insets(0, 0, 0, 0)));
		jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
		jRadioButton1.setName(System.getProperty("user.home"));
		add(jRadioButton1);

		buttonGroup1.add(jRadioButton2);
		jRadioButton2.setText("Anderes ...");
		jRadioButton2.setBorder(new javax.swing.border.EmptyBorder(
				new java.awt.Insets(0, 0, 0, 0)));
		jRadioButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
		jRadioButton2.setName("Anderes ...");
		add(jRadioButton2);
		
		jTextField.setText("Test");

/**/

	}

}
