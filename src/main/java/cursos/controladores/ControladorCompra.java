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

public class ControladorCompra {
	

	@RequestMapping(path = "/comprar", method = RequestMethod.GET) //Cambiarlo a metodo post 
	public ModelAndView verificacionC() {
		
		ModelMap modelo = new ModelMap();
		
		return new ModelAndView("verificacionCompra",modelo);
		
		
	}
	
	
	@RequestMapping(path = "/verificarCompra", method = RequestMethod.POST) //Cambiarlo a metodo post 
	public ModelAndView verificacionCompra() {
		
		ModelMap modelo = new ModelMap();
		
		return new ModelAndView("compraRealizada",modelo);
		
		
	}
	
	
	

}
