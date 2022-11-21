package repositorios;

import java.util.List;

import modelo.Curso;
import modelo.Curso_Unidad;
import modelo.DatosExamen;
import modelo.DatosPregunta;
import modelo.Estado;
import modelo.Examen;
import modelo.Pregunta;
import modelo.Respuesta;
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

	Examen obtenerExamenPorCurso(Curso curso_obtenido);

	List<Pregunta> obtenerPreguntasDelExamen(Examen examen);

	Pregunta buscarPreguntaPorId(int pregunta_id);

	Respuesta buscarRespuestaPorId(int respuesta_id);







	


	
}
