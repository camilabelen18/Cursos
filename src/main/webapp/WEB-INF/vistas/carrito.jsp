<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Carrito</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/carrito.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="cont-seccion-carrito">

		<%--Titulo --%>
		<div id="titulo">
			<h1>Carrito</h1>
		</div>
		
		<!-- Se valida si la lista de cursos no esta vacía -->
		<c:if test="${not empty lista_cursos_carrito}">
		
			<div id="producto-precio">
				<p id="producto">Producto</p>
				<p id="precio">Precio</p>
			</div>
			
			<c:forEach var="curso" items="${lista_cursos_carrito}">
			
				<div class="cuadro-seccion-carrito">
					
					<div class="eliminar-curso">
						<div>
							<a href="eliminarCursoDeListaCarrito?curso_id=${curso.id}">
								<i class="fa-solid fa-xmark"></i>
							</a>
						</div>
					</div>
				
					<div class="img-curso">
						<img src="imagenes/cursos/${curso.imagen}">
					</div>

					<div class="nombre-curso">
						<p>${curso.nombre}</p>
					</div>
					
					<div class="precio-curso">
						<p>$${curso.precio}</p>
					</div>
				</div>
				
			</c:forEach>
			
			<div id="cuadro-seccion-total">
			
				<div id="cuadro-total">
					<h2>TOTAL DEL CARRITO</h2>
					<div id="total-precio">
						<p>Total: </p>
						<p>$${precio_total }</p>
					</div>
					<form action="comprarCursosDelCarrito" method="GET" id="realizar-compra">
						<input type="submit" name="realizarCompra" value="Realizar compra">
					</form>
				</div>
			</div>
		</c:if>
		
		<c:if test="${not empty msj}">
			<p id="msj_error">${msj}</p>
		</c:if>

	</div>

	<%@ include file="/WEB-INF/vistas/footer.jsp"%>
	
</body>
</html>