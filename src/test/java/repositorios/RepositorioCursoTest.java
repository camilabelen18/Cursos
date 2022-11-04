package repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import archivos.SpringTest;
import modelo.Curso;
import modelo.Estado;

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

}
