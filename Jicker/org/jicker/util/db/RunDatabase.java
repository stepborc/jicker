package org.jicker.util.db;

import java.sql.SQLException;

import org.apache.log4j.Level;
import org.jicker.util.log.Log;

public class RunDatabase {
	Database db = null;

	public boolean start() {

		boolean dbStart = true;
		try {
			db = new Database("jicker");
			Log.log(Level.INFO, this, "E005",
					new String[] { "Datenbank erfolgreich gestartet." });
		} catch (Exception e1) {
			Log.log(Level.FATAL, this, "E004", new String[] {
					"Fehler beim Starten der Datenbank.", e1.toString() });
			dbStart = false;
		}
		return dbStart;
	}

	public boolean checkTable(String tableName) throws SQLException {
		return db.checkTable(tableName);
	}

	public boolean createTable() {
		boolean dbCreate = true;
		db.checkTable("MAIN");
		try {
			// erstellen einer leeren Tabelle
			db
					.update("CREATE TABLE main ( id INTEGER IDENTITY, str_col VARCHAR(256), num_col INTEGER, crc BIGINT)");
		} catch (SQLException ex2) {
			Log.log(Level.INFO, this, "E004", new String[] {
					"Tabelle besteht bereits.", ex2.toString() });
			dbCreate = false;
		}
		return dbCreate;
	}

	public void update(String update) {
		try {
			db.update(update);
		} catch (SQLException e) {
			Log.log(Level.FATAL, this, "E004",
					new String[] {
							"Fehler beim Ausführen der UPDATE Anweisung.",
							e.toString() });
		}
	}

	public void shutdown() {
		db.shutdown();
	}

	public void showTable() {
		try {
			db.query("SELECT * FROM main");
		} catch (SQLException e) {
			Log.log(Level.FATAL, this, "E004",
					new String[] {
							"Fehler beim Ausführen der SELECT Anweisung.",
							e.toString() });

		}

	}

	public void dropTable() {
//		try {
			db.dropTable();
//		} catch (SQLException e) {
//			Log.log(Level.FATAL, this, "E004", new String[] {
//					"Fehler beim Löschen der Tabelle.", e.toString() });
//		}
	}

}