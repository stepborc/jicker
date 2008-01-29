package org.jicker.gui.jwizz;

import javax.swing.ImageIcon;

import net.javaprog.ui.wizard.DataModel;
import net.javaprog.ui.wizard.DefaultWizardModel;
import net.javaprog.ui.wizard.Step;
import net.javaprog.ui.wizard.Wizard;
import net.javaprog.ui.wizard.WizardModel;
import net.javaprog.ui.wizard.WizardModelEvent;
import net.javaprog.ui.wizard.WizardModelListener;

public class InstallWizard {
    public static void main(String[] args) {
        DataModel data = new DataModel();
        
        WizardModel model = new DefaultWizardModel(new Step[]{
            new WelcomeStep(),
            new LicenseStep(),
            new LocationStep(data),
            new FinishStep()
        });
        model.addWizardModelListener(new WizardModelListener() {
            public void wizardFinished(WizardModelEvent e) {
                //do something with the collected data.
            }

            public void wizardCanceled(WizardModelEvent e) {}
            public void stepShown(WizardModelEvent e) {}
            public void wizardModelChanged(WizardModelEvent e) {}
        });
        
        Wizard wizard = new Wizard(model, "Installation Wizard", 
                new ImageIcon("Host24.gif"));
        
        wizard.pack();
        wizard.setLocationRelativeTo(null);
        wizard.setVisible(true);
        System.exit(0);
    }
}