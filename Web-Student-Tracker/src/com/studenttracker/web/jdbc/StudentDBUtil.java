package com.studenttracker.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {
	
	private DataSource dataSource;
	
	public StudentDBUtil(DataSource dataSource) {
		this.dataSource =dataSource;
	}
	
	public List<Student> getStudents() throws Exception{
			List<Student> students = new ArrayList<Student>();
			
			Connection conn = null;
			Statement myStmt = null;
			ResultSet myRs = null;
			
			try{
				conn = dataSource.getConnection();
				
				String sql = "select * from Student order by last_name";
				
				myStmt = conn.createStatement();
				
				myRs = myStmt.executeQuery(sql);
				
				while(myRs.next()) {
					int id = myRs.getInt("id");
					String firstName = myRs.getString("first_name");
					String lastName = myRs.getString("last_name");
					String email = myRs.getString("email");
					
					Student tempStudent = new Student(id,firstName,lastName,email);
					
					students.add(tempStudent);
					
				}
				
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				close(conn,myStmt,myRs);
				
			}
			
			
			return students;
	}

	private void close(Connection conn, Statement myStmt, ResultSet myRs) {
		try {
			if(myRs != null) myRs.close();
			if(myStmt != null) myStmt.close();
			if(conn!= null) conn.close();
		}catch(Exception ex) {
			
		}
		
	}

	public void addStudent(Student theStudent) {
		//create sql for insert
		String sql = "insert into student "
				+"(first_name,last_name,email) "
				+"values(?,?,?)";
		//set param values
		try(Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			stmt.setString(1, theStudent.getFirstName());
			stmt.setString(2, theStudent.getLastName());
			stmt.setString(3, theStudent.getEmail());
			
			stmt.execute();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		//execute the sql
		
	}

	public Student getStudent(String id) {
		String sql = "select * from Student where id=?";
		
		ResultSet myRs = null;
		
		try(Connection conn = dataSource.getConnection();
				PreparedStatement myStmt = conn.prepareStatement(sql);){
			
			
			myStmt.setInt(1,Integer.parseInt(id)); 
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				Student tempStudent = new Student(Integer.parseInt(id),firstName,lastName,email);
				
				return tempStudent;
				
			}
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			close(null,null,myRs);
			
		}
		return null;
		
	}

	public void updateStudent(Student theStudent) {
		String sql = "update Student set first_name=?, last_name=?, email=? where id=?";
		
		try(Connection conn = dataSource.getConnection();
				PreparedStatement myStmt = conn.prepareStatement(sql);){
			
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4,theStudent.getId()); 
			
			int rows = myStmt.executeUpdate();	
			System.out.println("Total rows update= "+rows);
						
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
	}

	public void deleteStudent(int id) {
		String sql = "delete from Student where id=?";
		
		try(Connection conn = dataSource.getConnection();
				PreparedStatement myStmt = conn.prepareStatement(sql);){
			
			myStmt.setInt(1,id); 
			
			int rows = myStmt.executeUpdate();
			System.out.println("Total rows deleted= "+rows);
						
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
		
	}

	public List<Student> searchStudents(String theSearchName) {
		List<Student> students = new ArrayList<>();
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int studentId;
        
        try {
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            //
            // only search by name if theSearchName is not empty
            //
            if (theSearchName != null && theSearchName.trim().length() > 0) {
                // create sql to search for students by name
                String sql = "select * from student where lower(first_name) like ? or lower(last_name) like ?";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
                myStmt.setString(2, theSearchNameLike);
                
            } else {
                // create sql to get all students
                String sql = "select * from student order by last_name";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");
                
                // create new student object
                Student tempStudent = new Student(id, firstName, lastName, email);
                
                // add it to the list of students
                students.add(tempStudent);            
            }
            
            return students;
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
        return null;
	}

	

}
