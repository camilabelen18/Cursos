<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" type="image/x-icon" href="favicon.ico">
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
  <script src="https://kit.fontawesome.com/be5ce1948e.js" crossorigin="anonymous"></script>
    <title>Cursos</title>
</head>
<body>

	<header>

		<div>
			<%--Logo cursos --%>
			<h1>Cursos</h1>
			<%--Buscador --%>
			<div class="d-flex justify-content-center ">
				<form class="d-flex align-items-center" action="buscar">
					<input class="form-control me-2" type="search"
						placeholder="Buscar Curso" aria-label="Search" id="txtCurso"
						name="txtCurso">
					<button class="btn btn-outline-success" type="submit">Buscar</button>
				</form>
			</div>
			<%-- Login --%>
			<ul class="nav">
				<li class="nav-item"><a href="login"
					class="nav-link link-dark px-2"> <svg
							xmlns="http://www.w3.org/2000/svg" width="30" height="30"
							fill="currentColor" class="bi bi-person-circle"
							viewBox="0 0 16 16">
          <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
          <path fill-rule="evenodd"
								d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
        </svg> Login
        <%--Carrito --%>
				</a></li>
				<li class="nav-item"><a routerLink="/carrito"
					class="nav-link link-dark px-2"> <svg
							xmlns="http://www.w3.org/2000/svg" width="30" height="30"
							fill="currentColor" class="bi bi-cart4" viewBox="0 0 16 16">
                    <path
								d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
                  </svg> 
				</a></li>
			</ul>

		</div>
		<nav>
		<%--Menu --%>
		<a href="index.jsp">Inicio</a>
		<a href="cursos.jsp">Cursos</a>
		<a href="index.jsp">Contacto</a>
		</nav>
	</header>
	
	
	<!-- <nav class="py-2 bg-light border-bottom">
  <div class="container d-flex flex-wrap">
    <ul class="nav me-auto">
      <li>
        <img src="imagenes/descarga.jpg" alt="" width="50" height="50">
      </li>
      <li class="nav-item"><a href="#" class="nav-link link-dark px-2 active" aria-current="page">WebManía</a></li>
      <li class="nav-item"><a href="/promos" class="nav-link link-dark px-2">Promos</a></li>
      <li class="nav-item"><a href="/candy" class="nav-link link-dark px-2">Candy</a></li>
      <li class="nav-item"><a href="/view" class="nav-link link-dark px-2">Suscribite</a></li>
      <li class="nav-item"><a href="/contacto" class="nav-link link-dark px-2">Contacto</a></li>
    </ul>
    <ul class="nav" >
      <li class="nav-item"><a href="login" class="nav-link link-dark px-2">
        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
          <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
          <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
        </svg>
          Login</a>
      </li>
      <li class="nav-item" >
          <a routerLink="/carrito" class="nav-link link-dark px-2">
                  <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-cart4" viewBox="0 0 16 16"  >
                    <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
                  </svg>
                  <span class="badge text-bg-secondary">{{productos.length}}</span>
          </a>
      </li>
    </ul>
  </div>
</nav>
<header class="py-3 mb-4 border-bottom text-center" style="margin-bottom: 0px !important;">
  <div class="d-flex justify-content-center ">
    <form class="d-flex align-items-center" action="buscar">
      <input class="form-control me-2" type="search" placeholder="Buscar Curso" aria-label="Search" id="txtCurso" name = "txtCurso" >
      <button class="btn btn-outline-success" type="submit">Buscar</button>
    </form>
  </div>
</header> -->

</body>
</html>