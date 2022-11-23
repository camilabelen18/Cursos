<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Editar Perfil</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/editarPerfil.css">
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<h1 class="editarPerfiltitulo">Editar Perfil</h1>
	<section class="contenedorEditar">
		<article class="formEditar">
		
			<form action="actualizarCambiosPerfil" method="POST" modelAttribute="datosEditarUsuario">
				<div class="textoEditar">
					<label for="nombre">Nombre: </label> <input
						 id="nombreUsuario" type="text" name="nombre"
						value="${usuario.nombre}">
				</div>
				<div class="textoEditar">
					<label for="email">Email: </label> <input  id="emailUsuario"
						type="text" name="email" value="${usuario.email}">
					<br>
				</div>
				<div class="textoEditar">
					<label for="password">Contraseña anterior: </label> <input
						id="passwordUsuario" type="password" name="passwordAnterior"
						value="">
				</div>
				<div class="textoEditar">
					<label for="password">Nueva contraseña: </label> <input
						id="passwordUsuarioNueva" type="password" name="passwordNueva"
						value="">
				</div>
				<div class="textoEditar">
					<label for="password">Repetir nueva contraseña: </label> <input
						id="passwordUsuarioRepetir" type="password" name="repeticionPasswordNueva"
						value="">
				</div>
				

				<input id="aniadirCambios" type="submit" value="Añadir cambios">
			</form>
		</article>
		
		<article class="fotoPerfil">
			<img id="fotoPerfil" src='uploads/<%= session.getAttribute("imgUsuario") %>'> <br> 
			<form action="cambiarFotoPerfil" method="post" enctype="multipart/form-data">
				<input type="file" name="imagen" id="imagen" size="20">
				<input id="cambiarFotoPerfil" name="cambiarFotoPerfil" type="submit" value="Cambiar foto">
			</form>
			
		</article>
	</section>
		


	<%@ include file="footer.jsp"%>
	
	

</body>
</html>