<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>RegistroUsuario</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/registroUsuario.css">
</head>
<body>

<%@ include file="header.jsp" %>
<div id = "main">

	<form action="usuarioRegistrado" method = "POST">
		<label for="nombreUsuario">Nombre de usuario *</label> 
		<br>
		<input id="nombreUsuario" type="text" name="nombreUsuario" required>
		<br><br><br>
		<label for="emailUsuario">Direccion de correo electronico *</label>
		<br>
		<input id="emailUsuario" type="text" name="emailUsuario" required>
		<br><br><br>
		<label for="pass1">Contraseña *</label>
		<br>
		<input id="pass1" type = "text" name="pass1" required>
		<br><br><br>
		<label for="pass2">Repita la contraseña *</label>
		<br>
		<input id="pass2" type = "text" name="pass2" required>
		<br><br><br>
		<label for="nroTarjeta">Número de tarjeta *</label>
		<br>
		<input id="nroTarjeta" type = "number" name="nroTarjeta" required>
		<br><br><br>
		<input id="registrar"type="submit" value="Registrarse">	
	</form>
	
</div>

</body>
</html>