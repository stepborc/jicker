package org.jicker.gui;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class MainWindow extends BaseWindow{
	public MainWindow(){
        JSplitPane aSplitPanel = new JSplitPane();
        LeftPanel aLeftPanel = new LeftPanel();
        JTabbedPane aCenterPanel = new JTabbedPane();
        //StatusBar aStatusBar = new StatusBar();

        setLayout(new BorderLayout());
        aSplitPanel.setLeftComponent(aLeftPanel);
        aSplitPanel.setRightComponent(aCenterPanel);
        add(aSplitPanel, BorderLayout.CENTER);
        //add(aStatusBar, BorderLayout.SOUTH);
	}

}
