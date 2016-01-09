<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Восстановление пароля</title>
	<link rel="stylesheet" type="text/css" href="http://getbootstrap.com/dist/css/bootstrap.css"> 
	<link rel="stylesheet" type="text/css" media="screen" href="style/style.css"/> 
</head>
<body>
<form:form method="GET" commandName="user" action="passwordRecovery" class="form-signin">
	<form:label path="login">login:</form:label>
	<form:input path="login" id="login" class="form-control form-control-inline" size="10"/>
	<br>
	<input type="submit" value="login" class="btn btn-primary btn-mini">
</form:form>
</body>
</html>