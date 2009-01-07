package org.jicker.mp3.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

public class JickerMp3UI extends JFrame {
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Eine JSplitPane einrichten
	private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	// linke Seite der JPlitPane
	private JPanel leftPanel = new JPanel();
	// BorderLayout setzen
	private LayoutManager leftPanelLayout = new BorderLayout();
	// rechte Seite der JPlitPane
	private JPanel rightPanel = new JPanel();
	// BorderLayout setzen
	private LayoutManager rightPanelLayout = new BorderLayout();
	public JickerMp3UI() {
		initJickerMp3UI();
	}
	private void initJickerMp3UI() {
		// Wenn CloseButton angeklickt wird, wird das Programm beendet
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// Groesse des Bildschirms auslesen
		Dimension displaySize = toolkit.getScreenSize();
		/*
		 * Auslesen der Bildschirm Hoehe und Breite. Die Ergebnisse halbieren
		 * und die Fensterbreite und Höhe setzen. Die Werte werden gecastet, um
		 * die Floatwerte der Multiplikation (eigentlich der Halbierung) zu
		 * vermeiden
		 */
		this.setSize((int) (displaySize.getWidth() * 0.5), (int) (displaySize
				.getHeight() * 0.5));
		/*
		 * Fenster wird in der Mitte des Bildschirms ausgegeben (weil Null
		 * übergeben wird)
		 * http://java.sun.com/j2se/1.4.2/docs/api/java/awt/Window
		 * .html#setLocationRelativeTo(java.awt.Component)
		 */
		this.setLocationRelativeTo(null);
		//Linke Seite der splitPane zuordnen
		splitPane.setLeftComponent(leftPanel);
		//Hintergrundfarbe setzen
		leftPanel.setBackground(Color.CYAN);
		leftPanel.setLayout(leftPanelLayout);
		//Rechte Seite der splitPane zuordnen
		splitPane.setRightComponent(rightPanel);
		//Hintergrundfarbe setzen
		rightPanel.setBackground(Color.DARK_GRAY);
		rightPanel.setLayout(rightPanelLayout);
		//Content fuer JFrame setzen
		this.setContentPane(splitPane);
		// Sichtbar machen
		this.setVisible(true);

	}

}
