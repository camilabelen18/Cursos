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


  <!--   Titulo -->
   <div class="titulo">
       <h1>Examen</h1>
       </div>
   
      
   <section id="mesa"> 
   
   <!--   Preguntas -->
   
            <article id="articulo_1">     
                   <form action="#" method="post" >
                       <p>Sirve para solo darle la posibilidad al usuario de seleccionar una sola opcion, todos los inputs del mismo grupo deben tener el mismo name pero distinto value</p>
                       <input id="1" type="radio" name="respuesta" value="1"> Respuesta 1
                       <input id="2" type="radio" name="respuesta" value="2"> Respuesta 2
                       <input id="3" type="radio" name="respuesta" value="3"> Respuesta 3
                     
                   </form>
               
               </article>
                  <article>     
                   <form action="#" method="post" >
                       <p>Pregunta bla bla bla </p>
                       <input id="1" type="radio" name="respuesta" value="1"> Respuesta 1
                       <input id="2" type="radio" name="respuesta" value="2"> Respuesta 2
                       <input id="3" type="radio" name="respuesta" value="3"> Respuesta 3
                     
                   
               
                   </form>
               </article>
               <article>     
                <form action="#" method="post" >
                    <p> Pregunta bla bla bla</p>
                    <input id="1" type="radio" name="respuesta" value="1"> Respuesta 1
                    <input id="2" type="radio" name="respuesta" value="2"> Respuesta 2
                    <input id="3" type="radio" name="respuesta" value="3"> Respuesta 3
                  
                
            
                </form>
            </article>
            <article>     
                <form action="#" method="post" >
                    <p> Pregunta bla bla bla</p>
                    <input id="1" type="radio" name="respuesta" value="1"> Respuesta 1
                    <input id="2" type="radio" name="respuesta" value="2"> Respuesta 2
                    <input id="3" type="radio" name="respuesta" value="3"> Respuesta 3
                  
                
            
                </form>
            </article>
            <article>     
                <form action="#" method="post" >
                    <p> Pregunta bla bla bla</p>
                    <input id="1" type="radio" name="respuesta" value="1"> Respuesta 1
                    <input id="2" type="radio" name="respuesta" value="2"> Respuesta 2
                    <input id="3" type="radio" name="respuesta" value="3"> Respuesta 3
                  
                
            
                </form>
            </article>
            <article>     
                <form action="#" method="post" >
                    <p> Pregunta bla bla bla</p>
                    <input id="1" type="radio" name="respuesta" value="1"> Respuesta 1
                    <input id="2" type="radio" name="respuesta" value="2"> Respuesta 2
                    <input id="3" type="radio" name="respuesta" value="3"> Respuesta 3
                  
                
            
                </form>
            </article>
            <article>     
                <form action="#" method="post" >
                    <p> Pregunta bla bla bla</p>
                    <input id="1" type="radio" name="respuesta" value="1"> Respuesta 1
                    <input id="2" type="radio" name="respuesta" value="2"> Respuesta 2
                    <input id="3" type="radio" name="respuesta" value="3"> Respuesta 3
                  
                
            
                </form>
            </article>
            <article>     
                <form action="#" method="post" >
                    <p> Pregunta bla bla bla</p>
                    <input id="1" type="radio" name="respuesta" value="1"> Respuesta 1
                    <input id="2" type="radio" name="respuesta" value="2"> Respuesta 2
                    <input id="3" type="radio" name="respuesta" value="3"> Respuesta 3
                  
                
            
                </form>
            </article>
            <article>     
                <form action="#" method="post" >
                    <p> Pregunta bla bla bla</p>
                    <input id="1" type="radio" name="respuesta" value="1"> Respuesta 1
                    <input id="2" type="radio" name="respuesta" value="2"> Respuesta 2
                    <input id="3" type="radio" name="respuesta" value="3"> Respuesta 3
                  
                
            
                </form>
            </article>
            <article>     
                <form action="#" method="post" >
                    <p> Pregunta bla bla bla</p>
                    <input id="1" type="radio" name="respuesta" value="1"> Respuesta 1
                    <input id="2" type="radio" name="respuesta" value="2"> Respuesta 2
                    <input id="3" type="radio" name="respuesta" value="3"> Respuesta 3
                  
                
            
                </form>
            </article>
            
         
               
   </section>
   
       <div class="finalizado">
              
                <input type="button" name="finalizoExamen" value="Finalizar" id="cambiar">
             
               
               <div class="contenido" id="contenido" style="display: none;">

               
               </div>
               
               <!--  Despues hacer un form con action para redirigir  -->

              
          </div>

         


<%@ include file="/WEB-INF/vistas/footer.jsp" %>

   <script src="js/jquery-3.6.0.min.js"></script>
   <script src="js/examenFuncion.js"></script>


</body>
</html>