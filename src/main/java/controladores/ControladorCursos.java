package controladores;

import java.util.ArrayList;
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
import servicios.ServicioGiftcard;
import servicios.ServicioUsuario;

@Controller
public class ControladorCursos {
	
	private ServicioGiftcard servicioGiftcard;
	private ServicioCurso servicioCurso;
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	public ControladorCursos(ServicioCurso servicioCurso, ServicioUsuario servicioUsuario,ServicioGiftcard servicioGiftcard) {
		this.servicioCurso = servicioCurso;
		this.servicioUsuario = servicioUsuario;
		this.servicioGiftcard = servicioGiftcard;
	}
	

	@RequestMapping(path = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar(@RequestParam("nombreCurso") String nombreCurso) {
		
		// Va a la BD y me trae el curso si existe y si no me muestra que no existe dentro de la misma vista
		ModelMap model = new ModelMap();
		
		// Devuelve una lista de cursos por su nombre
		List<Curso> busqueda_cursos = servicioCurso.getCursosPorNombre(nombreCurso);
		
		// Si la lista se encuantra vacia entonces se guarda un mensaje iformativo
		if(busqueda_cursos.isEmpty()) {
			model.put("sin_curso", "No existen cursos, vuelva a ingresar el nombre en la barra de busqueda");
		}
		
		model.put("lista_cursos", busqueda_cursos);
		model.put("busqueda", nombreCurso);
		
		return new ModelAndView("seccionCursos", model);
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
		
		return new ModelAndView("miscursos", model);
	}
	
	// Se obtienen todos los registros de la tabla 'curso' de la bd
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
		model.put("categoria", categoria);
		
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
	public ModelAndView agregarCurso(@ModelAttribute ("datosCrearCurso") DatosCreacionCurso datosCrearCurso, HttpSession sesion) {
		ModelMap modelo=new ModelMap();
		
		servicioCurso.agregarCurso(datosCrearCurso.getNombre(), datosCrearCurso.getCategoria(), datosCrearCurso.getDescripcion(), datosCrearCurso.getPrecio(), datosCrearCurso.getImagen());
		modelo.put("datosCrearCurso", new DatosCreacionCurso());
		
		int id_user = (int) sesion.getAttribute("idUsuario");
		Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		servicioUsuario.enviarNotificacion(usuario, "Se agrego el curso " + datosCrearCurso.getNombre(), sesion);
		
		
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
    	Examen examen = servicioCurso.obtenerExamenPorCurso(curso_obtenido);
		
    	
    	
		model.put("cursoUsuario", usuarioCurso);
		model.put("unidades", unidades);
		model.put("examen", examen);
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
				model.put("msj_exito", "Felicidades! Completaste el curso: " + curso.getNombre());
				view = "redirect:/misCursos";
			}
			else {
				model.put("cursoUsuario", usuarioCurso);
				model.put("unidades", unidades);
				model.put("unidad", unidades.get(0));
				model.put("msj_error", "Para completar el curso debe estar completado en un 50% o mas.");
				view = "vistaCurso";
			}
		}
		
