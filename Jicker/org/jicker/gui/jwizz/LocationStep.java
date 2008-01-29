package org.jicker.gui.jwizz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.javaprog.ui.wizard.AbstractStep;
import net.javaprog.ui.wizard.DataModel;
import net.javaprog.ui.wizard.DefaultDataLookup;

class LocationStep extends AbstractStep {
    protected DataModel data;
    protected JTextField fileTextField = new JTextField();
    protected JFileChooser fc = new JFileChooser();
    
    public LocationStep(DataModel data) {
        super("Choose location", "Please choose the installation location");
        this.data = data;
    }
    
    protected JComponent createComponent() {
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        final JPanel stepComponent = new JPanel();
        stepComponent.setLayout(new BoxLayout(stepComponent, BoxLayout.Y_AXIS));
        stepComponent.add(Box.createVerticalGlue());
        JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        inputPanel.add(new JLabel("Directory:"), BorderLayout.WEST);
        inputPanel.add(fileTextField);
        JButton browseButton = new JButton("Browse...");
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fc.showOpenDialog(stepComponent) == JFileChooser.APPROVE_OPTION) {
                    fileTextField.setText(fc.getSelectedFile().getPath());
                }
            }
        });
        inputPanel.add(browseButton, BorderLayout.EAST);
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
                inputPanel.getPreferredSize().height));
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
