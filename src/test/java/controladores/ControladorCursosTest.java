package controladores;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import modelo.Curso;
import modelo.DatosCreacionCurso;
import modelo.DatosRegistro;
import modelo.Estado;
import modelo.Examen;
import modelo.Pregunta;
import modelo.Respuesta;
import modelo.Unidad;
import modelo.Usuario;
import modelo.Usuario_Curso;
import servicios.CursoInexistenteException;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

public class ControladorCursosTest {
	
	
	ServicioCurso servicioCurso = mock(ServicioCurso.class);
	ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	ControladorCursos controladorCursos = new ControladorCursos(servicioCurso, servicioUsuario);
	HttpSession session = mock(HttpSession.class);
	
	
	@Test
	public void testQueAgregaCursos() {
		
		//Preparacion
		DatosCreacionCurso datos = new DatosCreacionCurso("Curso php",
		"Programacion","Curso de programacion php", 1000.0,"cursophp.png");
		
		//Ejecucion
		ModelAndView mav = controladorCursos.agregarCurso(datos, session);
		
		//Comprobacion
		assertThat(mav.getViewName()).isEqualTo("cursoAgregado");
		
	}
	
	
	@Test
	public void testQueCompletaUnidad() {
		//Preparacion
		Integer unidad_id = 1;
		Integer curso_id = 1;
		
		Unidad unidad  = new Unidad("Curso de programacion php", "cursophp.com");

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
		//Preparaci�n
		List<Curso> listaCursos = new ArrayList<Curso>();
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		Curso curso1 = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		listaCursos.add(curso);
		listaCursos.add(curso1);
		
		//Ejecuci�n
		when(servicioCurso.getCursosPorNombre(curso.getNombre())).thenReturn(listaCursos);
		when(servicioCurso.getCursosPorNombre(curso1.getNombre())).thenReturn(listaCursos);
		ModelAndView mav = controladorCursos.buscar(curso.getNombre());
		//Comprobaci�n
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
		assertThat(mav.getModel().get("msj_progreso")).isEqualTo("Para completar el curso debe estar completado en un 50% o mas.");
	}


	@Test 
	public void testQueSePuedaVerUnExamen () {
		//Preparacion 
		Pregunta pregunta_1 = new Pregunta("Esto es una prueba ?");
		Pregunta pregunta_2 = new Pregunta("Esto es una prueba_1 ?");
		Respuesta respuesta_1 = new Respuesta("Si",true);
		Respuesta respuesta_2 = new Respuesta("no",false);
		Respuesta respuesta_3 = new Respuesta("no se",true);
				
		
		   List<Examen> listaExamenes = new ArrayList();
		    Examen examen1 = new Examen(pregunta_1,respuesta_1,respuesta_2,respuesta_3);
			Examen examen2 = new Examen(pregunta_2,respuesta_1,respuesta_2,respuesta_3);
	        listaExamenes.add(examen1);
	        listaExamenes.add(examen2);
	        
		Curso curso_obtenido = new Curso("Curso php","Programacion","Curso de programacion php", 1000.0, "cursophp.png");
		
		//Ejecucion
		when(servicioCurso.buscarCursoPorId(curso_obtenido.getId())).thenReturn(curso_obtenido);
	    when(servicioCurso.obtenerExamenes(curso_obtenido)).thenReturn(listaExamenes);
		ModelAndView mav = controladorCursos.examen(curso_obtenido.getId());
        
		//Comprobacion
		assertThat(mav.getViewName()).isEqualTo("vistaExamen");
	}


