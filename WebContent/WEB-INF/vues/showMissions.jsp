<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.projetsoap.srcfiles.Message" %>
<%@ page import="com.projetsoap.srcfiles.User" %>
<%@ page import="com.projetsoap.utils.DatabaseOperations" %>

<%
	User user = (User)request.getAttribute("user");
	long idUser = user.getId();
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MES MISSIONS</title>
</head>
<body>

<td>
	<tr>${user.getFirstName()}</tr></br>
	<tr>${user.getLastName()}</tr></br>
	<tr>${user.getMail()}</tr></br>
	<tr>${user.getPhone()}</tr></br>
	<tr>${user.getIdZone()}</tr></br>
	<p>Mission en cours :</p>
</td>


</body>
</html>