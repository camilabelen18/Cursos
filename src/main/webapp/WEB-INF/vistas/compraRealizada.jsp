<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
	<title>CompraRealizada</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/compraRealizada.css">
</head>
<body>

    <%@ include file="header.jsp"%>

   <%--Contenido de la pagina --%>

   <main>
        <%--Titulo --%>
        <div id="titulo">
            <h2>Compra Realizada</h2>
        </div>

       <%--Caja contenedora principal --%>
        <div class="contenedor2">
        	<h4>Puede regresar al inicio</h4>
        </div>
    </main>
    
    <%@ include file="footer.jsp" %>

</body>
</html>