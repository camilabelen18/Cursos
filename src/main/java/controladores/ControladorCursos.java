package controladores;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import modelo.*;
import servicios.ServicioCurso;
import servicios.ServicioTarjeta;
import servicios.ServicioUsuario;

@Controller
public class ControladorCursos {
	
	private ServicioCurso servicioCurso;
	private ServicioUsuario servicioUsuario;
	private ServicioTarjeta servicioTarjeta;
	
	@Autowired
	public ControladorCursos(ServicioCurso servicioCurso, ServicioUsuario servicioUsuario, ServicioTarjeta servicioTarjeta) {
		this.servicioCurso = servicioCurso;
		this.servicioUsuario = servicioUsuario;
		this.servicioTarjeta = servicioTarjeta;
	}

	@RequestMapping(path = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar(@RequestParam("nombreCurso") String nombreCurso, HttpSession session) {
		
		// Va a la BD y me trae el curso si existe y si no me muestra que no existe dentro de la misma vista
		ModelMap model = new ModelMap();
		// Devuelve una lista de cursos por su nombre
		List<Curso> busqueda_cursos = servicioCurso.getCursosPorNombre(nombreCurso);
		// Si la lista se encuantra vacia entonces se guarda un mensaje iformativo
		String view= "seccionCursos";
		if(busqueda_cursos.isEmpty()) {
			model.put("sin_curso", "No existen cursos con ese nombre, ingrese otro.");
		}
		if(session.getAttribute("idUsuario") != null) {
			if(session.getAttribute("ROL").equals("admin")) {
				view = "seccionCursosAdmin";
			}
		}
		model.put("lista_cursos", busqueda_cursos);
		model.put("busqueda", nombreCurso);
		return new ModelAndView(view, model);
	}
	
	@RequestMapping(path="/misCursos", method= RequestMethod.GET)
	public ModelAndView misCursos(@ModelAttribute("msj_exito") String msj_exito, @ModelAttribute("msj_error") String msj_error, HttpSession session) {
		
		ModelMap model = new ModelMap();
		int id = (int) session.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id);
		List<Usuario_Curso> cursos = servicioUsuario.obtenerCursosDelUsuario(usuario);
		model.put("lista_cursos", cursos);
		model.put("msj_exito", msj_exito);
		model.put("msj_error", msj_error);
		return new ModelAndView("misCursos", model);
	}

	@RequestMapping(path= "/verListaCursos", method= RequestMethod.GET)
	public ModelAndView verListaCursos(@ModelAttribute("msj_exito") String msj_exito, @ModelAttribute("msj_error") String msj_error, HttpSession session) {
		
		ModelMap model = new ModelMap();
		List<Curso> cursos = servicioCurso.getCursos();
		String view= "seccionCursos";
		model.put("lista_cursos", cursos);
		model.put("msj_exito", msj_exito);
		model.put("msj_error", msj_error);
		model.put("categoria","Todos los cursos");
		//Si el usuario no es nulo, y su rol es "admin", entonces se mostrara la seccion de cursos de administrador
		if(session.getAttribute("idUsuario") != null) {
			if(session.getAttribute("ROL").equals("admin")) {
				view = "seccionCursosAdmin";
			}
		}
		return new ModelAndView(view, model);
	}

