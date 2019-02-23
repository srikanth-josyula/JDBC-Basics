package com.sample.jdbc;

import com.sample.jdbc.utils.ConnectionManager;
import com.sample.jdbc.utils.DBUtils;

public class App {
	public static void main(String[] args) {
		ConnectionManager db = ConnectionManager.getInstance();
		DBUtils cdb = new DBUtils();
		if(db.checkConnection() == true) {
			cdb.processExecute();
		}
	}
}
