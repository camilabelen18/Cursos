<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Mis Cursos</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/misc.css">
</head>
<body>

	<%@ include file="header.jsp"%>

	<div id="titulo">
		<h2>SECCION: MIS CURSOS</h2>
		<div class="dropdown">
			<button id="filtrarPor">Filtrar por</button>
			<div class="dropdown-content">
				<a href="#">Todos los cursos</a> <a href="#">En Curso</a> <a
					href="#">Finalizado</a> <a href="#">Cancelado</a>
			</div>
		</div>
	</div>
	</div>

	<div class="listaSeccionCursos">

		<!-- SE INICIA UN BUCLE EN DONDE POR CADA CURSO SE VA MOSTRANDO SUS DATOS EN UN CUADRO -->
		
		<c:forEach var="curso" items="${lista_cursos}">

			<div class="cuadro-seccion-cursos">
				<div>
					<img src="imagenes/cursos/${curso.imagen}">
				</div>

				<div class="cotenido-1-curso">
					<p class="nombreCurso">${curso.nombre}</p>
					<p>${curso.descripcion}</p>

				</div>


				<div class="enCurso">
					<form action="#">
					<p class="estado">${curso.estado}</p>
					</form>
				</div>
				
				<div class="cotenido-2-curso">
					<form action="#">
						<input type="submit" name="detalles" value="detalles">
					</form>
				</div>
			</div>

		</c:forEach>
	</div>


</body>
</html>