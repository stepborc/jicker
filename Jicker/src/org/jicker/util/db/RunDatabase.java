/*
 * 
 */
package org.jicker.util.db;

import java.sql.SQLException;

import org.apache.log4j.Level;
import org.jicker.util.log.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class RunDatabase.
 */
public class RunDatabase {
	//private static Log logger = Log.getInstance();
	/** The db. */
	Database db = null;

//	public RunDatabase() {
//	}

	/**
 * Start.
 * 
 * @return true, if successful
 */
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
	
	/**
	 * Check table.
	 * 
	 * @param tableName the table name
	 * 
	 * @return true, if successful
	 * 
	 * @throws SQLException the SQL exception
	 */
	public boolean checkTable(String tableName) throws SQLException{
		return db.checkTable(tableName);
	}
	
	/**
	 * Creates the table.
	 * 
	 * @return true, if successful
	 */
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

	/**
	 * Update.
	 * 
	 * @param update the update
	 */
	public void update(String update) {
		try {
			db.update(update);
		} catch (SQLException e) {
			Log.log(Level.FATAL, this, "E004", new String[] {"Fehler beim Ausführen der UPDATE Anweisung.", e.toString() });
		}
	}

	/**
	 * Shutdown.
	 */
	public void shutdown() {
		db.shutdown()
		;
	}

	/**
	 * Show table.
	 */
	public void showTable() {
		try {
			db.query("SELECT * FROM main");
		} catch (SQLException e) {
			Log.log(Level.FATAL, this, "E004", new String[] {"Fehler beim Ausführen der SELECT Anweisung.", e.toString() });
			
		}

	}

	/**
	 * Drop table.
	 */
	public void dropTable() {
		//try {
			db.dropTable();
		//} catch (SQLException e) {
		//	Log.log(Level.FATAL, this, "E004", new String[] {"Fehler beim Löschen der Tabelle.", e.toString() });
		//}
	}

}