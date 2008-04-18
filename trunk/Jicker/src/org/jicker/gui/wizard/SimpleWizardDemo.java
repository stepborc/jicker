/*
 * 
 */
package org.jicker.gui.wizard;

import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardPage;
import org.netbeans.spi.wizard.WizardPage.WizardResultProducer;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleWizardDemo.
 */
public class SimpleWizardDemo {

	/**
	 * The main method.
	 * 
	 * @param args the args
	 */
	public static void main(String[] args) {
		Class[] page = new Class[]{SimpleWizard.class, FinalPage.class}; 
		Wizard wizard = WizardPage.createWizard(page, WizardResultProducer.NO_OP);
		WizardDisplayer.showWizard (wizard);

	}

}
