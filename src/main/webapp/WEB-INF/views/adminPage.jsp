<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Admin index page</title>
	<style type='text/css'>
    body {  	
 	padding-bottom: 40px;
  	background-color: #eee;
}
  </style>
  
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  
  <style type="text/css">
   nav { 
    width: 100%;
   
   }
  </style>
  
</head>
<body>
<%@ include file="jspf/top.jspf" %>
Hello admin
<p>Hello ${user.login}</p>

<!-- ${listAccaunt} -->

<table>
    <tr>
        <th width="80">Person ID</th>
        <th width="120">Person login</th>
        <th width="120">Person password</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listAccaunt}" var="accaunt">
        <tr>
            <td><c:out value="${accaunt.idAccaunt}"/> </td>
            <td><c:out value="${accaunt.login}"/> </td>
            <td><c:out value="${accaunt.password}"/> </td>
            <td><a href="<c:url value='/editUserPage/${accaunt.idAccaunt}' />" >Edit</a></td>
            <td><a href="<c:url value='/remove/${accaunt.idAccaunt}' />" >Delete</a></td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>