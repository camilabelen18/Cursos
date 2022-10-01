package cursos.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import modelo.Curso;
import modelo.Usuario;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

@Controller
public class ControladorCursos {
	
	@Autowired
	private ServicioCurso servicioCurso;
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@RequestMapping(path = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar(@RequestParam("nombreCurso") String nombreCurso) {
		
		// Va a la BD y me trae el curso si existe y si no me muestra que no existe dentro de la misma vista
		ModelMap model = new ModelMap();
		
		// Devuelve una lista de cursos por su nombre
		List<Curso> busqueda_cursos = servicioCurso.getCursosPorNombre(nombreCurso);
		
		// Si la lista se encuantra vacia entonces se guarda un mensaje iformativo
		if(busqueda_cursos.isEmpty()) {
			model.put("sincurso", "No existen cursos, vuelva a ingresar el nombre en la barra de busqueda");
		}
		
		model.put("lista_cursos", busqueda_cursos);
		
		return new ModelAndView("seccionCursos", model);
	}
	
	@RequestMapping(path="/miseccion")
	public ModelAndView miseccion(){
		return new ModelAndView("miscursos");
	}
	
	// Se obtienen todos los registros de la tabla 'curso' de la bd
	@RequestMapping(path= "/verListaCursos", method= RequestMethod.GET)
	public ModelAndView verListaCursos(Model modelo) {
		
		List<Curso> cursos = servicioCurso.getCursos();
		
		modelo.addAttribute("lista_cursos", cursos);
		
		return new ModelAndView("seccionCursos");
	}

	
	// Se obtiene una lista de cursos por categoria
	@RequestMapping(path= "/verCursosPorCategoria", method= RequestMethod.GET)
	public ModelAndView verCursosPorCategoria(@RequestParam("categoria") String categoria, Model modelo) {
		
		List<Curso> cursos = servicioCurso.getCursosPorCategoria(categoria);
		
		modelo.addAttribute("lista_cursos", cursos);
		
		return new ModelAndView("seccionCursos");
	}
	
	// Se obtiene la lista de los cursos comprados por el usuario y los muestra en la vista 'seccionCursos.jsp'
	@RequestMapping(path= "/verCursosDelUsuario", method= RequestMethod.GET)
	public ModelAndView verCursosDelUsuario(@RequestParam("email") String email, Model modelo) {
		
		Usuario usuario = servicioUsuario.buscarUsuarioPorEmail(email);
		
		List<Curso> cursos = usuario.getMisCursos();
		
		modelo.addAttribute("lista_cursos", cursos);
		
		return new ModelAndView("seccionCursos");
	}

}
