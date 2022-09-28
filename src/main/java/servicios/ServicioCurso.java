package servicios;

import java.util.List;

import modelo.Curso;

public interface ServicioCurso {

	List<Curso> getCursos();

	List<Curso> getCursosPorCategoria(String categoria);
	
	List<Curso> getCursosPorNombre(String nombreCurso);
	
	Curso busquedaPorID(int id);

	void agregarCurso(Curso curso);

}
