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
		
		List<Usuario_Curso> cursos = servicioUsuario.obtenerCursosDelUsuario(usuario);
		
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
	public ModelAndView verCursosPorEstado(@RequestParam("estado") Estado estado, HttpSession session) {

		ModelMap model = new ModelMap();
		int id = (int) session.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id);
		
		List<Usuario_Curso> cursos = servicioCurso.getCursosPorEstado(estado, usuario);

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
	public ModelAndView irAEditarCurso(@RequestParam("id_curso") int cursoID, @RequestParam("nombre") String nombreCurso, @RequestParam("categoria") String catCurso, @RequestParam("descripcion") String descCurso, @RequestParam("precio") Double precioCurso) {
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
	public ModelAndView verCurso(@RequestParam("curso_id") Integer curso_id, HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id_user = (int) session.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(curso_id);
		Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);

		List<Unidad> unidades = servicioCurso.obtenerUnidades(curso_obtenido);
		
		model.put("cursoUsuario", usuarioCurso);
		model.put("unidades", unidades);
		model.put("unidad", unidades.get(0));
				
		return new ModelAndView("vistaCurso", model);
	}
	
	@RequestMapping (path= "/verUnidadCurso", method= RequestMethod.GET)
	public ModelAndView verUnidadCurso(@RequestParam("unidad_id") Integer unidad_id, @RequestParam("curso_id") Integer curso_id, HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id_user = (int) session.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(curso_id);
		Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);
		List<Unidad> unidades = servicioCurso.obtenerUnidades(curso_obtenido);
		Unidad unidad = servicioCurso.obtenerUnidadPorID(unidad_id);
		
		model.put("cursoUsuario", usuarioCurso);
		model.put("unidades", unidades);
		model.put("unidad", unidad);
		
		return new ModelAndView("vistaCurso", model);
	}
	
	@RequestMapping (path= "/completarUnidad", method= RequestMethod.GET)
	public ModelAndView completarUnidad(@RequestParam("unidad_id") Integer unidad_id, @RequestParam("curso_id") Integer curso_id, HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id_user = (int) session.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso = servicioCurso.buscarCursoPorId(curso_id);
		Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso, usuario);
		Unidad unidad = servicioCurso.obtenerUnidadPorID(unidad_id);
		
		if (unidad.getCompletado() == false) {
			
			servicioCurso.completarUnidad(unidad, usuarioCurso, servicioCurso.obtenerUnidades(curso));
		}
		
		List<Unidad> unidades = servicioCurso.obtenerUnidades(curso);
		
		model.put("cursoUsuario", usuarioCurso);
		model.put("unidades", unidades);
		model.put("unidad", unidad);
		
		return new ModelAndView("vistaCurso", model);
	}
	
	@RequestMapping(path = "/finalizar", method = RequestMethod.POST)
	public ModelAndView finalizarCurso(@RequestParam("curso_id") int idCurso, HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id_user = (int) session.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		Curso curso = servicioCurso.buscarCursoPorId(idCurso);
		Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso, usuario);
		List<Unidad> unidades = servicioCurso.obtenerUnidades(curso);
		String view = "";
		
		if (usuarioCurso.getCursoTerminado() == false) {
			
			// Se valida si el progreso del curso esta en un 50% o más
			if (usuarioCurso.getProgreso() >= 50.0) {
				
				servicioUsuario.finalizarCurso(usuarioCurso);
				model.put("msj", "Felicidades! Completaste el curso: " + curso.getNombre());
				view = "redirect:/misCursos";
			}
			else {
				model.put("cursoUsuario", usuarioCurso);
				model.put("unidades", unidades);
				model.put("unidad", unidades.get(0));
				model.put("msj_progreso", "Para completar el curso debe estar completado en un 50% o mas.");
				view = "vistaCurso";
			}
		}
		
		return new ModelAndView(view, model);
	}


	// Para hacer el controlador de examen
	@RequestMapping(path = "/examen", method = RequestMethod.POST)
	public ModelAndView examen(@RequestParam("curso_id") int curso_id) {
		
		ModelMap model = new ModelMap();
	//Buscas el curso 	
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(curso_id); //Por ahora solo del primer curso el del php C1
		
		//Hacemos una lista de preguntas y respuestas que estan en examenes 
		List<Examen> examenes = servicioCurso.obtenerExamenes(curso_obtenido);
		
		
	//	Examen examen = servicioCurso.obtenerExamenPorId(examen_id);

	
	//	boolean nota_examen =servicioCurso.sumarPuntajeExamen(examenes);
	
		//Obtengo el puntaje total sumando el puntaje individual de cada examen 
    //	int puntajeFinal = servicioCurso.getTotalDePuntajesExamen(examenes);	

		model.put("curso", curso_obtenido);
		model.put("examenes", examenes);
	//	model.put("nota_final", puntajeFinal);
	//	model.put("nota_examen", nota_examen );
	//	System.out.println(examenes);

		
		
	
		return new ModelAndView("vistaExamen", model);
	}


	// Finalizar el examen y que te sumen los puntos al usuario
	@RequestMapping(path = "/finalizarExamen", method = RequestMethod.POST)
	public ModelAndView finalizarExamen(@RequestParam("curso_id") int curso_id) {

		ModelMap model = new ModelMap();
		//Buscas el curso 	
		Curso curso_obtenido = servicioCurso.buscarCursoPorId(curso_id); //Por ahora solo del primer curso el del php C1
		
		//Obtenemos la respuesta del input seleccionado
	//	Examen examen = servicioCurso.obtenerExamenPorId(examen_id); //  java.awt.event.ActionEvent evento (esto en teoria sacaria el evento click usando java)
		
		//Hacemos una lista de preguntas y respuestas que estan en examenes 
		List<Examen> examenes = servicioCurso.obtenerExamenes(curso_obtenido);
		
		boolean nota_examen =servicioCurso.sumarPuntajeExamen(examenes);
	
		//Obtengo el puntaje total sumando el puntaje individual de cada examen 
		int puntajeFinal = servicioCurso.getTotalDePuntajesExamen(examenes);

		model.put("curso", curso_obtenido);
		model.put("examenes", examenes);
		model.put("nota_final", puntajeFinal);
		model.put("nota_examen", nota_examen );
	//	model.put("examen", examen);
	//	System.out.println(examenes);

        
		
	
		return new ModelAndView("vistaExamen", model);
	//	return new ModelAndView("vistaExamenFinalizado", model);
	}
	


}
