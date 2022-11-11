<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Perfil</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/vistaPerfil.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<h1 id="perfil">Perfil</h1>

	<div class="contenedorPerfil">
		<div class="datosPerfil">

			<p id="nombrePerfil">Nombre: ${usuario.nombre}</p>



			<p id="emailPerfil">
				Email: ${usuario.email}
				
			</p>

			<label for="password" id="passwordPerfil" >Contraseña: </label><input id="passwordPerfilCaja" type="password" name="password"
				value="${usuario.password}" disabled="disabled">

			<p id="rolPerfil">
				Rol: ${usuario.rol}
				
			</p>
			<a href="editarPerfil"> <input id="editarPerfil"
				name="editarPerfil" type="submit" value="Editar perfil"></a>
		</div>

		<div class="fotoPerfil">
			<img id="fotoPerfil" src='uploads/<%= session.getAttribute("imgUsuario") %>'>
		</div>
	</div>



	<%@ include file="footer.jsp"%>

</body>
</html>