package org.jicker.util.db;

import java.sql.SQLException;

import org.apache.log4j.Level;
import org.jicker.util.log.Log;

public class RunDatabase {
	//private static Log logger = Log.getInstance();
	Database db = null;

//	public RunDatabase() {
//	}

	public boolean start() {
		
		boolean dbStart = true;
		try {
			db = new Database("jicker");
			Log.log(Level.INFO, this, "E005", new String[]{"Datenbank erfolgreich gestartet."});
		} catch (Exception e1) {
			// e1.printStackTrace();
			dbStart = false;
		}
		return dbStart;
	}
	public boolean checkTable(String tableName) throws SQLException{
		return db.checkTable(tableName);
	}
	
	public boolean createTable() {
		boolean dbCreate = true;
		db.checkTable("MAIN");
		try {
			// erstellen einer leeren Tabelle
			// durch deklarieren der ID Spalte
			// hsql try {
			db.update("CREATE TABLE main ( id INTEGER IDENTITY, str_col VARCHAR(256), num_col INTEGER, crc BIGINT)");
		} catch (SQLException ex2) {
			// ignore
			Log.log(Level.INFO, this, "E004", new String[] {"Tabelle besteht bereits.", ex2.toString() });
			// second time we run program
			// should throw execption since table
			// already there
			//
			// this will have no effect on the db
			dbCreate = false;
		}
		return dbCreate;
	}

	public void update(String update) {
		try {
			db.update(update);
		} catch (SQLException e) {
			Log.log(Level.FATAL, this, "E004", new String[] {"Fehler beim Ausf�hren der UPDATE Anweisung.", e.toString() });
		}
	}

	public void shutdown() {
		db.shutdown()
		;
	}

	public void showTable() {
		try {
			db.query("SELECT * FROM main");
		} catch (SQLException e) {
			Log.log(Level.FATAL, this, "E004", new String[] {"Fehler beim Ausf�hren der SELECT Anweisung.", e.toString() });
			
		}

	}

	public void dropTable() {
		//try {
			db.dropTable();
		//} catch (SQLException e) {
		//	Log.log(Level.FATAL, this, "E004", new String[] {"Fehler beim L�schen der Tabelle.", e.toString() });
		//}
	}

}