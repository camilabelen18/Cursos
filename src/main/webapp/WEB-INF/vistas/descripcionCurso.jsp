<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Descripción Curso</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="imagenes/favicon.ico">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/descripcionCurso.css">
<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>
	<%@ include file="header.jsp"%>



	<div id="contenedor">
		<h1 id="tit">Descripción del Curso</h1>
		<div id="cont1">
			<img id="img1" src="imagenes/cursos/${curso.imagen}">
		</div>

		<div id="cont2">
			<p id="nombreCurso">${curso.nombre}</p>
			<p id="precioCurso">${curso.precio}</p>
		</div>

		<div id="cont3">
			<form action="agregarCursoAlCarrito" method="get">
				<input type="hidden" name="id_curso" value="${curso.id}">
				<%-- cambiar por vista de carrito --%>
				<input id="agregarCarrito" type="submit" value="Agregar al carrito">
			</form>
		</div>

		<div id="cont4">
			<form action="comprar" method="get">
				<input type="hidden" name="id_curso" value="${curso.id}"> <input
					type="hidden" name="precio" value="${curso.precio}"> <input
					id="comprarAhora" name="comprarAhora" type="submit"
					value="Comprar Ahora">
			</form>
		</div>
		<div id="descripcion">
			<p id="descTit">Descripción</p>
			<p id="descCuerpo">${curso.descripcion}</p>
		</div>
	</div>



	<%@ include file="footer.jsp"%>
</body>
</html>