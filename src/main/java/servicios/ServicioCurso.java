package servicios;

import java.util.List;

import modelo.Curso;
import modelo.Estado;
import modelo.Examen;
import modelo.Unidad;
import modelo.Usuario;
import modelo.Usuario_Curso;

public interface ServicioCurso {

	List<Curso> getCursos();

	List<Curso> getCursosPorCategoria(String categoria);
	
	List<Curso> getCursosPorNombre(String nombreCurso);
	
	Curso buscarCursoPorId(int id);

	void agregarCurso(String nombre, String Categoria, String descripcion, Double precio, String imagen);

	List<Usuario_Curso> getCursosPorEstado(Estado estado, Usuario usuario);

	void agregarCurso(Curso curso);

	void cambiarEstadoCurso(Usuario_Curso usuarioCurso, Estado estado);

	List<Unidad> obtenerUnidades(Curso curso);

	Unidad obtenerUnidadPorID(Integer unidad_id);

	void completarUnidad(Unidad unidad, Usuario_Curso usuarioCurso, List<Unidad> unidades);
	
	void actualizarCurso(int idCurso, String nombre, String categoria, String descripcion, Double precio);

	Examen obtenerExamenPorId(Integer examen_id);

	List<Examen> obtenerExamenes(Curso curso_obtenido);

	int getTotalDePuntajesExamen(List<Examen> examenes);

	boolean sumarPuntajeExamen(List<Examen> examenes);

}
