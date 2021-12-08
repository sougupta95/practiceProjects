package com.studenttracker.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Define datasource/connection pool for Resource injection
	
	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Setting up printWriter
		PrintWriter out= response.getWriter();
		response.setContentType("text/plain");
		
		//Get connection to DB
		Connection conn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			conn = dataSource.getConnection();
			//Create SQL Statement
			String sql = "select * from student";
			myStmt = conn.createStatement();
			
			//Execute SQL Statement
			myRs = myStmt.executeQuery(sql);
			
			//Process the resultset
			while(myRs.next()) {
				String email = myRs.getString("email");
				out.println(email);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
			
	}

}
