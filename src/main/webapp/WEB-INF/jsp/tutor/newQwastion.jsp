<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<form method="GET" action="addQwastion" class="form-signin">
	
	<label>Вопрос к тесту:</label>
	<input name="qwastionName" class="form-control form-control-inline" size="10"/>
	<br>
	
	
	<label>Ответ1:</label>
	<input name="answer1" class="form-control form-control-inline" size="10"/>
	<input type="radio" name="correctAnswer" value="1">
	<br><br>
	<label>Ответ2:</label>
	<input name="answer2" class="form-control form-control-inline" size="10"/>
	<input type="radio" name="correctAnswer" value="2">
	<br><br>
	<label>Ответ3:</label>
	<input name="answer3" class="form-control form-control-inline" size="10"/>
	<input type="radio" name="correctAnswer" value="3">
	<br><br>
	<label>Ответ4:</label>
	<input name="answer4" class="form-control form-control-inline" size="10"/>
	<input type="radio" name="correctAnswer" value="4">
	<br>	
	<br>
	<br>
	<input type="submit" value="Add" class="btn btn-primary btn-mini">
</form>
<form method="GET" action="completeCreationTestAndExit" class="form-signin">
<input type="submit" value="Завершить создание теста и выйти" class="btn btn-primary btn-mini">
</form>


</body>
</html>