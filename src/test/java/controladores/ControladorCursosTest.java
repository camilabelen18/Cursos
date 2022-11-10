package controladores;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import modelo.Curso;
import modelo.DatosCreacionCurso;
import modelo.Estado;
import modelo.Examen;
import modelo.Pregunta;
import modelo.Respuesta;
import modelo.Unidad;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

public class ControladorCursosTest {
	
	ServicioCurso servicioCurso = mock(ServicioCurso.class);
	ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	ControladorCursos controladorCursos = new ControladorCursos(servicioCurso, servicioUsuario);
	
	
	@Test
	public void testQueAgregaCursos() {
		
		//Preparacion
		DatosCreacionCurso datos = new DatosCreacionCurso("Curso php",
		"Programacion","Curso de programacion php", 1000.0,"cursophp.png");
		
		//Ejecucion
		ModelAndView mav = controladorCursos.agregarCurso(datos);
		
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
		when(servicioCurso.buscarCursoPorId(curso_id)).thenReturn(new Curso());
		when(servicioCurso.obtenerUnidadPorID(unidad_id)).thenReturn(unidad);
		ModelAndView mav = controladorCursos.completarUnidad(unidad_id, curso_id);
	
		//Comprobacion
		assertThat(mav.getViewName()).isEqualTo("vistaCurso");
	}
	
	
	
	@Test 
	public void testQueSePuedaVerUnExamen () {
		//Preparacion 
		Pregunta pregunta_1 = new Pregunta("¿Esto es una prueba ?");
		Pregunta pregunta_2 = new Pregunta("¿Esto es una prueba_1 ?");
		Respuesta respuesta_1 = new Respuesta("Si",true);
		Respuesta respuesta_2 = new Respuesta("no",false);
		Respuesta respuesta_3 = new Respuesta("no se",true);
				
		
		   List<Examen> listaExamenes = new ArrayList();
		    Examen examen1 = new Examen(pregunta_1,respuesta_1,respuesta_2,respuesta_3);
			Examen examen2 = new Examen(pregunta_2,respuesta_1,respuesta_2,respuesta_3);
	        listaExamenes.add(examen1);
	        listaExamenes.add(examen2);
	        
		Curso curso_obtenido = new Curso("Curso php","Programacion","Curso de programacion php", 1000.0,null, "cursophp.png");
		
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
		Pregunta pregunta_1 = new Pregunta("¿Esto es una prueba ?");
		Pregunta pregunta_2 = new Pregunta("¿Esto es una prueba_1 ?");
		Respuesta respuesta_1 = new Respuesta("Si",true);
		Respuesta respuesta_2 = new Respuesta("no",false);
		Respuesta respuesta_3 = new Respuesta("no se",true);
				
		   List<Examen> listaExamenes = new ArrayList();
		    Examen examen1 = new Examen(pregunta_1,respuesta_1,respuesta_2,respuesta_3);
			Examen examen2 = new Examen(pregunta_2,respuesta_1,respuesta_2,respuesta_3);
	        listaExamenes.add(examen1);
	        listaExamenes.add(examen2);
	        
		Curso curso_obtenido = new Curso("Curso php","Programacion","Curso de programacion php", 1000.0,null, "cursophp.png");
		
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
	
	

}