	@Test
	public void testFinalizarExamen() {
		
		//Preparacion 
		Pregunta pregunta_1 = new Pregunta("Esto es una prueba ?");
		Pregunta pregunta_2 = new Pregunta("Esto es una prueba_1 ?");
		Respuesta respuesta_1 = new Respuesta("Si",true);
		Respuesta respuesta_2 = new Respuesta("no",false);
		Respuesta respuesta_3 = new Respuesta("no se",true);
				
		   List<Examen> listaExamenes = new ArrayList();
		    Examen examen1 = new Examen(pregunta_1,respuesta_1,respuesta_2,respuesta_3);
			Examen examen2 = new Examen(pregunta_2,respuesta_1,respuesta_2,respuesta_3);
	        listaExamenes.add(examen1);
	        listaExamenes.add(examen2);
	        
		Curso curso_obtenido = new Curso("Curso php","Programacion","Curso de programacion php", 1000.0, "cursophp.png");
		
		boolean nota_examen = true;
		int puntajeFinal = 2;
		
		//Ejecucion
		when(servicioCurso.buscarCursoPorId(curso_obtenido.getId())).thenReturn(curso_obtenido);
		when(servicioCurso.sumarPuntajeExamen(listaExamenes)).thenReturn(nota_examen);
		when(servicioCurso.getTotalDePuntajesExamen(listaExamenes)).thenReturn(puntajeFinal);
		 when(servicioCurso.obtenerExamenes(curso_obtenido)).thenReturn(listaExamenes);
		
		ModelAndView mav = controladorCursos.finalizarExamen(curso_obtenido.getId());
        
		//Comprobacion
		assertThat(mav.getViewName()).isEqualTo("vistaExamen");
	}

	
		@Test
		public void queSePuedanVerMisCursos() {
			
			// Preparacion
			String mensaje = "mensaje";
			Usuario usuario = new Usuario("test", "test@test", "123", "cliente");
			Curso curso1 = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
			Curso curso2 = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
			
			Usuario_Curso cursoUsuario1 = new Usuario_Curso(usuario, curso1);
			Usuario_Curso cursoUsuario2 = new Usuario_Curso(usuario, curso2);
			
			List<Usuario_Curso> cursosUsuario = new ArrayList<Usuario_Curso>();
			cursosUsuario.add(cursoUsuario1);
			cursosUsuario.add(cursoUsuario2);
			
			// Ejecucion
			when(servicioUsuario.obtenerCursosDelUsuario(usuario)).thenReturn(cursosUsuario);
			when(session.getAttribute("idUsuario")).thenReturn(1);
			ModelAndView mav = controladorCursos.misCursos(session, mensaje);
			
			// Comprobacion
			assertThat(mav.getViewName()).isEqualTo("miscursos");
		}
		
		@Test
		public void queSePuedanVerLosCursosPorCategoria() {
			//preparacion
			String categoria= "Programacion";
			List<Curso> listaCursos = new ArrayList();
			Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
			Curso curso1 = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
			listaCursos.add(curso);
	        listaCursos.add(curso1);
			//ejecucion
	        when(servicioCurso.getCursosPorCategoria(categoria)).thenReturn(listaCursos);
			ModelAndView mav= controladorCursos.verCursosPorCategoria(categoria);
			//comprobacion
			assertThat(mav.getViewName()).isEqualTo("seccionCursos");
		}
		
		@Test
		public void queSePuedanIrAAgregarCurso() {
			//preparacion
			DatosCreacionCurso datos = new DatosCreacionCurso("Curso php",
					"Programacion","Curso de programacion php", 1000.0,"cursophp.png");
			//ejecucion
		
			ModelAndView mav = controladorCursos.irAAgregarCurso();
			//comprobacion
			assertThat(mav.getViewName()).isEqualTo("crearCurso");
		}
		
		@Test
		public void queSePuedanActualizarLosCursos() {
			//preparacion
			DatosCreacionCurso datos = new DatosCreacionCurso("Curso php",
					"Programacion","Curso de programacion php", 1000.0,"cursophp.png");
			int idCurso=1;
			
			//ejecucion
			ModelAndView mav = controladorCursos.actualizarCurso(idCurso, datos);
			//comprobacion
			assertThat(mav.getViewName()).isEqualTo("cursoActualizado");
		}

}
