package repositorios;

import java.util.List;

import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;

public interface RepositorioCurso {

	List<Curso> obtenerListaTotalCursos();
	
	List<Curso> obtenerListaCursosPorCategoria(String categoria);
	
	List<Curso> obtenerListaCursosPorNombre(String nombreCurso);
	
	Curso obtenerCursoPorID(int id);

	void agregarCurso(Curso curso);

	List<Curso> obtenerListaCursosPorEstado(Estado estado);
	
}
