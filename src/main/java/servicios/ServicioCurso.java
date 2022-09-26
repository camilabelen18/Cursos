package servicios;

import java.util.List;

import modelo.Curso;

public interface ServicioCurso {

	List<Curso> busqueda();
	void añadirCurso(Curso curso);
}
