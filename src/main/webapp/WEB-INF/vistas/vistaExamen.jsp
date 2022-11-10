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
 <link rel="stylesheet" href="css/vistaExamen.css"> 

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
            
          <form action="finalizarExamen?curso_id=${curso.id}" method="POST"> 
          
          <c:forEach var="itemExamen" items="${examenes}">
          
                 <p id="cuadro-pregunta">${itemExamen.pregunta.descripcion}</p>
                 
                       <div class="cuadro-respuestas">

                      <input id="respuesta_1" type="radio" name="respuesta${itemExamen.id}" value="${itemExamen.respuesta.id} "  >${itemExamen.respuesta.descripcion} 
                       <input id="respuesta_2" type="radio" name="respuesta${itemExamen.id}" value="${itemExamen.respuesta_2.id}"> ${itemExamen.respuesta_2.descripcion}
                       <input id="respuesta_3" type="radio" name="respuesta${itemExamen.id}" value="${itemExamen.respuesta_3.id}"> ${itemExamen.respuesta_3.descripcion}
                        
                       </div>  

                 
           </c:forEach>

                       <div class="cuadro-respuestas">

                       </div>  


                <div id="cuadro-seccion-finalizado">
                  <input type="submit" name="finalizoExamen" value="Finalizar" id="finalizar">
                
                    <div id="cuadro-puntaje-total">
				 	<h2 id="nota-examen">Nota de examen</h2>
				 	<div id="total-examen">
						<p>Nota: </p>
						<p>${nota_final }</p>
				     </div>
                </div>  
                
                <input id="prueba" type="radio" name="ashe" value="prueba"> tocalo
                
                
              </div>
              
          </form>

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
              
              </div> -->
