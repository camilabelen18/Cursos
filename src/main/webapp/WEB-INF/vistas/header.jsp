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
			<a href="home">
				<i class="fa-solid fa-graduation-cap" id="logoCurso"></i>
				<h1>Cursos</h1>
			</a>
		</div>
		
		<%--Buscador --%>
		<div id="barra_busqueda">
			<form action="buscar">
				<i class="fa-solid fa-magnifying-glass" id="lupa"></i>
				<input type="search" placeholder="Buscar un curso" aria-label="Search" id="nombreCurso" name="nombreCurso" value="${busqueda}">
			</form>
		</div>

		<%-- Login --%>
		<!-- Aca se valida si el usuario no inicio sesión -->
		<c:if test='<%=session.getAttribute("idUsuario") == null%>'>
			<div id="login">
				<a href="registro">Registrarse</a>
				<a href="login">Iniciar sesión</a>
			</div>
		</c:if>


			<%-- Carrito --%>
			<div>
				<a href="vistaCarrito">
					<i class="fa-sharp fa-solid fa-cart-shopping" id="carrito"></i>
				</a>
			</div>

		<!-- Aca se valida si el usuario inicio sesión -->
		<c:if test='<%=session.getAttribute("idUsuario") != null%>'>

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
					<c:if test="${not empty notificaciones}">

						<c:forEach var="usuarioNotificacion" items="${notificaciones}">

							<c:if test="${usuarioNotificacion.notificacionQuitada == false}">

								<!-- Si la notificacion esta leida -->
								<c:if test="${usuarioNotificacion.notificacionLeida == true}">
									<div class="mensajes">
										<p>${usuarioNotificacion.notificacion.mensaje}</p>
										<a href="quitarNotificacion?idNotif=${usuarioNotificacion.notificacion.id}">
											<i class="fa-solid fa-circle-xmark" title="Ocultar" id="eliminar"></i>
										</a>
									</div>
								</c:if>

								<!-- Si la notificacion no esta leida -->
								<c:if test="${usuarioNotificacion.notificacionLeida == false}">
									<div class="mensajesNoLeidos">
										<p>${usuarioNotificacion.notificacion.mensaje}</p>
										<div class="iconosNotificacion">
											<a href="quitarNotificacion?idNotif=${usuarioNotificacion.notificacion.id}">
												<i class="fa-solid fa-circle-xmark" title="Ocultar" id="eliminar"></i>
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

			<div class="menuCuentaUsuario">
				<img id="fotoUsuario" src='uploads/<%=session.getAttribute("imgUsuario")%>'>
				<div class="contenido-menu" id="contenidoMenuUsuario">
					<div class="fotoNombreUsuario">
						<div>
							<img id="fotoGrandeUsuario" src='uploads/<%=session.getAttribute("imgUsuario")%>'>
						</div>
						<div>
							<p><%=session.getAttribute("nombreUsuario")%></p>
							<!-- Puntos Giftcard -->
							<c:if test='<%=session.getAttribute("idUsuario") != null%>'>
								<div>
									<p id="texto-puntos">Mis puntos</p>
									<p id="mis-puntos">${puntosGiftcard}</p>
								</div>
							</c:if>
						</div>
					</div>
					<a href="verPerfil">
						<div class="iconoMenuUsuario"><i class="fa-regular fa-address-card"></i></div>
						<p>Ver perfil</p>
					</a>
					<a href="misCursos">
						<div class="iconoMenuUsuario"><i class="fa-regular fa-bookmark"></i></div>
						<p>Mis cursos</p>
					</a>
					<a href="verGiftcard">
						<div class="iconoMenuUsuario"><i class="fa-regular fa-credit-card"></i></div>
						<p>Mi giftcard</p>
					</a>
					<a href="cerrarSesion">
						<div class="iconoMenuUsuario"><i class="fa-solid fa-power-off"></i></div>
						<p>Cerrar sesión</p>
					</a>
				</div>
			</div>
		</c:if>
	</div>
	
	<%--Menu --%>
	<nav>
		<div id="secciones-boton">
			<ul>
				<li><a href="home">Inicio</a></li>
				<li><a href="verListaCursos">Cursos</a></li>
				<li><a href="home#cont-contacto">Contacto</a></li>
			</ul>
			<button class="darkModeSwitch" id="switch">
				<span><i class="fa-solid fa-sun"></i></span>
				<span><i class="fa-solid fa-moon"></i></span>
			</button>
		</div>
	</nav>
</header>


