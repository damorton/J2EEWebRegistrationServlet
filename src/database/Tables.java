/*
 * Enterprise Application Development
 * 
 * ICT in Education web site and database
 * 
 * David Morton K00179391
 * 
 * Tables.java 
 * 
 * @author:	David Morton K00179391
 * @date:	27.3.2014	
 */
package database;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {
	
	/*
	 * Constructor for tables class
	 * 
	 * @param Connection conn - the connection to the database
	 */
	public Tables(Connection conn) throws SQLException{
		System.out.println("Tables...");

		Statement stmt = (Statement) conn.createStatement();
		
		// Create the game table
		stmt.execute("CREATE TABLE IF NOT EXISTS attendees(att_id INTEGER NOT NULL AUTO_INCREMENT, name VARCHAR(40) NOT NULL UNIQUE, phone_number VARCHAR(40) NOT NULL, email_address VARCHAR(30) NOT NULL," +
				"CONSTRAINT attPK PRIMARY KEY(att_id))");
			
		// Create the player table
		stmt.execute(
				"CREATE TABLE IF NOT EXISTS presenters(pres_id INTEGER NOT NULL AUTO_INCREMENT, name VARCHAR(40) NOT NULL UNIQUE, phone_number VARCHAR(40) NOT NULL, email_address VARCHAR(30) NOT NULL," +
				"workshop_title VARCHAR(30) NOT NULL, twitter VARCHAR(30) NOT NULL, biography VARCHAR(1000) NOT NULL, abstract VARCHAR(1000) NOT NULL," +		
				"CONSTRAINT presPK PRIMARY KEY(pres_id))");		
	}
}
