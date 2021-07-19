<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/homePage.css">
<title>Insert title here</title>
</head>


<body>

	<h1>Welcome, <c:out value="${user.name}" /></h1>
	
	<a href="/logout">Logout</a><body>
	
	<table>
	    <thead>
	        <tr>
	            <th>Task</th>
	            <th>Creator</th>
	            <th>Assignee</th>
	            <th>Priority</th>
	        </tr>
	    </thead>
	    <tbody>
	       <c:forEach items="${tasks}" var="t">
	        <tr>
	            <td> <a href="/tasks/${t.id}"><c:out value="${t.task}"/></a></td>
				<td><c:out value="${t.creator.getName()}"/></td>
 				<td><c:out value="${t.assignee.getName()}"/></td>
				<td><c:out value="${t.priority}"/></td>
	            
	        </tr>        
	        </c:forEach>
	    </tbody>
	</table>

	<a href="tasks/new">Create Task</a>

</body>
</html>