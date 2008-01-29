package org.jicker.gui.jwizz;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.javaprog.ui.wizard.AbstractStep;

class FinishStep extends AbstractStep {
    public FinishStep() {
        super("Finish", "The installation will now be started");
    }
    
    protected JComponent createComponent() {
        JPanel stepComponent = new JPanel();
        stepComponent.add(
            new JLabel("<html>The installation wizard will now copy the necessary files<p>"
                + "to your hard drive. Please click \"Finish\".</html>"));
        return stepComponent;
    }
    
    public void prepareRendering() {
        setCanFinish(true);
    }
}