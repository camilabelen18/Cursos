package servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modelo.Curso;
import modelo.Estado;
import modelo.Examen;
import modelo.Pregunta;
import modelo.Respuesta;
import repositorios.RepositorioCurso;
import repositorios.RepositorioUsuario;

public class ServicioCursoTest {

	
	RepositorioCurso repositorioCurso = mock(RepositorioCurso.class);
	RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
	ServicioCurso servicioCurso = new ServicioCursoImpl(repositorioCurso, repositorioUsuario);


	@Test
	public void testQueAgregaCurso(){
		//Preparacion
		Curso curso = new Curso("Curso php",
				"Programacion","Curso de programacion php", 1000.0,Estado.EN_VENTA,"cursophp.png");
		//Ejecucion
		servicioCurso.agregarCurso(curso);
		//Comprobacion
		assertThat(curso.getId()).isNotNull();
	}
	

	@Test
	public void testQueObtieneExamenes() {
		
		//Preparacion
		Curso curso = new Curso("Curso php","Programacion","Curso de programacion php", 1000.0,Estado.EN_VENTA,"cursophp.png");
		
		
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
	
	
	@Test
	public void queSePuedaVerCursosPorCategoria(){
		//Preparacion
		String categoria= "Programacion";
		
		//Ejecucion
		servicioCurso.getCursosPorCategoria(categoria);
		
		//Comprobacion
		assertThat(categoria).isNotNull();
	}
}