	@RequestMapping(path= "/verCursosPorEstado", method= RequestMethod.GET)
	public ModelAndView verCursosPorEstado(@RequestParam("estado") Estado estado, HttpSession session) {

		ModelMap model = new ModelMap();
		int id = (int) session.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id);
		List<Usuario_Curso> cursos = servicioCurso.getCursosPorEstado(estado, usuario);
		model.put("lista_cursos", cursos);
		return new ModelAndView("misCursos", model);
	}

	@RequestMapping(path= "/verCursosPorCategoria", method= RequestMethod.GET)
	public ModelAndView verCursosPorCategoria(@RequestParam("categoria") String categoria, HttpSession session) {
		
		ModelMap model = new ModelMap();
		List<Curso> cursos = servicioCurso.getCursosPorCategoria(categoria);
		String view = "seccionCursos";
		if(session.getAttribute("idUsuario") != null) {
			if(session.getAttribute("ROL").equals("admin")) {
				view = "seccionCursosAdmin";
			}
		}
		model.put("lista_cursos", cursos);
		model.put("categoria", categoria);
		return new ModelAndView(view, model);
	}
	
	@RequestMapping (path= "/descripcionCurso", method= RequestMethod.GET)
	public ModelAndView irADescCurso(@RequestParam("id_curso") Integer id_curso) {
		ModelMap modelo = new ModelMap();
		String view = "";
		List<Unidad> unidades;
		try {
			Curso curso = servicioCurso.buscarCursoPorId(id_curso);
			unidades = servicioCurso.obtenerUnidades(curso);
			modelo.put("curso", curso);
			modelo.put("unidades", unidades);
			view = "descripcionCurso";
		}
		catch(Exception e) {
			view = "index";
		}
		return new ModelAndView(view, modelo);
	}
	
	@RequestMapping (path= "/verCurso", method= RequestMethod.GET)
	public ModelAndView verCurso(@RequestParam("curso_id") int curso_id, HttpSession session) {
		
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
		Tarjeta miTarjeta = usuario.getTarjeta();
		Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso, usuario);
		List<Unidad> unidades = servicioCurso.obtenerUnidades(curso);
		String view = "";
		if (usuarioCurso.getCursoTerminado() == false) {
			// Se valida si el progreso del curso esta en un 100%
			if (usuarioCurso.getProgreso() > 99.0) {
				servicioUsuario.finalizarCurso(usuarioCurso);
				servicioTarjeta.sumarPuntos(miTarjeta);
				servicioUsuario.enviarNotificacion(usuario, "Recibiste 2500 puntos en tu tarjeta.", session);
				model.put("msj_exito", "Felicidades! Completaste el curso " + curso.getNombre() + " y ganaste puntos.");
				view = "redirect:/misCursos";
			}
			else {
				model.put("cursoUsuario", usuarioCurso);
				model.put("unidades", unidades);
				model.put("unidad", unidades.get(0));
				model.put("msj_error", "Todas las unidades deben completarse para poder terminar el curso");
				view = "vistaCurso";
			}
		}
		return new ModelAndView(view, model);
	}

	/*******FUNCIONES ADMIN********/
	@RequestMapping("/editarCurso")
	public ModelAndView irAEditarCurso(@RequestParam("id_curso") int cursoID, @RequestParam("nombre") String nombreCurso,
									   @RequestParam("categoria") String catCurso, @RequestParam("descripcion") String descCurso, 
									   @RequestParam("precio") Double precioCurso) {
		ModelMap modelo = new ModelMap();
		DatosActualizarCurso datosActualizarCurso = new DatosActualizarCurso();
		modelo.put("datosActualizarCurso", datosActualizarCurso);
		modelo.put("nombreCurso", nombreCurso);
		modelo.put("catCurso", catCurso);
		modelo.put("descCurso", descCurso);
		modelo.put("precioCurso", precioCurso);
		modelo.put("cursoID", cursoID);
		return new ModelAndView("editarCurso", modelo);
	}

	@RequestMapping(path="/cursoActualizado", method = RequestMethod.POST)
	public ModelAndView actualizarCurso(@RequestParam("id_curso") int idCurso, @ModelAttribute ("datosCrearCurso") DatosActualizarCurso datosActC) {
		ModelMap modelo=new ModelMap();
		servicioCurso.actualizarCurso(idCurso, datosActC.getNombre(), datosActC.getCategoria(), 
				datosActC.getDescripcion(), datosActC.getPrecio());
		modelo.put("msj_exito", "El curso fue actualizado con exito!");
		return new ModelAndView("redirect:/verListaCursos", modelo);
	}

}