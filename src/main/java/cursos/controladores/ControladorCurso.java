package cursos.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import modelo.Curso;
import servicios.ServicioCurso;

@Controller
public class ControladorCurso {
	
	@Autowired
	private ServicioCurso servicioCurso;
	
	@RequestMapping(path = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar(@RequestParam("descripcion")String descripcion) {//Model modelo
		
		//va a la BD y me trae el curso si existe y si no me muestra que no existe dentro de la misma vista
		
		ModelMap model = new ModelMap();
		
		//buscar curso por descripcion
		List<Curso> busqueda_curso = servicioCurso.busqueda(descripcion); //devuelve la lista de cursos
		
		if(busqueda_curso.isEmpty()){
			model.put("sincurso","No existen cursos, vuelva a ingresar el nombre en la barra de busqueda");
			
		}
		
		model.put("busqueda_curso",busqueda_curso);
		return new ModelAndView("vistaBuscar",model);
	

	}
	
	@RequestMapping(path="/miseccion")
	public ModelAndView miseccion(){
		return new ModelAndView("miscursos");
	}

}
