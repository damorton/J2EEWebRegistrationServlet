package servlets;
/*
 * Enterprise Application Development
 * 
 * ICT in Education website and database
 * 
 * David Morton K00179391
 * 
 * Register.java 
 * 
 * Connects to the ICT database. It then takes the input from
 * the requesting HTML page and inserts the data in to the ICT database.
 * 
 * @author:	David Morton K00179391
 * @date:	27.3.2014	
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ConnectDB;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectDB database;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
	}

	/**
	 * doGet method is called from the HTML page that specifies GET as its
	 * method value.
	 * 
	 * @param request
	 *            - the request from the server
	 * @param response
	 *            - the response to the server
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		database = new ConnectDB(); // create a new connection to the database
		Statement stmt;
		try {
			stmt = (Statement) database.getConn().createStatement();

			// insert the data taken form the HTML page into the ICT database
			stmt.execute("INSERT INTO attendees VALUES(null, '"
					+ request.getParameter("Name") + "', '"
					+ request.getParameter("Phone") + "', '"
					+ request.getParameter("Email") + "')");
			System.out.println("Data inserted...closing database connection");

			database.getConn().close(); // close the connection to the database

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// display a HTML web page to the user informing them of a successful
		// registration
		response.setContentType("text/html"); // declare the type of file
		PrintWriter out = response.getWriter(); // get the print writer so we
												// can create the HTML page
		String title = "Registration"; // set the web page title

		// write the HTML code to a file and display the web page
		String docType = "<!doctype HTML>";
		out.println(docType + "<html>\n" + "<head><title>" + title
				+ "</title>\n</head>" + "<body>\n"
				+ "<h3>Registration complete.</h3>"
				+ "<a href='index.html'>Home</a>" + "</body></html>");

	}
}
