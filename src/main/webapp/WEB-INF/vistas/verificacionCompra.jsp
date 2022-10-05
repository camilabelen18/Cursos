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
	    <!--<c:if test="${empty cursoEncontrado}"> -->
	    
	    <%--Titulo --%>
			<div id="titulo">
				<div>
					<a href="index.jsp">INICIO / </a>
					<a href="verListaCursos">CURSOS / </a>
					<span>VERIFICAR COMPRA</span>
				</div>
				<h1>Verificar compra</h1>
			</div>
	
			<%--Caja contenedora principal --%>
			<div class="contenedor2">
			
				<form action="verificarCompra" method="post">
				
				    <input type="hidden" name="curso_id" value="${idCurso}">
				    
				    <h3 id="resumen">Resumen</h3>
				    
				    <div id="precioTotal">
				    	<span>Total:</span>
				    	<p>${precioCurso}$</p>
				    </div>
				    
					<h3 id="pagarTarjeta">Pagar con tarjeta</h3>
					
					<label for="nroTarjeta">Número de tarjeta</label>
					<input id="nroTarjeta" type="number" name="nroTarjeta">
					
					<input id="comprar" type="submit" value="Realizar compra">
				</form>
				
				<c:if test="${not empty tarjetaIncorrecta}">
					<div class="error">${tarjetaIncorrecta}</div>
				</c:if>
			</div>
	    <!--</c:if>-->
	
		<!--  <h2>${cursoEncontrado}</h2>-->
		
	</main>
	
	<%@ include file="footer.jsp" %>

</body>
</html>