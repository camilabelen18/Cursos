<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>RegistroUsuario</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/registroUsuario.css">
</head>
<body>

	<%@ include file="header.jsp" %>

	<div class="registro-main">

		<h1>Registro</h1>

		<p>Para registrarse en este sitio simplemente rellena los
			siguientes campos y tendrás una nueva cuenta creada para vos al
			instante.</p>

		<form:form class="registro-form" action="validar-registro" method="POST" 
				   modelAttribute="datosRegistro" onsubmit="return validar()">

			<label for="nombre">Nombre de usuario *</label>
			<form:input path="nombre" type="text" id="nombre" name="nombre" />

			<label for="email">Direccion de correo electronico *</label>
			<form:input path="email" type="email" id="email" name="email" />

			<label for="contrasenia">Escribe una contraseña *</label>
			<form:input path="contrasenia" type="password" id="contrasenia" name="contrasenia" />

			<label for="repetirContrasenia">Confirma la contraseña *</label>
			<form:input path="repetirContrasenia" type="password" id="repetirContrasenia" name="repetirContrasenia" />

			<div id="mensaje" class="error"></div>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>

			<input id="registrar" type="submit" value="Completar registro">

		</form:form>
	</div>

	<%@ include file="/WEB-INF/vistas/footer.jsp" %>
	
	<script src="js/registroUsuario.js"></script>

</body>
</html>