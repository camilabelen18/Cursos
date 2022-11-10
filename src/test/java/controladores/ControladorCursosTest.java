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
import modelo.Estado;
import modelo.Unidad;
import modelo.Usuario;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

public class ControladorCursosTest {
	
	
	ServicioCurso servicioCurso = mock(ServicioCurso.class);
	ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	ControladorCursos controladorCursos = new ControladorCursos(servicioCurso, servicioUsuario);
	HttpSession session= mock(HttpSession.class);
	
	
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
		public void queSePuedanVerMisCursos() {
			//preparacion
			String mensaje="mensaje";
			Usuario usuario = new Usuario("juan", "hola@hola.com","123","Cliente");
			
			 List<Curso> listaCursos = new ArrayList();
		        Curso curso = new Curso("Curso php", "Programacion",
		                                "Curso de programacion php",1000.0,
		                                Estado.EN_CURSO, "cursophp.png");
		        Curso curso1 = new Curso("Curso php", "Programacion",
		                                "Curso de programacion php",1000.0,
		                                Estado.EN_CURSO, "cursophp.png");
		        listaCursos.add(curso);
		        listaCursos.add(curso1); 
		      //ejecucion
			
			when(servicioUsuario.obtenerCursosDelUsuario(usuario)).thenReturn(listaCursos);
			
			when(session.getAttribute("idUsuario")).thenReturn(1);
			
			ModelAndView mav= controladorCursos.misCursos(session , mensaje);
			//comprobacion
			assertThat(mav.getViewName()).isEqualTo("miscursos");
		}
		
		@Test
		public void queSePuedanVerLosCursosPorCategoria() {
			//preparacion
			String categoria= "Programacion";
			List<Curso> listaCursos = new ArrayList();
	        Curso curso = new Curso("Curso php", "Programacion",
	                                "Curso de programacion php",1000.0,
	                                Estado.EN_CURSO, "cursophp.png");
	        Curso curso1 = new Curso("Curso php", "Programacion",
	                                "Curso de programacion php",1000.0,
	                                Estado.EN_CURSO, "cursophp.png");
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
