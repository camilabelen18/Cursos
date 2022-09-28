<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Cursos</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/seccionCursos.css">
</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="cont-seccion-cursos">
	
		<!-- Si al realizar la busqueda no se encontro ningun curso se muestra un mensaje de error -->
		<c:if test="${not empty lista_cursos}">
	
			<div id="contenedorFiltros">
				<label for="filtro">Filtrar por:</label>
				<select id="filtro" name="filtro">
					<option value=1>Más relevantes</option>
					<option value=2>Programacion</option>
					<option value=3>Diseño</option>
					<option value=4>Musica</option>
				</select>
			</div>

			<div class="listaSeccionCursos">
		
				<!-- SE INICIA UN BUCLE EN DONDE POR CADA CURSO SE VA MOSTRANDO SUS DATOS EN UN CUADRO -->
				<c:forEach var="curso" items="${lista_cursos}">
			
					<div class="cuadro-seccion-cursos">
						<div>
							<a href="descripcionCurso?id_curso=${curso.id}">
								<img src="imagenes/cursos/${curso.imagen}">
							</a>
						</div>
		
						<div class="cotenido-1-curso">
							<a href="descripcionCurso?id_curso=${curso.id}">
								<p class="nombreCurso">${curso.nombre}</p>
								<p>${curso.descripcion}</p>
							</a>
						</div>
						
						<div class="cotenido-2-curso">
							<p class="precioCurso">${curso.precio}$</p>	
							<form action="comprar" method="get">
							    <input type="hidden" name="idcurso" value="${curso.id}">
								<input type="submit" name="comprarAhora"  value="Comprar">
							</form>
						</div>
					</div>
					
				</c:forEach>
			</div>
		
		</c:if>
			
		<p id="msj_error_curso">${sincurso}</p>
		
	</div>

	<%@ include file="footer.jsp" %>
	
</body>
</html>