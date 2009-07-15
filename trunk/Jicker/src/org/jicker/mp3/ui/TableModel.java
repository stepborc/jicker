package org.jicker.mp3.ui;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class TableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private String[] columnNames = { "Datei", "Interpret" };
	private Vector<Object> spalte1 = new Vector<Object>();
	private Vector<Object> spalte2 = new Vector<Object>();

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return spalte1.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		if ((col == 0) && (!spalte1.isEmpty()))
			return spalte1.get(row);
		if ((col == 1) && (!spalte2.isEmpty()))
			return spalte2.get(row);
		else
			return 0;
	}

	public Class getColumnClass(int c) {
		return getValueAt(1, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {

		if (col < 0) {
			return false;
		} else {
			return true;
		}
	}

	public void setValueAt(Object value, int row, int col) {
		if (col == 0) {
			if (row < spalte1.size()) {
				spalte1.set(row, value);
			} else {
				spalte1.add(row, value);
			}
		} else if (col == 1) {
			if (row < spalte2.size()) {
				spalte2.set(row, value);
			} else {
				spalte2.add(row, value);
			}
		}
		fireTableCellUpdated(row, col);
	}
}