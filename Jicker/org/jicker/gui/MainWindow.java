package org.jicker.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

public class MainWindow extends BaseWindow{
	public MainWindow(){
        JSplitPane aSplitPanel = new JSplitPane();
        //LeftPanel aLeftPanel = new LeftPanel();
        JTabbedPane aCenterPanel = new JTabbedPane();
                
        JComponent panel1 = makeTextPanel("Panel #1");
        aCenterPanel.addTab("Photosets", panel1);
        aCenterPanel.setMnemonicAt(0, KeyEvent.VK_1);
        
        //String[] test = {"a","b","c"};
        //JList list = new JList(test);
 
        JTree list = new JTree();
        StatusBar aStatusBar = new StatusBar(null,null,null,"Test");
        
        
         
        setLayout(new BorderLayout());
        //aSplitPanel.setLeftComponent(aLeftPanel);
        aSplitPanel.setLeftComponent(list);
        aSplitPanel.setRightComponent(aCenterPanel);
        
        add(aSplitPanel, BorderLayout.CENTER);
        add(aStatusBar, BorderLayout.SOUTH);
	}
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}
