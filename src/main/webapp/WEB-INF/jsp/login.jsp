<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page import="ua.kharkov.sourceit.social.facebook.FBConnection"%>
<%
	FBConnection fbConnection = new FBConnection();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
 <link rel="stylesheet" type="text/css" href="http://getbootstrap.com/dist/css/bootstrap.css"> 
 <link rel="stylesheet" type="text/css" media="screen" href="style/style.css"/>  
 <style type='text/css'>
   
.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;"src/main/webapp/WEB-INF/jsp/index.jsp"
}
  </style>
  
<script type="text/javascript">
	<%@ include file="/js/validation.js"%>
</script>
<script type="text/javascript" src="jquery/jquery.js"></script>
<script src="jquery/scripts.js" type="text/javascript"></script>
</head>
<body>
<form:form method="POST" class="form-signin" name='frm' action="j_spring_security_check" ONSUBMIT="return validateForm()">
	
	<label for="j_username">login</label>
	<input id="login" name="j_username" type="text" class="form-control"/>
	<br>	
	<label for="j_password">Password</label>
	<input id="password" name="j_password" type="password" class="form-control"/>
	
	Role
	<select name="idRole">
	        		<option value="0">--- Select ---</option>
	        		<c:forEach var="role" items="${roles }">
	        			<option value="${role.idRole }">${role.name }</option>
	        		</c:forEach>
	        	</select>
	
	<a href="/Webtester/passwordRecoveryPage">Забыли пароль?</a>
	<br><br>
	
	<br>
	<input type="submit" value="login" class="btn btn-primary btn-mini">
</form:form>
<form:form method="POST" action="registrationPage" class="form-signin">
	<input type="submit" value="Registration" class="btn btn-primary btn-mini">
</form:form>

<!-- 
<label for="j_remember">Remember Me</label>
<input id="j_remember" name="_spring_security_remember_me" type="checkbox" />
  -->       

 <!-- <a href="${context }/fbLogin"> <img alt="fbLogin" src="images/login-facebook.png" /></a> -->
 
 <div align="center"><a href="<%=fbConnection.getFBAuthUrl()%>"> <img src="images/login-facebook.png" /> </a></div>

<c:if test="${not empty error}">
		<span class="error">${error}</span>
	</c:if>


<c:if test="${not empty userAdded}">
<div id="coolmenu" align="center" class="successMsg">		
	${userAdded}
</div>
</c:if>

<c:if test="${not empty ConfirmEmail}">
<div id="coolmenu" align="center" class="successMsg">		
	${ConfirmEmail}
</div>
</c:if>


</body>
</html>