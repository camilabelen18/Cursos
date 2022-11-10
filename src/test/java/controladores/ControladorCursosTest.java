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
	public void testQuePermiteBuscar() {
		//Preparación
		List<Curso> listaCursos = new ArrayList();
		Curso curso = new Curso("Curso php", "Programacion", 
								"Curso de programacion php",1000.0, 
								Estado.EN_CURSO, "cursophp.png");
		Curso curso1 = new Curso("Curso php", "Programacion", 
								"Curso de programacion php",1000.0, 
								Estado.EN_CURSO, "cursophp.png");
		listaCursos.add(curso);
		listaCursos.add(curso1);
		
		//Ejecución
		when(servicioCurso.getCursosPorNombre(curso.getNombre())).thenReturn(listaCursos);
		when(servicioCurso.getCursosPorNombre(curso1.getNombre())).thenReturn(listaCursos);
		ModelAndView mav = controladorCursos.buscar(curso.getNombre());
		//Comprobación
		assertThat(mav.getViewName()).isEqualTo("seccionCursos");
	}
	
	@Test
	public void testQuePermitaVerCursosPorEstado() {
		//List<Curso> cursos = servicioCurso.getCursosPorEstado(estado);
		
		//Preparacion
		List<Curso> listaCursos = new ArrayList();
		Curso curso = new Curso("Curso php", "Programacion", 
								"Curso de programacion php",1000.0, 
								Estado.EN_CURSO, "cursophp.png");
		Curso curso1 = new Curso("Curso php", "Programacion", 
								"Curso de programacion php",1000.0, 
								Estado.EN_CURSO, "cursophp.png");
		listaCursos.add(curso);
		listaCursos.add(curso1);
		
		//Ejecucion
		when(servicioCurso.getCursosPorEstado(curso.getEstado())).thenReturn(listaCursos);
		when(servicioCurso.getCursosPorEstado(curso1.getEstado())).thenReturn(listaCursos);
		ModelAndView mav = controladorCursos.verCursosPorEstado(curso.getEstado());
		//Comprobacion
		assertThat(mav.getViewName()).isEqualTo("miscursos");
	}
	
	@Test
	public void testQuePermitaIrAEditarCursos() {
		//Preparacion
		DatosCreacionCurso datosCrearCurso = new DatosCreacionCurso();
		Curso curso = new Curso("Curso php", "Programacion", 
								"Curso de programacion php",1000.0, 
								Estado.EN_CURSO, "cursophp.png");
		//Ejecucion
		ModelAndView mav = controladorCursos.irAEditarCurso(curso.getId(), curso.getNombre(), 
							curso.getCategoria(),curso.getDescripcion(), curso.getPrecio());
		//Comprobacion
		assertThat(mav.getViewName()).isEqualTo("editarCurso");
	}
	
	@Test
	public void testQuePermitaVerUnidadCurso() {
		//Preparacion
		Curso curso = new Curso("Curso php", "Programacion", 
								"Curso de programacion php",1000.0, 
								Estado.EN_CURSO, "cursophp.png");
		Unidad unidad = new Unidad("prueba", "prueba.com");
		List<Unidad> unidades = new ArrayList();
		unidades.add(unidad);
		//Ejecucion
		when(servicioCurso.buscarCursoPorId(curso.getId())).thenReturn(new Curso());
		when(servicioCurso.obtenerUnidades(curso)).thenReturn(unidades);
		when(servicioCurso.obtenerUnidadPorID(unidad.getId())).thenReturn(unidad);
		ModelAndView mav = controladorCursos.verUnidadCurso(unidad.getId(), curso.getId());
		//Comprobacion
		assertThat(mav.getViewName()).isEqualTo("vistaCurso");
	}
	//Preparacion
	//Ejecucion
	//Comprobacion

}
