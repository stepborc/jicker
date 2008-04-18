/*
 * 
 */
package org.jicker.gui.jwizz;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import net.javaprog.ui.wizard.DataModel;
import net.javaprog.ui.wizard.DefaultWizardModel;
import net.javaprog.ui.wizard.Step;
import net.javaprog.ui.wizard.Wizard;
import net.javaprog.ui.wizard.WizardModel;
import net.javaprog.ui.wizard.WizardModelEvent;
import net.javaprog.ui.wizard.WizardModelListener;

// TODO: Auto-generated Javadoc
/**
 * The Class InstallWizard.
 */
public class InstallWizard {
    
    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(String[] args) {
        final DataModel data = new DataModel();
        
        WizardModel model = new DefaultWizardModel(new Step[]{
            new WelcomeStep(),
            new LicenseStep(),
            new PropertyLocationStep(data),
            new FinishStep()
        });
        model.addWizardModelListener(new WizardModelListener() {
            public void wizardFinished(WizardModelEvent e) {
            	//Irgendetwas mit den Daten aus den Klassen tun
            	System.out.println(data.getData("location"));
            	try {
					new File("jicker.properties").createNewFile();
				} catch (IOException e1) {
					System.out.println("jicker.properties konnte nicht angelegt werden.");
				}
            }

            public void wizardCanceled(WizardModelEvent e) {}
            public void stepShown(WizardModelEvent e) {}
            public void wizardModelChanged(WizardModelEvent e) {}
        });
        
        Wizard wizard = new Wizard(model, "Jicker Firststeps", new ImageIcon("jicker.gif"));

        wizard.pack();
        wizard.setLocationRelativeTo(null);
        wizard.setVisible(true);
        wizard.dispose();
        //System.exit(0);
    }
}