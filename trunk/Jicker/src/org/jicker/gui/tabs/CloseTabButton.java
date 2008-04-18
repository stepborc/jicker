/*
 * 
 */
package org.jicker.gui.tabs;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

// TODO: Auto-generated Javadoc
/**
 * The Class CloseTabButton.
 */
class CloseTabButton extends JPanel implements ActionListener {
	  
  	/** The pane. */
  	private JTabbedPane pane;
	  
  	/**
  	 * Instantiates a new close tab button.
  	 * 
  	 * @param pane the pane
  	 * @param index the index
  	 */
  	public CloseTabButton(JTabbedPane pane, int index) {
	    this.pane = pane;
	    setOpaque(false);
	    add(new JLabel(
	        pane.getTitleAt(index),
	        pane.getIconAt(index),
	        JLabel.LEFT));
	    Icon closeIcon = new CloseIcon();
	    JButton btClose = new JButton(closeIcon);
	    btClose.setPreferredSize(new Dimension(
	        closeIcon.getIconWidth(), closeIcon.getIconHeight()));
	    add(btClose);
	    btClose.addActionListener(this);
	    pane.setTabComponentAt(index, this);
	  }
	  
  	/* (non-Javadoc)
  	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
  	 */
  	public void actionPerformed(ActionEvent e) {
	    int i = pane.indexOfTabComponent(this);
	    if (i != -1) {
	      pane.remove(i);
	    }
	  }
	}