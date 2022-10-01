package cursos.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import modelo.Curso;
import modelo.Usuario;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

@Controller
public class ControladorRegistroUsuario {
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@RequestMapping("/registroUsuario")
	public ModelAndView irAAgregarCurso() {
		return new ModelAndView("registroUsuario");
		
	}

	@RequestMapping(path="/usuarioRegistrado", method = RequestMethod.POST)
	public ModelAndView agregarCurso(@RequestParam("nombreUsuario") String nombreUsuario, @RequestParam("emailUsuario") String emailUsuario, @RequestParam("pass1") String pass1, @RequestParam("nroTarjeta") Integer nroTarjeta) {
		ModelMap modelo=new ModelMap();
		Usuario usuario = new Usuario();
		usuario.setNombre(nombreUsuario);
		usuario.setEmail(emailUsuario);
		usuario.setPassword(pass1);
		usuario.setNroTarjeta(nroTarjeta);
		
		servicioUsuario.agregarUsuario(usuario);
		
		return new ModelAndView("usuarioRegistrado", modelo);
	}

}
