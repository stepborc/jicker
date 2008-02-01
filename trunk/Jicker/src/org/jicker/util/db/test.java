package org.jicker.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class test {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Class.forName("org.hsqldb.jdbcDriver");
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:"
				+ "org/jicker/util/db/testDb", "sa", "");
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData meta = null;
		 
		st = conn.createStatement(); // statement objects can be reused with
		rs = st.executeQuery("SELECT * FROM MAIN");
		meta = rs.getMetaData();
		//int n = meta.getColumnCount();
		List<String> wantedColumnNames = new ArrayList<String>();
		for (int n = 1; n < meta.getColumnCount();n++){
			wantedColumnNames.add(meta.getColumnName(n).toString());
		}
		System.out.println(wantedColumnNames.toString());
	       
		
	       while (rs.next())
	        {
	            Map<String, Object> row = new LinkedHashMap<String, Object>();
	 
	            for (int i = 0; i < meta.getColumnCount()- 1; ++i)
	            {
	                String columnName   = (String)wantedColumnNames.get(i);
	                Object value = rs.getObject(columnName);
	                row.put(columnName, value);
	            }
	 
	            rows.add(row);
	        }
		
		st.close();
rs.close();
conn.close();
System.out.println(rows.toString());
	}
	

}
