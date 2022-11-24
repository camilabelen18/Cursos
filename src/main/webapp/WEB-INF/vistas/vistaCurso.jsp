<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Vista del curso</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/vistaCurso.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<div class="tituloCurso">
		<h1>${cursoUsuario.curso.nombre}</h1>
	</div>
	
	<c:if test="${not empty msj_progreso}">
		<p class="msj_progreso">${msj_progreso}</p>
	</c:if>

	<div class="contenedor-vistaCurso">
		
		<div id="contVistaCurso-1">
			<h2>${unidad.descripcion}</h2>
			<div id="video-curso">
				<iframe src="${unidad.video_url}" frameborder="0" allowfullscreen></iframe>
			</div>
			<div id="marcarUnidad">
				<c:if test="${unidad.completado == false}">
					<a href="completarUnidad?unidad_id=${unidad.id}&curso_id=${cursoUsuario.curso.id}">
						MARCAR ESTA UNIDAD COMO COMPLETA
					</a>
				</c:if>
				
				<c:if test="${unidad.completado == true}">
					<p>UNIDAD COMPLETADA</p>
				</c:if>
			</div>
		</div>
		
		<div id="contVistaCurso-2">
			<div class="progreso">
				<h2>Progreso ${cursoUsuario.progreso}%</h2>
				<progress max="100" value="${cursoUsuario.progreso}"></progress>
			</div>
			
			<div class="contenido">
				<h2>Contenido</h2>
				
				<div id="contenido-curso">
				
					<c:forEach var="itemUnidad" items="${unidades}">
					
						<c:if test="${itemUnidad.completado == false}">
							<a href="verUnidadCurso?unidad_id=${itemUnidad.id}&curso_id=${cursoUsuario.curso.id}">
								<i class="fa-solid fa-circle" id="icon_circulo"></i> ${itemUnidad.descripcion}
							</a>
						</c:if>
						
						<c:if test="${itemUnidad.completado == true}">
							<a href="verUnidadCurso?unidad_id=${itemUnidad.id}&curso_id=${cursoUsuario.curso.id}">
								<i class="fa-solid fa-circle-check" id="icon_check"></i> ${itemUnidad.descripcion}
							</a>
						</c:if>

					</c:forEach>
				</div>
			</div>
			
			<div class="botones-vistaCurso">
				<form action="misCursos">
					<input type="submit" name="volver" value="Volver" class="btn-tipo-1">
				</form>
				
				<c:if test="${cursoUsuario.cursoTerminado == false}">
					<form action="finalizar?curso_id=${cursoUsuario.curso.id}" method="POST">
						<input type="submit" name="terminarCurso" value="Terminar curso" class="btn-tipo-2">
					</form>
				</c:if>

          
       
                <c:if test="${cursoUsuario.cursoTerminado == false}">
				<form action="examen?curso_id=${cursoUsuario.curso.id}" method="POST">
					<input type="submit" name="examen" value="Examen" class="btn-tipo-2">
				</form>
				</c:if>
				
				<c:if test="${cursoUsuario.cursoTerminado == true}">
					<c:if test="${examen.estadoHabilitado == false}">
					<form action="examen?curso_id=${cursoUsuario.curso.id}" method="POST">
						<input type="submit" name="examen" value="Examen" class="btn-tipo-1">
					</form>
					</c:if>
					
					<c:if test="${examen.estadoHabilitado == true}">
					<form action="examen?curso_id=${cursoUsuario.curso.id}" method="POST">
						<input type="submit" name="examen" value="Examen" class="btn-tipo-2">
					</form>
					</c:if>
				</c:if>
		
				
				<c:if test="${cursoUsuario.cursoTerminado == true}">
					<form action="historialExamen?curso_id=${cursoUsuario.curso.id}" method="POST">
						<input type="submit" name="historialExamen" value="Historial Examen" class="btn-tipo-1">
					</form>
				</c:if>
				
				
				
				
				
		
			</div>
		</div>
		
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>