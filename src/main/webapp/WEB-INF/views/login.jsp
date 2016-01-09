<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
 <link rel="stylesheet" type="text/css" href="http://getbootstrap.com/dist/css/bootstrap.css"> 
 <link rel="stylesheet" type="text/css" media="screen" href="../style/st4.css"/>  
 <style type='text/css'>
    body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}
.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
  </style>
  
<script type="text/javascript">
	<%@ include file="/js/validation.js"%>
</script>
</head>
<body>

	<form method="POST"
		action="<%=request.getContextPath()%>/j_spring_security_check"
		class="box login">


		<fieldset class="boxBody">


			<label> Username </label> <input type='text' name='user_login'
				value=''> <label> Password </label> <input type='password'
				name='password_login' />

			<c:if test="${not empty error}">
				<div class="error" style="text-align:right;">${error}</div>
			</c:if>


		</fieldset>



		<footer> <input name="_spring_security_remember_me"
			type="checkbox" class="checkAdmin" /> <label for="remember_me">Запомнить</label>




		<input type="submit" class="btnLogin" value="Вход"> </footer>


	</form>





 <a href="${context }/fbLogin"> <img alt="fbLogin" src="${context }/resources/images/login-facebook.png" /></a>

<c:if test="${not empty error}">
    <div>          
        user with such login / password was not found
    </div>
</c:if>

</body>
</html>