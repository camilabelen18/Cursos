<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
	<title>Mis Cursos</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/misc.css">
</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="titulo">
		<h3>SECCION: MIS CURSOS</h3>
	</div>
	<div class="dropdown">
		<h6>Filtrar por:</h6>
		<button class="dropbtn">Todos mis cursos</button>
		<div class="dropdown-content">
			<a href="#">En Curso</a> 
			<a href="#">Finalizado</a> 
			<a href="#">Cancelado</a>
		</div>
	</div>
	
</body>
</html>