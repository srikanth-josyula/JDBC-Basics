package com.sample.jdbc.sql;

public class SQLQueries {

	public static final String CREATE_DB_QUERY = "CREATE DATABASE STUDENTS";
	public static final String CREATE_TABLE_QUERY = "CREATE TABLE REGISTRATION " + "(id INTEGER not NULL, "
			+ " first VARCHAR(255), " + " last VARCHAR(255), " + " age INTEGER, " + " PRIMARY KEY ( id ))";
	public static final String INSERT_STATEMENT = "INSERT INTO Registration " + "VALUES (110, 'Bhatt', 'Alia', 18)";
	public static final String INSERT_PREPSTATEMENT = "INSERT INTO registration (id, first, last, age) "
			+ "VALUES(?, ?, ?, ?)";
	public static final String UPDATE_STATEMENT = "UPDATE Registration " + "SET age = 27 WHERE id = 100";
	public static final String SELECT_STATEMENT = "SELECT id, first, last, age FROM Registration";
}
