<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Mi giftcard</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/miGiftcard.css">
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
		
	</div>

	<%@ include file="footer.jsp"%>
	
</body>
</html>