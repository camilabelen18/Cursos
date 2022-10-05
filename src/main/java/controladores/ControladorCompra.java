package controladores;

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

import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

@Controller
public class ControladorCompra {
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	private ServicioCurso servicioCurso;

	@RequestMapping(path = "/comprar", method = RequestMethod.GET)
	public ModelAndView verificacionCompra(@RequestParam("id_curso") int idCurso, @RequestParam("precio") Double precioCurso, HttpSession session) {
		
		ModelMap model = new ModelMap();
		String viewName = "";
		
		// Si comprueba si el usuario tiene iniciada la sesión
		if (session.getAttribute("idUsuario") != null) {
			
			int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
			Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
			
			//Se comprueba que el curso no se encuentre en la lista de cursos del usuario
			if(!servicioUsuario.existeCursoEnListaUsuario(idCurso, usuario)) {
				
				model.put("idCurso", idCurso);
				model.put("precioCurso", precioCurso);
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
		String viewName = "";

		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.busquedaPorID(id);

		// Se verifica si el numero de tarjeta del usuario es igual al numero de tarjeta ingresado
		if (usuario.getNroTarjeta().equals(nroTarjeta)) {

			servicioUsuario.guardarCursoEnListaUsuario(curso_obtenido, usuario);
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

}
