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

import modelo.*;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

@Controller
public class ControladorCursos {
	
	private ServicioCurso servicioCurso;
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	public ControladorCursos(ServicioCurso servicioCurso, ServicioUsuario servicioUsuario) {
		this.servicioCurso = servicioCurso;
		this.servicioUsuario = servicioUsuario;
	}
	

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
	
	@RequestMapping(path="/misCursos", method= RequestMethod.GET)
	public ModelAndView misCursos(HttpSession session, @ModelAttribute("msj") String msj) {
		
		ModelMap model = new ModelMap();
		
		int id = (int) session.getAttribute("idUsuario");
		
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id);
		
		List<Curso> cursos = servicioUsuario.obtenerCursosDelUsuario(usuario);
		
		model.put("lista_cursos", cursos);
		model.put("msj", msj);
		
		return new ModelAndView("miscursos", model);
	}
	
	// Se obtienen todos los registros de la tabla 'curso' de la bd
	@RequestMapping(path= "/verListaCursos", method= RequestMethod.GET)
	public ModelAndView verListaCursos(@ModelAttribute("error_sesion") String msj_sesion, @ModelAttribute("cursoYaComprado") String msj_curso, HttpSession session) {
		
		ModelMap model = new ModelMap();
		List<Curso> cursos = servicioCurso.getCursos();
		String view= "seccionCursos";
		
		model.put("lista_cursos", cursos);
		model.put("msj_error_sesion", msj_sesion);
		model.put("msj_error_curso", msj_curso);
		
		//Si el usuario no es nulo, y su rol es "admin", entonces se mostrara la seccion de cursos de administrador
		if(session.getAttribute("idUsuario") != null) {
			
			if(session.getAttribute("ROL").equals("admin")) {
				view = "seccionCursosAdmin";
			}
		}
		
		return new ModelAndView(view, model);
	}
	
	// Se obtiene una lista de cursos por estado
	@RequestMapping(path= "/verCursosPorEstado", method= RequestMethod.GET)
	public ModelAndView verCursosPorEstado(@RequestParam("estado") Estado estado) {

		ModelMap model = new ModelMap();
		List<Curso> cursos = servicioCurso.getCursosPorEstado(estado);

		model.put("lista_cursos", cursos);

		return new ModelAndView("miscursos", model);
	}


	// Se obtiene una lista de cursos por categoria
	@RequestMapping(path= "/verCursosPorCategoria", method= RequestMethod.GET)
	public ModelAndView verCursosPorCategoria(@RequestParam("categoria") String categoria) {
		
		ModelMap model = new ModelMap();
		List<Curso> cursos = servicioCurso.getCursosPorCategoria(categoria);
		
		model.put("lista_cursos", cursos);
		
		return new ModelAndView("seccionCursos", model);
	}
	
	@RequestMapping("/agregarCurso")
	public ModelAndView irAAgregarCurso() {
		ModelMap modelo = new ModelMap();
		DatosCreacionCurso datosCrearCurso = new DatosCreacionCurso();
		modelo.put("datosCrearCurso", datosCrearCurso);
		return new ModelAndView("crearCurso", modelo);
	}
	
	@RequestMapping("/editarCurso")
	public ModelAndView irAEditarCurso(@RequestParam("id_curso") int cursoID, @RequestParam("nombre") String nombreCurso, @RequestParam("categoria") String catCurso, @RequestParam("descripcion") String descCurso, @RequestParam("precio") String precioCurso) {
		ModelMap modelo = new ModelMap();
		DatosCreacionCurso datosCrearCurso = new DatosCreacionCurso();
		modelo.put("datosCrearCurso", datosCrearCurso);
		modelo.put("nombreCurso", nombreCurso);
		modelo.put("catCurso", catCurso);
		modelo.put("descCurso", descCurso);
		modelo.put("precioCurso", precioCurso);
		modelo.put("cursoID", cursoID);
		return new ModelAndView("editarCurso", modelo);
		
	}

	@RequestMapping(path="/cursoAgregado", method = RequestMethod.POST)
	public ModelAndView agregarCurso(@ModelAttribute ("datosCrearCurso") DatosCreacionCurso datosCrearCurso) {
		ModelMap modelo=new ModelMap();
		
		servicioCurso.agregarCurso(datosCrearCurso.getNombre(), datosCrearCurso.getCategoria(), datosCrearCurso.getDescripcion(), datosCrearCurso.getPrecio(), datosCrearCurso.getImagen());
		modelo.put("datosCrearCurso", new DatosCreacionCurso());
		
		return new ModelAndView("cursoAgregado", modelo);
	}
	
	@RequestMapping(path="/cursoActualizado", method = RequestMethod.POST)
	public ModelAndView actualizarCurso(@RequestParam("id_curso") int idCurso, @ModelAttribute ("datosCrearCurso") DatosCreacionCurso datosCrearCurso) {
		ModelMap modelo=new ModelMap();
		
		servicioCurso.actualizarCurso(idCurso, datosCrearCurso.getNombre(), datosCrearCurso.getCategoria(), datosCrearCurso.getDescripcion(), datosCrearCurso.getPrecio());
		modelo.put("datosCrearCurso", new DatosCreacionCurso());
		
		return new ModelAndView("cursoActualizado", modelo);
	}
	
	@RequestMapping (path= "/descripcionCurso", method= RequestMethod.GET)
	public ModelAndView irADescCurso(@RequestParam("id_curso") Integer id_curso) {
		
		ModelMap modelo = new ModelMap();
		String view = "";
		
		try {
			Curso curso = servicioCurso.buscarCursoPorId(id_curso);
			modelo.put("curso", curso);
			view = "descripcionCurso";
		}
		catch(Exception e) {
			view = "index";
		}
		
		return new ModelAndView(view, modelo);
	}
	
	@RequestMapping (path= "/verCurso", method= RequestMethod.POST)
	public ModelAndView verCurso(@RequestParam("curso_id") Integer curso_id) {
		
		ModelMap model = new ModelMap();
		Curso curso = servicioCurso.buscarCursoPorId(curso_id);
		List<Unidad> unidades = servicioCurso.obtenerUnidades(curso);
		
		model.put("curso", curso);
		model.put("unidades", unidades);
		model.put("unidad", unidades.get(0));
				
		return new ModelAndView("vistaCurso", model);
	}
	
	@RequestMapping (path= "/verUnidadCurso", method= RequestMethod.GET)
	public ModelAndView verUnidadCurso(@RequestParam("unidad_id") Integer unidad_id, @RequestParam("curso_id") Integer curso_id) {
		
		ModelMap model = new ModelMap();
		Curso curso = servicioCurso.buscarCursoPorId(curso_id);
		List<Unidad> unidades = servicioCurso.obtenerUnidades(curso);
		Unidad unidad = servicioCurso.obtenerUnidadPorID(unidad_id);
		
		model.put("curso", curso);
		model.put("unidades", unidades);
		model.put("unidad", unidad);
		
		return new ModelAndView("vistaCurso", model);
	}
	
	@RequestMapping (path= "/completarUnidad", method= RequestMethod.GET)
	public ModelAndView completarUnidad(@RequestParam("unidad_id") Integer unidad_id, @RequestParam("curso_id") Integer curso_id) {
		
		ModelMap model = new ModelMap();
		Curso curso = servicioCurso.buscarCursoPorId(curso_id);
		Unidad unidad = servicioCurso.obtenerUnidadPorID(unidad_id);
		
		if (unidad.getCompletado() == false) {
			
			servicioCurso.completarUnidad(unidad, curso, servicioCurso.obtenerUnidades(curso));
		}
		
		List<Unidad> unidades = servicioCurso.obtenerUnidades(curso);
		
		model.put("curso", curso);
		model.put("unidades", unidades);
		model.put("unidad", unidad);
		
		return new ModelAndView("vistaCurso", model);
	}
	
	@RequestMapping(path = "/finalizar", method = RequestMethod.POST)
	public ModelAndView finalizarCurso(@RequestParam("curso_id") int idCurso) {
		
		ModelMap model = new ModelMap();
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(idCurso);
		List<Unidad> unidades = servicioCurso.obtenerUnidades(curso_obtenido);
		String view = "";
		
		if (curso_obtenido.getCursoTerminado() == false) {
			
			// Se valida si el progreso del curso esta en un 50% o más
			if (curso_obtenido.getProgreso() >= 50.0) {
				
				servicioUsuario.finalizarCurso(curso_obtenido);
				model.put("msj", "Felicidades! Completaste el curso: " + curso_obtenido.getNombre());
				view = "redirect:/misCursos";
			}
			else {
				model.put("curso", curso_obtenido);
				model.put("unidades", unidades);
				model.put("unidad", unidades.get(0));
				model.put("msj_progreso", "Para completar el curso debe estar completado en un 50% o mas.");
				view = "vistaCurso";
			}
		}
		
		return new ModelAndView(view, model);
	}

	// Para hacer el controlador de examen
	@RequestMapping(path = "/examen", method = RequestMethod.GET)
	public ModelAndView examen() {

		ModelMap model = new ModelMap();

		return new ModelAndView("vistaExamen", model);
	}

	// Finalizar el examen y que te sumen los puntos al usuario
	@RequestMapping(path = "/finalizoExamen", method = RequestMethod.GET)
	public ModelAndView finalizoExamen() {

		ModelMap model = new ModelMap();

		return new ModelAndView("vistaExamenFinalizado", model);
	}

}
