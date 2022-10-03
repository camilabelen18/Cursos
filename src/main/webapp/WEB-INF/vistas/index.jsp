<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Cursos</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>

	<!-- Aca se incluye el "HEADER" -->
	<%@ include file="header.jsp"%>

	<!-- PRESENTACIÓN -->
	<div class="cont-index-1" style="background-image: url('imagenes/slaider.jpg');">
		<h1>CURSOS ONLINE</h1>
    </div>

	<!-- CATEGORÍAS -->
	<div class="cont-index-2">

		<h2>Categorías</h2>

		<div class="categorias-index">
			<div class="cat-index">
				<a href="verCursosPorCategoria?categoria=diseno">
					<img src="imagenes/cat-diseño.jpg">
					<p>Diseño</p>
				</a>
			</div>

			<div class="cat-index">
				<a href="verCursosPorCategoria?categoria=programacion">
					<img src="imagenes/cat-programacion.jpg">
					<p>Programación</p>
				</a>
			</div>

			<div class="cat-index">
				<a href="verCursosPorCategoria?categoria=musica">
					<img src="imagenes/cat-musica.jpg">
					<p>Música</p>
				</a>
			</div>
		</div>
	</div>

	<!-- CONTACTO -->
	<div class="cont-contacto">
		<div class="txt-contacto">
			<p>Contacto</p>
		</div>
		<form action="#" class="formulario">
			<div class="nom-cor">
				<input type="text" name="nombre" id="nombre" placeholder="Nombre:">
				<input type="text" name="correo" id="correo" placeholder="Correo:">
			</div>
			<div class="mensaje">
				<textarea name="mensaje" id="mensaje" cols="20" rows="10"
					placeholder="Mensaje:"></textarea>
			</div>
			<div class="txt-btn">
				<p>*Te responderemos a la brevedad por correo electrónico</p>
				<input type="submit" value="Enviar">
			</div>
		</form>
	</div>
	
	<!-- Aca se incluye el "FOOTER" -->
	<%@ include file="footer.jsp"%>

</body>
</html>