<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>VerificacionCompra</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/verificacionCompra.css">
    <link rel="stylesheet" href="css/darkmode.css">
</head>
<body>
	<%@ include file="partial/header.jsp"%>

	<main>
		<h1>Verificar compra</h1>
		<div class="contenedor2">
			<form action="realizarCompra" method="post">
				<input type="hidden" name="curso_id" value="${idCurso}">
				<h3 id="resumen">Resumen</h3>
				<hr>
				<div id="precioTotal">
					<span>Total:</span>
					<p id="pt">${precioCurso}$</p>
				</div>
				<h3 id="metodoPago">Método de pago</h3>
				<!-- Pagar con tarjeta -->
				<input type="radio" name="metodoPago" id="tarjetaDebito" value="tarjeta" checked="checked">
				<label for="tarjetaDebito">Pagar con tarjeta débito</label>
				<input id="tarjetaDebito" type="number" name="tarjetaDebito" placeholder="Número de tarjeta">
				<!-- Pagar con puntos -->
				<input type="radio" name="metodoPago" id="misPuntos" value="puntos">
				<label for="misPuntos">Pagar con mis puntos</label>
				<input id="misPuntos" type="number" name="misPuntos" placeholder="Número de tarjeta">
				<!-- Boton -->
				<input id="comprar" type="submit" value="Realizar compra">
			</form>
			<form action="pagoConMP" method="get" class="d-inline">
                <input type="hidden" name="idCurso" value="${idCurso}">
                <script src="https://www.mercadopago.com.ar/integrations/v1/web-payment-checkout.js" 
                data-preference-id="${preference.id}"></script>
            </form>
			<c:if test="${not empty tarjetaIncorrecta}">
				<div class="error">${tarjetaIncorrecta}</div>
			</c:if>
			<c:if test="${not empty saldoInsuficiente}">
				<div class="error">${saldoInsuficiente}</div>
			</c:if>
		</div>
	</main>

	<script type="text/javascript">
		var boton = document.getElementsByClassName("mercadopago-button")[0];
		boton.innerHTML = "Pagar con MP";
	</script>

	<%@ include file="partial/footer.jsp"%>
</body>
</html>