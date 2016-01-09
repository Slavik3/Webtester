<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Admin index page</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  	<style type="text/css">
   	nav { 
    width: 100%;
   }
  </style>
  <link rel="stylesheet" type="text/css" media="screen" href="style/style.css"/> 
</head>
<body>
<!-- <a href="j_spring_security_logout">Logout</a> -->
<%@ include file="../../jspf/top.jspf" %>
<p>Hello ${user.login}</p>

<!-- ${listAccaunt} -->

<table>
    <tr>
        <th width="80">Person ID</th>
        <th width="120">Person login</th>
        <th width="120">Person email</th>
        <th width="60">Active</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listAccaunt}" var="accaunt">
        <tr>
            <td><c:out value="${accaunt.id}"/> </td>
            <td><c:out value="${accaunt.login}"/> </td>
            <td><c:out value="${accaunt.email}"/> </td>
            <td>
            	<a href="<c:url value='/isActive/${accaunt.id}' />" > <c:out value="${accaunt.isActive}"/></a>
            </td>
            <td><a href="<c:url value='/editUserPage/${accaunt.id}' />" >Edit</a></td>
            <td><a href="<c:url value='/remove/${accaunt.id}' />" >Delete</a></td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>