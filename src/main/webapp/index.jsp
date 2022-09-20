<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
    
    <title>Cursos</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

	<header>
		<div class="contenedor1">
		
			<%--Logo cursos --%>
			<h1>Cursos</h1>
			
			<%--Buscador --%>
			<div id="barra_busqueda">
				<form action="buscar">
					<input type="search" placeholder="Buscar curso" aria-label="Search" id="txtCurso" name="txtCurso">
				</form>
			</div>
			
			<%-- Login --%>
			<div id="login">
				<a href="#">Creá tu cuenta</a>
				<a href="#">Ingresá</a>
			</div>
			
			<%--Carrito --%>
			<div>
				<a href="#">
					<img id="carrito" alt="" src="imagenes/carrito.png">
				</a>
			</div>

		</div>

		<%--Menu --%>
		<nav>
			<ul>
				<li><a href="index.jsp">Inicio</a></li>
				<li><a href="cursos.jsp">Cursos</a></li>
				<li><a href="index.jsp">Contacto</a></li>
			</ul>
		</nav>
	</header>

</body>
</html>