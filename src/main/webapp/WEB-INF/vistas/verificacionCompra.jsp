<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image/x-icon" href="favicon.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/be5ce1948e.js" crossorigin="anonymous"></script>
    
    <title>VerificacionCompra</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/verificacionCompra.css">
    
</head>
<body>

	<%@ include file="header.jsp"%>

	<%--Contenido de la pagina --%>

	<main>
		<%--Titulo --%>
		<div id="titulo">
			<h4>VERIFICACION DE COMPRA</h4>
		</div>

		<%--Caja contenedora principal --%>
		<div class="contenedor2">
			<form action="verificarCompra" method="post">
				<h6>Pagar con tarjeta</h6>
				<label for="nroTarjeta">Numero de tarjeta</label>
				<input id="nroTarjeta" type="text" name="nroTarjeta" placeholder="numero-tarjeta">
				<label for="nroTarjeta">Email</label>
				<input id="email" type="text" name="email" placeholder="email">
				<input id="comprar"type="submit" value="comprar">
			</form>
		</div>
	</main>
	
	<%@ include file="footer.jsp" %>

</body>
</html>