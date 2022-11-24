<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Añadir Curso</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/crearCurso.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>

	<%@ include file="header.jsp" %>
<div id ="formularioCrearCurso">
	<form:form action="cursoAgregado" method = "POST" modelAttribute="datosCrearCurso" enctype="multipart/formdata" onsubmit = "return validar()">
		<label for="nombre">Nombre del curso</label>
		<input id="nombre" type="text" name="nombre">
		
		<label for="categoria">Categoria</label>
		<input id="categoria" type="text" name="categoria">
		
		<label for="descripcion">Descripción</label>
		<input id="descripcion" type="text" name="descripcion">
		
		<label for="precio">Precio</label>
		<input id="precio" type="number" name="precio">
		
		<label for="imgCurso">Imagen del Curso</label>
		<input id="imgCurso" type="file" name="imgCurso" />
		
		<div id="mensaje" class="error"></div>
		<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
		
		<input id="añadir"type="submit" value="Añadir">	
	</form:form>
</div>

<%@ include file="footer.jsp" %>

<script src="js/crearCurso.js"></script>

</body>
</html>