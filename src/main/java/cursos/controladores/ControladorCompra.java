package cursos.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

@Controller
public class ControladorCompra {
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	private ServicioCurso servicioCurso;

	@RequestMapping(path = "/comprar", method = RequestMethod.GET)
	public ModelAndView verificacionCompra(@RequestParam("id_curso") int id, Model modelo) {
		
		modelo.addAttribute("idCurso", id);
								
		return new ModelAndView("verificacionCompra");
	}
	
		
	@RequestMapping(path = "/verificarCompra", method = RequestMethod.POST)
	public ModelAndView verificarCompra(@RequestParam("nroTarjeta") Integer nroTarjeta, 
			@RequestParam("email") String email, @RequestParam("curso_id") int id) {
		
		try {
			
			Curso curso_obtenido = servicioCurso.busquedaPorID(id);
			
			Usuario usuario = servicioUsuario.buscarUsuarioPorEmail(email);
			
			// Se verifica si el numero de tarjeta del usuario es igual al numero de tarjeta ingresado
			
			if (usuario.getNroTarjeta().equals(nroTarjeta)) {
				
				

				servicioUsuario.guardarCursoEnListaUsuario(curso_obtenido, usuario);
			    
				return new ModelAndView("compraRealizada");
			}
			
		}
		catch (Exception e) {
			e.getMessage();
		}
						
		return new ModelAndView("verificacionCompra");
	}

}
