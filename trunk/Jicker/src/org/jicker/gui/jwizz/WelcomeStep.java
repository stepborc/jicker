/*
 * 
 */
package org.jicker.gui.jwizz;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.javaprog.ui.wizard.AbstractStep;

// TODO: Auto-generated Javadoc
/**
 * The Class WelcomeStep.
 */
class WelcomeStep extends AbstractStep {
    
    /**
     * Instantiates a new welcome step.
     */
    public WelcomeStep() {
        super("Willkommen", "Der Firststep Wizard");
    }
    
    /* (non-Javadoc)
     * @see net.javaprog.ui.wizard.AbstractStep#createComponent()
     */
    protected JComponent createComponent() {
        JPanel stepComponent = new JPanel();
        stepComponent.add(
            new JLabel("<html>Dieser Wizard richtet einige grundlegende <p>"+
            		        "Einstellungen zum Start von Jicker ein.<p>"
            			  + "Mit den unten stehenden Buttons kann man navigieren.</html>"));
        return stepComponent;
    }

    /* (non-Javadoc)
     * @see net.javaprog.ui.wizard.Step#prepareRendering()
     */
    public void prepareRendering() {}
}