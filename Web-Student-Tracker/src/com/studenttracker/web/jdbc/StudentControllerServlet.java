package com.studenttracker.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDBUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;

	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			studentDbUtil = new StudentDBUtil(dataSource);
		}catch(Exception ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		command = (command != null && !command.isEmpty())? command: "LIST";
		
		switch(command) {
		case "LIST":
			listStudents(request,response);
			break;
		//case "ADD":
			//addStudent(request,response);
			//break;
		case "LOAD":
			loadStudent(request,response);
			break;
		case "DELETE":
			deleteStudent(request,response);
			break;
	//	case "UPDATE":
		//	updateStudent(request,response);
			//break;
		default:
			listStudents(request,response);
			break;
		}
		 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = req.getParameter("command");
		command = (command != null && !command.isEmpty())? command: "LIST";
		
		switch(command) {
		case "LIST":
			listStudents(req,resp);
			break;
		case "ADD":
			addStudent(req,resp);
			break;
		case "LOAD":
			loadStudent(req,resp);
			break;
		case "UPDATE":
			updateStudent(req,resp);
			break;
		case "SEARCH":
			searchStudent(req,resp);
			break;
		default:
			listStudents(req,resp);
			break;
		}
	}

	private void searchStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String theSearchName = req.getParameter("theSearchName");
        
        // search students from db util
        List<Student> students = studentDbUtil.searchStudents(theSearchName);
        
        // add students to the request
        req.setAttribute("Student_List", students);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = req.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(req, resp);
		
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("studentId"));
		studentDbUtil.deleteStudent(id);
		response.sendRedirect("StudentControllerServlet");
		//request.removeAttribute("command");
		//request.removeAttribute("studentId");
		//listStudents(request,response);		
	}

	private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("studentId"));
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		
		Student theStudent = new Student(id,firstName,lastName,email);
		
		studentDbUtil.updateStudent(theStudent);
		resp.sendRedirect("StudentControllerServlet");
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("studentId");
		Student theStudent = studentDbUtil.getStudent(id);
		request.setAttribute("The_Student", theStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student theStudent = new Student(firstName,lastName,email);
		studentDbUtil.addStudent(theStudent);
		request.setAttribute("command", null);
		response.sendRedirect("StudentControllerServlet");
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			List<Student> students = studentDbUtil.getStudents();
			request.setAttribute("Student_List", students);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
	}

}
