package org.jicker.gui.jwizz;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.javaprog.ui.wizard.AbstractStep;

class WelcomeStep extends AbstractStep {
    public WelcomeStep() {
        super("Willkommen", "Der Firststep Wizard");
    }
    
    protected JComponent createComponent() {
        JPanel stepComponent = new JPanel();
        stepComponent.add(
            new JLabel("<html>Dieser Wizard richtet einige grundlegende <p>"+
            		        "Einstellungen zum Start von Jicker ein.<p>"
            			  + "Mit den unten stehenden Buttons kann man navigieren.</html>"));
        return stepComponent;
    }

    public void prepareRendering() {}
}