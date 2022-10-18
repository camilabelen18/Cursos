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


import modelo.Carrito;
import modelo.Curso;
import modelo.Usuario;
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

	
	//Cambiar a post si es necesario 
	
	@RequestMapping(path ="/carrito", method = RequestMethod.GET)
    public ModelAndView carrito(@RequestParam("id_curso") int id,HttpSession sesion) {
	
	    ModelMap model = new ModelMap();
		
		int id_user = (int) sesion.getAttribute("idUsuario");
		
		Curso curso_obtenido = servicioCurso.busquedaPorID(id);
		
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		
		
	    servicioUsuario.agregarCursoAlCarrito(curso_obtenido,usuario.getCarrito());
	    
	    
	    Set<Curso> cursos = usuario.getCarrito().getCursosDelCarrito();
	    
	    
	    double preciosTotal= servicioUsuario.getTotalDePrecios(cursos);
		
		model.put("lista_cursos_carrito",cursos );
		model.put("precio_total", preciosTotal);
	
	   //Mostrar un cartel que diga agregaste al carrito con exito 	
		return new ModelAndView("carrito", model);
		
	
		
    }
	
	
	@RequestMapping(path ="/vistaCarrito", method = RequestMethod.GET)
	public ModelAndView vistaCarrito(HttpSession sesion) {
		
	    ModelMap model = new ModelMap();
		
		int id_user = (int) sesion.getAttribute("idUsuario");
		
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		
		Set<Curso> cursos = usuario.getCarrito().getCursosDelCarrito();
	    
	    double preciosTotal= servicioUsuario.getTotalDePrecios(cursos);
		
		model.put("lista_cursos_carrito",cursos );
		model.put("precio_total", preciosTotal);
	
		
		return new ModelAndView("carrito", model);
		
	}
	
	
	
	
	
	
	
	
	
	
/*	
	@RequestMapping(path ="/carrito", method = RequestMethod.GET)
    public ModelAndView carrito(@RequestParam("id_curso") int id) {
		//boton en descripcion de agregar carrito, ese boton tiene un id que me identifica el carrito
		//idcurso y algo de carrito para guardar el curso en el carrito
		//pasar el idcurso y obtener el curso
	 	//servicio.guardarCursoEnElCarrito(carrito,cursoobtenido)
		
		
		ModelMap model = new ModelMap();
	//	String viewName="";
		
		Curso curso_obtenido = servicioCurso.busquedaPorID(id);
		
	//	int id_carrito = (int) session.getAttribute("idCarrito");
		

	    Carrito carrito = servicioCarrito.buscarCarritoPorId(id);
		
		servicioCarrito.guardarCursoEnListaDeCarrito(curso_obtenido,carrito);
		
		List<Curso> cursos_carrito = carrito.getCursosDelCarrito();
		
		
        model.put("listaCarrito", cursos_carrito);
		
		
		
		
 //		viewName="carrito";
		
		return new ModelAndView("carrito", model);
		
    }
	
	*/
	
	
	
	
	
	

}
