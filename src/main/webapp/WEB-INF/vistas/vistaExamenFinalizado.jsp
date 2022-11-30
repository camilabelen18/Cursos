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
<!-- <link rel="stylesheet" href="css/vistaExamen.css"> -->
<link rel="stylesheet" href="css/vistaExamenFinalizado.css">
<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>

<%@ include file="header.jsp"%>
<div class="cuadro-seccion-preguntas">  
   <!--   Titulo -->
<div class="titulo">
 <h1>Examen Finalizado</h1>
</div>

 <!--   Contenido -->
<div class="Contenido">


      <div id="cuadro-seccion-finalizado">
                  <div id="cuadro-puntaje-total">
					<h2 id="nota-examen">Nota de examen</h2>
					<div id="total-examen">
						<p>Nota: </p>
						<p>${notaSacada }</p>
						<p>Puntos ganados : ${ puntos } </p>
						<p class="NotaNumero">${notaSacada }</p>
						<p>Puntos obtenidos:</p>
						<p class="NotaNumero"> ${puntos } </p>
						<p>${msj } </p>
				     </div>
				      <a href="misCursos">
                <input type="submit" name="volverAtras" value="Volver" id="volverAtras"> 
                </a> 
                </div>
               
                </div>
      
</div>
 </div>

  

<%@ include file="/WEB-INF/vistas/footer.jsp" %>
</body>
</html>