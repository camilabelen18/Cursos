<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="modelo.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	List<Usuario_Notificacion> notificaciones = (List<Usuario_Notificacion>)session.getAttribute("notificaciones");
	boolean notificacionesLeidas = false;
	boolean notificacionesQuitadas = false;
	
	if (notificaciones != null) {
		
		for (int i = 0; i < notificaciones.size(); i++) {

			if (notificaciones.get(i).getNotificacionLeida() == false) {
				notificacionesLeidas = true;
				break;
			}
		}
		
		for (int i = 0; i < notificaciones.size(); i++) {

			if (notificaciones.get(i).getNotificacionQuitada() == false) {
				notificacionesQuitadas = true;
				break;
			}
		}
	}

	// Se obtiene la lista de notificaciones de la sesion
	pageContext.setAttribute("notificaciones", notificaciones);
	pageContext.setAttribute("notificacionesLeidas", notificacionesLeidas);
	pageContext.setAttribute("notificacionesQuitadas", notificacionesQuitadas);
	
	
	if(session.getAttribute("user") != null){
		Usuario usuario = (Usuario) session.getAttribute("user");
		pageContext.setAttribute("puntosGiftcard", usuario.getGiftcard().getMisPuntos());
	}
	
	
%>

<head><link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Rouge Script"></head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
<header>
	<div class="cont-1-header" id="cont-header">

		<%--Logo cursos --%>
		<div class="cursosLogo">
			<i class="fa-solid fa-graduation-cap" id="logoCurso"></i>
			<h1>Cursos</h1>
		</div>
		
		<%--Buscador --%>
		<div id="barra_busqueda">
			<form action="buscar">
				<input type="search" placeholder="Buscar curso..." aria-label="Search" id="nombreCurso" name="nombreCurso">
				<i class="fa-solid fa-magnifying-glass" id="lupa"></i>
			</form>
		</div>

		<%-- Login --%>
		<!-- Aca se valida si el usuario no inicio sesión -->
		<c:if test='<%=session.getAttribute("idUsuario") == null%>'>
			<div id="login">
				<a href="registro">Creá tu cuenta</a>
				<a href="login">Ingresá</a>
			</div>
		</c:if>


		<%-- Usuario --%>
		<!-- Aca se valida si el usuario inicio sesión -->
		<c:if test='<%=session.getAttribute("idUsuario") != null%>'>

			<div class="menuCuentaUsuario">
				<img id="fotoUsuario" src='uploads/<%=session.getAttribute("imgUsuario")%>'>
				<div class="contenido-menu" id="contenidoMenuUsuario">
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
			
				<c:if test="${notificacionesLeidas == false}">
					<i class="fa-solid fa-bell" id="notificacion"></i>
				</c:if>
				
				<c:if test="${notificacionesLeidas == true}">
					<i class="fa-solid fa-bell" id="notificacion"></i>
					<i class="fa-solid fa-circle" id="circulo"></i>
				</c:if>
				
				<div class="contenido-menu" id="contenido-menu-notificaciones">
					<h3>Notificaciones</h3>
					<hr>
					<c:if test="${not empty notificaciones}">

						<c:forEach var="usuarioNotificacion" items="${notificaciones}">

							<c:if test="${usuarioNotificacion.notificacionQuitada == false}">

								<!-- Si la notificacion esta leida -->
								<c:if test="${usuarioNotificacion.notificacionLeida == true}">
									<div class="mensajes">
										<p>${usuarioNotificacion.notificacion.mensaje}</p>
										<a href="quitarNotificacion?idNotif=${usuarioNotificacion.notificacion.id}">
											<i class="fa-solid fa-circle-xmark" title="Eliminar" id="eliminar"></i>
										</a>
									</div>
								</c:if>

								<!-- Si la notificacion no esta leida -->
								<c:if test="${usuarioNotificacion.notificacionLeida == false}">
									<div class="mensajesNoLeidos">
										<p>${usuarioNotificacion.notificacion.mensaje}</p>
										<div class="iconosNotificacion">
											<a href="quitarNotificacion?idNotif=${usuarioNotificacion.notificacion.id}">
												<i class="fa-solid fa-circle-xmark" title="Quitar" id="eliminar"></i>
											</a>
											<a href="marcarNotificacionLeida?idNotif=${usuarioNotificacion.notificacion.id}">
												<i class="fa-solid fa-circle-check" title="Marcar como leída" id="marcarComoLeida"></i>
											</a>
										</div>
									</div>
								</c:if>
							</c:if>
						</c:forEach>
					</c:if>

					<c:if test="${notificacionesQuitadas == false}">
						<div class="mensajes">
							<p>No hay notificaciones.</p>
						</div>
					</c:if>

					<hr>
					
					<a href="verNotificaciones" id="verNotificaciones">Ver más notificaciones</a>
				</div>
			</div>
		</c:if>

		<%-- Carrito --%>
		<div>
			<a href="vistaCarrito">
				<i class="fa-sharp fa-solid fa-cart-shopping" id="carrito"></i>
			</a>
		</div>

	<%--Puntos Giftcard --%>
		<c:if test='<%=session.getAttribute("idUsuario") != null%>'>
			<div>
				<p id="texto-puntos">Mis puntos</p>
				<p id="mis-puntos">${puntosGiftcard}</p>
			</div>
		</c:if>
	</div>
	
	<%--Menu --%>
	<nav>
		<ul>
			<li><a href="index.jsp">Inicio</a></li>
			<li><a href="verListaCursos">Cursos</a></li>
			<li><a href="index.jsp#cont-contacto">Contacto</a></li>
			<button class="darkModeSwitch" id="switch">
				<span><i class="fa-solid fa-sun"></i></span>
				<span><i class="fa-solid fa-moon"></i></span>
			</button>
		</ul>
	</nav>
</header>


