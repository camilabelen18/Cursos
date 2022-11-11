package repositorios;

import java.util.List;

import modelo.Curso;
import modelo.Curso_Unidad;
import modelo.Estado;
import modelo.Examen;
import modelo.Unidad;
import modelo.Usuario;

public interface RepositorioCurso {

	List<Curso> obtenerListaTotalCursos();
	
	List<Curso> obtenerListaCursosPorCategoria(String categoria);
	
	List<Curso> obtenerListaCursosPorNombre(String nombreCurso);
	
	Curso obtenerCursoPorID(int id);

	void agregarCurso(Curso curso);

	List<Curso> obtenerListaCursosPorEstado(Estado estado);

	List<Unidad> obtenerUnidadesDelCurso(Curso curso);

	Unidad obtenerUnidadPorID(Integer unidad_id);

	void actualizarUnidad(Unidad unidad);

	void actualizarCurso(Curso curso_obtenido);

	Examen obtenerExamenPorID(Integer examen_id);

	List<Examen> obtenerExamenesDelCurso(Curso curso_obtenido);
	
}
