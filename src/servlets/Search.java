package servlets;
/*
 * Enterprise Application Development
 * 
 * ICT in Education web site and database
 * 
 * David Morton K00179391
 * 
 * Search.java 
 * 
 * Connects to the ICT database. Searches the data for a 
 * specific string if entered by the user. If not string is entered
 * will print all information contained in the ICT database.
 * 
 * @author:	David Morton K00179391
 * @date:	27.3.2014	
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.ResultSetMetaData;

import database.ConnectDB;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectDB database;
	private ResultSet result;
	private ResultSetMetaData rsmd;
	private Statement stmt;
	private String[] descriptors = { "ID\t\t", "Name\t\t", "Phone\t\t",
			"Email\t\t" };

	/**
	 * @throws SQLException
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() throws SQLException {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		database = new ConnectDB();

		try {
			stmt = (Statement) database.getConn().createStatement();
			String keywords = request.getParameter("SearchAtt");

			if (keywords != null) {
				result = stmt
						.executeQuery("select * from attendees where name like '%"
								+ keywords + "%'");
			} else {
				result = stmt.executeQuery("select * from attendees;");
			}

			rsmd = (ResultSetMetaData) result.getMetaData(); // result set meta data
															
			result.first(); // set the index to the first record

			int colCount = rsmd.getColumnCount(); // number of columns in the table
												
			String tableName = rsmd.getTableName(1); // table name
			response.setContentType("text/html");
			
			// create an html page with the results
			PrintWriter out = response.getWriter();
			String title = "Database Information";
			String docType = "<!doctype HTML>";
			out.println(docType
					+ "<html>\n"
					+ "<head><link rel='stylesheet' type='text/css' href='stylesheet.css'><link rel='shortcut icon' href='images/brand.png'><title>"
					+ title
					+ "</title>\n</head>"
					+ "<body><section class='wrapper'><section class='content'>\n"
					+ "<h3>Search complete</h3>"
					+ "<a href='index.html'>Home</a>");

			out.println("<pre><h2>" + tableName + " information</h2>");
			out.println("-----------------------------------------------------------------------");

			do {
				for (int i = 1; i <= colCount; i++) {
					String info = result.getString(i);
					out.print(descriptors[(i - 1)] + info + "\n");
				}
				out.println("-----------------------------------------------------------------------");

			} while (result.next());

			out.println("</pre></section></section></body></html>");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
