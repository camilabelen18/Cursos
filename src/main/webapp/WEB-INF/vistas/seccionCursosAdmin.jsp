<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Cursos</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/seccionCursos.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>
	<%@ include file="partial/header.jsp"%>
	
	<div class="cont-seccion-cursos">
		<div id="contenedorFiltros">
			<c:if test="${categoria =='diseno'}">
				<h1>Cursos de diseño</h1>
			</c:if>
			<c:if test="${categoria =='programacion'}">
				<h1>Cursos de programación</h1>
			</c:if>
			<c:if test="${categoria =='musica'}">
				<h1>Cursos de  música</h1>
			</c:if>
			<c:if test="${categoria == 'Todos los cursos'}">
				<h1>Todos los cursos</h1>
			</c:if>
			<c:if test="${busqueda != null}">
				<h3 id="busqueda">Los resultados de la busqueda para el curso '${busqueda}' son: </h3>
			</c:if>
			<div class="dropdown">
				<button id="filtrarPor">Filtrar por</button>
				<div class="dropdown-content">
					<a href="verListaCursos">Todos los cursos</a>
					<a href="verCursosPorCategoria?categoria=programacion">C. de programacion</a>
					<a href="verCursosPorCategoria?categoria=diseno">C. de diseño</a>
					<a href="verCursosPorCategoria?categoria=musica">C. de música</a>
				</div>
			</div>
		</div>
		<c:if test="${not empty msj_exito}">
			<p class="msj_exito">${msj_exito}</p>
		</c:if>
		<div class="listaSeccionCursos">
			<!-- SE INICIA UN BUCLE EN DONDE POR CADA CURSO SE VA MOSTRANDO SUS DATOS EN UN CUADRO -->
			<c:forEach var="curso" items="${lista_cursos}">
				<div class="cuadro-seccion-cursos">
					<a href="descripcionCurso?id_curso=${curso.id}">
						<div class="cuadro-seccion-cursos-flex">
							<div>
								<img src="imagenes/cursos/${curso.imagen}">
							</div>
							<div class="cotenido-1-curso">
								<p class="nombreCurso">${curso.nombre}</p>
								<p>${curso.descripcion}</p>
							</div>
							<div class="cotenido-2-curso">
								<p class="precioCurso">${curso.precio}$</p>
								<form action="editarCurso" method="get">
									<input type="hidden" name="id_curso" value="${curso.id}">
									<input type="hidden" name="nombre" value="${curso.nombre}">
									<input type="hidden" name="categoria" value="${curso.categoria}">
									<input type="hidden" name="descripcion" value="${curso.descripcion}">
									<input type="hidden" name="precio" value="${curso.precio}">
									<input type="submit" name="editarCurso" value="Editar Curso">
								</form>
							</div>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
		<!-- Si al realizar la busqueda no se encontro ningun curso se muestra un mensaje de error -->
		<c:if test="${not empty sin_curso}">
			<div class="msj-sincurso">
				<p id="sin_curso">${sin_curso}</p>
			</div>
		</c:if>
	</div>

	<%@ include file="partial/footer.jsp"%>
</body>
</html>