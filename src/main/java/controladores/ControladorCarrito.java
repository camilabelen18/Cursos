package controladores;

import java.util.List;
import java.util.Set;

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


import modelo.*;
import servicios.ServicioCarrito;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;


@Controller
public class ControladorCarrito {
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	private ServicioCurso servicioCurso;
	
	@Autowired
	private ServicioCarrito servicioCarrito;

	
	@RequestMapping(path ="/vistaCarrito", method = RequestMethod.GET)
	public ModelAndView vistaCarrito(HttpSession sesion) {
		
	    ModelMap model = new ModelMap();	    
		int id_user = (int) sesion.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Carrito carrito = usuario.getCarrito();
		
		Set<Curso> cursos = carrito.getCursosDelCarrito();
	    
		servicioCarrito.calcularTotal(carrito);
		
	    double preciosTotal = carrito.getTotal();
		
		model.put("lista_cursos_carrito", cursos);
		model.put("precio_total", preciosTotal);
		
		return new ModelAndView("carrito", model);
	}
	
	
	//Cambiar a post si es necesario 
	@RequestMapping(path ="/agregarCursoAlCarrito", method = RequestMethod.GET)
    public ModelAndView agregarCursoAlCarrito(@RequestParam("id_curso") int id, HttpSession sesion) {
	
		int id_user = (int) sesion.getAttribute("idUsuario");
		Curso curso_obtenido = servicioCurso.busquedaPorID(id);
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Carrito carrito = usuario.getCarrito();
		
		servicioCarrito.agregarCursoAlCarrito(curso_obtenido, carrito);
		
	    //Mostrar un cartel que diga agregaste al carrito con exito 	
		return new ModelAndView("index");
    }

}
