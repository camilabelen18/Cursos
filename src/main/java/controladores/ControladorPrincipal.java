package controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import servicios.ServicioPrincipal;

@Controller
public class ControladorPrincipal {
	
	@Autowired
	private ServicioPrincipal servicioPrincipal;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
		
		// Se insertan todos los registros necesarios del sistema
		servicioPrincipal.insertarRegistros();
		
        return new ModelAndView("index");
    }

}
