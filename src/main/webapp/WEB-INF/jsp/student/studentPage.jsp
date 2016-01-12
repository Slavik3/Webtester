<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>student</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css" media="screen" href="style/style.css"/> 
</head>
<body>
<%@ include file="../../jspf/top.jspf" %>
Hello student
${url}

	<c:forEach items="${listTest}" var="test">
       <a href="<c:url value='/passTheTest/${test.id}' />" > <c:out value="${test.name}"/> </a> 
       <br>     
        
    </c:forEach>

</body>
</html>