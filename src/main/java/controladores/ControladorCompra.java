package controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import servicios.ServicioCarrito;
import modelo.*;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

@Controller
public class ControladorCompra {
	
	private ServicioUsuario servicioUsuario;
	private ServicioCurso servicioCurso;
	private ServicioCarrito servicioCarrito;
	
	@Autowired
	public ControladorCompra(ServicioUsuario servicioUsuario, ServicioCurso servicioCurso, ServicioCarrito servicioCarrito) {
		this.servicioUsuario = servicioUsuario;
		this.servicioCurso = servicioCurso;
		this.servicioCarrito = servicioCarrito;
	}


	@RequestMapping(path = "/comprar", method = RequestMethod.GET)
	public ModelAndView verificacionCompra(@RequestParam("id_curso") int idCurso, @RequestParam("precio") Double precioCurso, HttpSession session) {
		
		ModelMap model = new ModelMap();
		String viewName = "";
		
		// Si comprueba si el usuario tiene iniciada la sesión
		if (session.getAttribute("idUsuario") != null) {
			
			int id_user = (int) session.getAttribute("idUsuario");
			Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
			Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
			Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);

			if(!servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario) || usuarioCurso.getEstado() == Estado.CANCELADO) {
				model.put("idCurso", idCurso);
				model.put("precioCurso", precioCurso);
				model.put("curso", curso_obtenido);
				viewName = "verificacionCompra";
			}
			else {
				model.addAttribute("cursoYaComprado", "El curso ya fue comprado, compre otro curso.");
				viewName = "redirect:/verListaCursos";
				//servicioNotificacion.enviar(usuario, "El curso ya fue comprado, compre otro curso.");
			}
		}
		else {
			model.addAttribute("error_sesion", "Para comprar necesitas ingresar a tu cuenta.");
			viewName = "redirect:/verListaCursos";
		}
		
		return new ModelAndView(viewName, model);
	}
	
	
	@RequestMapping(path = "/verificarCompra", method = RequestMethod.POST)
	public ModelAndView verificarCompra(@RequestParam("nroTarjeta") Integer nroTarjeta, @RequestParam("curso_id") int curso_id, HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(curso_id);
		Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);
		String viewName = "";
		
		try {
			// Se verifica si el numero de tarjeta del usuario es igual al numero de tarjeta ingresado.
			// Si no son iguales, lanza una excepcion.
			servicioUsuario.verificarTarjetaUsuario(usuario, nroTarjeta);			
				
			if (servicioUsuario.existeCursoEnListaUsuario(curso_id, usuario) && usuarioCurso.getEstado() == Estado.CANCELADO) {
				
				servicioCurso.cambiarEstadoCurso(usuarioCurso, Estado.EN_CURSO);
			}
			else {
				servicioUsuario.guardarCursoEnListaUsuario(curso_obtenido, usuario);
			}
			viewName = "compraRealizada";
			
			servicioUsuario.enviarNotificacion(usuario, "Compraste el curso " + curso_obtenido.getNombre(), session);
		}
		catch (Exception e) {
			model.put("tarjetaIncorrecta", "El número de tarjeta ingresado es incorrecto.");
			model.put("idCurso", curso_obtenido.getId());
			model.put("precioCurso", curso_obtenido.getPrecio());
			viewName = "verificacionCompra";
		}
		
		return new ModelAndView(viewName, model);
	}
	
	//FUNCIONALIDAD CANCELAR
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
				
				model.put("msj", "La compra fue cancelada con exito!");
			}
			catch (Exception e) {
				model.put("msj", "La compra no puede ser cancelada luego de 48 horas");
			}
		}
		else {
			model.put("msj", "Curso no encontrado...");
			
		}
		
		return new ModelAndView("redirect:/misCursos", model);
	}
	
	@RequestMapping(path = "/eliminarCompra", method = RequestMethod.POST)
	public ModelAndView eliminarCompra(@RequestParam("curso_id") int idCurso, HttpSession session) {
		
		ModelMap model = new ModelMap();
		
		int id_user = (int) session.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
		
		if(servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario)) {
			
			servicioUsuario.eliminarCurso(curso_obtenido, usuario);
			model.put("msj", "El curso '" + curso_obtenido.getNombre() + "' fue eliminado con exito!");
		}
		else {
			model.put("msj", "Curso no encontrado...");
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
		servicioCarrito.vaciarCursosDelCarrito(cursosCarrito);
	
		return new ModelAndView("compraRealizada", model);
	}
	
	
	@RequestMapping(path = "/verMediosDePago", method = RequestMethod.POST)
	public ModelAndView verMediosDePago(@RequestParam("precio") Double total, @RequestParam("id_curso") int idCurso) {
		
		ModelMap model = new ModelMap();
		
		Curso curso = servicioCurso.buscarCursoPorId(idCurso);
		
		model.put("precioTotal", total);
		model.put("curso", curso);
		
		return new ModelAndView("mediosDePago", model);
	}
	
}
