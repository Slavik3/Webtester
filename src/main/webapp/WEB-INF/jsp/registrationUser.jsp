<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>registrationUser</title>
	<link rel="stylesheet" type="text/css" href="http://getbootstrap.com/dist/css/bootstrap.css"> 
	<link rel="stylesheet" type="text/css" media="screen" href="style/style.css"/> 
	<script type="text/javascript" src="jquery/jquery.js"></script>
	<script src="jquery/scripts.js" type="text/javascript"></script>
	
</head>
<body>
<form:form method="POST" commandName="user" action="registration" class="form-signin">
	<form:label path="login">login:</form:label>
	<form:input path="login" class="form-control form-control-inline" size="10"/>
	<br>	
	<form:label path="password">Password:</form:label>
	<form:password path="password" class="form-control" size="10"/>
	<br>
	<form:label path="email">email:</form:label>
	<form:input path="email" class="form-control form-control-inline" size="10"/>
	<br>
	<br>
	<input type="submit" value="Registration" class="btn btn-primary btn-mini">
</form:form>

<c:if test="${not empty userExist}">
<div id="coolmenu" align="center" class="errorMsg">		
	${userExist}
</div>
</c:if>

</body>
</html>