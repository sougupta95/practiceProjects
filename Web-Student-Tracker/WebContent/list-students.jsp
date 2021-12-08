<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<html>
	<head>
		<title>Student Tracker App</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
	</head>
	
	<body>
		<div id ="wrapper">
			<div id="header">
				<h2>Demo University</h2>
			</div>
		</div>
		
		<div id="container">
			<div id= "content">
			
				<!-- Add Button -->
				<input type="button" value="Add Student"
					onclick="window.location.href='add-student-form.jsp';return false;"
					class="add-student-button"
					/>
				<form action="StudentControllerServlet" method="POST">
        
	                <input type="hidden" name="command" value="SEARCH" />
	            
	                Search student: <input type="text" name="theSearchName" />
	                
	                <input type="submit" value="Search" class="add-student-button" />
            
            	</form>
            	<div style="clear: both;"></div>
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email Address</th>
						<th>Action</th>
					</tr>
					<c:forEach var="tempStudent" items="${Student_List}">
						<c:url var="tempLink" value="StudentControllerServlet">
							<c:param name="command" value="LOAD"/>
							<c:param name="studentId" value="${tempStudent.id}"/>
						</c:url>
							
						<c:url var="deleteLink" value="StudentControllerServlet">
							<c:param name="command" value="DELETE"/>
							<c:param name="studentId" value="${tempStudent.id}"/>
						</c:url>
						
						<tr>
							<td>${tempStudent.firstName}</td>
							<td>${tempStudent.lastName}</td>
							<td>${tempStudent.email}</td>
							<td>
								<a href="${tempLink}">Update</a>
								 | 
								<a href="${deleteLink}"
									onclick="if(!(confirm('Are you sure you want to delete?'))) return false">
									Delete</a>
							</td>
						</tr>
					</c:forEach>					
				</table>
			</div>
		
		</div>
	</body>
</html>