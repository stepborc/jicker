package org.jicker.mp3.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -3316519290894600666L;
	JTable table = new JTable(new TableModel());

	public MainFrame() {
		// TODO Auto-generated method stub
		setSize(800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width - getSize().width) / 2,
				(d.height - getSize().height) / 2);

		setVisible(true);

		table.getModel().setValueAt("test1", 0, 0);
		table.getModel().setValueAt("", 0, 1);
		table.getModel().setValueAt("test2", 1, 0);
		table.getModel().setValueAt("", 1, 1);
		table.getModel().setValueAt("test3", 2, 0);
		table.getModel().setValueAt("", 2, 1);
		table.getModel().setValueAt("test4", 3, 0);
		table.getModel().setValueAt("", 3, 1);
		table.getModel().setValueAt("test5", 4, 0);
		table.getModel().setValueAt("", 4, 1);
		table.getModel().setValueAt("test6", 5, 0);
		table.getModel().setValueAt("", 5, 1);
		JScrollPane pane = new JScrollPane(table);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(pane);

		JButton test = new JButton("test");
		test.setName("testbutton");
		test.addActionListener(this);
		panel.add(test);

		this.add(panel);
		panel.updateUI();
	}

	public static void main(String arguments[]) {
		MainFrame a = new MainFrame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int rowcount = ((AbstractTableModel) table.getModel()).getRowCount();
		int colcount = ((AbstractTableModel) table.getModel()).getColumnCount();

		System.out.println("___________________________________________");
		for (int i = 0; i < rowcount; i++) {
			System.out.println(((AbstractTableModel) table.getModel())
					.getValueAt(i, 1));
		}
	}
}


