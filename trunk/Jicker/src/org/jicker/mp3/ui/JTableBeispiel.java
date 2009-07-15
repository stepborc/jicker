package org.jicker.mp3.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jicker.mp3.id3.GetId3Tags;

public class JTableBeispiel extends JFrame {

	public JTableBeispiel() {
		setTitle("MP3 Sammlung");
		setSize(300, 300);
		setBackground(Color.gray);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		String[] spaltenName = { "Datei", "Interpret" };
		//String[][] dataValues = { { "Wish you were here", "Pink Floyd" },
		//		{ "Julia", "The Beatles" } };
		// String[][] gMp3 = null;
		// new GetId3Tags().getMP3Array(gMp3);
		
		//JTable table = new JTable(new CreateDataTable().dataTableValue(), spaltenName);
		Object[][] tObject = new CreateDataTable().dataTableValue();
		TableModel model = new DefaultTableModel(tObject,spaltenName);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		topPanel.add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JTableBeispiel jtb = new JTableBeispiel();
		jtb.setVisible(true);

	}
}
