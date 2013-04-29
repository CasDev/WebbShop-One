package se.casdev.database.access;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import se.casdev.warnings.Messenge;
import se.casdev.warnings.Warning;

public class ConnectingMySql {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Warning.addServerMessage(new Messenge("", "Could not start the connection to MySQL"));
			Warning.addClientMessage(new Messenge("", "We are currently having some dtechnical difficulties. Please, try again - if that fails wait a few minutes."));
		}
	}

	private static Connection conn;

	public Connection getConnection(String db, String user,
			String psw) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/" + db,
					user, psw);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			Warning.addServerMessage(new Messenge("connection.fail", "Couldn't start the connection to the database"));
			Warning.addClientMessage(new Messenge("technical.difficulties", "We are currently having some technical difficulties. Please, try again - if that fails wait a few minutes."));
		}

		return conn;
	}

	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			Warning.addServerMessage(new Messenge("disconnection.fail", "Couldn't stop the connection of the connection"));
			Warning.addClientMessage(new Messenge("technical.difficulties", "We are currently having some technical difficulties. Please, try again - if that fails wait a few minutes."));
		}
	}

}
