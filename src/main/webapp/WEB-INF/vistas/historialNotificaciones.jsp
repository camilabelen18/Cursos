<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Notificaciones</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/historialNotificaciones.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>

	<%@ include file="header.jsp"%>
	
	<div class="cont-seccion-notificaciones">
	
		<div id="titulo">
			<h1>Notificaciones</h1>
		</div>
	
		<c:if test="${not empty notificaciones}">
	
			<c:forEach var="usuarioNotificacion" items="${notificaciones}">
			
				<div class="cuadro-notificacion">
					<div class="mensajeNotificacion">
						<p>${usuarioNotificacion.notificacion.mensaje}</p>
					</div>
					
					<div class="eliminar-notificacion">
						<div>
							<a href="eliminarNotificacion?idNotif=${usuarioNotificacion.notificacion.id}">
								<i class="fa-solid fa-xmark"></i>
							</a>
						</div>
					</div>
				</div>
			</c:forEach>
	
		</c:if>
		
		<c:if test="${empty notificaciones}">
			<p id="sin_notificaciones">No hay notificaciones.</p>
		</c:if>
	</div>

	<%@ include file="/WEB-INF/vistas/footer.jsp" %>

</body>
</html>