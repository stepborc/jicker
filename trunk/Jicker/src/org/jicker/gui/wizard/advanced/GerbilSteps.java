/*  The contents of this file are subject to the terms of the Common Development
and Distribution License (the License). You may not use this file except in
compliance with the License.
    You can obtain a copy of the License at http://www.netbeans.org/cddl.html
or http://www.netbeans.org/cddl.txt.
    When distributing Covered Code, include this CDDL Header Notice in each file
and include the License file at http://www.netbeans.org/cddl.txt.
If applicable, add the following below the CDDL Header, with the fields
enclosed by brackets [] replaced by your own identifying information:
"Portions Copyrighted [year] [name of copyright owner]" */

package org.jicker.gui.wizard.advanced;

import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPanelProvider;

// TODO: Auto-generated Javadoc
/**
 * The Class GerbilSteps.
 * 
 * @author Timothy Boudreau
 */
public class GerbilSteps extends WizardPanelProvider {
    
    /**
     * Creates a new instance of GerbilSteps.
     */
    public GerbilSteps() {
        super (new String[] { "Finish" }, new String[] { "Finish" });
    }
    
    /* (non-Javadoc)
     * @see org.netbeans.spi.wizard.WizardPanelProvider#createPanel(org.netbeans.spi.wizard.WizardController, java.lang.String, java.util.Map)
     */
    protected JComponent createPanel(WizardController controller, String id, Map settings) {
        controller.setForwardNavigationMode (WizardController.MODE_CAN_FINISH);
        controller.setProblem(null);
        return new JLabel ("Sorry, Gerbils are a bit dull");
    }
    
}
