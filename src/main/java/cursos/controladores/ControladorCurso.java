package cursos.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCurso {
	
	@RequestMapping(path = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar(@RequestParam("txtCurso") String busqueda, Model modelo) {
		
		//va a la BD y me trae el curso si existe y si no me muestra que no existe dentro de la misma vista
		
		modelo.addAttribute("busqueda_curso", busqueda);
		
		return new ModelAndView("cursos");
	}

}
