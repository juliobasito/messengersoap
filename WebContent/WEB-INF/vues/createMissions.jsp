<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="?action=uploadMission" method="POST">
	Titre : <input type="text" name="Title"></br>
	N° Camion : <input type="text" name="Id_Truck"></br>
	Description : <input type="text" name="Content"></br>
	Lieu Départ : <input type="text" name="Start"></br>
	Lieu arrivée : <input type="text" name="End"></br>
	Id départ : <input type="text" name="Id_Start"></br>
	ID arrivée : <input type="text" name="Id_End"></br>
	N° chargement : <input type="text" name="ShipmentCode"></br>
	<input type="submit" value="Envoyer">
</form>
	

</body>
</html>