<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Mi giftcard</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="imagenes/favicon.ico">
<link rel="stylesheet" href="css/miGiftcard.css">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="tituloGiftcard">
		<h1>Mi Giftcard</h1>
	</div>

	<div class="contenedor-miGiftcard">

		<div id="contMiGiftcard-1">
			<div id="giftcard">
				<h2>${usuario.nombre}</h2>
				<div id="numTarjeta">
					<p>Número de tarjeta</p>
					<h3>${giftcard.numTarjeta}</h3>
				</div>
			</div>
		</div>

		<div id="contMiGiftcard-2">
			<div>
				<p class="giftcardText">Mis puntos</p>
				<p class="giftcardNum">${giftcard.misPuntos}</p>
			</div>
			<div>
				<p class="giftcardText">Saldo actual</p>
				<p class="giftcardNum">$ ${giftcard.saldoActual}</p>
			</div>
		</div>
		<div class="envioPuntosForm">
			<form:form action="puntosEnviados" method="POST">
				<label for="email">Direccion de correo electronico del
					usuario a enviar puntos</label>
				<input path="email" type="email" id="email" name="email" />
				<br>
				<label for="puntos">Puntos a enviar</label>
				<input path="puntos" type="number" id="puntos" name="puntos" />
				<div id="mensaje" class="error"></div>
				<c:if test="${not empty error}">
					<div class="msjerror">${error}</div>
				</c:if>

				<input id="enviar" type="submit" value="Enviar Puntos">
			</form:form>

			<c:if test="${not empty usuarioInexistente}">
				<div class="msjerror">${usuarioInexistente}</div>
			</c:if>


			<c:if test="${not empty puntosInsuficientes}">
				<div class="msjerror">${puntosInsuficientes}</div>
			</c:if>
		</div>
	</div>



	<%@ include file="footer.jsp"%>
	<script src="js/envioPuntos.js"></script>
</body>
</html>