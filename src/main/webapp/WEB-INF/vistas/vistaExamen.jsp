<%@page import="controladores.ControladorCursos"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
	<meta charset="UTF-8">
	<title>Examen</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/vistaExamen.css"> 
	<link rel="stylesheet" href="css/darkmode.css">
</head>
<body>

<%@ include file="header.jsp"%>

     
      
   <div class="contenido-seccion-preguntas"> 
   
    <!--   Titulo -->
   <div id="titulo">
       <h1>Examen</h1>
      </div>
   
   <!--   Preguntas -->
    
          
          <div class="cuadro-seccion-preguntas">  
            
 
          <form:form action="finalizarExamen?curso_id=${curso.id}" method="POST" modelAttribute="datosExamen">
          <!-- Elementos de entrada de datos, el elemento path debe indicar en que atributo 
			del objeto 'datosExamen/datosPregunta' se guardan los datos ingresados -->
          
          <c:forEach var="itemPregunta" items="${datosExamen.datosPregunta}" varStatus="vs">
          
                 <p id="cuadro-pregunta">${itemPregunta.descripcion}</p>
                 
                       <div class="cuadro-respuestas">
                        <form:hidden path="datosPregunta[${vs.index}].preguntaId" value="${itemPregunta.pregunta.id}" />
                        <form:radiobutton  path="datosPregunta[${vs.index}].respuestaElegida" value="${itemPregunta.respuesta_1.id}" />${itemPregunta.respuesta_1.descripcion}
                        <form:radiobutton  path="datosPregunta[${vs.index}].respuestaElegida" value="${itemPregunta.respuesta_2.id}"/>${itemPregunta.respuesta_2.descripcion}
                        <form:radiobutton  path="datosPregunta[${vs.index}].respuestaElegida" value="${itemPregunta.respuesta_3.id}"/>${itemPregunta.respuesta_3.descripcion}
                       </div>  

           </c:forEach>

                <div id="cuadro-seccion-finalizado">
                  <input type="submit" name="finalizoExamen" value="Finalizar" id="finalizar">
                
                 <!--   <div id="cuadro-puntaje-total">
				 	<h2 id="nota-examen">Nota de examen</h2>
				 	<div id="total-examen">
						<p>Nota: </p>
						<p>${notaSacada }</p>
				     </div>  --> 
                </div>  
                

                
                
              </div>
              
           </form:form>

                     </div>
            

            </div>
            
          
            
             



    <%@ include file="/WEB-INF/vistas/footer.jsp" %>

   	 <script src="js/jquery-3.6.0.min.js"></script>
     <script src="js/examenFuncion.js"></script>
    
</body>
</html>



        <!--  Despues hacer un form con action para redirigir 
                  <div id="cuadro-finalizado">
               <input type="button" name="finalizoExamen" value="Finalizar" id="cambiar">
             
              <div class="contenido" id="contenido" style="display: none;">
               
              </div>
              
               <%--              <input path="respuestaElegida" type="radio" value="${examenes[0].respuesta_2.id}"/>${examenes[0].respuesta_2.descripcion}--%>
 <%--              <input path="respuestaElegida" type="radio" value="${examenes[0].respuesta_3.id}"/>${examenes[0].respuesta_3.descripcion}
                      <input id="respuesta_1" type="radio" name="respuesta${itemPregunta.id}" value="${itemPregunta.respuesta_1.id} " >${itemPregunta.respuesta_1.descripcion} 
                       <input id="respuesta_2" type="radio" name="respuesta${itemPregunta.id}" value="${itemPregunta.respuesta_2.id}"> ${itemPregunta.respuesta_2.descripcion}
                       <input id="respuesta_3" type="radio" name="respuesta${itemPregunta.id}" value="${itemPregunta.respuesta_3.id}"> ${itemPregunta.respuesta_3.descripcion}--%>
              
              </div> -->
