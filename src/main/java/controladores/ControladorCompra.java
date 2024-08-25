package controladores;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mercadopago.resources.Preference;

import servicios.SaldoInsuficienteException;
import servicios.ServicioCarrito;
import modelo.*;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;
import servicios.TarjetaInvalidaException;
import servicios.ServicioMercadoPago;
import servicios.ServicioTarjeta;

@Controller
public class ControladorCompra {
	
	private ServicioUsuario servicioUsuario;
	private ServicioCurso servicioCurso;
	private ServicioCarrito servicioCarrito;
	private ServicioMercadoPago servicioMercadoPago = new ServicioMercadoPago();
	private ServicioTarjeta servicioTarjeta;
	
	@Autowired
	public ControladorCompra(ServicioUsuario servicioUsuario, ServicioCurso servicioCurso, ServicioCarrito servicioCarrito, ServicioTarjeta servicioTarjeta) {
		this.servicioUsuario = servicioUsuario;
		this.servicioCurso = servicioCurso;
		this.servicioCarrito = servicioCarrito;
		this.servicioTarjeta = servicioTarjeta;
	}

	@RequestMapping(path = "/verificacionCompra", method = RequestMethod.POST)
	public ModelAndView verificacionCompra(@RequestParam("id_curso") int idCurso, @RequestParam("precio") Double precioCurso, HttpSession session) {
		
		ModelMap model = new ModelMap();
		String viewName = "";
		// Si comprueba si el usuario tiene iniciada la sesión
		if (session.getAttribute("idUsuario") != null) {
			int id_user = (int) session.getAttribute("idUsuario");
			Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
			Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
			Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);
			if (!servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario) || usuarioCurso.getEstado() == Estado.CANCELADO) {
				Preference preference = servicioMercadoPago.checkout(usuario, precioCurso);
				model.put("preference", preference);
				model.put("idCurso", idCurso);
				model.put("precioCurso", precioCurso);
				model.put("curso", curso_obtenido);
				viewName = "verificacionCompra";
			}
			else {
				model.addAttribute("msj_error", "El curso ya fue comprado, compre otro curso.");
				viewName = "redirect:/verListaCursos";
			}
		}
		else {
			model.addAttribute("msj_error", "Para comprar necesitas ingresar a tu cuenta.");
			viewName = "redirect:/verListaCursos";
		}
		return new ModelAndView(viewName, model);
	}

	@RequestMapping(path = "/realizarCompra", method = RequestMethod.POST)
	public ModelAndView realizarCompra(@RequestParam("metodoPago") String metodoPago, @RequestParam("curso_id") int curso_id, 
			@RequestParam("misPuntos") Integer numeroTarjetaPuntos, HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(curso_id);
		Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);
		String viewName = "";
		// Compra con tarjeta debito
		if (metodoPago.equals("tarjeta")) {
			if (servicioUsuario.existeCursoEnListaUsuario(curso_id, usuario) && usuarioCurso.getEstado() == Estado.CANCELADO) {
				servicioCurso.cambiarEstadoCurso(usuarioCurso, Estado.EN_CURSO);
			}
			else {
				servicioUsuario.guardarCursoEnListaUsuario(curso_obtenido, usuario);
			}
			viewName = "compraRealizada";
			servicioUsuario.enviarNotificacion(usuario, "Compraste el curso " + curso_obtenido.getNombre(), session);
		}
		// Compra con tarjeta puntos
		if (metodoPago.equals("puntos")) {
			try {
				Tarjeta tarjeta = usuario.getTarjeta();
				servicioTarjeta.verificarTarjetaDePuntos(tarjeta, numeroTarjetaPuntos);
				servicioTarjeta.verificarSaldoDeTarjetaDePuntos(tarjeta, curso_obtenido);
				if (servicioUsuario.existeCursoEnListaUsuario(curso_id, usuario) && usuarioCurso.getEstado() == Estado.CANCELADO) {
					servicioCurso.cambiarEstadoCurso(usuarioCurso, Estado.EN_CURSO);
				}
				else {
					servicioUsuario.guardarCursoEnListaUsuario(curso_obtenido, usuario);
				}
				viewName = "compraRealizada";
				servicioUsuario.enviarNotificacion(usuario, "Compraste el curso " + curso_obtenido.getNombre(), session);
			}
			catch (TarjetaInvalidaException e) {
				model.put("tarjetaIncorrecta", "El numero de tarjeta ingresado es incorrecto.");
				Preference preference = servicioMercadoPago.checkout(usuario, curso_obtenido.getPrecio());
				model.put("preference", preference);
				model.put("idCurso", curso_obtenido.getId());
				model.put("precioCurso", curso_obtenido.getPrecio());
				model.put("curso", curso_obtenido);
				viewName = "verificacionCompra";
			}
			catch (SaldoInsuficienteException e) {
				model.put("saldoInsuficiente", "El saldo de la tarjeta es insuficiente.");
				Preference preference = servicioMercadoPago.checkout(usuario, curso_obtenido.getPrecio());
				model.put("preference", preference);
				model.put("idCurso", curso_obtenido.getId());
				model.put("precioCurso", curso_obtenido.getPrecio());
				model.put("curso", curso_obtenido);
				viewName = "verificacionCompra";
			}
		    session.setAttribute("user", servicioUsuario.buscarUsuarioPorID(id_user));
		}
		return new ModelAndView(viewName, model);
	}

	@RequestMapping(path = "/cancelarCompra", method = RequestMethod.POST)
	public ModelAndView cancelarCompra(@RequestParam("curso_id") int idCurso, HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
		Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);
		
		if(servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario)) {
			try {
				// Si no se puedo cancelar el curso, se lanza una excepcion
				servicioUsuario.cancelarCurso(curso_obtenido, usuarioCurso);
				model.put("msj_exito", "La compra fue cancelada con exito!");
			}
			catch (Exception e) {
				model.put("msj_error", "La compra no puede ser cancelada luego de 48 horas");
			}
		}
		else {
			model.put("msj_error", "Curso no encontrado...");
		}
	    session.setAttribute("user", servicioUsuario.buscarUsuarioPorID(id_user));
		return new ModelAndView("redirect:/misCursos", model);
	}
	
	@RequestMapping(path = "/eliminarCompra", method = RequestMethod.POST)
	public ModelAndView eliminarCompra(@RequestParam("curso_id") int idCurso, HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id_user = (int) session.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
		
		if(servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario)) {
			servicioUsuario.eliminarCursoDelUsuario(curso_obtenido, usuario);
			model.put("msj_exito", "El curso '" + curso_obtenido.getNombre() + "' fue eliminado con exito!");
		}
		else {
			model.put("msj_error", "Curso no encontrado...");
		}
		return new ModelAndView("redirect:/misCursos", model);
	}
	
	@RequestMapping(path = "/comprarCursosDelCarrito", method = RequestMethod.GET)
	public ModelAndView comprarCursosDelCarrito(HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id_user = (int) session.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);
		// Se agregan los cursos a la lista del usuario
		List<Curso> cursos = servicioCarrito.obtenerCursosDelCarrito(carrito);
		servicioCarrito.comprarCursosDelCarrito(cursos, usuario);
		// Se vacia la lista del carrito
		List<Carrito_Curso> cursosCarrito = servicioCarrito.obtenerCarritoCursos(carrito);
		servicioCarrito.vaciarCursosDelCarrito(cursosCarrito, carrito, session);
		return new ModelAndView("compraRealizada", model);
	}

	@RequestMapping(path = "/pagoConMP", method = RequestMethod.GET)
	public ModelAndView pagoConMP(@RequestParam("idCurso") int idCurso, HttpSession session) {

		ModelMap model = new ModelMap();
		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
		Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);
		String viewName = "";
		if (servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario) && usuarioCurso.getEstado() == Estado.CANCELADO) {
			servicioCurso.cambiarEstadoCurso(usuarioCurso, Estado.EN_CURSO);
		}
		else {
			servicioUsuario.guardarCursoEnListaUsuario(curso_obtenido, usuario);
		}
		viewName = "compraRealizada";
		servicioUsuario.enviarNotificacion(usuario, "Compraste el curso " + curso_obtenido.getNombre(), session);
		return new ModelAndView(viewName, model);
	}
	
}