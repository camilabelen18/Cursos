<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Perfil</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/vistaPerfil.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<h1 id="perfil">PERFIL</h1>
	
	<div class="contenedorPerfil">
		<div class="datosPerfil">
			<p id="nombrePerfil">Nombre: ${usuario.nombre}</p>
				<%-- <%=session.getAttribute("nombreUsuario")%> --%>
			
			
			<p id="emailPerfil">
				Email: ${usuario.email}
				<%-- <%=session.getAttribute("emailUsuario")%> --%>
				</p>
				
			<p id="passwordPerfil">
				Contraseña: ${usuario.password}
				<%-- <%=session.getAttribute("passwordUsuario")%> --%>
				</p>
				
			<p id="rolPerfil">
				Rol: ${usuario.rol}
				<%-- <%=session.getAttribute("rolUsuario")%> --%>
				</p>
				
			<input id="editarPerfil" name="editarPerfil" type="submit"
				value="Editar perfil">
		</div>

		<div class="fotoPerfil">
			<img id="fotoPerfil" src="imagenes/logoPerfil.png" >

		<br>
	
	<input id="cambiarFotoPerfil" name="cambiarFotoPerfil" type="submit"
		value="Cambiar foto">
		
		</div>
</div>



	<%@ include file="footer.jsp"%>

</body>
</html>