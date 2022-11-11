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
	
	private boolean ingresaPorPrimeraVez = true;
	
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
		
	//	servicioPrincipal.insertarRegistros();
		
		/* De ser necesario, descomentar este metodo, eliminar la base de datos "proyecto_cursos", 
		 * volverla a crear con el mismo nombre, ejecutar el proyecto para que se inserten 
		 * todos los registros y una vez que se termine de ejecutar detener el servidor.
		 * 
		 * Después, volver a comentar el metodo y volver a ejecutar el proyecto para comprobar
		 * que se insertaron los registros correctamente.
		 */
		
		
		// Se establece una imagen por defecto a todos los usuarios al ejecutar el proyecto
		if (ingresaPorPrimeraVez) {
			
			servicioPrincipal.actualizarImagenDeUsuarios();
			ingresaPorPrimeraVez = false;
		}
		
        return new ModelAndView("index");
    }

}
