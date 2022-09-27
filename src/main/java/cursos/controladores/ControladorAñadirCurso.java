package cursos.controladores;

import javax.inject.Inject;

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
public class ControladorA�adirCurso {
	

	@Autowired
	private ServicioCurso servicioCurso;
	
	@RequestMapping("/a�adirCurso")
	public ModelAndView irAA�adirCurso() {
		return new ModelAndView("crearCurso");
		
	}

	@RequestMapping(path="/cursoA�adido", method = RequestMethod.POST)
	public ModelAndView a�adirCurso(@RequestParam("nombreCurso") String nombreCurso, @RequestParam("descCurso") String descCurso, @RequestParam("precioCurso") Double precioCurso) {
		ModelMap modelo=new ModelMap();
		Curso curso = new Curso();
		curso.setNombre(nombreCurso);
		curso.setDescripcion(descCurso);
		curso.setPrecio(precioCurso);
		servicioCurso.a�adirCurso(curso);
		
		
		return new ModelAndView("cursoA�adido", modelo);
	}


}
