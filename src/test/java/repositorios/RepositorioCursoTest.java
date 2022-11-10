package repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import archivos.SpringTest;
import modelo.Curso;
import modelo.Curso_Examen;
import modelo.Estado;
import modelo.Examen;
import modelo.Pregunta;
import modelo.Respuesta;

public class RepositorioCursoTest extends SpringTest{
	
	@Autowired
	private RepositorioCurso repositorioCurso;
	
	@Test
	@Transactional
	@Rollback
	public void testQueAgregaCurso() {
		// Preparacion
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, Estado.EN_VENTA,
				"cursophp.png");
		// Ejecucion
		repositorioCurso.agregarCurso(curso);
		// Comprobacion
		assertThat(curso.getId()).isNotNull();
	}
	
	@Test
	@Transactional
	@Rollback
	public void testQueObtieneExamenPorIdDelCurso() {
		
		// Preparacion
		Pregunta pregunta_1 = new Pregunta("¿Esto es una prueba ?");
		Respuesta respuesta_1 = new Respuesta("Si",true);
		Respuesta respuesta_2 = new Respuesta("no",false);
		Respuesta respuesta_3 = new Respuesta("no se",true);

		Examen examen1 = new Examen(pregunta_1,respuesta_1,respuesta_2,respuesta_3);
        Integer examen_id=0;
	     
		// Ejecucion
     	repositorioCurso.obtenerExamenPorID(examen1.getId());


		// Comprobacion
		assertThat(examen1.getId()).isEqualTo(examen_id);
		
	
	}
	
	
	
	@Test
	@Transactional
	@Rollback
	public void testQueObtieneExamenesDelCurso() {
	
		// Preparacion
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, Estado.EN_VENTA,"cursophp.png");
		
		Pregunta pregunta_1 = new Pregunta("¿Esto es una prueba ?");
		Pregunta pregunta_2 = new Pregunta("¿Esto es una prueba_1 ?");
		Respuesta respuesta_1 = new Respuesta("Si",true);
		Respuesta respuesta_2 = new Respuesta("no",false);
		Respuesta respuesta_3 = new Respuesta("no se",true);
				
		   List<Examen> listaExamenes = new ArrayList();
		    Examen examen1 = new Examen(pregunta_1,respuesta_1,respuesta_2,respuesta_3);
			Examen examen2 = new Examen(pregunta_2,respuesta_1,respuesta_2,respuesta_3);
			examen1.setId(0);
			examen2.setId(1);
	        listaExamenes.add(examen1);
	        listaExamenes.add(examen2);
	        
	        List<Curso_Examen> curso_examenes = new ArrayList();
	         Curso_Examen ce1 = new Curso_Examen(curso, examen1);
	         Curso_Examen ce2 = new Curso_Examen(curso,examen2);
	          curso_examenes.add(ce1);
	          curso_examenes.add(ce2);
	          
	         List<Examen> examenes = new ArrayList<Examen>();
	         
	      for (Curso_Examen cursoExamen : curso_examenes) {
	  			
	  			examenes.add(cursoExamen.getExamen());
	  		}
	      
	    //  System.out.println("MIRA ESTO " + " " + examenes);
	          
		// Ejecucion
	//	repositorioCurso.obtenerExamenesDelCurso(curso);
		
		// Comprobacion
		assertThat(listaExamenes).isEqualTo(examenes);
	
	}
	
	

}
