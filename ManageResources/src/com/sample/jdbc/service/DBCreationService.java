package com.sample.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.sample.jdbc.exceptions.Exceptions;
import com.sample.jdbc.sql.SQLQueries;

public class DBCreationService {

	public void processCreation(Connection conn, Statement stmt) {

		try {
			
			System.out.println("DBCreationService::processCreation::Creating table into the database");

			stmt = conn.createStatement();
			stmt.executeUpdate(SQLQueries.CREATE_TABLE_QUERY);

			stmt.close();
			conn.close();

			System.out.println("DBCreationService::processCreation::successfully action performed");

		} catch (SQLException e) {
			Exceptions.processException(e);

		}

	}

}
