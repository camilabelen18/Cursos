package controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import modelo.*;
import servicios.*;

@Controller
public class ControladorGiftcard {
	
	private ServicioGiftcard servicioGiftcard;
	
	@Autowired
	public ControladorGiftcard(ServicioGiftcard servicioGiftcard) {
		this.servicioGiftcard = servicioGiftcard;
	}
	

	@RequestMapping(path ="/verGiftcard", method = RequestMethod.GET)
	public ModelAndView verGiftcard() {
		
		ModelMap model = new ModelMap();
		
		return new ModelAndView("miGiftcard", model);
	}

}
