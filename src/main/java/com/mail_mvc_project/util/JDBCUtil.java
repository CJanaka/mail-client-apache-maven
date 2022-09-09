package com.mail_mvc_project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

	public static final String DB_DRIVER = "org.postgresql.Driver";
	public static final String DB_URL = "jdbc:postgresql://localhost:5432/mail";
	public static final String DB_USERNAME = "postgres";
	public static final String DB_PASSWORD = "jana";

	public static Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver class not found " + e.getMessage());
		}

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println("Connection error " + e.getMessage());
		}

		if (connection == null) {
			System.out.println("Connection is null");
		}
		return connection;
	}
}