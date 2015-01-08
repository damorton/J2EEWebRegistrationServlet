
	Enterprise Application Development

	The ICT registration website utilises a Java/J2EE application that stores registrar details in a MySQL database.
	
	Database Configuration and Project launch
	-----------------------------------------
	
	1. Configure the database connection in the ConnectDB.java file.
	
	e.g.
	
		// server configuration settings
		private String url = "jdbc:mysql://localhost:3306/"; 	// the database location/url
		private String dbName = "DATABASE_NAME"; 		
		private String user = "USERNAME"; 						
		private String password = "PASSWORD";					
	
	2. Run Main.java
	
		This will create the database used for the web site registration information.
	
	3. Run the project on the Tomcat 7 server.
	
		Select the project in the package explorer pane.
	
		Go to Run -> Run As -> Run on server.
	
		This will run the project on the server and display the 
		index.html file of the project.
	
	END
	

