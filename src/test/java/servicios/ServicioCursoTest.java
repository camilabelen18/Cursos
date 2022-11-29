package servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modelo.Curso;
import modelo.Estado;
import modelo.Examen;
import modelo.Pregunta;
import modelo.Respuesta;
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
				"Programacion","Curso de programacion php", 1000.0, "cursophp.png");
		Curso curso1 = new Curso("Curso php",
				"Programacion","Curso de programacion php", 1000.0, "cursophp.png");
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
				   				1000.0,"cursophp.png");
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
	
/*
	@Test
	public void testQueObtieneExamenes() {
		
		//Preparacion
		Curso curso = new Curso("Curso php","Programacion","Curso de programacion php", 1000.0,"cursophp.png");
		
		
		//Ejecucion
      	List<Examen> listaExamenes_1=	servicioCurso.obtenerExamenes(curso);
		
		//Comprobacion
		 assertThat(listaExamenes_1).isEmpty();
		
	}
	
	@Test
	public void testQueObtieneTotalDePuntajesExamen() {
	
		//Preparacion
		Pregunta pregunta_1 = new Pregunta("�Esto es una prueba ?");
		Pregunta pregunta_2 = new Pregunta("�Esto es una prueba_1 ?");
		Respuesta respuesta_1 = new Respuesta("Si",true);
		Respuesta respuesta_2 = new Respuesta("no",false);
		Respuesta respuesta_3 = new Respuesta("no se",true);
				
		   List<Examen> listaExamenes = new ArrayList();
		    Examen examen1 = new Examen(pregunta_1,respuesta_1,respuesta_2,respuesta_3);
			Examen examen2 = new Examen(pregunta_2,respuesta_1,respuesta_2,respuesta_3);
	        listaExamenes.add(examen1);
	        listaExamenes.add(examen2);
	        
	      int puntajeTotal = 0;
	      
	      for (Examen examen : listaExamenes) {
				
				puntajeTotal += examen.getPuntaje();
			}
			
		
		//Ejecucion
	   int resultado=   servicioCurso.getTotalDePuntajesExamen(listaExamenes);
		
		//Comprobacion
	      assertThat(puntajeTotal).isEqualTo(resultado);

	}
 */
	@Test
	public void testQueObtieneCursosPorNombre(){
		//Preparacion
		Curso curso = new Curso("Curso php","Pepe",
   				"Curso de programacion php", 
   				1000.0,"cursophp.png");
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
/*	
	@Test
	public void testQueSumarPuntajeExamen() {
		
		//Preparacion
		Pregunta pregunta_1 = new Pregunta("�Esto es una prueba ?");
		Pregunta pregunta_2 = new Pregunta("�Esto es una prueba_1 ?");
		Respuesta respuesta_1 = new Respuesta("Si",true);
		Respuesta respuesta_2 = new Respuesta("no",false);
		Respuesta respuesta_3 = new Respuesta("no se",true);
				
		   List<Examen> listaExamenes = new ArrayList();
		    Examen examen1 = new Examen(pregunta_1,respuesta_1,respuesta_2,respuesta_3);
			Examen examen2 = new Examen(pregunta_2,respuesta_1,respuesta_2,respuesta_3);
	        listaExamenes.add(examen1);
	        listaExamenes.add(examen2);
		
	        boolean verdadero=false;
	        
	        for (Examen examen : listaExamenes) {
				
				if(examen.getRespuesta().getRespuesta_correcta()  || examen.getRespuesta_2().getRespuesta_correcta()  || examen.getRespuesta_3().getRespuesta_correcta()  ) {
					examen.setPuntaje(1);
					verdadero=true;
				} 
			}

		//Ejecucion
	       boolean resultado = servicioCurso.sumarPuntajeExamen(listaExamenes);
		
		//Comprobacion
	       assertThat(verdadero).isEqualTo(resultado);
		
	}
	 */
	
	@Test
	public void queSePuedaVerCursosPorCategoria(){
		//Preparacion
		String categoria= "Programacion";
		
		//Ejecucion
		servicioCurso.getCursosPorCategoria(categoria);
		
		//Comprobacion
		assertThat(categoria).isNotNull();
	}

	@Test
	public void testQueBuscaCursoPorId(){
		//Preparacion
		Curso curso = new Curso("Curso php","Pepe",
   								"Curso de programacion php", 
   								1000.0,"cursophp.png");
		//Ejecucion
		servicioCurso.agregarCurso(curso);
		when(repositorioCurso.obtenerCursoPorID(curso.getId())).thenReturn(curso);
		Curso curso_obtenido =servicioCurso.buscarCursoPorId(curso.getId());
		//Comprobacion
		assertThat(curso_obtenido.getId()).isEqualTo(curso.getId());
	}
	
	@Test
	public void testQueAgregaCurso(){
		
		//Preparacion
		Usuario usuario = new Usuario("Camila", "camilabelen906@gmail.com", "1234", "admin");
		Curso curso = new Curso("Curso php","Programacion","Curso de programacion php", 1000.0, "cursophp.png");
		
		//Ejecucion
		when(session.getAttribute("idUsuario")).thenReturn(usuario.getId());
		repositorioCurso.agregarCurso(curso);
		servicioCurso.agregarCurso(curso);
		
		//Comprobacion
	}
}


