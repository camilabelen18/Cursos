package controladores;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import modelo.Curso;
import modelo.DatosCreacionCurso;
import modelo.DatosExamen;
import modelo.DatosPregunta;
import modelo.DatosRegistro;
import modelo.Estado;
import modelo.Examen;
import modelo.Giftcard;
import modelo.Pregunta;
import modelo.Respuesta;
import modelo.Unidad;
import modelo.Usuario;
import modelo.Usuario_Curso;
import modelo.Usuario_Examen;
import servicios.CursoInexistenteException;
import servicios.ServicioCurso;
import servicios.ServicioGiftcard;
import servicios.ServicioUsuario;

public class ControladorCursosTest {

	ServicioCurso servicioCurso = mock(ServicioCurso.class);
	ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	ServicioGiftcard servicioGiftcard = mock(ServicioGiftcard.class);
	ControladorCursos controladorCursos = new ControladorCursos(servicioCurso, servicioUsuario, servicioGiftcard);
	HttpSession session = mock(HttpSession.class);

	@Test
	public void testQueAgregaCursos() {

		// Preparacion
		DatosCreacionCurso datos = new DatosCreacionCurso("Curso php", "Programacion", "Curso de programacion php",
				1000.0, "cursophp.png");

		// Ejecucion
		ModelAndView mav = controladorCursos.agregarCurso(datos);

		// Comprobacion
		assertThat(mav.getViewName()).isEqualTo("cursoAgregado");

	}

	@Test
	public void testQueCompletaUnidad() {
		// Preparacion
		Integer unidad_id = 1;
		Integer curso_id = 1;

		Unidad unidad = new Unidad("Curso de programacion php", "cursophp.com");

		unidad.setCompletado(false);
		
		//Ejecucion	
		when(session.getAttribute("idUsuario")).thenReturn(1);
		when(servicioCurso.buscarCursoPorId(curso_id)).thenReturn(new Curso());
		when(servicioCurso.obtenerUnidadPorID(unidad_id)).thenReturn(unidad);
		ModelAndView mav = controladorCursos.completarUnidad(unidad_id, curso_id, session);
	
		//Comprobacion
		assertThat(mav.getViewName()).isEqualTo("vistaCurso");
	}

	@Test
	public void queSeMuestreLaListaDeCursosParaElCliente() {

		// Preparacion
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(new Curso());
		cursos.add(new Curso());
		cursos.add(new Curso());

		when(session.getAttribute("idUsuario")).thenReturn(null);
		when(servicioCurso.getCursos()).thenReturn(cursos);

		// Ejecucion
		ModelAndView mav = controladorCursos.verListaCursos(null, null, session);

		// Comprobacion
		assertThat(mav.getViewName()).isEqualTo("seccionCursos");
		seCompruebaCantidadEsperadaDeLaLista(mav);
	}

	@Test
	public void testQuePermiteBuscar() {
		//Preparacion
		List<Curso> listaCursos = new ArrayList<Curso>();
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		Curso curso1 = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		listaCursos.add(curso);
		listaCursos.add(curso1);

		// Ejecucion
		when(servicioCurso.getCursosPorNombre(curso.getNombre())).thenReturn(listaCursos);
		when(servicioCurso.getCursosPorNombre(curso1.getNombre())).thenReturn(listaCursos);
		ModelAndView mav = controladorCursos.buscar(curso.getNombre());
		// Comprobacion
		assertThat(mav.getViewName()).isEqualTo("seccionCursos");
	}

