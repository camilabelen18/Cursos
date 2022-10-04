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
	
	private static boolean ingresaPorPrimeraVez = true;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
		
		if (ingresaPorPrimeraVez) {
			
			// Se insertan todos los registros necesarios del sistema
			servicioPrincipal.insertarRegistros();
			
			ingresaPorPrimeraVez = false;
		}
		
        return new ModelAndView("index");
    }

}
