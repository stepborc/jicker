package org.jicker.gui.jwizz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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

import org.jicker.JickerGlobals;

import net.javaprog.ui.wizard.AbstractStep;
import net.javaprog.ui.wizard.DataModel;
import net.javaprog.ui.wizard.DefaultDataLookup;

class LocationStep extends AbstractStep {
    protected DataModel data;
    protected JTextField fileTextField = new JTextField();
    protected JFileChooser fc = new JFileChooser();
    
    public LocationStep(DataModel data) {
        super("Arbeitsverzeichnis", "Verzeichnis zum speichern der Programmeigenschaften auswählen");
        this.data = data;
    }
    
    protected JComponent createComponent() {
    	
        final JRadioButton defaultLocationButton = new JRadioButton(
                "Standardverzeichnis " + JickerGlobals.PROP_DIR, true);
        final JRadioButton noDefaultButton = new JRadioButton(
                "Verzeichnis ändern ...");
        //Übergeordnete Box
        final JPanel stepComponent = new JPanel();
        stepComponent.setLayout(new BoxLayout(stepComponent, BoxLayout.PAGE_AXIS));
        
        //Eingefügte Box mit 2 Radiobutton, vertikal
        //zweier Radiobutton gruppieren
        ButtonGroup group = new ButtonGroup();
        group.add(defaultLocationButton);
        group.add(noDefaultButton);

        final JPanel radioButtonComponent = new JPanel();
        
        radioButtonComponent.setLayout(new BoxLayout(radioButtonComponent, BoxLayout.Y_AXIS));
        radioButtonComponent.setAlignmentY(0.0f);
        radioButtonComponent.add(defaultLocationButton);
        radioButtonComponent.add(noDefaultButton);
        
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        //radioButtonComponent.add(Box.createVerticalGlue());
        //stepComponent.add(Box.createVerticalGlue());
        //JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        JPanel inputPanel = new JPanel();
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
    
    public void prepareRendering() {}
}
