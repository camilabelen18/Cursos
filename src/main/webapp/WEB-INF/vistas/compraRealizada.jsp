<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" type="image/x-icon" href="favicon.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/be5ce1948e.js" crossorigin="anonymous"></script>
    
    <title>CompraRealizada</title>
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
               <h4>Puede regresar al inicio </h4>
        </div>
    </main>
    
    <%@ include file="footer.jsp" %>



	<h1>Compra realizada </h1>


</body>
</html>