	@Test
	public void testQuePermitaVerCursosPorEstado() {
		
		//Preparacion
		Usuario usuario = new Usuario("test", "test@test", "123", "cliente");
		Curso curso1 = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		Curso curso2 = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		
		Usuario_Curso cursoUsuario1 = new Usuario_Curso(usuario, curso1);
		cursoUsuario1.setEstado(Estado.EN_CURSO);
		Usuario_Curso cursoUsuario2 = new Usuario_Curso(usuario, curso2);
		cursoUsuario2.setEstado(Estado.EN_CURSO);
		
		List<Usuario_Curso> cursosUsuario = new ArrayList<Usuario_Curso>();
		cursosUsuario.add(cursoUsuario1);
		cursosUsuario.add(cursoUsuario2);
		
		//Ejecucion
		when(session.getAttribute("idUsuario")).thenReturn(1);
		when(servicioUsuario.buscarUsuarioPorID(1)).thenReturn(usuario);
		when(servicioCurso.getCursosPorEstado(Estado.EN_CURSO, usuario)).thenReturn(cursosUsuario);
		ModelAndView mav = controladorCursos.verCursosPorEstado(Estado.EN_CURSO, session);
		
		//Comprobacion
		assertThat(mav.getViewName()).isEqualTo("miscursos");
	}

	@Test
	public void testQuePermitaIrAEditarCursos() {
		
		//Preparacion
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		
		//Ejecucion
		ModelAndView mav = controladorCursos.irAEditarCurso(curso.getId(), curso.getNombre(), 
							curso.getCategoria(),curso.getDescripcion(), curso.getPrecio());
		//Comprobacion
		assertThat(mav.getViewName()).isEqualTo("editarCurso");
	}
	
	
	@Test
	public void testQuePermitaVerUnidadCurso() {
		
		//Preparacion
		Usuario usuario = new Usuario("test", "test@test", "123", "cliente");
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		Unidad unidad = new Unidad("prueba", "prueba.com");
		Usuario_Curso cursoUsuario = new Usuario_Curso(usuario, curso);
		List<Unidad> unidades = new ArrayList<Unidad>();
		unidades.add(unidad);
		
		//Ejecucion
		when(session.getAttribute("idUsuario")).thenReturn(1);
		when(servicioCurso.buscarCursoPorId(1)).thenReturn(curso);
		when(servicioUsuario.obtenerUsuarioCurso(curso, usuario)).thenReturn(cursoUsuario);
		when(servicioCurso.obtenerUnidades(curso)).thenReturn(unidades);
		when(servicioCurso.obtenerUnidadPorID(unidad.getId())).thenReturn(unidad);
		ModelAndView mav = controladorCursos.verUnidadCurso(unidad.getId(), curso.getId(), session);
		
		//Comprobacion
		assertThat(mav.getViewName()).isEqualTo("vistaCurso");
	}


	private void seCompruebaCantidadEsperadaDeLaLista(ModelAndView mav) {

		int cantidadEsperada = 3;
		List<Curso> listaCursos = (List<Curso>) mav.getModel().get("lista_cursos");
		assertThat(listaCursos).hasSize(cantidadEsperada);
	}

	@Test
	public void queSeMuestreLaListaDeCursosParaElAdministrador() {

		// Preparacion
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(new Curso());
		cursos.add(new Curso());
		cursos.add(new Curso());

		when(session.getAttribute("idUsuario")).thenReturn(1);
		when(session.getAttribute("ROL")).thenReturn("admin");
		when(servicioCurso.getCursos()).thenReturn(cursos);

		// Ejecucion
		ModelAndView mav = controladorCursos.verListaCursos(null, null, session);

		// Comprobacion
		assertThat(mav.getViewName()).isEqualTo("seccionCursosAdmin");
		seCompruebaCantidadEsperadaDeLaLista(mav);
	}

	@Test
	public void queSePuedaVerElContenidoDeUnCurso() {

		// Preparacion
		Integer curso_id = 1;
		Curso curso = new Curso("php", "programacion","descripcion curso", 1000.0,"cursophp.png");
		List<Unidad> unidadesDelCurso = new ArrayList<Unidad>();
		Unidad unidad = new Unidad("descripcion unidad", "www.videounidad.com");
		unidadesDelCurso.add(unidad);
		
		when(session.getAttribute("idUsuario")).thenReturn(1);
		when(servicioCurso.buscarCursoPorId(curso_id)).thenReturn(curso);
		when(servicioCurso.obtenerUnidades(curso)).thenReturn(unidadesDelCurso);

		// Ejecucion
		ModelAndView mav = controladorCursos.verCurso(curso_id, session);
		
		// Comprobacion
		assertThat(mav.getViewName()).isEqualTo("vistaCurso");
		assertThat(mav.getModel().get("unidad")).isEqualTo(unidad);
	}

