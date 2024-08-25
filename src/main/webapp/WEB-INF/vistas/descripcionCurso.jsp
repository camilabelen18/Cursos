<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Descripción Curso</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/descripcionCurso.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>
	<%@ include file="partial/header.jsp"%>

	<div class="contenedor">
		<div class="cont1">
			<div>
				<img id="img1" src="imagenes/cursos/${curso.imagen}">
			</div>
			<div class="infoCurso">
				<div>
					<p id="nombreCurso">${curso.nombre}</p>
					<p id="precioCurso">$ ${curso.precio}</p>
				</div>
				<div>
					<form action="agregarCursoAlCarrito" method="get">
						<input type="hidden" name="id_curso" value="${curso.id}">
						<input id="agregarCarrito" type="submit" value="Agregar al carrito">
					</form>
					<form action="verificacionCompra" method="POST">
						<input type="hidden" name="id_curso" value="${curso.id}">
						<input type="hidden" name="precio" value="${curso.precio}">
						<input id="comprarAhora" name="comprarAhora" type="submit" value="Comprar Ahora">
					</form>
				</div>
			</div>
		</div>
		<div class="cont2">
			<div class="descripcion">
				<h2>Descripción</h2>
				<p>${curso.descripcion}<br><br>Categoría: ${curso.categoria}</p>
			</div>
			<div class="contenidoCurso">
				<h2>Contenido del curso</h2>
				<c:forEach var="unidad" items="${unidades}">
					<p>${unidad.descripcion}</p>
				</c:forEach>
			</div>
		</div>
	</div>

	<%@ include file="partial/footer.jsp"%>
	
</body>
</html>