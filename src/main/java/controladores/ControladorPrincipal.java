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
		
	//	servicioPrincipal.insertarRegistros();
		
		/* De ser necesario, descomentar este metodo, eliminar la base de datos "proyecto_cursos", 
		 * volverla a crear con el mismo nombre y ejecutar el proyecto para que se inserten 
		 * todos los registros.
		 * Después, volver a comentar el metodo.
		 */
		
        return new ModelAndView("index");
    }

}