	@Test
	public void queSePuedaVerLaDescripcionDeUnCurso() {

		// Preparacion
		Integer id_curso = 1;
		Curso curso = new Curso("test", "programacion","descripcion curso", 100.0,"test.png");
		
		when(servicioCurso.buscarCursoPorId(id_curso)).thenReturn(curso);

		// Ejecucion
		ModelAndView mav = controladorCursos.irADescCurso(id_curso);

		// Comprobacion
		assertThat(mav.getViewName()).isEqualTo("descripcionCurso");
		assertThat(mav.getModel().get("curso")).isEqualTo(curso);
	}

	@Test
	public void queNoSePuedaVerLaDescripcionDeUnCursoInexistente() {

		// Preparacion
		Integer id_curso = 1;

		doThrow(CursoInexistenteException.class).when(servicioCurso).buscarCursoPorId(id_curso);

		// Ejecucion
		ModelAndView mav = controladorCursos.irADescCurso(id_curso);

		// Comprobacion
		assertThat(mav.getViewName()).isEqualTo("index");
	}

	@Test
	public void queSePuedaFinalizarUnCurso() {

		// Preparacion
		int idCurso = 1;
		Usuario usuario = new Usuario("test", "test@test", "123", "cliente");
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		Unidad unidad = new Unidad("prueba", "prueba.com");
		
		Usuario_Curso cursoUsuario = new Usuario_Curso(usuario, curso);
		cursoUsuario.setProgreso(70.0);
		
		List<Unidad> unidades = new ArrayList<Unidad>();
		unidades.add(unidad);
		
		// Ejecucion
		when(session.getAttribute("idUsuario")).thenReturn(1);
		when(servicioUsuario.buscarUsuarioPorID(1)).thenReturn(usuario);
		when(servicioCurso.buscarCursoPorId(idCurso)).thenReturn(curso);
		when(servicioUsuario.obtenerUsuarioCurso(curso, usuario)).thenReturn(cursoUsuario);
		when(servicioCurso.obtenerUnidades(curso)).thenReturn(unidades);
		ModelAndView mav = controladorCursos.finalizarCurso(idCurso, session);
		
		// Comprobacion
		assertThat(mav.getViewName()).isEqualTo("redirect:/misCursos");
		assertThat(mav.getModel().get("msj")).isEqualTo("Felicidades! Completaste el curso: " + curso.getNombre());
	}

	@Test
	public void queNoSePuedaFinalizarUnCursoConProgresoMenorAlCincuenta() {

		// Preparacion
		int idCurso = 1;
		Usuario usuario = new Usuario("test", "test@test", "123", "cliente");
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		Unidad unidad = new Unidad("prueba", "prueba.com");
		
		Usuario_Curso cursoUsuario = new Usuario_Curso(usuario, curso);
		cursoUsuario.setProgreso(30.0);
		
		List<Unidad> unidades = new ArrayList<Unidad>();
		unidades.add(unidad);
		
		// Ejecucion
		when(session.getAttribute("idUsuario")).thenReturn(1);
		when(servicioUsuario.buscarUsuarioPorID(1)).thenReturn(usuario);
		when(servicioCurso.buscarCursoPorId(idCurso)).thenReturn(curso);
		when(servicioUsuario.obtenerUsuarioCurso(curso, usuario)).thenReturn(cursoUsuario);
		when(servicioCurso.obtenerUnidades(curso)).thenReturn(unidades);
		ModelAndView mav = controladorCursos.finalizarCurso(idCurso, session);
		
		// Comprobacion
		assertThat(mav.getViewName()).isEqualTo("vistaCurso");
		assertThat(mav.getModel().get("msj_progreso"))
				.isEqualTo("Para completar el curso debe estar completado en un 50% o mas.");
	}

