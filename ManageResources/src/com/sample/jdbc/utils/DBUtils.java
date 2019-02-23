package com.sample.jdbc.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.sample.jdbc.constants.DBType;
import com.sample.jdbc.exceptions.Exceptions;
import com.sample.jdbc.service.DBFetchService;

public class DBUtils {

	Connection conn = null;
	Statement stmt = null;

	public void processExecute() {
		try {
			System.out.println("DBUtils::processExecute::query exection started");

			conn = ConnectionManager.getConnection(DBType.POSTGREDB);

			DBFetchService.forwardSet(conn, stmt);
			//DBFetchService.deleteRow(conn, stmt);
			//DBFetchService.updateRow(conn, stmt);
			DBFetchService.insertRow(conn, stmt);
			DBFetchService.reverseSet(conn, stmt);
			
			
			System.out.println("DBUtils::processExecute::successfully action performed");

		} catch (SQLException e) {
			Exceptions.processException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.err.println("DBUtils::processExecute::Message: " + e.getMessage());
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("DBUtils::processExecute::Message: " + e.getMessage());
				}
			}
		}
	}
}