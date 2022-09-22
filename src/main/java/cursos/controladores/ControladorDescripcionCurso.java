package cursos.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorDescripcionCurso {

	
	@RequestMapping ("/descripcionCurso")
	public ModelAndView irADescCurso() {
		return new ModelAndView("descripcionCurso");
	}
}
