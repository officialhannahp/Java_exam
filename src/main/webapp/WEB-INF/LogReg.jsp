<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/LogReg.css">
<title>Insert title here</title>
</head>


<body>
	<div class="loginbox">
		<h1>Login</h1>
		<p><c:out value="${error}" /></p>
	    <form method="post" action="/loginprocess">
	        <p>
	            <input class="input" type="text" id="email" name="email" placeholder="E-mail"/>
	        </p>
	        <p>
	            <input class="input" type="password" id="password" name="password" placeholder="Password"/>
	        </p>
	        <input class="submit" type="submit" value="Login"/>
	    </form>    	
	</div>


	<div class="regbox">
		<h1>Register</h1>   
	    <p><form:errors path="user.*"/></p>    
	    <form:form method="POST" action="/registerUser" modelAttribute="user">
	    	<p>
	            <form:input class="input" type="text" path="name" placeholder="Name"/>
	        </p>
	        <p class="email">
	          <form:input class="input" type="email" path="email" placeholder="E-mail"/>
	        </p>
	        <p>
	            <form:password class="input" path="password" placeholder="Password"/>
	        </p>
	        <p>
	            <form:password class="input" path="passwordConfirmation" placeholder="Password Confirmation"/>
	        </p>
	        <input class="submit" type="submit" value="Register"/>
	    </form:form>
	</div>    
    
</body>
</html>