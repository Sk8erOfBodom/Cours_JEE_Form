package com.bge.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.bge.exception.DBException;

public class HSQLConnection {
	private final static String URL = "/comp/env/jdbc/jee_form";

	private static Connection connection;

	private HSQLConnection() throws DBException {
		try {
			Context ctx = new InitialContext();
			DataSource dataSource = (DataSource) ctx.lookup("java:" + URL);
			HSQLConnection.connection = dataSource.getConnection();
			System.out.println("Connection to database established.");
		} catch (Exception e) {
			new DBException("Error: Connection to database failed: " + e.getMessage());
		}
	}

	public static Connection get() throws DBException {
		if (HSQLConnection.connection == null) {
			new HSQLConnection();
		}

		return HSQLConnection.connection;
	}

	public static void close() throws DBException {
		try {
			HSQLConnection.connection.close();
			HSQLConnection.connection = null;
		} catch (SQLException e) {
			throw new DBException("Error closing the database: " + e.getMessage());
		}
	}
}
