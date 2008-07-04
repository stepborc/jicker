// StatusBar.java
// $Id: StatusBar.java,v 1.3 2000/08/17 15:07:28 bmahe Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 1999.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.jicker.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * A classical status bar.
 *
 * @version $Revision: 1.3 $
 * @author Benoît Mahé (bmahe@w3.org)
 */
public class StatusBar extends JPanel {

	/** The statusl. */
	JLabel statusl = null;

	/** The buttons. */
	JButton buttons[] = null;

	/** The descr. */
	String descr[] = null;

	/**
	 * Constructor.
	 * <p>
	 * <b>Note</b>: icons, commands and descr MUST have the same size.
	 *
	 * @param icons
	 *            The Buttons Icons (on the left)
	 * @param commands
	 *            The actions commands
	 * @param descr
	 *            The actions descriptions
	 * @param status
	 *            The default status
	 */
	public StatusBar(Icon icons[], String commands[], String descr[],
			String status) {
		super(new BorderLayout(0, 0));
		this.descr = descr;

		statusl = new JLabel(status);
		statusl.setBorder(BorderFactory.createLoweredBevelBorder());
		if (icons != null) {
			int len = icons.length;
			JPanel p = new JPanel(new GridLayout(1, len));
			buttons = new JButton[len];
			MouseAdapter ma = new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					Object source = e.getSource();
					for (int i = 0; i < buttons.length; i++) {
						if (source == buttons[i]) {
							setStatus(StatusBar.this.descr[i]);
						}
					}
				}

				public void mouseExited(MouseEvent e) {
					setStatus("");
				}
			};
			for (int i = 0; i < len; i++) {
				buttons[i] = new JButton(icons[i]);
				buttons[i].setActionCommand(commands[i]);
				buttons[i].setMargin(new Insets(0, 0, 0, 0));
				buttons[i].addMouseListener(ma);
				p.add(buttons[i]);
			}
			add(p, "West");
		}
		add(statusl, "Center");
	}

	/**
	 * Adds the action listener.
	 *
	 * @param listener
	 *            the listener
	 */
	public void addActionListener(ActionListener listener) {
		if (buttons != null) {
			for (int i = 0; i < buttons.length; i++) {
				buttons[i].addActionListener(listener);
			}
		}
	}

	/**
	 * Set the Status.
	 *
	 * @param text
	 *            the status
	 */
	public void setStatus(String text) {
		statusl.setText(text);
	}

	/**
	 * Clear the status bar.
	 */
	public void clearStatus() {
		statusl.setText("");
	}

	/**
	 * Get the current status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return statusl.getText();
	}

}