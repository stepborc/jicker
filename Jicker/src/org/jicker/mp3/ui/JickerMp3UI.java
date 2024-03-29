package org.jicker.mp3.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

public class JickerMp3UI extends JFrame implements TreeSelectionListener {
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
		 * und die Fensterbreite und H�he setzen. Die Werte werden gecastet, um
		 * die Floatwerte der Multiplikation (eigentlich der Halbierung) zu
		 * vermeiden
		 */
		this.setSize((int) (displaySize.getWidth() * 0.5), (int) (displaySize
				.getHeight() * 0.5));
		/*
		 * Fenster wird in der Mitte des Bildschirms ausgegeben (weil Null
		 * �bergeben wird)
		 * http://java.sun.com/j2se/1.4.2/docs/api/java/awt/Window
		 * .html#setLocationRelativeTo(java.awt.Component)
		 */
		this.setLocationRelativeTo(null);
		// Tree generieren
		//Eigenen Klasse CreateTree verwenden
		CreateTree model = new CreateTree();

		JTree list = new JTree(model.createTree());
		list.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		list.addTreeSelectionListener(this);
		JScrollPane listScroll = new JScrollPane();
		listScroll.getViewport().add(list);

		// Linke Seite der splitPane zuordnen
		splitPane.setLeftComponent(listScroll);
		// Hintergrundfarbe setzen
		leftPanel.setBackground(Color.CYAN);
		leftPanel.setLayout(leftPanelLayout);
		// Rechte Seite der splitPane zuordnen
		splitPane.setRightComponent(rightPanel);
		// Hintergrundfarbe setzen
		rightPanel.setBackground(Color.DARK_GRAY);
		rightPanel.setLayout(rightPanelLayout);
		// Content fuer JFrame setzen
		this.setContentPane(splitPane);
		// Sichtbar machen
		this.setVisible(true);

	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
