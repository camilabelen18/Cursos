<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Mis Cursos</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/misc.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="cont-seccion-miscursos">

		<div id="contenedorFiltros">
		
			<div class="dropdown">
				<button id="filtrarPor">Filtrar por</button>
				<div class="dropdown-content">
					<a href="misCursos">Todos los cursos</a>
					<a href="verCursosPorEstado?estado=EN_CURSO">En curso</a>
					<a href="verCursosPorEstado?estado=FINALIZADO">Completados</a>
					<a href="verCursosPorEstado?estado=CANCELADO">Cancelados</a>
				</div>
			</div>
		</div>
		
		<c:if test="${not empty msj_exito}">
			<p class="msj_exito ">${msj_exito}</p>
		</c:if>
		
		<c:if test="${not empty msj_error}">
			<p class="msj_error ">${msj_error}</p>
		</c:if>

		<div class="listaSeccionCursos">

			<!-- SE INICIA UN BUCLE EN DONDE POR CADA CURSO SE VA MOSTRANDO SUS DATOS EN UN CUADRO -->

			<c:forEach var="cursoUsuario" items="${lista_cursos}">

				<div class="cuadro-seccion-cursos">
					<div>
						<img src="imagenes/cursos/${cursoUsuario.curso.imagen}">
					</div>

					<div class="cotenido-1-curso">
						<p class="nombreCurso">${cursoUsuario.curso.nombre}</p>
						<p>${cursoUsuario.curso.descripcion}</p>

					</div>

					<div class="cotenido-2-curso">

						<c:if test="${cursoUsuario.estado == 'EN_CURSO'}">
							<p id="estadoEnCurso">En curso</p>
							<form action="verCurso?curso_id=${cursoUsuario.curso.id}" method="POST">
								<input type="submit" name="verCurso" value="Ver curso">
							</form>
							<form action="cancelarCompra?curso_id=${cursoUsuario.curso.id}" method="POST">
								<input type="submit" name="cancelar" value="Cancelar">
							</form>
						</c:if>
						
						<c:if test="${cursoUsuario.estado == 'FINALIZADO'}">
							<p id="estadoCompletado">Completado</p>
							<form action="verCurso?curso_id=${cursoUsuario.curso.id}" method="POST">
								<input type="submit" name="verCurso" value="Ver curso">
							</form>
						</c:if>

						<c:if test="${cursoUsuario.estado == 'CANCELADO'}">
							<p id="estadoCancelado">Cancelado</p>
							<form action="eliminarCompra?curso_id=${cursoUsuario.curso.id}" method="POST">
								<input type="submit" name="eliminar" value="Eliminar">
							</form>
							<form action="comprar" method="get">
								<input type="hidden" name="id_curso" value="${cursoUsuario.curso.id}">
								<input type="hidden" name="precio" value="${cursoUsuario.curso.precio}">
								<input type="submit" name="comprarAhora" value="Comprar">
							</form>
						</c:if>

					</div>
				</div>
				
			</c:forEach>

		</div>

	</div>
	
	<%@ include file="/WEB-INF/vistas/footer.jsp" %>

</body>
</html>