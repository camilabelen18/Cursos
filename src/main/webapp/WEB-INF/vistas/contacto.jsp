<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Contacto</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/contacto.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>

	<%@ include file="partial/header.jsp"%>
	
	<!-- CONTACTO -->
	<div class="cont-contacto" id="cont-contacto">
		<h1>Contacto</h1>
		<form action="#" class="formulario">
			<div class="nom-cor">
				<input type="text" name="nombre" id="nombre" placeholder="Nombre:">
				<input type="text" name="correo" id="correo" placeholder="Correo:">
			</div>
			<div class="mensaje">
				<textarea name="mensaje" id="mensaje" cols="20" rows="10"
					placeholder="Mensaje:"></textarea>
			</div>
			<div class="txt-btn">
				<p>*Te responderemos a la brevedad por correo electrónico</p>
				<input type="submit" value="Enviar">
			</div>
		</form>
	</div>

	<%@ include file="partial/footer.jsp"%>

</body>
</html>