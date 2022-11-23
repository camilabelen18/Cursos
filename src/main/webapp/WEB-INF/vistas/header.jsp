<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Se obtiene la lista de notificaciones de la sesion -->
<% pageContext.setAttribute("notificaciones", session.getAttribute("notificaciones")); %>

<header>
	<div class="cont-1-header" id="cont-header">

		<%--Logo cursos --%>
		<div class="cursosLogo">
			<h1>Cursos</h1>
			<i class="fa-solid fa-graduation-cap" id="logoCurso"></i>
		</div>


		<%--Buscador --%>
		<div id="barra_busqueda">
			<form action="buscar">
				<input type="search" placeholder="Buscar curso" aria-label="Search" id="nombreCurso" name="nombreCurso">
			</form>
		</div>

		<%-- Login --%>
		<!-- Aca se valida si el usuario no inicio sesión -->
		<c:if test='<%=session.getAttribute("idUsuario") == null%>'>

			<div id="login">
				<a href="registro">Creá tu cuenta</a> <a href="login">Ingresá</a>
			</div>

		</c:if>



		<%-- Usuario --%>
		<!-- Aca se valida si el usuario inicio sesión -->
		<c:if test='<%=session.getAttribute("idUsuario") != null%>'>

			<div class="menuCuentaUsuario">
				<img id="fotoUsuario" src='uploads/<%=session.getAttribute("imgUsuario")%>'>
				<div class="contenido-menu">
					<div class="fotoNombreUsuario">
						<img id="fotoGrandeUsuario" src='uploads/<%=session.getAttribute("imgUsuario")%>'>
						<p><%=session.getAttribute("nombreUsuario")%></p>
					</div>
					<a href="verPerfil">Ver perfil</a>
					<a href="misCursos">Mis cursos</a>
					<a href="verGiftcard">Mi giftcard</a>
					<a href="cerrarSesion">Cerrar sesión</a>
				</div>
			</div>

			<div class="menuNotificaciones">
				<i class="fa-solid fa-bell" id="notificacion"></i>
				<div class="contenido-menu">
					<!-- No hay notificaciones nuevas -->
					<!-- Mostrar una lista de notificaciones primero las nuevas abajo las leidas -->
					<!-- Historial de notificaciones -->
					<!--  for each -->
					<h3>Notificaciones</h3>
					<hr>
					<c:if test="${not empty notificaciones}">
						<c:forEach var="nota" items="${notificaciones}">
							<div class="mensajes">
								<p>${nota.mensaje}</p>
								<a href="#">
									<i class="fa-solid fa-circle-xmark"></i>
								</a>
							</div>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty notificaciones}">
						<h3>Notificaciones</h3>
						<div class="mensajes">
							<p>No hay notificaciones.</p>
						</div>
					</c:if>
				</div>
			</div>
		</c:if>
		
		<%-- Carrito --%>
		<div>
			<a href="vistaCarrito">
				<i class="fa-sharp fa-solid fa-cart-shopping" id="carrito"></i>
			</a>
		</div>
	</div>

	<%--Menu --%>
	<nav>
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


