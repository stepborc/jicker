package org.jicker.util.db;

import java.sql.SQLException;

public class RunDatabase {
	Database db = null;

	public RunDatabase() {

	}

	public boolean start() {
		boolean dbStart = true;
		try {
			db = new Database("jicker");
		} catch (Exception e1) {
			// e1.printStackTrace();
			dbStart = false;
		}
		return dbStart;
	}

	public boolean createTable() {
		boolean dbCreate = true;
		try {
			// erstellen einer leeren Tabelle
			// durch deklarieren der ID Spalte
			// hsql try {
			db.update("CREATE TABLE main ( id INTEGER IDENTITY, str_col VARCHAR(256), num_col INTEGER, crc BIGINT)");
		} catch (SQLException ex2) {
			// ignore
			System.out.println("Tabelle bestand bereits: " + ex2.toString());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void shutdown() {
		try {
			db.shutdown();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showTable() {
		try {
			db.query("SELECT * FROM main");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void dropTable() {
		try {
			db.dropTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}