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
import servicios.ServicioCurso;

@Controller
public class ControladorAgregarCurso {
	

	@Autowired
	private ServicioCurso servicioCurso;
	
	@RequestMapping("/agregarCurso")
	public ModelAndView irAAgregarCurso() {
		return new ModelAndView("crearCurso");
		
	}

	@RequestMapping(path="/cursoAgregado", method = RequestMethod.POST)
	public ModelAndView agregarCurso(@RequestParam("nombreCurso") String nombreCurso, @RequestParam("descCurso") String descCurso, @RequestParam("precioCurso") Double precioCurso) {
		ModelMap modelo=new ModelMap();
		Curso curso = new Curso();
		curso.setNombre(nombreCurso);
		curso.setDescripcion(descCurso);
		curso.setPrecio(precioCurso);
		servicioCurso.agregarCurso(curso);
		
		return new ModelAndView("cursoAgregado", modelo);
	}


}
