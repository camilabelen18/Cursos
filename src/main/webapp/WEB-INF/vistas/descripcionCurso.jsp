<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="icon" type="image/x-icon" href="favicon.ico">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/be5ce1948e.js"
	crossorigin="anonymous"></script>

<title>Descripción Curso</title>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/descripcionCurso.css">

</head>
<body>
		<%@ include file="header.jsp"%>
	
	<%--incluir el header aca arriba --%>
	<h1 id= "tit">Descripción del Curso</h1>
	
	<div id="contenedor">
<div id="cont1">
   <img id= "cursophp" alt="" src="imagenes/cursophp.jpg">
</div>

<div id="cont2">
<p id= "nombreCurso">Curso PHP desde 0</p>
<p id= "precioCurso">10,00$</p>
</div>

<div id="cont3">
	<form action="index.jsp"> <%-- cambiar por vista de carrito --%>
  <input id="agregarCarrito" type="submit" value="Agregar al carrito">
  </form>
</div>

<div id="cont4">
	<form action="comprar">
  <input id="comprarAhora" type="submit" value="Comprar Ahora">
  </form>
</div>
	</div>
	
	<div id= "descripcion">
	<p id="descTit">Descripción</p>
	<p id="descCuerpo">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. 
Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, 
ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla 
vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum 
felis eu pede mollis pretium. Integer tincidunt. </p>
	</div>
	
		<%@ include file="footer.jsp" %>
</body>
</html>