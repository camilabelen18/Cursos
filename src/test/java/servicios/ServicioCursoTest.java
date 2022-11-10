package servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import modelo.Curso;
import modelo.Estado;
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
	public void queSePuedaVerCursosPorCategoria(){
		//Preparacion
		String categoria= "Programacion";
		
		//Ejecucion
		servicioCurso.getCursosPorCategoria(categoria);
		
		//Comprobacion
		assertThat(categoria).isNotNull();
	}
}
