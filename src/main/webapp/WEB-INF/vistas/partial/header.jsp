<%@ include file="scripts.jsp"%>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Rouge Script">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
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
		<%-- Carrito --%>
		<div>
			<a href="vistaCarrito">
				<p>${cantidadCarrito}</p>
				<i class="fa-sharp fa-solid fa-cart-shopping" id="carrito"></i>
			</a>
		</div>
		<%-- Login --%>
		<!-- Aca se valida si el usuario no inicio sesión -->
		<c:if test='<%=session.getAttribute("idUsuario") == null%>'>
			<div id="login">
				<a href="login">Iniciar sesión</a>
				<a href="registro">Registrarse</a>
			</div>
		</c:if>
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
						<div class="iconoMenuUsuario">
							<i class="fa-regular fa-address-card"></i>
						</div>
						<p>Ver perfil</p>
					</a> <a href="misCursos">
						<div class="iconoMenuUsuario">
							<i class="fa-regular fa-bookmark"></i>
						</div>
						<p>Mis cursos</p>
					</a> <a href="verGiftcard">
						<div class="iconoMenuUsuario">
							<i class="fa-regular fa-credit-card"></i>
						</div>
						<p>Mi giftcard</p>
					</a> <a href="cerrarSesion">
						<div class="iconoMenuUsuario">
							<i class="fa-solid fa-power-off"></i>
						</div>
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
				<li><a href="verListaCursos">Todos los cursos</a></li>
				<li><a href="contacto">Contacto</a></li>
			</ul>
			<button class="darkModeSwitch" id="switch">
				<span><i class="fa-solid fa-sun"></i></span>
				<span><i class="fa-solid fa-moon"></i></span>
			</button>
		</div>
	</nav>
</header>
