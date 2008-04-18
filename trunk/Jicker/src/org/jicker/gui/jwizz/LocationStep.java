/*
 * 
 */
package org.jicker.gui.jwizz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.javaprog.ui.wizard.AbstractStep;
import net.javaprog.ui.wizard.DataModel;
import net.javaprog.ui.wizard.DefaultDataLookup;

import org.jicker.JickerGlobals;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationStep.
 */
class LocationStep extends AbstractStep {
    
    /** The data. */
    protected DataModel data;
    
    /** The file text field. */
    protected JTextField fileTextField = new JTextField();
    
    /** The fc. */
    protected JFileChooser fc = new JFileChooser();
    
    /** The input panel. */
    protected JPanel inputPanel = new JPanel();
    
    /**
     * Instantiates a new location step.
     * 
     * @param data the data
     */
    public LocationStep(DataModel data) {
        super("Arbeitsverzeichnis", "Verzeichnis zum speichern der Programmeigenschaften auswählen");
        this.data = data;
    }
    
    /* (non-Javadoc)
     * @see net.javaprog.ui.wizard.AbstractStep#createComponent()
     */
    protected JComponent createComponent() {
    	
        final JRadioButton defaultLocationButton = new JRadioButton(
                "Standardverzeichnis " + JickerGlobals.PROP_DIR, true);
        final JRadioButton noDefaultButton = new JRadioButton(
                "Verzeichnis ändern ...");
        //Übergeordnete Box
        final JPanel stepComponent = new JPanel();
        stepComponent.setLayout(new BoxLayout(stepComponent, BoxLayout.PAGE_AXIS));
        stepComponent.setAlignmentX(0.0f);
        stepComponent.setAlignmentY(0.0f);
        //Eingefügte Box mit 2 Radiobutton, vertikal
        //zweier Radiobutton gruppieren
        ButtonGroup group = new ButtonGroup();
        group.add(defaultLocationButton);
        group.add(noDefaultButton);

        noDefaultButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				inputPanel.setVisible(true);
				
			}
        	
        });
        
        final JPanel radioButtonComponent = new JPanel();
        radioButtonComponent.setLayout(new BoxLayout(radioButtonComponent, BoxLayout.Y_AXIS));
        radioButtonComponent.add(defaultLocationButton);
        radioButtonComponent.add(noDefaultButton);
        radioButtonComponent.setAlignmentX(0.0f);
        radioButtonComponent.setAlignmentY(0.0f);
        
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        //radioButtonComponent.add(Box.createVerticalGlue());
        //stepComponent.add(Box.createVerticalGlue());
        //JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        //JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.X_AXIS));
        //inputPanel.add(new JLabel("Directory:"), BorderLayout.WEST);
        inputPanel.add(new JLabel("Directory:"));
        inputPanel.add(fileTextField);
        JButton browseButton = new JButton("Browse...");
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fc.showOpenDialog(stepComponent) == JFileChooser.APPROVE_OPTION) {
                    fileTextField.setText(fc.getSelectedFile().getPath());
                }
            }
        });
        //inputPanel.add(browseButton, BorderLayout.EAST);
        inputPanel.add(browseButton);
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputPanel.getPreferredSize().height));
        inputPanel.setVisible(false);
        stepComponent.add(radioButtonComponent);
        stepComponent.add(inputPanel);
        stepComponent.add(Box.createVerticalGlue());
        Method method = null;
        try {
            method = fileTextField.getClass().getMethod("getText", null);
        } catch(NoSuchMethodException nsme) {}
        data.registerDataLookup("location", 
                new DefaultDataLookup(fileTextField, method, null));
        return stepComponent;
    }
    
    /* (non-Javadoc)
     * @see net.javaprog.ui.wizard.Step#prepareRendering()
     */
    public void prepareRendering() {}
}
