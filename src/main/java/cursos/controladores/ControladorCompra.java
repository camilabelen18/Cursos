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
import modelo.Usuario;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

@Controller
public class ControladorCompra {
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	private ServicioCurso servicioCurso;

	@RequestMapping(path = "/comprar")
	public String verificacionCompra(HttpServletRequest request, Model modelo) {

		int idCurso = Integer.parseInt(request.getParameter("idcurso"));
		
		String compra = request.getParameter("comprarAhora");
		
		Curso curso = servicioCurso.busquedaPorID(idCurso);
		
		modelo.addAttribute("curso", curso);
								
		return "verificacionCompra";
	}
	
		
	@RequestMapping(path = "/verificarCompra", method = RequestMethod.POST)
	public ModelAndView verificarCompra(@RequestParam("nroTarjeta") Integer nroTarjeta, 
			@RequestParam("email") String email, @ModelAttribute("curso") Curso curso) {
		
		Curso curso_obtenido = servicioCurso.busquedaPorID(curso.getId());
		
		Usuario usuario = servicioUsuario.buscarUsuarioPorEmail(email);
		
		try {
			
			if(usuario.getNroTarjeta().equals(nroTarjeta)) {
				
				usuario.getListaCursos().add(curso_obtenido);
				
				return new ModelAndView("index");
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		
						
		return new ModelAndView("verificacionCompra");
	}

	

}
