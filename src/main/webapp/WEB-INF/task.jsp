<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/task.css">
<title>Insert title here</title>
</head>


<body>

	<h1>Task: <c:out value="${task.task}"></c:out></h1>
	
	<h3>Assignee: <c:out value="${task.creator.getName()}"></c:out></h3>
	<h3>Creator: <c:out value="${task.assignee.getName()}"></c:out></h3>
	<h3>Priority: <c:out value="${task.priority}"></c:out></h3>


	<a href="/tasks/${task.id}/edit">Edit</a>
	
	<form action="/tasks/delete/${task.id}" method="POST">
	<input type="hidden" name="_method" value="delete">
	<input type="submit" value="delete">
	</form>
	<a href="/tasks">Home</a>
	
</body>
</html>