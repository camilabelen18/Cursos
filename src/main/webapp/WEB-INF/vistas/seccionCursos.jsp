<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Cursos</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/seccionCursos.css">
</head>
<body>

	<%@ include file="header.jsp"%>
	
	<section id="contenedorFiltros">

		<h2>CURSOS</h2>

		<form action="proyecto-limpio-spring/cursos" method="get"
			class="formulario">
			<label for="orden">Filtrar por categoria: </label> <select id="orden"
				name="orden">
				<option value=1>Programacion</option>
				<option value=2>Arte</option>
				<option value=3>Diseño</option>
				<option value=4>Musica</option>
			</select>
		</form>




		<form action="proyecto-limpio-spring/cursos" method="get"
			class="formulario">

			<label for="filtro">Ordenar por: </label> <select id="filtro"
				name="filtro">
				<option value=1>Mas Relevantes</option>
				<option value=2>Nivel</option>
				<option value=3>Duracion</option>
			</select>
		</form>
	</section>

	<section class="listaDeCursos">
		<div id="contenedor">
			<div id="cont1">
				<!-- <img id= "cursophp" alt="" src="imagenes/cursophp.jpg"> -->
			</div>

			<div id="cont2">
				<p id="nombreCurso">Curso PHP: Basico</p>
				<p id="precioCurso">$10,00</p>
		
			<p id="descCuerpo">Lorem ipsum dolor sit amet, consectetuer
				adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum
				sociis natoque penatibus et magnis dis parturient montes, nascetur
				ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu,
				pretium quis, sem.</p>

			
				<form action="comprar">
					<input id="comprarAhora" type="submit" value="Comprar Ahora">
				</form>
			</div>
		</div>




		<div id="contenedor">
			<div id="cont1">
				<!-- IMAGEN CURSO JAVA -->
			</div>

			<div id="cont2">
				<p id="nombreCurso">Curso JAVA: Avanzado</p>
				<p id="precioCurso">$15,00</p>
			
				<p id="descCuerpo">Lorem ipsum dolor sit amet, consectetuer
					adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.
					Cum sociis natoque penatibus et magnis dis parturient montes,
					nascetur ridiculus mus. Donec quam felis, ultricies nec,
					pellentesque eu, pretium quis, sem.</p>
			
				<form action="comprar">
					<input id="comprarAhora" type="submit" value="Comprar Ahora">
				</form>
			</div>
		</div>
		
		
		<div id="contenedor">
			<div id="cont1">
				<!-- IMAGEN CURSO PHOTOSHOP -->
			</div>

			<div id="cont2">
				<p id="nombreCurso">Curso de Photoshop: Basico</p>
				<p id="precioCurso">$10,00</p>
				
			<p id="descCuerpo">Lorem ipsum dolor sit amet, consectetuer
				adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum
				sociis natoque penatibus et magnis dis parturient montes, nascetur
				ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu,
				pretium quis, sem.</p>
			
				<form action="comprar">
					<input id="comprarAhora" type="submit" value="Comprar Ahora">
				</form>
			</div>
		</div>
		


		<div id="contenedor">
			<div id="cont1">
				<!-- IMAGEN CURSO ILLUSTRATOR -->
			</div>

			<div id="cont2">
				<p id="nombreCurso">Curso de Illustrator: Intermedio</p>
				<p id="precioCurso">$12,00</p>
			
			<p id="descCuerpo">Lorem ipsum dolor sit amet, consectetuer
				adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum
				sociis natoque penatibus et magnis dis parturient montes, nascetur
				ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu,
				pretium quis, sem.</p>
			
				<form action="comprar">
					<input id="comprarAhora" type="submit" value="Comprar Ahora">
				</form>
			</div>
		</div>


		<div id="contenedor">
			<div id="cont1">
				<!-- IMAGEN CURSO GUITARRA -->
			</div>

			<div id="cont2">
				<p id="nombreCurso">Curso de Guitarra: Avanzado</p>
				<p id="precioCurso">$20,00</p>
			
			<p id="descCuerpo">Lorem ipsum dolor sit amet, consectetuer
				adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum
				sociis natoque penatibus et magnis dis parturient montes, nascetur
				ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu,
				pretium quis, sem.</p>
			
				<form action="comprar">
					<input id="comprarAhora" type="submit" value="Comprar Ahora">
				</form>
			</div>
		</div>
	</section>
	
	<%@ include file="footer.jsp" %>
</body>
</html>