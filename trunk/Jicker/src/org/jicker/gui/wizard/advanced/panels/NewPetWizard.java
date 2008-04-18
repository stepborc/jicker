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
package org.jicker.gui.wizard.advanced.panels;

import java.util.Map;

import org.netbeans.spi.wizard.WizardBranchController;
import org.netbeans.spi.wizard.WizardPanelProvider;


// TODO: Auto-generated Javadoc
/**
 * This is the main entry point, from which the wizard is created.
 * 
 * @author Timothy Boudreau
 */
public class NewPetWizard extends WizardBranchController {

    /**
     * Instantiates a new new pet wizard.
     */
    NewPetWizard(  ) {
        super( new InitialSteps(  ) );
    }
   
    /* (non-Javadoc)
     * @see org.netbeans.spi.wizard.WizardBranchController#getPanelProviderForStep(java.lang.String, java.util.Map)
     */
    protected WizardPanelProvider getPanelProviderForStep(String step, Map collectedData) {
        //There's only one branch point, so we don't need to test the
        //value of step
        Object species = collectedData.get(SpeciesPanel.KEY_SPECIES);
        if (SpeciesPanel.VALUE_CAT.equals(species)) {
            return getCatLoversSteps();
        } else if (SpeciesPanel.VALUE_DOG.equals(species)) {
            return getDogLoversSteps();
        } else if (SpeciesPanel.VALUE_GERBIL.equals(species)) {
            return null;//new GerbilSteps();
        } else {
            return null;
        }
    }

    /**
     * Gets the dog lovers steps.
     * 
     * @return the dog lovers steps
     */
    private WizardPanelProvider getDogLoversSteps() {
        if (dogLoversSteps == null) {
            dogLoversSteps = new DogLoversSteps();
        }
        return dogLoversSteps;
    }

    /**
     * Gets the cat lovers steps.
     * 
     * @return the cat lovers steps
     */
    private WizardPanelProvider getCatLoversSteps() {
        if (catLoversSteps == null) {
            catLoversSteps = new CatLoversSteps();
        }
        return catLoversSteps;
    }
    
    /** The cat lovers steps. */
    private CatLoversSteps catLoversSteps = null;
    
    /** The dog lovers steps. */
    private DogLoversSteps dogLoversSteps = null;
}
