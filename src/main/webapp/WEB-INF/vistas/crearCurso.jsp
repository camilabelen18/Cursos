<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Añadir Curso</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/crearCurso.css">
</head>
<body>

	<%@ include file="header.jsp" %>

	<form action="cursoAgregado" method = "POST">
		<label for="nombreCurso">Nombre del curso</label>
		<input id="nombreCurso" type="text" name="nombreCurso">
		
		<label for="descCurso">Descripción</label>
		<input id="descCurso" type="text" name="descCurso">
		
		<label for="precioCurso">Precio</label>
		<input id="precioCurso" type="number" name="precioCurso">
		
		<input id="añadir"type="submit" value="añadir">	
	</form>

</body>
</html>