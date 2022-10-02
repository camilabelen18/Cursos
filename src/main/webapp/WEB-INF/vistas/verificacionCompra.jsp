<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
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
<main>
	<%--Contenido de la pagina --%>

    <c:if test="${empty cursoEncontrado}">
    <%--Titulo --%>
		<div id="titulo">
			<h4>VERIFICACION DE COMPRA</h4>
		</div>

		<%--Caja contenedora principal --%>
		<div class="contenedor2">
			<form action="verificarCompra" method="post">
			
			    <input type="hidden" name="curso_id" value="${idCurso}">
			    
				<h6>Pagar con tarjeta</h6>
				<label for="nroTarjeta">Numero de tarjeta</label>
				<input id="nroTarjeta" type="number" name="nroTarjeta" placeholder="numero-tarjeta">
				
				<label for="nroTarjeta">Email</label>
				<input id="email" type="text" name="email" placeholder="email">
				
				<input id="comprar"type="submit" value="comprar">
			</form>
		</div>
    </c:if>
	
		
	
	
	<h2>${cursoEncontrado}</h2>
	</main>
	<%@ include file="footer.jsp" %>

</body>
</html>