<%@page import="controladores.ControladorCursos"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Examen</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="imagenes/favicon.ico">
<link rel="stylesheet" href="css/styles.css">
 <link rel="stylesheet" href="css/vistaHistorialExamen.css"> 
<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>

  <%@ include file="header.jsp"%>

	<div id="cont-seccion-principal">

		<%--Titulo --%>
		<div id="titulo">
			<h1>Historial de Examenes </h1>
		</div>
		
		<!-- Se valida si la lista de usuario examenes no esta vacía -->
		<c:if test="${not empty usuarioExamenes}">
		
			 <div id="nombre-puntaje-fecha">
				<p id="nombre">Nombre</p>
				<p id="puntaje">Puntaje</p>
				<p id="fecha">Fecha </p>
				<p id="hora">Hora</p>
				
			</div>
			
			
			<c:forEach var="itemUsuarioExamen" items="${usuarioExamenes}">
			
				<div class="cuadro-seccion-usuarioExamen">
					
					<div class="nombre-usuario">
						<p>${itemUsuarioExamen.usuario.nombre}</p>
					</div>
					
					<div class="puntaje">
						<p>${itemUsuarioExamen.puntaje_final}</p>
					</div>
					
					<div class="fecha">
						<p>${itemUsuarioExamen.fecha_finalizacion_examen}</p>
					</div>
					
					<div class="hora">
						<p>${itemUsuarioExamen.hora_finalizacion_examen}</p>
					</div>
				</div>
				
			</c:forEach>
			
			
			
		</c:if>
		
		<c:if test="${not empty msj}">
			<p id="msj_error">${msj}</p>
		</c:if>

	</div>

	<%@ include file="/WEB-INF/vistas/footer.jsp"%>
	
</body>
</html>