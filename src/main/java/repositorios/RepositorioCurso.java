package repositorios;

import java.util.List;

import modelo.Curso;

public interface RepositorioCurso {

	List<Curso> obtenerListaCursosPorDescripcion(String descripcion);

	Curso obtenerListaCursosPorID(Long id);

}
