package servicios;

import java.util.List;

import modelo.Curso;
import modelo.Estado;
import modelo.Unidad;
import modelo.Usuario;

public interface ServicioCurso {

	List<Curso> getCursos();

	List<Curso> getCursosPorCategoria(String categoria);
	
	List<Curso> getCursosPorNombre(String nombreCurso);
	
	Curso buscarCursoPorId(int id);

	void agregarCurso(String nombre, String Categoria, String descripcion, Double precio, String imagen);

	List<Curso> getCursosPorEstado(Estado estado);

	void agregarCurso(Curso curso);

	void cambiarEstadoCurso(Curso curso_obtenido, Estado estado);

	List<Unidad> obtenerUnidades(Curso curso);

	Unidad obtenerUnidadPorID(Integer unidad_id);

	void completarUnidad(Unidad unidad, Curso curso, List<Unidad> unidades);
	
	void actualizarCurso(int idCurso, String nombre, String categoria, String descripcion, Double precio);

}
