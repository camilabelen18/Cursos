<%@ page import="modelo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Se obtiene la lista de notificaciones de la sesion -->
<%
	pageContext.setAttribute("notificaciones", session.getAttribute("notificaciones"));
/* pageContext.setAttribute("notificacionesLeidas", 2);

List<Usuario_Notificacion> lista = (List<Usuario_Notificacion>)session.getAttribute("notificaciones");
int notificacionesLeidas = 0;
for(int i = 0; i< lista.size(); i++){
	if(lista.get(i).getNotificacionLeida()==false){
		notificacionesLeidas = 1;
		break;
	}
} */
%>

<head><link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Rouge Script"></head>

<header>
	<div class="cont-1-header" id="cont-header">

		<%--Logo cursos --%>
		<div class="cursosLogo">

			<i class="fa-solid fa-graduation-cap" id="logoCurso"></i>
			<h1>Cursos</h1>
		</div>

		<!-- 	<nav>
		<ul>
			<li><a href="index.jsp">Inicio</a></li>
			<li><a href="verListaCursos">Cursos</a></li>
			<li><a href="index.jsp">Contacto</a></li>
			<button class="darkModeSwitch" id="switch">
				<span><i class="fa-solid fa-sun"></i></span> <span><i
					class="fa-solid fa-moon"></i></span>
			</button>
		</ul>

	</nav> -->
		<%--Buscador --%>
		<div id="barra_busqueda">
			<form action="buscar">
				<input type="search" placeholder="Buscar curso..."
					aria-label="Search" id="nombreCurso" name="nombreCurso"> <i
					class="fa-solid fa-magnifying-glass" id="lupa"></i>
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
				<img id="fotoUsuario"
					src='uploads/<%=session.getAttribute("imgUsuario")%>'>
				<div class="contenido-menu" id="contenidoMenuUsuario">
					<div class="fotoNombreUsuario">
						<img id="fotoGrandeUsuario"
							src='uploads/<%=session.getAttribute("imgUsuario")%>'>
						<p><%=session.getAttribute("nombreUsuario")%></p>
					</div>
					<a href="verPerfil">Ver perfil</a> <a href="misCursos">Mis
						cursos</a> <a href="verGiftcard">Mi giftcard</a> <a
						href="cerrarSesion">Cerrar sesión</a>
				</div>
			</div>

			<div class="menuNotificaciones">


				<i class="fa-solid fa-bell" id="notificacion"></i> <i
					class="fa-solid fa-circle" id="circulo"></i>

				<div class="contenido-menu" id="contenido-menu-notificaciones">
					<!-- No hay notificaciones nuevas -->
					<!-- Mostrar una lista de notificaciones primero las nuevas abajo las leidas -->
					<!-- Historial de notificaciones -->
					<!--  for each -->
					<h3>Notificaciones</h3>
					<hr>
					<c:if test="${not empty notificaciones}">

						<c:forEach var="usuarioNotificacion" items="${notificaciones}">

							<c:if test="${usuarioNotificacion.notificacionQuitada == false}">

								<!-- Si la notificacion esta leida -->
								<c:if test="${usuarioNotificacion.notificacionLeida == true}">
									<div class="mensajes">
										<p>${usuarioNotificacion.notificacion.mensaje}</p>
										<a
											href="quitarNotificacion?idNotif=${usuarioNotificacion.notificacion.id}">
											<i class="fa-solid fa-circle-xmark" title="Eliminar"
											id="eliminar"></i>
										</a>
									</div>
									<i class="fa-solid fa-bell" id="notificacion"></i>
								</c:if>

								<!-- Si la notificacion no esta leida -->
								<c:if test="${usuarioNotificacion.notificacionLeida == false}">
									<div class="mensajesNoLeidos">
										<p>${usuarioNotificacion.notificacion.mensaje}</p>
										<div class="iconosNotificacion">
											<a
												href="quitarNotificacion?idNotif=${usuarioNotificacion.notificacion.id}">
												<i class="fa-solid fa-circle-xmark" title="Eliminar"
												id="eliminar"></i>
											</a> <a
												href="marcarNotificacionLeida?idNotif=${usuarioNotificacion.notificacion.id}">
												<i class="fa-solid fa-circle-check"
												title="Marcar como leída" id="marcarComoLeida"></i>
											</a>
										</div>

									</div>
								</c:if>
							</c:if>
						</c:forEach>

						<c:forEach var="usuarioNotificacion" items="${notificaciones}">
							<c:if test="${usuarioNotificacion.notificacionQuitada == false}">
							</c:if>
						</c:forEach>

					</c:if>

					<c:if test="${empty notificaciones}">
						<div class="mensajes">
							<p>No hay notificaciones.</p>
						</div>
					</c:if>

					<hr>

					<a href="verNotificaciones" id="verNotificaciones"> Ver más
						notificaciones </a>
				</div>
			</div>
		</c:if>

		<%-- Carrito --%>
		<div>
			<a href="vistaCarrito"> <i
				class="fa-sharp fa-solid fa-cart-shopping" id="carrito"></i>
			</a>
		</div>

		<c:if test='<%=session.getAttribute("idUsuario") != null%>'>
			<div>
				<p id="texto-puntos">Mis puntos</p>
				<p id="mis-puntos">1200</p>
			</div>
		</c:if>
	</div>
	

	<%--Menu --%>
	<nav>
		<ul>
			<li><a href="index.jsp">Inicio</a></li>
			<li><a href="verListaCursos">Cursos</a></li>
			<li><a href="index.jsp">Contacto</a></li>
			<button class="darkModeSwitch" id="switch">
				<span><i class="fa-solid fa-sun"></i></span> <span><i
					class="fa-solid fa-moon"></i></span>
			</button>
		</ul>

	</nav>
</header>


