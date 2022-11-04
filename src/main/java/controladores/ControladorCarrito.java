package controladores;

import java.util.List;

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


import modelo.*;
import servicios.*;


@Controller
public class ControladorCarrito {
	
	private ServicioCurso servicioCurso;
	private ServicioCarrito servicioCarrito;
	
	@Autowired
	public ControladorCarrito(ServicioCurso servicioCurso, ServicioCarrito servicioCarrito) {
		this.servicioCurso = servicioCurso;
		this.servicioCarrito = servicioCarrito;
	}


	@RequestMapping(path ="/vistaCarrito", method = RequestMethod.GET)
	public ModelAndView vistaCarrito(HttpSession sesion) {
		
	    ModelMap model = new ModelMap();
	    String view = "";
	    
	    // Si comprueba si el usuario tiene iniciada la sesión
	 	if (sesion.getAttribute("idUsuario") != null) {
	 		
	 		try {
	 			int id_user = (int) sesion.getAttribute("idUsuario");
				Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);
				
				// Si no se obtiene ningun curso del carrito entonces se lanza una excepcion
				List<Curso> cursos = servicioCarrito.obtenerCursosDelCarrito(carrito);
				
			    double preciosTotal = servicioCarrito.getTotalDePrecios(cursos);
				
				model.put("lista_cursos_carrito", cursos);
				model.put("precio_total", preciosTotal);
				view = "carrito";
			}
	 		catch (Exception e) {
	 			// Si el carrito se encuentra vacio entonces se guarda un mensaje iformativo
	 			model.put("msj", "El carrito se encuentra vacio.");
	 			view = "carrito";
			}
	 	}
	 	else {
	 		view = "redirect:/";
	 	}
		
		return new ModelAndView(view, model);
	}
	
	
	//Cambiar a post si es necesario 
	@RequestMapping(path ="/agregarCursoAlCarrito", method = RequestMethod.GET)
    public ModelAndView agregarCursoAlCarrito(@RequestParam("id_curso") int id, HttpSession sesion) {
	
		int id_user = (int) sesion.getAttribute("idUsuario");
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(id);
		Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);
		
		servicioCarrito.agregarCursoAlCarrito(curso_obtenido, carrito);
		
	    //Mostrar un cartel que diga agregaste al carrito con exito 	
		return new ModelAndView("redirect:/verListaCursos");
    }
	
	
	@RequestMapping(path ="/eliminarCursoDeListaCarrito", method = RequestMethod.GET)
    public ModelAndView eliminarCursoDeListaCarrito(@RequestParam("curso_id") int curso_id, HttpSession sesion) {
	
		int id_user = (int) sesion.getAttribute("idUsuario");
		Curso curso = servicioCurso.buscarCursoPorId(curso_id);
		Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);
		Carrito_Curso carritoCurso = servicioCarrito.obtenerCarritoCurso(carrito, curso);
		
		servicioCarrito.eliminarCursoDelCarrito(carritoCurso);
		
		return new ModelAndView("redirect:/vistaCarrito");
    }

}
