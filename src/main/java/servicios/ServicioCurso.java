package servicios;

import java.util.List;

import modelo.Curso;

public interface ServicioCurso {

	void aņadirCurso(Curso curso);

	List<Curso> busqueda(String descripcion);
	Curso busquedaPorID(Long id);
	
	


}
