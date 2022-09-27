package cursos.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import modelo.Curso;
import modelo.Usuario;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

import javax.servlet.http.HttpServletRequest;

@Controller

public class ControladorCompra {
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	private ServicioCurso servicioCurso;

	@RequestMapping(path = "/comprar", method = RequestMethod.GET) //Cambiarlo a metodo post 
	public ModelAndView verificacionC() {
		
		ModelMap modelo = new ModelMap();
				
		return new ModelAndView("verificacionCompra",modelo);		
		
	}
	
		
	@RequestMapping(path = "/verificarCompra", method = RequestMethod.POST) //Cambiarlo a metodo post 
	public ModelAndView verificarCompra(@RequestParam("nroTarjeta") Integer nroTarjeta, 
			@RequestParam("email") String email) {
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuario = servicioUsuario.buscarUsuarioPorEmail(email);
		
		try {
			if(usuario.getNroTarjeta().equals(nroTarjeta)) {
				//usuario.getListaCursos().add();//falta recibir el curso
				return new ModelAndView("compraRealizada");
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		//else que pida que ingrese de nuevo la tarjeta
				
		return new ModelAndView("verificacionCompra",modelo);
				
	}

	
	//si la tarjeta es valida compra
	//guardar lo que compro
	//vista de seccion de compra me devuelve el id, buscar por id y lo guardo en la lista de cursos


//validar si la tarjeta que esta en el request param es la misma que esta en la base de datos

//validar nro tarjeta
//cuando haces click en comprar te lleva a una vista: que compre o que no compre
//guardar lo que compro (datos del curso) Lista de cursos relacionada a lo que compro el cliente


	
	

}
