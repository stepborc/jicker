package org.jicker.gui;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class MainWindow extends BaseWindow{
	public MainWindow(){
        JSplitPane aSplitPanel = new JSplitPane();
        LeftPanel aLeftPanel = new LeftPanel();
        JTabbedPane aCenterPanel = new JTabbedPane();
        String[] test = {"a","b","c"};
        JList list = new JList(test);
        //StatusBar aStatusBar = new StatusBar();

 
         
        setLayout(new BorderLayout());
        //aSplitPanel.setLeftComponent(aLeftPanel);
        aSplitPanel.setLeftComponent(list);
        aSplitPanel.setRightComponent(aCenterPanel);
        add(aSplitPanel, BorderLayout.CENTER);
        //add(aStatusBar, BorderLayout.SOUTH);
	}

}
