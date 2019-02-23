package com.sample.jdbc.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sample.jdbc.sql.SQLQueries;

public class DBFetchService {

	public static void forwardSet(Connection conn, Statement stmt) {

		try {
			System.out.println("DBFetchService::forwardSet::fetching data from table");
			stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			ResultSet result = stmt.executeQuery(SQLQueries.SELECT_STATEMENT);
			while (result.next()) {

				System.out.print("Employee ID = " + result.getInt("id") + " :: ");
				System.out.println("Employee Name = " + result.getString("first") + " " + result.getString("last"));

			}
			System.out.println("DBFetchService::forwardSet::data is successfully fetched");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void reverseSet(Connection conn, Statement stmt) {

		try {
			System.out.println("DBFetchService::forwardSet::fetching data from table");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet result = stmt.executeQuery(SQLQueries.SELECT_STATEMENT);
			result.afterLast();
			while (result.previous()) {

				System.out.print("Employee ID = " + result.getInt("id") + " :: ");
				System.out.println("Employee Name = " + result.getString("first") + " " + result.getString("last"));

			}

			System.out.println("DBFetchService::forwardSet::data is successfully fetched");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteRow(Connection conn, Statement stmt) {

		try {
			System.out.println("DBFetchService::forwardSet::fetching data from table");
			// Here ResultSet.CONCUR_READ_ONLY cannot be used as we are updating
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.CLOSE_CURSORS_AT_COMMIT);

			ResultSet result = stmt.executeQuery(SQLQueries.SELECT_STATEMENT);

			result.absolute(1);
			result.deleteRow();

			System.out.println("DBFetchService::forwardSet::data is successfully fetched");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateRow(Connection conn, Statement stmt) {

		try {
			System.out.println("DBFetchService::forwardSet::fetching data from table");
			// Here ResultSet.CONCUR_READ_ONLY cannot be used as we are updating
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.CLOSE_CURSORS_AT_COMMIT);

			ResultSet result = stmt.executeQuery(SQLQueries.SELECT_STATEMENT);

			result.beforeFirst();
			while (result.next()) {
				// Retrieve by column name
				int newID = result.getInt("id") + 5;
				result.updateDouble("id", newID);
				result.updateRow();
				System.out.println(result.getInt("id"));
			}

			System.out.println("DBFetchService::forwardSet::data is successfully fetched");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertRow(Connection conn, Statement stmt) {

		try {
			System.out.println("DBFetchService::forwardSet::fetching data from table");
			// Here ResultSet.CONCUR_READ_ONLY cannot be used as we are updating
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.CLOSE_CURSORS_AT_COMMIT);

			ResultSet result = stmt.executeQuery(SQLQueries.SELECT_STATEMENT);
			System.out.println("Inserting a new record...");
			result.moveToInsertRow();
			result.updateInt("id",104);
			result.updateString("first","John");
			result.updateString("last","Paul");
			result.updateInt("age",40);
		      //Commit row
			result.insertRow();

			System.out.println("DBFetchService::forwardSet::data is successfully fetched");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
