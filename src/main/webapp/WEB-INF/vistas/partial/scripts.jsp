<%@ page import="java.util.*"%>
<%@ page import="modelo.*"%>

<%
	List<Usuario_Notificacion> notificaciones = (List<Usuario_Notificacion>)session.getAttribute("notificaciones");
	boolean notificacionesLeidas = false;
	boolean notificacionesQuitadas = false;
	
	if (notificaciones != null) {
		
		for (int i = 0; i < notificaciones.size(); i++) {

			if (notificaciones.get(i).getNotificacionLeida() == false) {
				notificacionesLeidas = true;
				break;
			}
		}
		
		for (int i = 0; i < notificaciones.size(); i++) {

			if (notificaciones.get(i).getNotificacionQuitada() == false) {
				notificacionesQuitadas = true;
				break;
			}
		}
	}

	// Se obtiene la lista de notificaciones de la sesion
	pageContext.setAttribute("notificaciones", notificaciones);
	pageContext.setAttribute("notificacionesLeidas", notificacionesLeidas);
	pageContext.setAttribute("notificacionesQuitadas", notificacionesQuitadas);
	
	
	if(session.getAttribute("user") != null){
		Usuario usuario = (Usuario) session.getAttribute("user");
		pageContext.setAttribute("puntosTarjeta", usuario.getTarjeta().getMisPuntos());
	}
	
	if(session.getAttribute("user") != null){
		Carrito carritoUsuario = (Carrito) session.getAttribute("carritoUsuario");
		pageContext.setAttribute("cantidadCarrito", carritoUsuario.getCantidad());
	}
%>