package servicios;

import java.util.List;

import modelo.Curso;

public interface ServicioCurso {

	void añadirCurso(Curso curso);

	List<Curso> busqueda(String descripcion);
	Curso busquedaPorID(Long id);
	
	


}
