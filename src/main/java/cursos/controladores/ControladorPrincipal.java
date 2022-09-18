package cursos.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorPrincipal {

	// Escucha la url /, y redirige a la URL /home
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		
		return new ModelAndView("redirect:/home");
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		
		return new ModelAndView("index");
	}
	
	@RequestMapping(path = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar(@RequestParam("txtCurso") String busqueda, Model modelo) {
		
		//va a la BD y me trae el curso si existe y si no me muestra que no existe dentro de la misma vista
		
		modelo.addAttribute("busqueda_curso", busqueda);
		
		return new ModelAndView("cursos");
	}

}
