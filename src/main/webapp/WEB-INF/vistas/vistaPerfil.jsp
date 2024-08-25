<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Vista perfil</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/vistaPerfil.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>
	<%@ include file="partial/header.jsp"%>

	<div class="cont-perfil">
		<h1>Detalles del perfil (${usuario.rol})</h1>
		<div class="contenedorEditar">
			<!-- CUADRO DATOS USUARIO -->
			<div class="formEditar">
				<form action="actualizarCambiosPerfil" method="POST" modelAttribute="datosEditarUsuario">
					<label for="nombre">Nombre de usuario</label>
					<input id="nombreUsuario" type="text" name="nombre" value="${usuario.nombre}">
					<label for="email">Dirección de correo electrónico</label>
					<input id="emailUsuario" type="text" name="email" value="${usuario.email}">
					<label for="password">Contraseña actual</label>
					<input id="passwordUsuario" type="password" name="passwordAnterior" value="">
					<label for="password">Nueva contraseña</label>
					<input id="passwordUsuarioNueva" type="password" name="passwordNueva" value="">
					<input id="aniadirCambios" type="submit" value="Guardar cambios">
				</form>
			</div>
			<!-- CUADRO FOTO USUARIO -->
			<div class="fotoPerfil">
				<img id="fotoPerfil" src='uploads/<%=session.getAttribute("imgUsuario")%>'>
				<h2>${usuario.nombre}</h2>
				<form action="cambiarFotoPerfil" method="post" enctype="multipart/form-data">
					<div class="imputFile">
						<input type="file" name="imagen" id="imagen" value="">
						Cambiar foto de perfil
					</div>
					<input id="cambiarFotoPerfil" name="cambiarFotoPerfil" type="submit" value="Guardar foto">
				</form>
			</div>
		</div>
		<c:if test="${not empty msjError1}">
			<div class="error">${msjError1}</div>
		</c:if>
		<c:if test="${not empty msjError2}">
			<div class="error">${msjError2}</div>
		</c:if>
	</div>

	<%@ include file="partial/footer.jsp"%>
</body>
</html>