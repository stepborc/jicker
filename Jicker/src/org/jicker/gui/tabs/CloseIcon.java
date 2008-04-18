/*
 * 
 */
package org.jicker.gui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

// TODO: Auto-generated Javadoc
/**
 * The Class CloseIcon.
 */
class CloseIcon implements Icon {
	  
  	/* (non-Javadoc)
  	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
  	 */
  	public void paintIcon(Component c, Graphics g, int x, int y) {
	    g.setColor(Color.RED);
	    g.drawLine(6, 6, getIconWidth() - 7, getIconHeight() - 7);
	    g.drawLine(getIconWidth() - 7, 6, 6, getIconHeight() - 7);
	  }
	  
  	/* (non-Javadoc)
  	 * @see javax.swing.Icon#getIconWidth()
  	 */
  	public int getIconWidth() {
	    return 17;
	  }
	  
  	/* (non-Javadoc)
  	 * @see javax.swing.Icon#getIconHeight()
  	 */
  	public int getIconHeight() {
	    return 17;
	  }
	}
