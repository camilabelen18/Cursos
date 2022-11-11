package servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;
import modelo.Usuario_Curso;
import repositorios.RepositorioCurso;
import repositorios.RepositorioUsuario;

public class ServicioCursoTest {

	RepositorioCurso repositorioCurso = mock(RepositorioCurso.class);
	RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
	HttpSession session = mock(HttpSession.class);
	ServicioCurso servicioCurso = new ServicioCursoImpl(repositorioCurso, repositorioUsuario);

	@Test
	public void testQueVerificaQueNoHayCursos() {
		 //Ejecucion
		when(repositorioCurso.obtenerListaTotalCursos()).thenReturn(new ArrayList<Curso>());
		List<Curso> lista_cursos = servicioCurso.getCursos();
		 //Comprobacion
		assertThat(lista_cursos).isEmpty();
	}
	
	@Test
	public void testQueVerificaObtenerCursos() {
		 //Preparacion
		Curso curso = new Curso("Curso php",
				"Programacion","Curso de programacion php", 1000.0,Estado.EN_VENTA,"cursophp.png");
		Curso curso1 = new Curso("Curso php",
				"Programacion","Curso de programacion php", 1000.0,Estado.EN_VENTA,"cursophp.png");
		 //Ejecucion
		servicioCurso.agregarCurso(curso);
		servicioCurso.agregarCurso(curso1);
		when(repositorioCurso.obtenerListaTotalCursos()).thenReturn(new ArrayList<Curso>());
		List<Curso> lista_cursos = servicioCurso.getCursos();
		 //Comprobacion
		assertThat(lista_cursos).isNotNull();
	}
	
	@Test
	public void testQuePermiteObtenerCursoPorCategoria(){
		//Preparacion
		Curso curso = new Curso("Curso php","Pepe",
				   				"Curso de programacion php", 
				   				1000.0,Estado.EN_VENTA,"cursophp.png");
		String categoria = "Programacion";
		//Ejecucion
		servicioCurso.agregarCurso(curso);
		when(repositorioCurso.obtenerListaCursosPorCategoria(categoria)).thenReturn(new ArrayList<Curso>());		
		List<Curso> lista_cursos = servicioCurso.getCursosPorCategoria(categoria);
		//Comprobacion
		for (Curso curso2 : lista_cursos) {
			if(curso2.getCategoria() == "Programacion") {
				assertThat(curso2.getCategoria()).isEqualTo("Programacion");
			}
			else {
				assertThat(curso2.getCategoria()).isNotEqualTo("Programacion");
			}
		}
	}
	
	@Test
	public void testQueObtieneCursosPorNombre(){
		//Preparacion
		Curso curso = new Curso("Curso php","Pepe",
   				"Curso de programacion php", 
   				1000.0,Estado.EN_VENTA,"cursophp.png");
		String nombre = "Curso php";
		//Ejecucion
		servicioCurso.agregarCurso(curso);
		when(repositorioCurso.obtenerListaCursosPorNombre(curso.getNombre())).thenReturn(new ArrayList<Curso>());
		List<Curso> lista_nombre = servicioCurso.getCursosPorNombre(curso.getNombre());
		
		//Comprobacion
		for (Curso curso2 : lista_nombre) {
			if(curso2.getNombre() == nombre){
				assertThat(curso.getNombre()).isEqualTo("Curso php");
			}else {
				assertThat(curso.getNombre()).isNotEqualTo("Curso php");
			}
		}
		
	}
	
	@Test
	public void testQueBuscaCursoPorId(){
		//Preparacion
		Curso curso = new Curso("Curso php","Pepe",
   								"Curso de programacion php", 
   								1000.0,Estado.EN_VENTA,"cursophp.png");
		//Ejecucion
		servicioCurso.agregarCurso(curso);
		when(repositorioCurso.obtenerCursoPorID(curso.getId())).thenReturn(curso);
		Curso curso_obtenido =servicioCurso.buscarCursoPorId(curso.getId());
		//Comprobacion
		assertThat(curso_obtenido.getId()).isEqualTo(curso.getId());
	}
	
//	@Test
//	public void testQueAgregaCurso(){
//		//Preparacion
//		 Usuario usuario = new Usuario("Camila", "camilabelen906@gmail.com", "1234", "admin");
//		 Curso curso = new Curso("Curso php","Programacion",
//				 				 "Curso de programacion php", 
//				 				 1000.0,Estado.EN_VENTA,"cursophp.png");
//		//Ejecucion
//		when(session.getAttribute("idUsuario")).thenReturn(usuario.getId());
//		repositorioCurso.agregarCurso(curso);
//		servicioCurso.agregarCurso(curso);
//		//Comprobacion
//	}
}


