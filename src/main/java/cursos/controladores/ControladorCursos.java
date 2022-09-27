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
import servicios.ServicioCurso;

@Controller
public class ControladorCursos {
	
	@Autowired
	private ServicioCurso servicioCurso;
	
	@RequestMapping(path = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar( Model modelo) {
		
		//va a la BD y me trae el curso si existe y si no me muestra que no existe dentro de la misma vista
		
		List<Curso> listaCurso = servicioCurso.busqueda(); //devuelve la lista de cursos
		
		modelo.addAttribute("busqueda_curso", listaCurso);
	
		return new ModelAndView("vistaBuscar");
	}
	
	@RequestMapping(path= "/cursos", method= RequestMethod.GET)
	public ModelAndView listaDeCursos() {
		
		ModelMap modelo = new ModelMap();
		
		return new ModelAndView("seccionCursos",modelo);
	}

}
