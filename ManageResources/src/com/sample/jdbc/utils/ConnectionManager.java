package com.sample.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sample.jdbc.constants.DBConstants;
import com.sample.jdbc.constants.DBType;

public class ConnectionManager {

	private static ConnectionManager instance = null;

	public static Connection getConnection(DBType dbType) throws SQLException {

		switch (dbType) {
		case MYSQL:
			return DriverManager.getConnection(DBConstants.MYSQLCS, DBConstants.MYSQLPWD, DBConstants.MYSQLPWD);
		case POSTGREDB:
			return DriverManager.getConnection(DBConstants.POSTGRECS + DBConstants.POSTGREDB, DBConstants.POSTGREUSER,
					DBConstants.POSTGREPWD);
		default:
			return null;
		}

	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	/**
	 * Concepts like try with resource and auto closeable used which are introduced
	 * in java 7
	 */
	public boolean checkConnection() {

		boolean flag = false;

		try (Connection conn = getConnection(DBType.POSTGREDB)) {
			Class.forName(DBConstants.POSTGREDRIVER);
			System.out.println("DBUtils::checkConnection::Connecting to database");

			if (conn != null) {
				System.out.println("DBUtils::checkConnection::Connected to database");
				flag = true;
			} else {
				System.out.println("DBUtils::checkConnection::Unable to connect to database");
				flag = false;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
