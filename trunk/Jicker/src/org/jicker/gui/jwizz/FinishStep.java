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
 * The Class FinishStep.
 */
class FinishStep extends AbstractStep {
    
    /**
     * Instantiates a new finish step.
     */
    public FinishStep() {
        super("Finish", "The installation will now be started");
    }
    
    /* (non-Javadoc)
     * @see net.javaprog.ui.wizard.AbstractStep#createComponent()
     */
    protected JComponent createComponent() {
        JPanel stepComponent = new JPanel();
        stepComponent.add(
            new JLabel("<html>The installation wizard will now copy the necessary files<p>"
                + "to your hard drive. Please click \"Finish\".</html>"));
        return stepComponent;
    }
    
    /* (non-Javadoc)
     * @see net.javaprog.ui.wizard.Step#prepareRendering()
     */
    public void prepareRendering() {
        setCanFinish(true);
    }
}