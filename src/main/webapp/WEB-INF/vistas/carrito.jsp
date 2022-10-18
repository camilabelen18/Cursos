<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Carrito</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/carrito.css">
</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="cont-seccion-carrito">
	
		<%--Titulo --%>
		<div id="titulo">
			<h1>Carrito</h1>
		</div>
		
		<c:forEach var="curso" items="${lista_cursos_carrito}">
		<div class="cuadro-seccion-carrito">
		   <div>
				<img src="imagenes/cursos/${curso.imagen}">
			</div>

				<div class="cotenido-1-curso">
					<p class="nombreCurso">${curso.nombre}</p>
					<p>${curso.precio}</p>
					

			</div>
			
		
	     	</div>
		
		  	</c:forEach>
		  	
		    <div class="total">
		  		<h2>Precio total</h2>
			  <h3> ${precio_total }</h3>
			</div>
			
			
			
		
	<%-- 	<div class="listaSeccionCarrito">
		
			<!-- SE INICIA UN BUCLE EN DONDE POR CADA CURSO SE VA MOSTRANDO SUS DATOS EN UN CUADRO y SE SUMA EL TOTAL -->

			<c:forEach var="curso" items="${cursos_carrito}">

				<div class="cuadro-seccion-carrito">
					<div>
						<img src="imagenes/cursos/${curso.imagen}">
					</div>

					<div class="cotenido-1-curso">
						<p class="nombreCurso">${curso.nombre}</p>
						<p>${curso.precio}</p>

					</div>
					
				</div>
				
				<div class="total">
				<h3>TOTAL DEL CARRITO</h3>
				<div id="precioTotal">
					<span>Total:</span>
					<p>${precioCurso}$</p>
				</div>
				<form action="comprar" method="get">
								<input type="hidden" name="id_curso" value="${curso.id}">
								<input type="hidden" name="precio" value="${curso.precio}">
								<input type="submit" name="Compra" value="Comprar">
				</form>
				</div>

			</c:forEach>
			 --%>

			


		</div>


	
	<%@ include file="/WEB-INF/vistas/footer.jsp" %>

</body>
</html>