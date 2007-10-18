package org.jicker.gui;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class MainWindow extends BaseWindow{
	public MainWindow(){
        JSplitPane aSplitPanel = new JSplitPane();
        //LeftPanel aLeftPanel = new LeftPanel();
        JTabbedPane aCenterPanel = new JTabbedPane();
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

}
