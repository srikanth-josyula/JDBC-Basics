package com.sample.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.sample.jdbc.constants.DBType;
import com.sample.jdbc.exceptions.Exceptions;
import com.sample.jdbc.sql.SQLQueries;
import com.sample.jdbc.utils.ConnectionManager;

public class DBUpdateService {

	public void processExecute() {
		Connection conn = null;
		Statement stmt = null;

		try {
			System.out.println("DBUpdateService::processExecute::Updating records of the table");

			conn = ConnectionManager.getConnection(DBType.POSTGREDB);
		      stmt = conn.createStatement();
		     
		      int result = stmt.executeUpdate(SQLQueries.UPDATE_STATEMENT);

		      if(result == 1) {
					System.out.println("DBUpdateService::processExecute::Updated records successfully");
		      }else {
					System.out.println("DBUpdateService::processExecute::unable to update records of the table");
		      }
		      
			System.out.println("DBUpdateService::processExecute::successfully action performed");

		} catch (SQLException e) {
			Exceptions.processException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.err.println("DBUpdateService::processExecute::Message: " + e.getMessage());
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("DBUpdateService::processExecute::Message: " + e.getMessage());
				}
			}
		}

	}

}
