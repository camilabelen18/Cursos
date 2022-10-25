<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Perfil</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/editarPerfil.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<h1>Editar Perfil</h1>
	<section class="contenedorEditar">
		<article class="formEditar">
		
			<form action="actualizarCambiosPerfil" method="POST">
				<div class="textoEditar">
					<label for="nombreUsuario">Nombre: </label> <input
						id="nombreUsuario" type="text" name="nombreUsuario"
						value="${usuario.nombre}">
				</div>
				<div class="textoEditar">
					<label for="emailUsuario">Email: </label> <input id="emailUsuario"
						type="text" name="emailUsuario" value="${usuario.email}">
					<br>
				</div>
				<div class="textoEditar">
					<label for="passwordUsuario">Password: </label> <input
						id="passwordUsuario" type="text" name="passwordUsuario"
						value="${usuario.password}">
				</div>
				<div class="textoEditar">
					<p>Rol:${usuario.rol}</p>
				</div>

				<input id="aniadirCambios" type="submit" value="Añadir cambios">
			</form>
		</article>
		<article class="fotoPerfil">
			<img id="fotoPerfil" src="imagenes/usuario.png"> <br> <input
				id="cambiarFotoPerfil" name="cambiarFotoPerfil" type="submit"
				value="Cambiar foto">
		</article>
	</section>


	<%@ include file="footer.jsp"%>

</body>
</html>