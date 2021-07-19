<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<%@ page isErrorPage="true" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/edit.css">
<title>Insert title here</title>
</head>


<body>

	<h1>Edit <c:out value="${editTask.task}"/></h1>
	
	<form:form action="/tasks/${editTask.id}/edit" method="POST" modelAttribute="editTask">
	<form:input type="hidden" path="creator" value="${user.id}"></form:input> 
 		<p>
			<form:label path="task">Task:  </form:label>
			<form:input path="task"/>
			<form:errors path="task"/>
		</p>
		
		<p>
		<form:label path="assignee">Assignee</form:label>
		<form:select path="assignee">
			<c:forEach items="${users}" var="user">
				<c:if test="${user.name != creator.name}">
 					<form:option value="${user}"><c:out value="${user.name}"/></form:option>
 				</c:if>
			</c:forEach>	
		</form:select>
		<form:errors path="assignee"/>
		</p>
		
		
	
	<p>
		<form:label path="priority">Priority:  </form:label>
		<form:select path="priority">
			<form:option value="low">Low</form:option>
			<form:option value="medium">Medium</form:option>
			<form:option value="high">High</form:option>
		</form:select>
		<form:errors path="priority" />
	</p>
	
	<input type="submit" value="Edit">

	</form:form>
<a href="/tasks">Home</a>




</body>
</html>