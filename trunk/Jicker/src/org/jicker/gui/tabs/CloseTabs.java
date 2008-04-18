/*
 * 
 */
package org.jicker.gui.tabs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

// TODO: Auto-generated Javadoc
/**
 * The Class CloseTabs.
 */
public class CloseTabs {
  
  /**
   * The main method.
   * 
   * @param args the arguments
   */
  public static void main(String args[]) {
    Runnable runner = new Runnable() {
      public void run() {
        JFrame frame = new JFrame("JTabbedPane");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane jtp = new JTabbedPane();
        frame.add(jtp, BorderLayout.CENTER);
        
        JButton viewPhotosets = new JButton("Photosets");
        jtp.add("Photosets", viewPhotosets);
        JButton viewPhotos = new JButton("Photos");
        jtp.add("Photos",viewPhotos);
        new CloseTabButton(jtp,0);
        
        //new CloseTabButton(jtp,1);
/*        for (int i=2; i<7; i++) {
          JButton button = new JButton("Card " + i);
          jtp.add("Btn " + i, button);
          new CloseTabButton(jtp, i);
        }*/
        frame.setSize(400, 200);
        frame.setVisible(true);
      }
    };
    EventQueue.invokeLater(runner);
  }
}