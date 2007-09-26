package org.jicker.util.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Level;
import org.jicker.util.log.Log;

public class Db {
	Connection conn;

	public boolean startDb() {
		boolean start = false;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection("jdbc:hsqldb:"
					+ "org/jicker/util/db/testDb", "sa", "");
			start = true;
			Log.log(Level.INFO, this, "DBstart", null);
			if (!this.checkTable("MAIN")) {
				this
						.update("CREATE TABLE main ( id INTEGER IDENTITY, str_col VARCHAR(256), num_col INTEGER, crc BIGINT)");
				Log.log(Level.INFO, this, "DBtablecreate", null);
				start = true;
			}
		} catch (ClassNotFoundException c) {
			start = false;
			Log
					.log(Level.FATAL, this, "DBclass", new String[] { c
							.toString() });
		} catch (SQLException s) {
			start = false;
			Log
					.log(Level.FATAL, this, "DBinuse", new String[] { s
							.toString() });
		}
		// Testen ob die notwendige Tabelle MAIN exisitiert

		return start;
	}

	public boolean stopDb() {
		boolean stop = false;
		Statement st = null;
		try {
			st = conn.createStatement();
			st.execute("SHUTDOWN");
			st.close();
			conn.close();
			stop = true;
			Log.log(Level.INFO, this, "DBstop", null);
		} catch (SQLException s) {
			Log.log(Level.FATAL, this, "DBstoperror", new String[] { s
					.toString() });
			stop = false;
		}
		return stop;
	}

	public boolean checkTable(String tableName) {
		Statement st = null;
		ResultSet rs = null;
		boolean tableCheck = false;
		try {
			st = conn.createStatement();
			DatabaseMetaData md = conn.getMetaData();
			String schema = "PUBLIC";
			rs = md.getTables(null, schema, "%", null);
			while (rs.next()) {
				if (rs.getString(3).equals("MAIN")) {
					tableCheck = true;
					break;
				}
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Log.log(Level.FATAL, this, "E004", new String[] {
					"Fehler beim prüfen der Tabelle.", e.toString() });
		}

		return tableCheck;
	}

	public boolean update(String update) {
		boolean success = false;
		try {
			Statement st = null;
			st = conn.createStatement();
			st.executeUpdate(update); // run the query
			st.close();
		} catch (Exception s) {
			success = false;
			Log.log(Level.ERROR, this, "DBupdate", new String[] { update,
					s.toString() });
		}
		return success;
	}

	public void dropTable() {
		this.update("DROP TABLE MAIN");
		Log.log(Level.INFO, this, "DBtabledrop", null);
	}
}
