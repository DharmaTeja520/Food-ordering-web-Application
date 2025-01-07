package com.tapfoods.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	private static final String URL = "jdbc:mysql://localhost:3306/online_food_delivery"; // Update with correct DB URL
	private static final String USER = "root"; // Replace with your DB username
	private static final String PASSWORD = "Dharma"; // Replace with your DB password

	public static Connection myConnect() {
		Connection con = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the MySQL driver is loaded
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} 
		catch (ClassNotFoundException e) 
		{
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			System.err.println("Database connection failed.");
			e.printStackTrace();
		}
		return con; // This will be null if the connection fails
	}
}
