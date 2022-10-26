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

import modelo.Carrito;
import modelo.Carrito_Curso;
import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;
import servicios.ServicioCarrito;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

@Controller
public class ControladorCompra {
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	private ServicioCurso servicioCurso;
	
	@Autowired
	private ServicioCarrito servicioCarrito;

	@RequestMapping(path = "/comprar", method = RequestMethod.GET)
	public ModelAndView verificacionCompra(@RequestParam("id_curso") int idCurso, @RequestParam("precio") Double precioCurso, HttpSession session) {
		
		ModelMap model = new ModelMap();
		String viewName = "";
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
		
		// Si comprueba si el usuario tiene iniciada la sesión
		if (session.getAttribute("idUsuario") != null) {
			
			int id_user = (int) session.getAttribute("idUsuario");
			Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
			
			System.out.println("Comprobando si existe curso..");
			//Se comprueba que el curso no se encuentre en la lista de cursos del usuario
			

			if(!servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario) || curso_obtenido.getEstado() == Estado.CANCELADO) {
				model.put("idCurso", idCurso);
				model.put("precioCurso", precioCurso);
				model.put("curso", curso_obtenido);
				viewName = "verificacionCompra";
			}
			else {
				model.addAttribute("cursoYaComprado", "El curso ya fue comprado, compre otro curso.");
				viewName = "redirect:/verListaCursos";
			}
		}
		else {
			model.addAttribute("error_sesion", "Para comprar necesitas ingresar a tu cuenta.");
			viewName = "redirect:/verListaCursos";
		}
		
		return new ModelAndView(viewName, model);
	}
	
	
	@RequestMapping(path = "/verificarCompra", method = RequestMethod.POST)
	public ModelAndView verificarCompra(@RequestParam("nroTarjeta") Integer nroTarjeta, @RequestParam("curso_id") int id, HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(id);
		String viewName = "";

		// Se verifica si el numero de tarjeta del usuario es igual al numero de tarjeta ingresado
		if (usuario.getNroTarjeta().equals(nroTarjeta)) {
			if(curso_obtenido.getEstado()==Estado.CANCELADO) {
				servicioCurso.cambiarEstadoCurso(curso_obtenido,Estado.EN_CURSO);
			}else {
				servicioUsuario.guardarCursoEnListaUsuario(curso_obtenido, usuario);
			}
			
			viewName = "compraRealizada";

		}
		else {
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
		
		if(servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario)) {
			
			servicioUsuario.cancelarCurso(curso_obtenido);
			model.put("msj", "La compra fue cancelada con exito!");
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
	
}