	@Test
	public void testQueSePuedaVerUnExamen() {
		// Preparacion
		Respuesta respuesta_1 = new Respuesta("Si", true);
		Respuesta respuesta_2 = new Respuesta("no", false);
		Respuesta respuesta_3 = new Respuesta("no se", true);
		Pregunta pregunta_1 = new Pregunta("pregunta 1 ?", respuesta_1, respuesta_2, respuesta_3);
		Pregunta pregunta_2 = new Pregunta("pregunta 2 ?", respuesta_1, respuesta_2, respuesta_3);
		Pregunta pregunta_3 = new Pregunta("pregunta 3 ?", respuesta_1, respuesta_2, respuesta_3);
		Pregunta pregunta_4 = new Pregunta("pregunta 4 ?", respuesta_1, respuesta_2, respuesta_3);
		Pregunta pregunta_5 = new Pregunta("pregunta 5 ?", respuesta_1, respuesta_2, respuesta_3);
		List<Pregunta> preguntas = new ArrayList<Pregunta>();
		preguntas.add(pregunta_1);
		preguntas.add(pregunta_2);
		preguntas.add(pregunta_3);
		preguntas.add(pregunta_4);
		preguntas.add(pregunta_5);
		
		Examen examen = new Examen(pregunta_1, pregunta_2, pregunta_3, pregunta_4, pregunta_5);
		examen.setEstadoHabilitado(false);

		// Integer curso_id = 1;
		Curso curso = new Curso("php", "programacion", "descripcion curso", 1000.0, Estado.EN_VENTA, "cursophp.png");

		curso.setCursoTerminado(true);
		List<Unidad> unidadesDelCurso = new ArrayList<Unidad>();

		Unidad unidad = new Unidad("descripcion unidad", "www.videounidad.com");

		unidadesDelCurso.add(unidad);

		Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");

		// Usuario_Examen usuarioExamen =
		// servicioUsuario.obtenerExamenUsuario(examen,usuario);

		// List<Pregunta> preguntas = new ArrayList<Pregunta>();

		List<Pregunta> preguntasAlAzar = new ArrayList<Pregunta>();
		Respuesta respuesta_4 = new Respuesta("Si", true);
		Respuesta respuesta_5 = new Respuesta("no", false);
		Respuesta respuesta_6 = new Respuesta("no se", true);
		Respuesta respuesta_7 = new Respuesta("Si", true);
		Respuesta respuesta_8 = new Respuesta("no", false);
		Respuesta respuesta_9 = new Respuesta("no se", true);
		Respuesta respuesta_10 = new Respuesta("Si", true);
		Respuesta respuesta_11 = new Respuesta("no", false);
		Respuesta respuesta_12 = new Respuesta("no se", true);
		Respuesta respuesta_13 = new Respuesta("Si", true);
		Respuesta respuesta_14 = new Respuesta("no", false);
		Respuesta respuesta_15 = new Respuesta("no se", true);
		Respuesta respuesta_16 = new Respuesta("Si", true);
		Respuesta respuesta_17 = new Respuesta("no", false);
		Respuesta respuesta_18 = new Respuesta("no se", true);
		Pregunta pregunta_6 = new Pregunta("pregunta 1 ?", respuesta_4, respuesta_5, respuesta_6);
		Pregunta pregunta_7 = new Pregunta("pregunta 2 ?", respuesta_7, respuesta_8, respuesta_9);
		Pregunta pregunta_8 = new Pregunta("pregunta 3 ?", respuesta_10, respuesta_11, respuesta_12);
		Pregunta pregunta_9 = new Pregunta("pregunta 4 ?", respuesta_13, respuesta_14, respuesta_15);
		Pregunta pregunta_10 = new Pregunta("pregunta 5 ?", respuesta_16, respuesta_17, respuesta_18);
		preguntasAlAzar.add(pregunta_6);
		preguntasAlAzar.add(pregunta_7);
		preguntasAlAzar.add(pregunta_8);
		preguntasAlAzar.add(pregunta_9);
		preguntasAlAzar.add(pregunta_10);

		DatosExamen datosExamen = new DatosExamen();

		for (Pregunta pregunta : preguntasAlAzar) {
			DatosPregunta datosPregunta = new DatosPregunta();
			datosPregunta.setDescripcion(pregunta.getDescripcion());
			datosPregunta.setPregunta(pregunta);
			datosPregunta.setRespuesta_1(pregunta.getRespuesta_1());
			datosPregunta.setRespuesta_2(pregunta.getRespuesta_2());
			datosPregunta.setRespuesta_3(pregunta.getRespuesta_3());
			datosPregunta.setPreguntaId(pregunta.getId());
			datosExamen.getDatosPregunta().add(datosPregunta);
		}

	//	System.out.println(datosExamen);

		// Ejecucion
		when(servicioCurso.buscarCursoPorId(curso.getId())).thenReturn(curso);
		when(servicioCurso.obtenerExamenPorCurso(curso)).thenReturn(examen);
		when(servicioCurso.guardarPreguntasEnDatosExamen(preguntasAlAzar)).thenReturn(datosExamen);
		when(servicioCurso.obtenerPreguntasDelExamen(examen)).thenReturn(preguntas);
		when(servicioCurso.PreguntasAzar(preguntasAlAzar)).thenReturn(preguntasAlAzar);
		when(session.getAttribute("idUsuario")).thenReturn(1);
		ModelAndView mav = controladorCursos.examen(curso.getId(), session);

	
      // System.out.println((DatosExamen)  mav.getModel().get("datosExamen"));
		// Comprobacion
		assertThat(mav.getModel().get("curso")).isEqualTo(curso);
	//	assertThat(mav.getModel().get("datosExamen")).isEqualTo(datosExamen);
		assertThat(mav.getViewName()).isEqualTo("vistaExamen");
	}

	
	  @Test public void testFinalizarExamen() {
	  
	  //Preparacion 
		  String mensaje = "El examen se aprobo y ganaste puntos ";
		    Respuesta respuesta_1 = new Respuesta("Si", true);
			Respuesta respuesta_2 = new Respuesta("no", false);
			Respuesta respuesta_3 = new Respuesta("no se", true);
			Pregunta pregunta_1 = new Pregunta("pregunta 1 ?", respuesta_1, respuesta_2, respuesta_3);
			Pregunta pregunta_2 = new Pregunta("pregunta 2 ?", respuesta_1, respuesta_2, respuesta_3);
			Pregunta pregunta_3 = new Pregunta("pregunta 3 ?", respuesta_1, respuesta_2, respuesta_3);
			Pregunta pregunta_4 = new Pregunta("pregunta 4 ?", respuesta_1, respuesta_2, respuesta_3);
			Pregunta pregunta_5 = new Pregunta("pregunta 5 ?", respuesta_1, respuesta_2, respuesta_3);
			List<Pregunta> preguntas = new ArrayList<Pregunta>();
			preguntas.add(pregunta_1);
			preguntas.add(pregunta_2);
			preguntas.add(pregunta_3);
			preguntas.add(pregunta_4);
			preguntas.add(pregunta_5);
			
			Examen examen = new Examen(pregunta_1, pregunta_2, pregunta_3, pregunta_4, pregunta_5);
			examen.setEstadoHabilitado(false);

			Curso curso = new Curso("php", "programacion", "descripcion curso", 1000.0, Estado.EN_VENTA, "cursophp.png");
			
			Giftcard giftCard = new Giftcard(555, 1250, 0.0);
			Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");
			usuario.setGiftcard(giftCard);

			DatosExamen datosExamen = new DatosExamen();

			for (Pregunta pregunta : preguntas) {
				DatosPregunta datosPregunta = new DatosPregunta();
				datosPregunta.setDescripcion(pregunta.getDescripcion());
				datosPregunta.setPregunta(pregunta);
				datosPregunta.setRespuesta_1(pregunta.getRespuesta_1());
				datosPregunta.setRespuesta_2(pregunta.getRespuesta_2());
				datosPregunta.setRespuesta_3(pregunta.getRespuesta_3());
				datosPregunta.setPreguntaId(pregunta.getId());
				datosExamen.getDatosPregunta().add(datosPregunta);
			}
			
			//sacamos la lista de preguntas con sus respuestas seleccionadas de datosExamen
			List<DatosPregunta> listaDp = datosExamen.getDatosPregunta();
			//Obtengo las respuestas en bruto 
			List<Respuesta> listaRobtenida = new ArrayList<Respuesta>();
			listaRobtenida.add(respuesta_1);
			listaRobtenida.add(respuesta_1);
			listaRobtenida.add(respuesta_3);
			listaRobtenida.add(respuesta_1);
			listaRobtenida.add(respuesta_1);
			
			//El puntaje o la nota que saco el usuario al hacer el examen 
		 int	 notaSacada = 10;

	           usuario.getGiftcard();
	           
	         //  System.out.println("Mira aca : ");
	         //  System.out.println(usuario.getGiftcard());
	  
	  //Ejecucion
			when(servicioCurso.obtenerRespuestas(listaDp)).thenReturn(listaRobtenida);
			when(servicioUsuario.sumarNota(listaRobtenida)).thenReturn(notaSacada);
			when(servicioUsuario.buscarUsuarioPorID(usuario.getId())).thenReturn(usuario);
			when(servicioUsuario.verificarSiHizoElExamenCuatroVecesOmas(usuario,examen)).thenReturn(false);
		//	when(servicioGiftcard.sumarPuntos(usuario.getGiftcard())).thenReturn(giftCard);
			when(session.getAttribute("idUsuario")).thenReturn(1);
			ModelAndView mav = controladorCursos.finalizarExamen(curso.getId(), datosExamen, session);
	  
	  
	  //Comprobacion
			assertThat(mav.getModel().get("msj")).isEqualTo(mensaje);
			assertThat(mav.getModel().get("notaSacada")).isEqualTo(notaSacada);
			assertThat(mav.getModel().get("curso")).isEqualTo(curso);
			assertThat(mav.getModel().get("puntos")).isEqualTo(usuario.getGiftcard().getMisPuntos());
	 
			assertThat(mav.getViewName()).isEqualTo("vistaExamenFinalizado"); 
	  }
	  
