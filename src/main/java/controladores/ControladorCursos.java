package controladores;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import modelo.DatosActualizarCurso;
import modelo.DatosRegistro;
import modelo.DatosCreacionCurso;
import modelo.Estado;
import modelo.Usuario;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

@Controller
public class ControladorCursos {
	
	@Autowired
	private ServicioCurso servicioCurso;
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@RequestMapping(path = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar(@RequestParam("nombreCurso") String nombreCurso) {
		
		// Va a la BD y me trae el curso si existe y si no me muestra que no existe dentro de la misma vista
		ModelMap model = new ModelMap();
		
		// Devuelve una lista de cursos por su nombre
		List<Curso> busqueda_cursos = servicioCurso.getCursosPorNombre(nombreCurso);
		
		// Si la lista se encuantra vacia entonces se guarda un mensaje iformativo
		if(busqueda_cursos.isEmpty()) {
			model.put("sincurso", "No existen cursos, vuelva a ingresar el nombre en la barra de busqueda");
		}
		
		model.put("lista_cursos", busqueda_cursos);
		
		return new ModelAndView("seccionCursos", model);
	}
	
	@RequestMapping(path="/misCursos")
	public ModelAndView misCursos(HttpSession session, Model modelo,@ModelAttribute("curso_cancelado")Curso curso_cancelado){
		
		int id = (int) session.getAttribute("idUsuario");
		
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id);
		
		List<Curso> cursos = usuario.getMisCursos();
		
		modelo.addAttribute("lista_cursos", cursos);
	
		modelo.addAttribute("curso_cancelado",curso_cancelado);
		
		return new ModelAndView("miscursos");
	}
	
	// Se obtienen todos los registros de la tabla 'curso' de la bd
	@RequestMapping(path= "/verListaCursos", method= RequestMethod.GET)
	public ModelAndView verListaCursos(Model modelo, @ModelAttribute("error_sesion") String msj_sesion, @ModelAttribute("cursoYaComprado") String msj_curso, HttpSession session) {
		
		
		String view= "seccionCursos";
		List<Curso> cursos = servicioCurso.getCursos();
		
		modelo.addAttribute("lista_cursos", cursos);
		modelo.addAttribute("msj_error_sesion", msj_sesion);
		modelo.addAttribute("msj_error_curso", msj_curso);
		
		//Si el usuario no es nulo, y su rol es "admin", entonces se mostrara la seccion de cursos de administrador
		if(session.getAttribute("idUsuario") != null){
			if(session.getAttribute("ROL").equals("admin"))
			view = "seccionCursosAdmin";
		}
		
		return new ModelAndView(view);
	}
	
	// Se obtiene una lista de cursos por estado
	@RequestMapping(path= "/verCursosPorEstado", method= RequestMethod.GET)
	public ModelAndView verCursosPorEstado(@RequestParam("estado") Estado estado, Model modelo) {

		List<Curso> cursos = servicioCurso.getCursosPorEstado(estado);

		modelo.addAttribute("lista_cursos", cursos);

		return new ModelAndView("miscursos");
	}


	// Se obtiene una lista de cursos por categoria
	@RequestMapping(path= "/verCursosPorCategoria", method= RequestMethod.GET)
	public ModelAndView verCursosPorCategoria(@RequestParam("categoria") String categoria, Model modelo) {
		
		List<Curso> cursos = servicioCurso.getCursosPorCategoria(categoria);
		
		modelo.addAttribute("lista_cursos", cursos);
		
		return new ModelAndView("seccionCursos");
	}
	
	// Se obtiene la lista de los cursos comprados por el usuario y los muestra en la vista 'seccionCursos.jsp'
	@RequestMapping(path= "/verCursosDelUsuario", method= RequestMethod.GET)
	public ModelAndView verCursosDelUsuario(@RequestParam("email") String email, Model modelo) {
		
		Usuario usuario = servicioUsuario.buscarUsuarioPorEmail(email);
		
		List<Curso> cursos = usuario.getMisCursos();
		
		modelo.addAttribute("lista_cursos", cursos);
		
		return new ModelAndView("seccionCursos");
	}
	
	@RequestMapping("/agregarCurso")
	public ModelAndView irAAgregarCurso() {
		ModelMap modelo = new ModelMap();
		DatosCreacionCurso datosCrearCurso = new DatosCreacionCurso();
		modelo.put("datosCrearCurso", datosCrearCurso);
		return new ModelAndView("crearCurso", modelo);
		
	}
	
	@RequestMapping("/editarCurso")
	public ModelAndView irAEditarCurso(@RequestParam("nombre") String nombreCurso, @RequestParam("categoria") String catCurso, @RequestParam("descripcion") String descCurso, @RequestParam("precio") String precioCurso) {
		ModelMap modelo = new ModelMap();
		DatosCreacionCurso datosCrearCurso = new DatosCreacionCurso();
		modelo.put("datosCrearCurso", datosCrearCurso);
		modelo.put("nombreCurso", nombreCurso);
		modelo.put("catCurso", catCurso);
		modelo.put("descCurso", descCurso);
		modelo.put("precioCurso", precioCurso);
		return new ModelAndView("editarCurso", modelo);
		
	}

	@RequestMapping(path="/cursoAgregado", method = RequestMethod.POST)
	public ModelAndView agregarCurso(@ModelAttribute ("datosCrearCurso") DatosCreacionCurso datosCrearCurso) {
		ModelMap modelo=new ModelMap();
		
		servicioCurso.agregarCurso(datosCrearCurso.getNombre(), datosCrearCurso.getCategoria(), datosCrearCurso.getDescripcion(), datosCrearCurso.getPrecio(), datosCrearCurso.getImagen());
		modelo.put("datosCrearCurso", new DatosCreacionCurso());
		
		return new ModelAndView("cursoAgregado", modelo);
	}
	
//	@RequestMapping(path="/cursoActualizado", method = RequestMethod.POST)
//	public ModelAndView actualizarCurso(@RequestParam("id_curso") int idCurso, @ModelAttribute ("datosActualizarCurso") DatosActualizarCurso datosActualizarCurso) {
//		ModelMap modelo=new ModelMap();
//		
//		servicioCurso.actualizarCurso(idCurso, datosActualizarCurso.getNombre(), datosActualizarCurso.getCategoria(), datosActualizarCurso.getDescripcion(), datosActualizarCurso.getPrecio());
//		modelo.put("datosActualizarCurso", new DatosActualizarCurso());
//		
//		return new ModelAndView("cursoActualizado", modelo);
//	}
	
	@RequestMapping (path= "/descripcionCurso", method= RequestMethod.GET)
	public ModelAndView irADescCurso(@RequestParam("id_curso") Integer id_curso, Model modelo) {
		
		String view = "";
		
		try {
			Curso curso = servicioCurso.busquedaPorID(id_curso);
			modelo.addAttribute(curso);
			view = "descripcionCurso";
		}
		catch(Exception e) {
			view = "index";
		}
		
		return new ModelAndView(view);
	}
	
	@RequestMapping (path= "/verCurso", method= RequestMethod.POST)
	public ModelAndView verCurso(@RequestParam("curso_id") Integer curso_id) {
		
		ModelMap model = new ModelMap();
		
		Curso curso = servicioCurso.busquedaPorID(curso_id);
		model.put("curso", curso);
		
		return new ModelAndView("vistaCurso", model);
	}
	

}
