package org.jicker.gui.tabs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CloseTabs {
  public static void main(String args[]) {
    Runnable runner = new Runnable() {
      public void run() {
        JFrame frame = new JFrame("JTabbedPane");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane jtp = new JTabbedPane();
        frame.add(jtp, BorderLayout.CENTER);
        for (int i=0; i<5; i++) {
          JButton button = new JButton("Card " + i);
          jtp.add("Btn " + i, button);
          // new CloseTabButton(jtp, i);
        }
        frame.setSize(400, 200);
        frame.setVisible(true);
      }
    };
    EventQueue.invokeLater(runner);
  }
}