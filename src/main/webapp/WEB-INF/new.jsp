<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/new.css">
<title>Insert title here</title>
</head>


<body>

	<h1>Create a new task</h1>
	
	<form:form action="/tasks/new" method="POST" modelAttribute="new">
	<p>
		<form:label path="task">Task</form:label>
		<form:input path="task" />
	</p>
	
	<p>
		<form:label path="assignee">Assignee</form:label>
		<form:select path="assignee">
			<c:forEach items="${users}" var="user">
				<c:if test="${user.name != currentUser.name}">
					<form:option value="${user}"><c:out value="${user.name}"/></form:option>
				</c:if>
			</c:forEach>	
		</form:select>
	</p>
	
	<p>
		<form:label path="priority">Priority</form:label>
		<form:select path="priority">
			<form:option value="low">Low</form:option>
			<form:option value="medium">Medium</form:option>
			<form:option value="high">High</form:option>
		</form:select>
	</p>
	
	<input type="submit" value="Create">

</form:form>

<a href="/tasks">Home</a>

</body>
</html>