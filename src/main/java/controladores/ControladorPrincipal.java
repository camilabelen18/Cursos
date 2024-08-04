package controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorPrincipal {

	@RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {		
        return new ModelAndView("redirect:/home");
    }

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAInicio() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(path = "/contacto", method = RequestMethod.GET)
	public ModelAndView irAContacto() {
		return new ModelAndView("contacto");
	}

}
