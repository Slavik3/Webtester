<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>qwastion</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<style type='text/css'>
		.form-signin {
  		max-width: 330px;
  		padding: 15px;
  		margin: 0 auto;
		}
  	</style>
	<link rel="stylesheet" type="text/css" media="screen" href="style/style.css"/> 
</head>
<body>
<%@ include file="../../jspf/top.jspf" %>
	111
	<form method="GET" action="/Webtester/nextTestQwastion" class="form-signin">
		
			${qwastion}
			<br>
			<c:forEach items="${listAnswer}" var="answer">
				<input type="radio" name="correctAnswer" value="${answer.id}"> ${answer.name} <br>
			</c:forEach>
		
		<c:if test="${not empty next}">
			<input type="submit" value="${next}" class="btn btn-primary btn-mini">
			<INPUT TYPE="button" onClick="history.go(0)" VALUE="Refresh">
		</c:if>
		<c:if test="${not empty end}">
			<input type="submit" value="${end}" class="btn btn-primary btn-mini">
		</c:if>
	</form>


<br>

    
    
</body>
</html>