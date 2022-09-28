<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>VerificacionCompra</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
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
			<form:form action="verificarCompra" modelAttribute="curso" method="post">
			
			    <form:hidden path="id"/>

				<h6>Pagar con tarjeta</h6>
				<label for="nroTarjeta">Numero de tarjeta</label>
				<input id="nroTarjeta" type="text" name="nroTarjeta" placeholder="numero-tarjeta">
				<label for="nroTarjeta">Email</label>
				<input id="email" type="text" name="email" placeholder="email">
				<input id="comprar"type="submit" value="comprar">
			</form:form>
		</div>
	</main>
	
	<%@ include file="footer.jsp" %>

</body>
</html>