	  @Test 
	  public void queSePuedaVerElHistorialDeExamen() {
		//Preparacion 
		  String mensaje = "El examen se aprobo y ganaste puntos ";
		    Respuesta respuesta_1 = new Respuesta("Si", true);
			Respuesta respuesta_2 = new Respuesta("no", false);
			Respuesta respuesta_3 = new Respuesta("no se", true);
			Pregunta pregunta_1 = new Pregunta("pregunta 1 ?", respuesta_1, respuesta_2, respuesta_3);
			Pregunta pregunta_2 = new Pregunta("pregunta 2 ?", respuesta_1, respuesta_2, respuesta_3);
			Pregunta pregunta_3 = new Pregunta("pregunta 3 ?", respuesta_1, respuesta_2, respuesta_3);
			Pregunta pregunta_4 = new Pregunta("pregunta 4 ?", respuesta_1, respuesta_2, respuesta_3);
			Pregunta pregunta_5 = new Pregunta("pregunta 5 ?", respuesta_1, respuesta_2, respuesta_3);
			List<Pregunta> preguntas = new ArrayList<Pregunta>();
			preguntas.add(pregunta_1);
			preguntas.add(pregunta_2);
			preguntas.add(pregunta_3);
			preguntas.add(pregunta_4);
			preguntas.add(pregunta_5);
			
			Examen examen = new Examen(pregunta_1, pregunta_2, pregunta_3, pregunta_4, pregunta_5);
			Integer idCurso = 0;
			Curso curso = new Curso("php", "programacion", "descripcion curso", 1000.0, Estado.EN_VENTA, "cursophp.png");
			
			Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");
			
			Usuario_Examen ue = new Usuario_Examen(usuario, examen);
			
			List<Usuario_Examen> usuarioExamenes = new ArrayList<Usuario_Examen>();
			usuarioExamenes.add(ue);
			
		//Ejecucion
			when(servicioUsuario.buscarUsuarioPorID(usuario.getId())).thenReturn(usuario);
			when(servicioCurso.buscarCursoPorId(idCurso)).thenReturn(curso);
			when(servicioCurso.obtenerExamenPorCurso(curso)).thenReturn(examen);
			when(servicioUsuario.obtenerExamenesDelUsuario(usuario, examen)).thenReturn(usuarioExamenes);
			when(session.getAttribute("idUsuario")).thenReturn(1);
			ModelAndView mav = controladorCursos.historialExamen(curso.getId(), session);
		//Comprobacion
			System.out.println("ACA MIRA ");
			System.out.println(curso);
			System.out.println(usuarioExamenes);
			
			assertThat(mav.getModel().get("curso")).isEqualTo(curso);
			assertThat(mav.getModel().get("usuarioExamenes")).isEqualTo(usuarioExamenes);
			assertThat(mav.getViewName()).isEqualTo("vistaHistorialExamen");
	  }
	 

