package org.jicker.mp3.ui;


import java.sql.Array;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class CreateDataTable {
	public ArrayList<TableObject> dataTableValue() {
		String[][] test = { {"test1","Test1"},{"test2","Test2"} };
		ArrayList<TableObject> liste = new ArrayList<TableObject>();
		TableObject t = new TableObject("1","2");
		TableObject t1 = new TableObject("3","4");
		liste.add(t);
		liste.add(t1);

		new AbstractTableModel() {
		    public String getColumnName(int col) {
		        Object[] columnNames;
				return columnNames[col].toString();
		    }
		    public int getRowCount() { return rowData.length; }
		    public int getColumnCount() { return columnNames.length; }
		    public Object getValueAt(int row, int col) {
		        return rowData[row][col];
		    }
		    public boolean isCellEditable(int row, int col)
		        { return true; }
		    public void setValueAt(Object value, int row, int col) {
		        rowData[row][col] = value;
		        fireTableCellUpdated(row, col);
		    }
		}

		
		
		return liste;

	}

}
