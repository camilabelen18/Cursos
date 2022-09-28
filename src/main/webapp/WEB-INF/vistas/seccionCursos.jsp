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

	
	<c:if test="${not empty busqueda_curso}">
		<c:forEach items="${busqueda_curso}" var="buscarCurso">
	
				<tr>
				<!-- Mostrar el curso con una imagen dependiendo que curso sea -->
				<td>${buscarCurso.descripcion}</td>
				<td>${buscarCurso.nombre}</td>
				<td>${buscarCurso.precio}</td><br>
				<div id="cont4">
				<form action="descripcionCurso">
					<input id="descripcionCurso" type="submit" value="Ver detalles">
				</form>
			</div>
				<!-- <input action="descripcionCurso" id="verdetalles"type="submit" value="Ver Detalles"><br> -->	
				<!-- boton de ver de talles o ir a curso -->
			</tr>
		</c:forEach>
	</c:if>

	<p>${sincurso}</p>

	<%@ include file="header.jsp"%>

	<div class="cont-seccion-cursos">

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
		
			<c:if test="${not empty lista_cursos}">
		
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
							<form action="comprar">
								<input type="submit" name="comprarAhora"  value="Comprar">
							</form>
						</div>
					</div>
					
				</c:forEach>
			
			</c:if>
			
			
			
		</div>
	</div>

	<%@ include file="footer.jsp" %>
	
</body>
</html>