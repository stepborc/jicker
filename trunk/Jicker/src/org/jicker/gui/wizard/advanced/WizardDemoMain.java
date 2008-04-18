/*
 * 
 */

package org.jicker.gui.wizard.advanced;

import java.awt.Rectangle;

import javax.swing.UIManager;

import org.netbeans.api.wizard.WizardDisplayer;

// TODO: Auto-generated Javadoc
/**
 * Demo of a wizard that uses arbitrary panels rather than WizardPanel objects.
 */
public class WizardDemoMain
{
    
    /**
     * The main method.
     * 
     * @param ignored the arguments
     * 
     * @throws Exception the exception
     */
    public static void main (String[] ignored) throws Exception {
        //Use native L&F
        UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
        
        WizardDisplayer.showWizard (new NewPetWizard().createWizard(), 
                new Rectangle (20, 20, 500, 400));
        System.exit(0);
    }

}

