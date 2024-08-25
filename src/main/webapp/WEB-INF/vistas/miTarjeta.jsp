<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Mi Tarjeta</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
	<link rel="stylesheet" href="css/miTarjeta.css">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>
	<%@ include file="partial/header.jsp"%>

	<div class="tituloTarjeta">
		<h1>Mi tarjeta de puntos</h1>
	</div>
	<div class="contenedor-miTarjeta">
		<div id="contMiTarjeta-1">
			<div id="tarjeta">
				<h2>${usuario.nombre}</h2>
				<div class="datosTarjeta">
					<div>
						<p>Mis puntos</p>
						<h3>${tarjeta.misPuntos}</h3>
					</div>
					<div>
						<p>Saldo actual</p>
						<h3>$${tarjeta.saldoActual}</h3>
					</div>
					<div>
						<p>Número de tarjeta</p>
						<h3>${tarjeta.numTarjeta}</h3>
					</div>
				</div>
			</div>
		</div>
		<div id="contMiTarjeta-2">
			<h3 style="margin-bottom: 20px;">Regala puntos a otro usuario</h3>
			<div class="regalarPuntos">
				<form:form action="enviarPuntos" method="POST" onsubmit="return validar()">
					<label for="email">Direccion de correo electronico del usuario a enviar puntos</label>
					<input path="email" type="email" id="email" name="email" />
					<br>
					<label for="puntos">Puntos a enviar</label>
					<input path="puntos" type="number" id="puntos" name="puntos" />
					<br>
					<div class="enviarPts">
						<input id="enviar" type="submit" value="Enviar">
					</div>
				</form:form>
				<div id="mensaje" class="msjError"></div>
				<c:if test="${not empty usuarioInexistente}">
					<div class="msjError">${usuarioInexistente}</div>
				</c:if>
				<c:if test="${not empty puntosInsuficientes}">
					<div class="msjError">${puntosInsuficientes}</div>
				</c:if>
				<c:if test="${not empty puntosEnviados}">
					<div class="msjExito">${puntosEnviados}</div>
				</c:if>
			</div>
		</div>
	</div>
	
	<%@ include file="partial/footer.jsp"%>
	<script src="js/envioPuntos.js"></script>
</body>
</html>