package org.jicker.gui.wizard;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import org.netbeans.spi.wizard.WizardPage;

public class SimpleWizard extends WizardPage {
	private static final long serialVersionUID = 1L;
	private ButtonGroup buttonGroup1;
	private JRadioButton jRadioButton1;

	public SimpleWizard() {
		initComponents();
	}

	private void initComponents() {
		buttonGroup1 = new javax.swing.ButtonGroup();
		jRadioButton1 = new javax.swing.JRadioButton();
		setLayout(new java.awt.GridLayout());
		buttonGroup1.add(jRadioButton1);
		jRadioButton1.setText("Mammal");
		jRadioButton1.setBorder(new javax.swing.border.EmptyBorder(
				new java.awt.Insets(0, 0, 0, 0)));
		jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
		jRadioButton1.setName("mammal");
		add(jRadioButton1);

	}

}
