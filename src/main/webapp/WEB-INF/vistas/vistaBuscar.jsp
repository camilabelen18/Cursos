<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Cursos</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

	<%@ include file="header.jsp"%>

	<h1>Estoy en curso</h1>

	<c:forEach items="${busqueda_curso}" var="buscarCurso">
		<tr>
			<td class="text-center">${buscarCurso.nombre}</td>
			<td class="text-center">${buscarCurso.descripcion}</td>
			<td class="text-center">${buscarCurso.precio}</td>
			<td class="text-center">${buscarCurso.estado}</td><br>
			
		</tr>
	</c:forEach>

	<%@ include file="footer.jsp"%>

</body>
</html>