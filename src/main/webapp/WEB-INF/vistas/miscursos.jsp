<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Mis Cursos</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/misc.css">
</head>
<body>

	<%@ include file="header.jsp"%>

	<div id="titulo">
		<h3>SECCION: MIS CURSOS</h3>
		<div id="contenedorFiltros">

			<label for="filtro">Filtrar por:</label> <select id="filtro"
				name="filtro">
				<option value=1>Todos mis cursos</option>
				<option value=2>En curso</option>
				<option value=3>Finalizado</option>
				<option value=4>Cancelado</option>
			</select>
		</div>
	</div>

	<form action="verCursosDelUsuario" method="get">

		<label for="email">Email</label> <input id="email" type="text"
			name="email" placeholder="email"> <input id="enviar"
			type="submit" value="comprar">
	</form>

</body>
</html>