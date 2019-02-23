package com.sample.jdbc.exceptions;

import java.sql.SQLException;

public class Exceptions {

	public static void processException(SQLException e) {
		System.err.println("DBExceptions::processException::SQLState: " + ((SQLException) e).getSQLState());
		System.err.println("DBExceptions::processException::Error Code: " + ((SQLException) e).getErrorCode());

		if (((SQLException) e).getNextException() != null) {
			System.err.println("DBExceptions::processException::Message: " + e.getNextException());
		} else {
			System.err.println("DBExceptions::processException::Message: " + e.getMessage());
		}
	}

}
