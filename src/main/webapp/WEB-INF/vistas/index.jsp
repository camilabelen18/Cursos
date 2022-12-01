<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Cursos</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/darkmode.css">
</head>
<body>

	<!-- Aca se incluye el "HEADER" -->
	<%@ include file="header.jsp"%>

	<!-- PRESENTACIÓN -->
	<div class="cont-index-1" style="background-image: url('imagenes/banner.jpg');">

		<div class="titulo-subtitulo" >
			<h1>Amplía tus oportunidades profesionales</h1>
			<p>Elige entre nuestros cursos con contenido nuevo cada mes</p>

		</div>
	</div>
    
    <!-- CURSOS MAS CALIFICADOS -->
	<div class="cont-index-2">

		<h2>Ultimos cursos agregados</h2>

		<div class="cursos-mas-calificados">
			<div class="curso-calificado">
				<div>
					<img src="imagenes/cursos/curso-spring.jpg" alt="Spring framework">
				</div>
				<div class="precio-curso-cal">
					<p>$ 2500.0</p>
				</div>
				<div class="descripcion-curso-cal">
					<p>Spring Framework 5</p>
				</div>
				<form action="verMediosDePago" method="POST" id="compraCursoCalificado">
					<input type="hidden" name="id_curso" value="2">
					<input type="hidden" name="precio" value="2500.0">
					<input type="submit" name="pagar" value="Comprar">
				</form>
			</div>

			<div class="curso-calificado">
				<div>
					<img src="imagenes/cursos/adobe-photoshop.jpg" alt="Adobe Photoshop">
				</div>
				<div class="precio-curso-cal">
					<p>$ 1000.0</p>
				</div>
				<div class="descripcion-curso-cal">
					<p>Adobe Photoshop</p>
				</div>
				<form action="verMediosDePago" method="POST" id="compraCursoCalificado">
					<input type="hidden" name="id_curso" value="4">
					<input type="hidden" name="precio" value="1000.0">
					<input type="submit" name="pagar" value="Comprar">
				</form>
			</div>
			
			<div class="curso-calificado">
				<div>
					<img src="imagenes/cursos/diseno-videojuegos.jpg" alt="Videojuegos">
				</div>
				<div class="precio-curso-cal">
					<p>$ 3200.0</p>
				</div>
				<div class="descripcion-curso-cal">
					<p>Modelado para videojuegos</p>
				</div>
				<form action="verMediosDePago" method="POST" id="compraCursoCalificado">
					<input type="hidden" name="id_curso" value="6">
					<input type="hidden" name="precio" value="3200.0">
					<input type="submit" name="pagar" value="Comprar">
				</form>
			</div>
			
			<div class="curso-calificado">
				<div>
					<img src="imagenes/cursos/curso-piano.jpg" alt="Curso piano">
				</div>
				<div class="precio-curso-cal">
					<p>$ 2000.0</p>
				</div>
				<div class="descripcion-curso-cal">
					<p>Curso de piano completo</p>
				</div>
				<form action="verMediosDePago" method="POST" id="compraCursoCalificado">
					<input type="hidden" name="id_curso" value="8">
					<input type="hidden" name="precio" value="2000.0">
					<input type="submit" name="pagar" value="Comprar">
				</form>
			</div>
		</div>
	</div>

	<!-- CATEGORÍAS -->
	<div class="cont-index-3">

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
	<div class="cont-contacto" id="cont-contacto">
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