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

	<%@ include file="header.jsp"%>

	<div class="cont-seccion-cursos">

		<div id="contenedorFiltros">

			<div class="dropdown">
				<button id="filtrarPor">Filtrar por</button>
				<div class="dropdown-content">
					<a href="#">Más relevantes</a>
					<a href="verCursosPorCategoria?categoria=programacion">C. de programacion</a>
					<a href="verCursosPorCategoria?categoria=diseno">C. de diseño</a>
					<a href="verCursosPorCategoria?categoria=musica">C. de música</a>
				</div>
			</div>
		</div>
		
		<c:if test="${not empty msj_error}">
			<p class="msj_error">${msj_error}</p>
		</c:if>
		
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
								<p class="precioCurso">$ ${curso.precio}</p>
								<form action="verMediosDePago" method="POST">
									<input type="hidden" name="id_curso" value="${curso.id}">
									<input type="hidden" name="precio" value="${curso.precio}">
									<input type="submit" name="pagar" value="Comprar">
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
			<i class="animate__animated animate__heartBeat fa-brands fa-searchengin" id="no-encontrado"></i>
			<p id="sin_curso">${sin_curso}</p>
			</div>
		</c:if>
		
	</div>

	<%@ include file="footer.jsp" %>
	
</body>
</html>