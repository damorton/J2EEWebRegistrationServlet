package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	
	// server configuration settings
	private String url = "jdbc:mysql://localhost:3306/";
	private String dbName = "ict_database";	
	private String user = "admin";
	private String password = "password";
	
	private Connection conn = null; // connection to the ICT database
	
	private Statement stmt;
	
	public ConnectDB(){
		
		System.out.println("ConnectDB...");
		try{
			// setup connection
			Class.forName("com.mysql.jdbc.Driver"); // register the driver for the server
			conn = DriverManager.getConnection(url, user, password); // connect to the MySQL server
			System.out.println("Connected");
			stmt = (Statement) conn.createStatement(); 
			stmt.execute("CREATE DATABASE IF NOT EXISTS " + dbName + ";"); // create the ICT database 
			System.out.println("...created database if it dosent exist.");
			conn = DriverManager.getConnection(url+dbName, user, password); // connect to the ICT database

		}catch(Exception e){
			e.printStackTrace();
		}		
			
	}
	
	public Connection getConn(){
		return conn;
	}

	public void closeConnection() throws SQLException{
		conn.close();
	}
}
