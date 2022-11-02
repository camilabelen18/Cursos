<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/login.css">
</head>

<body>

	<%@ include file="header.jsp" %>

	<div class="login-main">
	
		<h1>Ingresá</h1>

		<form:form action="validar-login" method="POST" modelAttribute="datosLogin"  onsubmit="return validar()">

			<!-- Elementos de entrada de datos, el elemento path debe indicar en que atributo 
			del objeto 'datosLogin' se guardan los datos ingresados -->
			
			<form:input path="email" type="email" id="email" placeholder="Ingresa tu email" />
			
			<form:input path="password" type="password" id="password" placeholder="Ingresa tu contraseña" />

			<input type="submit" name="login" value="Ingresar">
			
		</form:form>
		
		<p>
			<span>¿No tenes cuenta? </span>
			<a href="registro">Registrate</a>
		</p>
		
		<div id="mensaje" class="error"></div>
		
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		
	</div>
	
	<%@ include file="/WEB-INF/vistas/footer.jsp" %>
	
	<script src="js/login.js"></script>

</body>
</html>
