package com.util;
/*
for every database operation one this we need and that is
connection so we create static method (createConnection)
which return connection

*/
import java.sql.Connection;
import java.sql.DriverManager;

public class StudentUtil {

	public static Connection createConnection()
	{
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/acute", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
