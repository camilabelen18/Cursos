<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Medios de pago</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/mediosDePago.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<div class="tituloMediosDePago">
		<h1>Medios de pago</h1>
	</div>
	
	<div id="cont-mediosPago">
		<div class="botonPago">
			<a href="comprar?id_curso=${curso.id}&precio=${precioTotal}">
				Pagar con tarjeta
			</a>
		</div>
		<div class="botonPago">
			<a href="verificarCompraConGiftcard?idCurso=${curso.id}&precioTotal=${precioTotal}">
				Pagar con giftcard
			</a>
		</div>
		<div class="botonPago">
			<a href="#">
				Pagar con mercado pago
			</a>
		</div>
	</div>
	

	<%@ include file="footer.jsp"%>
	
</body>
</html>