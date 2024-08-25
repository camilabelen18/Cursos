<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Editar Curso</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/editarCurso.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>

	<%@ include file="partial/header.jsp"%>
	
	<div id="formularioEditC">
		<h1 style="margin-bottom: 40px;">Editar curso</h1>
		<form:form action="cursoActualizado" method="POST" modelAttribute="datosActualizarCurso">
			<label for="nombre">Nombre del curso</label>
			<input id="nombre" type="text" name="nombre" value="${nombreCurso}">

			<label for="categoria">Categoria</label>
			<input id="categoria" type="text" name="categoria" value="${catCurso}">

			<label for="descripcion">Descripción</label>
			<input id="descripcion" type="text" name="descripcion" value="${descCurso}">

			<label for="precio">Precio</label>
			<input id="precio" type="number" name="precio" value="${precioCurso}">
			
			<input type="hidden" name="id_curso" value="${cursoID}">

			<input id="añadir" type="submit" value="Actualizar">
		</form:form>
	</div>

	<%@ include file="partial/footer.jsp"%>

</body>
</html>