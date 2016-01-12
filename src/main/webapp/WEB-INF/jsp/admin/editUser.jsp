<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type='text/css'>
	.form-signin {
  	max-width: 330px;
  	padding: 15px;
  	margin: 0 auto;
	}
  </style>
	<title>Edit User</title>
	<link rel="stylesheet" type="text/css" href="http://getbootstrap.com/dist/css/bootstrap.css"> 
	<link rel="stylesheet" type="text/css" media="screen" href="style/style.css"/> 
</head>
<body>
<%@ include file="../../jspf/top.jspf" %>
<form method="GET" action="/webtester/editUser" class="form-signin">
	<input value="${user.id}" name="idAccaunt" type="hidden"/>
	<label>login:</label>
	<input value="${user.login}" name="login" class="form-control form-control-inline" size="10"/>
	<br>	
	<label>Email:</label>
	<input value="${user.email}" name="email" class="form-control" size="10"/>
	<br>
	<br>
	<input type="submit" value="Edit" class="btn btn-primary btn-mini">
</form>
</body>
</html>