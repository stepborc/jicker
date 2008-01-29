package org.jicker.gui.jwizz;

import javax.swing.*;
import net.javaprog.ui.wizard.*;

class WelcomeStep extends AbstractStep {
    public WelcomeStep() {
        super("Welcome", "This is the Installation Wizard");
    }
    
    protected JComponent createComponent() {
        JPanel stepComponent = new JPanel();
        stepComponent.add(
            new JLabel("<html>This wizard will guide you through the installation process.<p>"
                + "You can navigate through the steps using the buttons below.</html>"));
        return stepComponent;
    }

    public void prepareRendering() {}
}