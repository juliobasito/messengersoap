<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% long missionId = (long)request.getAttribute("missionId"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="?action=uploadMessage" method="POST">
	Id camioneur : <input type="text" name="Id_camioneur"></br>
	Niveau d'importance : <input type="text" name="Type"></br>
	Mission Id : <input type="text" name="Id_Mission" value="<%=missionId %>"></br>
	<input type="submit" value="Envoyer">
</form>
</body>
</html>