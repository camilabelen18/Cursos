package repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import archivos.SpringTest;
import modelo.*;

public class RepositorioCursoTest extends SpringTest{
	
	@Autowired
	private RepositorioCurso repositorioCurso;
	
	@Test
	@Transactional
	@Rollback
	public void testQueObtieneListaCursosPorNombre(){
		//Preparacion
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		//Ejecucion
		repositorioCurso.agregarCurso(curso);
		List<Curso> lista_cursos = repositorioCurso.obtenerListaCursosPorNombre(curso.getNombre());
		//Comprobacion
		for (Curso curso2 : lista_cursos) {
			if(curso2.getNombre() == curso.getNombre()) {
				assertThat(curso2.getNombre()).isEqualTo("Curso php");
			}else {
				assertThat(curso2.getNombre()).isNotEqualTo("Curso php");
			}
		}
		
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void testQueObtieneListaCursosPorId(){
		//Preparacion
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, "cursophp.png");
		//Ejecucion
		repositorioCurso.agregarCurso(curso);
		Curso curso_obtenido = repositorioCurso.obtenerCursoPorID(1);
		//Comprobacion
		assertThat(curso_obtenido.getId()).isEqualTo(1);
		
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void testQueAgregaCurso() {
		// Preparacion
		Curso curso = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0,
				"cursophp.png");
		// Ejecucion
		repositorioCurso.agregarCurso(curso);
		// Comprobacion
		assertThat(curso.getId()).isNotNull();
	}

}
