package com.caffein.common;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class OracleJDBCConnectionTest {
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:51521:XE";
	private static final String USER = "hr";
	private static final String PASSWORD = "1234";
	
	@Test
	public void testConnection() throws Exception {
		Class.forName(DRIVER);
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
