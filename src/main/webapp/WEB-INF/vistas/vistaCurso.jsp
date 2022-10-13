<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Vista del curso</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/vistaCurso.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="contenedor-vistaCurso">
		
		<div id="contVistaCurso-1">
			<h1>${curso.nombre}</h1>
			<div id="img-curso">
				<img src="imagenes/cursos/${curso.imagen}">
			</div>
			<h2>Descripción</h2>
			<p>${curso.descripcion}</p>
		</div>
		
		<div id="contVistaCurso-2">
			<div class="temario">
				<h3>Temario</h3>
				<div id="contenido-curso">
					<p>● Curso Introducción. Video 1</p>
					<p>● Curso Video 2</p>
					<p>● Curso Video 3</p>
					<p>● Curso Video 4</p>
					<p>● Curso Video 5</p>
					<p>● Curso Video 6</p>
				</div>
			</div>
			<div class="botones-vistaCurso">
				<form action="misCursos">
					<input type="submit" name="volver" value="Volver" class=".btn-tipo-1">
				</form>
				<form action="finalizar?curso_id=${curso.id}" method="POST">
					<input type="submit" name="finalizar" value="Finalizar" class=".btn-tipo-2">
				</form>
				<form action="cancelarCompra?curso_id=${curso.id}" method="POST">
					<input type="submit" name="cancelar" value="cancelar" class=".btn-tipo-1">
				</form>

			</div>
		</div>
		
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>