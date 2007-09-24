package org.jicker.util.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Level;
import org.jicker.util.log.Log;

public class Database {
	Connection conn;
	private static Log logger = Log.getInstance();

	public Database(String db_file_name_prefix) {

		// Laden des HSQL Database Engine JDBC Treibers
		// hsqldb.jar sollte im Classpath sein, oder Teil der aktuellen Jar
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			logger
					.log(Level.FATAL, this, "E001",
							new String[] { e.toString() });
			// e.printStackTrace();
		}

		// Verbinden zur Datenbank. Damit werden die Datenbankdateiten geladen
		// und die Datenbank startet, falls Sie noch nicht läuft.
		// db_file_name_prefix wird benutzt um die DB zu öffnen oder zu
		// erstellen
		// db_file_name_prefix kann Verzeichnamen relativ zum aktuellen
		// Verzeichnis beinhalten
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:"
					+ "org/jicker/util/db/" + db_file_name_prefix, "sa", "");
		} catch (SQLException e) {
			logger.log(Level.FATAL, this, "E003", new String[] {
					"Datenbank wird bereits verwendet wird.", e.toString() });
		}
	}

	public void shutdown() {

		Statement st;
		try {
			st = conn.createStatement();
			st.execute("SHUTDOWN");
			conn.close();
		} catch (SQLException e) {
			logger.log(Level.FATAL, this, "E004", new String[] {
					"Fehler beim Stoppen der Datenbank.", e.toString() });
		}

/*		// db writes out to files and performs clean shuts down
		// otherwise there will be an unclean shutdown
		// when program ends
		try {
			st.execute("SHUTDOWN");
		} catch (SQLException e) {
			logger.log(Level.FATAL, this, "E004", new String[] {
					"Fehler beim Stoppen der Datenbank.", e.toString() });
		}
		try {
			conn.close();
		} catch (SQLException e) {
			logger.log(Level.FATAL, this, "E004", new String[] {
					"Fehler beim schließen der Connection.", e.toString() });
		} // if there are no other open connection
*/	}

	// use for SQL command SELECT
	public synchronized void query(String expression) throws SQLException {

		Statement st = null;
		ResultSet rs = null;

		st = conn.createStatement(); // statement objects can be reused with

		// repeated calls to execute but we
		// choose to make a new one each time
		rs = st.executeQuery(expression); // run the query

		// do something with the result set.
		dump(rs);
		st.close(); // NOTE!! if you close a statement the associated ResultSet
		// is

		// closed too
		// so you should copy the contents to some other object.
		// the result set is invalidated also if you recycle an Statement
		// and try to execute some other query before the result set has been
		// completely examined.
	}

	// use for SQL commands CREATE, DROP, INSERT and UPDATE
	public synchronized void update(String expression) throws SQLException {

		Statement st = null;

		st = conn.createStatement(); // statements

		int i = st.executeUpdate(expression); // run the query

		if (i == -1) {
			System.out.println("db error : " + expression);
		}

		st.close();
	} // void update()

	public static void dump(ResultSet rs) throws SQLException {

		// the order of the rows in a cursor
		// are implementation dependent unless you use the SQL ORDER statement
		ResultSetMetaData meta = rs.getMetaData();
		int colmax = meta.getColumnCount();
		int i;
		Object o = null;

		// the result set is a cursor into the data. You can only
		// point to one row at a time
		// assume we are pointing to BEFORE the first row
		// rs.next() points to next row and returns true
		// or false if there is no next row, which breaks the loop
		for (; rs.next();) {
			for (i = 0; i < colmax; ++i) {
				o = rs.getObject(i + 1); // Is SQL the first column is
				// indexed

				// with 1 not 0
				System.out.print(o.toString() + " ");
			}

			System.out.println(" ");
		}
	} // void dump( ResultSet rs )

	public synchronized void dropTable() throws SQLException {
		Statement st = null;

		st = conn.createStatement(); // statements

		int i = st.executeUpdate("DROP TABLE main"); // run the query

		if (i == -1) {
			System.out.println("db error : " + "");
		}

		st.close();

	}

	public boolean checkTable(String tableName) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		boolean tableCheck = false;
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
		return tableCheck;
	}
}
