<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <link rel="stylesheet" href="css/darkmode.css">
</head>

<header>
	<div class="cont-1-header" id="cont-header">

		<%--Logo cursos --%>
		<div class="cursosLogo">
			<h1>Cursos</h1>
			<img id="logoCurso" src="imagenes/logo-curso.png">
		</div>


		<%--Buscador --%>
		<div id="barra_busqueda">
			<form action="buscar">
				<input type="search" placeholder="Buscar curso" aria-label="Search"
					id="nombreCurso" name="nombreCurso">
			</form>
		</div>

		<%-- Login --%>
		<!-- Aca se valida si el usuario no inicio sesión -->
		<c:if test='<%= session.getAttribute("idUsuario") == null %>'>

			<div id="login">
				<a href="registro">Creá tu cuenta</a> <a href="login">Ingresá</a>
			</div>

		</c:if>

		<%-- Carrito --%>
		<div>
			<a href="vistaCarrito"> <img id="carrito" alt=""
				src="imagenes/carrito.png">
			</a>
		</div>

		<%-- Usuario --%>
		<!-- Aca se valida si el usuario inicio sesión -->
		<c:if test='<%= session.getAttribute("idUsuario") != null %>'>

			<div class="menuCuentaUsuario">
				<img id="fotoUsuario"
					src='uploads/<%= session.getAttribute("imgUsuario") %>'>
				<div class="contenido-menu">
					<div class="fotoNombreUsuario">
						<img id="fotoGrandeUsuario"
							src='uploads/<%= session.getAttribute("imgUsuario") %>'>
						<p><%= session.getAttribute("nombreUsuario") %></p>
					</div>
					<a href="verPerfil">Ver perfil</a> <a href="misCursos">Mis
						cursos</a> <a href="verGiftcard">Mi giftcard</a> <a
						href="cerrarSesion">Cerrar sesión</a>
				</div>
			</div>
		</c:if>

	</div>

	<%-- Menu --%>
	<nav class="menu">
		<ul>
			<li><a href="index.jsp">Inicio</a></li>
			<li><a href="verListaCursos">Cursos</a></li>
			<li><a href="index.jsp">Contacto</a></li>
			<button class="darkModeSwitch" id="switch">
				<span><i class="fa-solid fa-sun"></i></span> 
				<span><i class="fa-solid fa-moon"></i></span>
			</button>
		</ul>
	</nav>
</header>


