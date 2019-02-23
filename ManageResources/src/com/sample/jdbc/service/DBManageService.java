package com.sample.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import com.sample.jdbc.constants.DBConstants;
import com.sample.jdbc.constants.DBType;
import com.sample.jdbc.exceptions.Exceptions;
import com.sample.jdbc.sql.SQLQueries;
import com.sample.jdbc.utils.ConnectionManager;

public class DBManageService {

	public static final String TABLENAME = DBConstants.POSTGREDB;
	HashMap<Integer, String> hmap = new HashMap<Integer, String>();
	Random random = new Random();

	public void processExecute() {
		Statement stmt = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		hmap.put(400, "Ranbir_Kapoor");
		hmap.put(401, "Shahid_Kapoor");

		try {
			conn = ConnectionManager.getConnection(DBType.POSTGREDB);
			System.out.println("DBManageService::processExecute::Inserting records into the table");

			/**
			 * Normal Statement creation and execution for single
			 */
			stmt = conn.createStatement();
			stmt.executeUpdate(SQLQueries.INSERT_STATEMENT);
			
			/**
			 * Normal Statement creation and execution for Batch
			 */
			pstmt = conn.prepareStatement(SQLQueries.INSERT_PREPSTATEMENT);
			conn.setAutoCommit(false);

			for (Entry<Integer, String> mentry : hmap.entrySet()) {
				pstmt.setInt(1, (int) mentry.getKey());
				pstmt.setString(2, mentry.getValue().toString().split("_")[0]);
				pstmt.setString(3, mentry.getValue().toString().split("_")[1]);
				pstmt.setInt(4, (random.nextInt(30 - 20) + 20));

				pstmt.addBatch();

			}

			int[] count = pstmt.executeBatch();

			System.out.println("DBManageService::processExecute::successfully inserted " + count.length + " records");

			conn.commit();

			stmt.close();
			pstmt.close();
			conn.close();

			System.out.println("DBManageService::processExecute::successfully action performed");

		} catch (SQLException e) {
			Exceptions.processException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.err.println("DBManageService::processExecute::Message: " + e.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("DBManageService::processExecute::Message: " + e.getMessage());
				}
			}
		}

	}

}
