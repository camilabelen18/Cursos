<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Saludo</title>
</head>
<body>

  <!-- El mensaje1 es el atributo que viene del modelo (osea lo que esta en el metodo de java, de controlador de prueba) -->
      
      <c:if test="${not empty mensaje1}">  <!-- aca comprueba si no esta vacio el atributo/variable mensaje -->
      <h4>Mensaje 1: ${mensaje1} </h4>
      </c:if>
      <c:if test="${not empty mensaje1}">
     <h4>Mensaje 2: ${mensaje2} </h4>
      </c:if>

     <!-- Placed at the end of the document so the pages load faster, puestos al final del documento para que cargue mas rapido -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>



</body>
</html>