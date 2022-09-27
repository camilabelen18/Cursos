<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image/x-icon" href="favicon.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/be5ce1948e.js" crossorigin="anonymous"></script>
    
	

<title>Vista Buscar</title>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/vistaBuscar.css">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
   
</head>
<body>

	<%@ include file="header.jsp"%>

<c:if test="${not empty busqueda_curso}">
	<c:forEach items="${busqueda_curso}" var="buscarCurso">

			<tr>
			<!-- Mostrar el curso con una imagen dependiendo que curso sea -->
			<td>${buscarCurso.descripcion}</td>
			<td>${buscarCurso.nombre}</td>
			<td>${buscarCurso.precio}</td><br>
			<!-- <input action="descripcionCurso" id="verdetalles"type="submit" value="Ver Detalles"><br> -->	
			<!-- boton de ver de talles o ir a curso -->
		</tr>
	</c:forEach>
</c:if>

<p>${sincurso}</p>

	<%@ include file="footer.jsp"%>

</body>
</html>