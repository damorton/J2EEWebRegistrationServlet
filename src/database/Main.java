/*
 * Enterprise Application Development
 * 
 * ICT in Education web site and database
 * 
 * David Morton K00179391
 * 
 * Main.java 
 * 
 * @author:	David Morton K00179391
 * @date:	27.3.2014	
 */


package database;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		System.out.println("Main...");
		// Connect to the database
		ConnectDB database = new ConnectDB();

		// Create the tables for the database
		@SuppressWarnings("unused")
		Tables tables = new Tables(database.getConn());
		
		// Close the connection to the database
		database.closeConnection();

	}

}