	@Test
	public void queSePuedanVerMisCursos() {
		// preparacion
		String mensaje = "mensaje";
		Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");

		List<Curso> listaCursos = new ArrayList();
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, Estado.EN_CURSO,
				"cursophp.png");
		Curso curso1 = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, Estado.EN_CURSO,
				"cursophp.png");
		listaCursos.add(curso);
		listaCursos.add(curso1);
		// ejecucion

		when(servicioUsuario.obtenerCursosDelUsuario(usuario)).thenReturn(listaCursos);

		when(session.getAttribute("idUsuario")).thenReturn(1);

		ModelAndView mav = controladorCursos.misCursos(session, mensaje);
		// comprobacion
		assertThat(mav.getViewName()).isEqualTo("miscursos");
	}

	@Test
	public void queSePuedanVerLosCursosPorCategoria() {
		// preparacion
		String categoria = "Programacion";
		List<Curso> listaCursos = new ArrayList();
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, Estado.EN_CURSO,
				"cursophp.png");
		Curso curso1 = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, Estado.EN_CURSO,
				"cursophp.png");
		listaCursos.add(curso);
		listaCursos.add(curso1);
		// ejecucion
		when(servicioCurso.getCursosPorCategoria(categoria)).thenReturn(listaCursos);
		ModelAndView mav = controladorCursos.verCursosPorCategoria(categoria);
		// comprobacion
		assertThat(mav.getViewName()).isEqualTo("seccionCursos");
	}

	@Test
	public void queSePuedanIrAAgregarCurso() {
		// preparacion
		DatosCreacionCurso datos = new DatosCreacionCurso("Curso php", "Programacion", "Curso de programacion php",
				1000.0, "cursophp.png");
		// ejecucion

		ModelAndView mav = controladorCursos.irAAgregarCurso();
		// comprobacion
		assertThat(mav.getViewName()).isEqualTo("crearCurso");
	}

	@Test
	public void queSePuedanActualizarLosCursos() {
		// preparacion
		DatosCreacionCurso datos = new DatosCreacionCurso("Curso php", "Programacion", "Curso de programacion php",
				1000.0, "cursophp.png");
		int idCurso = 1;

		// ejecucion
		ModelAndView mav = controladorCursos.actualizarCurso(idCurso, datos);
		// comprobacion
		assertThat(mav.getViewName()).isEqualTo("cursoActualizado");
	}

}
