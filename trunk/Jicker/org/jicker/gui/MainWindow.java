package org.jicker.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class MainWindow extends BaseWindow implements TreeSelectionListener{
	public StatusBar aStatusBar;
	public JTree list;
	public JComponent panel1;
	public MainWindow(){
        JSplitPane aSplitPanel = new JSplitPane();
        //LeftPanel aLeftPanel = new LeftPanel();
        JTabbedPane aCenterPanel = new JTabbedPane();
                
        panel1 = makeTextPanel("Panel #1");
        aCenterPanel.addTab("Photosets", panel1);
        aCenterPanel.setMnemonicAt(0, KeyEvent.VK_1);
        
        //String[] test = {"a","b","c"};
        //JList list = new JList(test);
 
        //JTree list = new JTree();
        SetTree model = new SetTree();
        
        list = new JTree(model.createTree());
        list.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        list.addTreeSelectionListener(this);
        JScrollPane listScroll = new JScrollPane();
        listScroll.getViewport().add(list);
        aStatusBar = new StatusBar(null,null,null,"Test");
        aStatusBar.setStatus("Zweiter Test");
        
         
        setLayout(new BorderLayout());
        //aSplitPanel.setLeftComponent(aLeftPanel);
        aSplitPanel.setLeftComponent(listScroll);
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
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		panel1.removeAll();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)list.getLastSelectedPathComponent();
		Object nodeInfo = node.getUserObject();
		aStatusBar.setStatus(nodeInfo.toString());
		JLabel text = new JLabel(nodeInfo.toString());
		
		panel1.add(text);
		
	}
}
