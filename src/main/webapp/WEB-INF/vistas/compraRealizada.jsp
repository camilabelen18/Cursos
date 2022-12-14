<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>CompraRealizada</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="imagenes/favicon.ico">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/exito.css">
<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>

	<%@ include file="header.jsp"%>

	<%--Contenido de la pagina --%>

	<main>

		<div id="tituloCompra">
			<!-- 		<i class="animate_animated animate__fadeInDown fa-regular fa-face-smile" id="emoji"></i>-->
			<h2>LA COMPRA FUE REALIZADA DE MANERA EXITOSA!!!</h2>
			<h4>Muchas gracias por comprar el curso.</h4>
			
			<div id="botonesCurso">
				<form action="misCursos">
					<input type="submit" name="misCursos" value="Ir a mis cursos">
				</form>
			</div>
		</div>

	</main>


	<%@ include file="footer.jsp"%>

</body>
</html>