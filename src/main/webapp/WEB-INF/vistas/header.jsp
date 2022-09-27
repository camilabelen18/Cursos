<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header>
	<div class="contenedor1">

		<%--Logo cursos --%>
		<h1>Cursos</h1>

		<%--Buscador --%>
		<div id="barra_busqueda">
			<form action="buscar">
				<input type="search" placeholder="Buscar curso" aria-label="Search"
					id="txtCurso" name="descripcion">
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
			<li><a href="cursos">Cursos</a></li>
			<li><a href="index.jsp">Contacto</a></li>
		</ul>
	</nav>
</header>