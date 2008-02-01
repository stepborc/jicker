package org.jicker.gui.wizard;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
 
class GridBagLayoutDemo 
{ 
  static void addComponent( Container cont, 
                            GridBagLayout gbl, 
                            Component c, 
 
                            int x, int y, 
                            int width, int height, 
                            double weightx, double weighty ) 
  { 
    GridBagConstraints gbc = new GridBagConstraints(); 
    gbc.fill = GridBagConstraints.BOTH; 
    gbc.gridx = x; gbc.gridy = y; 
    gbc.gridwidth = width; gbc.gridheight = height; 
    gbc.weightx = weightx; gbc.weighty = weighty; 
    gbl.setConstraints( c, gbc ); 
    cont.add( c ); 
  } 
 
  public static void main( String[] args ) 
  { 
    JFrame f = new JFrame(); 
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
    Container c = f.getContentPane(); 
 
    GridBagLayout gbl = new GridBagLayout(); 
    c.setLayout( gbl ); 
 
    //                                      x  y  w  h  wx   wy 
 
    //addComponent( c, gbl, (Component)new ButtonGroup(), 0, 0, 2, 2, 1.0, 1.0 ); 
    addComponent( c, gbl, new JRadioButton("1"), 2, 0, 1, 1, 0  , 1.0 ); 
    addComponent( c, gbl, new JRadioButton("2"), 2, 1, 1, 1, 0  , 0   ); 
    addComponent( c, gbl, new JButton("4"), 0, 2, 3, 1, 0  , 1.0 ); 
    addComponent( c, gbl, new JButton("5"), 0, 3, 2, 1, 0  , 0   ); 
    addComponent( c, gbl, new JButton("6"), 0, 4, 2, 1, 0  , 0   ); 
    addComponent( c, gbl, new JTextField("7"), 2, 3, 1, 2, 0  , 0   ); 
 
    f.setSize( 300, 200 ); 
    f.setVisible( true ); 
  } 
}