		return new ModelAndView(view, model);
	}

	
	
	// Para hacer el controlador de examen
		@RequestMapping(path = "/examen", method = RequestMethod.POST)
		public ModelAndView examen(@RequestParam("curso_id") int curso_id, HttpSession session ) {
			
			ModelMap model = new ModelMap();
		  //Buscas el curso 	
			Curso curso_obtenido = servicioCurso.buscarCursoPorId(curso_id); //Por ahora solo del primer curso el del php C1
			List<Unidad> unidades = servicioCurso.obtenerUnidades(curso_obtenido);
			String view = "";
			//Obtenemos el examen del curso 
	    	Examen examen = servicioCurso.obtenerExamenPorCurso(curso_obtenido );
	    	//Busco a el usuario que realizo el examen para despues agregarlo a la lista de usuario_examen y ponerle su puntaje y la hora en que lo realizo 
			int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		    Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		    Usuario_Curso usuarioCurso = servicioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);
		    //Obtengo el examen que hizo el usuario 
		    Usuario_Examen usuarioExamen = servicioUsuario.obtenerExamenUsuario(examen,usuario);
	        //Obtenemos una lista de preguntas del examen 
	    	List<Pregunta> preguntas = servicioCurso.obtenerPreguntasDelExamen(examen);

	    	//Las hacemos "aleatorias" 
	    	List<Pregunta> preguntasAlAzar = servicioCurso.PreguntasAzar(preguntas);

	      //Y lo ponemos en una clase de datos 
	        DatosExamen datosExamen = servicioCurso.guardarPreguntasEnDatosExamen(preguntasAlAzar);
	        
	        //Verificamos fecha nuevamente para ver si el periodo de gracia paso y cambiarle el estado a examen que hizo el usuario 
	        servicioUsuario.verificarFechaDeExamen(usuarioExamen);
	        //Obtenemos el examen actual
	        examen = servicioCurso.obtenerExamenPorCurso(curso_obtenido );

	        //Valida si el curso esta terminado
			if (usuarioCurso.getCursoTerminado() == false) {
				
				model.put("curso", curso_obtenido);
				model.put("unidades", unidades);
				model.put("unidad", unidades.get(0));
				model.put("msj_error", "Para hacer el examen el curso tiene que estar completado ");
				view = "vistaCurso";

			}
			  else if(examen.estadoHabilitado == true) { //Si el examen esta habilitado lo podes hacer
				     model.put("curso", curso_obtenido);
					 model.put("unidades", unidades);
					 model.put("unidad", unidades.get(0));
					 model.put("msj_error", "El examen se habilitara en 3 minutos ");
					 model.put("examen", examen );
					view = "vistaCurso";
				
			 } else {
				   model.put("curso", curso_obtenido);
				   model.put("datosExamen", datosExamen);
				   view = "vistaExamen";
				
			 }
			   
	  

			return new ModelAndView(view, model);
		}


	


		// Finalizar el examen y que te sumen los puntos al usuario
		@RequestMapping(path = "/finalizarExamen", method = RequestMethod.POST)
		public ModelAndView finalizarExamen(@RequestParam("curso_id") int curso_id,@ModelAttribute("datosExamen") DatosExamen datosExamen, HttpSession session) {

			ModelMap model = new ModelMap();
		    String view = "";
			//Buscas el curso 	
			Curso curso_obtenido = servicioCurso.buscarCursoPorId(curso_id); 
			//Busco a el usuario que realizo el examen para despues agregarlo a la lista de usuario_examen y ponerle su puntaje y la hora en que lo realizo 
			int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		    Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		    //Obtengo la giftCard del usuario y le sumo los puntos cuando apruebo
		    Giftcard giftcard = usuario.getGiftcard();
		    //Busco el examen que tiene el curso enlazado 
		    Examen examen = servicioCurso.obtenerExamenPorCurso(curso_obtenido );
			//sacamos la lista de preguntas con sus respuestas seleccionadas de datosExamen
			List<DatosPregunta> listaDp = datosExamen.getDatosPregunta();
			//Obtengo las respuestas en bruto 
			List<Respuesta> listaRobtenida = servicioCurso.obtenerRespuestas(listaDp);
			//El puntaje o la nota que saco el usuario al hacer el examen 
			int notaSacada = servicioUsuario.sumarNota(listaRobtenida);
			
			//Se guardaria a Usuario_Examen el usuario que tiene la sesion y el examen que tiene el curso 
		    //y se setearia la fecha y la hora en que hizo el examen y los puntos que saco de dicho examen 
		     servicioUsuario.guardarExamenDeUsuario(usuario,examen,notaSacada);
		     
		     Usuario_Examen usuarioExamen = servicioUsuario.obtenerExamenUsuario(examen,usuario);

		  // los intentos para hacer el examen son 3 y te da puntos  y si hiciste el examen por 4 ves no te da puntos 
		 	//pero si te da el curso como completado o finalizado correctamente si lo aprobaste con mayor a 7 
		     //Tambien usar el examen para no confundir 
		    if (servicioUsuario.verificarSiHizoElExamenCuatroVecesOmas(usuario,examen) == true) { //Ya no ganas puntos 
		    	//Aprobado
		    	System.out.println("ENTRASTE ACA A LA PARTE CUANDO YA HICISTE CUATRO VECES O MAS A EL EXAMEN");
		    	  if(servicioUsuario.aproboExamenUsuario(notaSacada) == true) {
		    		  
		    		    servicioCurso.actualizarExamenAaprobado(examen);
		
			    	    model.put("msj", "El examen se aprobo, pero no ganas puntos");
			    		model.put("notaSacada", notaSacada);
			    		model.put("curso", curso_obtenido);
			    		view="vistaExamenFinalizado";
			    	 
			    	 
			     }
		    	//Desaprobado
		    	  else {
			    	    //Si desaprobas 
						//Te muestran la nota, no te dan puntos y se te desabilita el examen por 2 dias 48 hs (usamos min ) 
			    	 
		    		  servicioUsuario.cancelarExamen(usuarioExamen,examen);
					//	Boolean a =servicioUsuario.cancelarExamen(usuarioExamen);
					//	System.out.println("FUNCIONA AAA" + a);
						
					
						model.put("msj", "El examen se desaprobo y ya no vas a poder ganar puntos"); 
			    		model.put("notaSacada", notaSacada);
			    		model.put("curso", curso_obtenido);
			    		view="vistaExamenFinalizado";
			    	 
			     }

		    }
		    else {
		    	System.out.println("ENTRASTE ACA CUANDO ES ENTRE UNA VES O LA TERCERA ");
		    	 if(servicioUsuario.aproboExamenUsuario(notaSacada) == true) {
			    		//El camino verdadero
						//Si aprobas entre la primera ves  y la tercera te dan los puntos dependiendo la  nota de aprobado 10 = 500, 9 =400, etc 
		    		 
		    		    int puntosObtenidos = servicioGiftcard.sumarPuntos(giftcard,notaSacada); //Modificar 
		    		     
		    		     servicioCurso.actualizarExamenAaprobado(examen);
		    		     
		    		     usuario = servicioUsuario.buscarUsuarioPorID(id_user);
		    		     giftcard = usuario.getGiftcard();
		    			
			    
			    	    model.put("msj", "El examen se aprobo y ganaste puntos");
			    		model.put("notaSacada", notaSacada);
			    		model.put("puntos", puntosObtenidos);
			    		model.put("curso", curso_obtenido);
			    		view="vistaExamenFinalizado";
			    	 
			     }
		    	//Desaprobado
		    	  else {
			    	    //Si desaprobas 
						//Te muestran la nota, no te dan puntos 
			    	    //Esto desahibilita a el examen por dos dias (usamos min)
		    		  
		    		    servicioUsuario.cancelarExamen(usuarioExamen,examen); 
		    		  
					
						model.put("msj", "El examen se desaprobo y no ganaste puntos"); 
			    		model.put("notaSacada", notaSacada);
			    		model.put("curso", curso_obtenido);
			    		view="vistaExamenFinalizado";
			    	 
			     }
		    	
		    }
		    
		    	

		    session.setAttribute("user", servicioUsuario.buscarUsuarioPorID(id_user));

			return new ModelAndView(view, model);
		
		}
		
		
		@RequestMapping(path = "/historialExamen", method = RequestMethod.POST)
		public ModelAndView historialExamen(@RequestParam("curso_id") int curso_id, HttpSession session) {
			
			ModelMap model = new ModelMap();
		    String view = "";
		    
		    //Busco al usuario que tiene la sesion iniciada 
			int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		    Usuario usuario = servicioUsuario.buscarUsuarioPorID(id_user);
	
			//Buscas el curso 	
			Curso curso_obtenido = servicioCurso.buscarCursoPorId(curso_id); 
			//Busco el examen que tiene el curso enlazado 
		    Examen examen = servicioCurso.obtenerExamenPorCurso(curso_obtenido );
			
			// Si no se obtiene ningun examen del usuario entonces se lanza una excepcion
		    List<Usuario_Examen> usuarioExamenes = servicioUsuario.obtenerExamenesDelUsuario(usuario,examen);
		
		    
		 // Si tiene muestra 
		 	if (usuarioExamenes.size() > 0) {
		 		
				    model.put("curso", curso_obtenido);
				    model.put("usuarioExamenes", usuarioExamenes);
					view = "vistaHistorialExamen";
						
				
		 	}
		 	else {
		 	// Si la lista usuario_examen se encuentra vacia entonces se guarda un mensaje informativo
	 			model.put("msj", "No se hizo ningun examen todavia ");
	 			view = "vistaHistorialExamen";
		 	}
			
			return new ModelAndView(view, model);
			
			
		
		}
}
