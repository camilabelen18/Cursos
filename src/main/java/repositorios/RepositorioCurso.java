package repositorios;

import java.util.List;

import modelo.Curso;

public interface RepositorioCurso {

	List<Curso> obtenerListaCursos();

	Curso a�adirCurso(Curso curso);


	
}
