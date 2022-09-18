package cursos.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller

public class ControladorPruebaGabriel {
	
	@RequestMapping(path = "/hola", method = RequestMethod.GET)
	public ModelAndView saludar1() {
		ModelMap modelo = new ModelMap();
		modelo.put("mensaje1","Hola como estas?");
		modelo.put("mensaje2"," bien,soy gabriel");
		return new ModelAndView("hola",modelo);
	}
	

}
