package servicios;

import java.util.List;

import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;

public interface ServicioCurso {

	List<Curso> getCursos();

	List<Curso> getCursosPorCategoria(String categoria);
	
	List<Curso> getCursosPorNombre(String nombreCurso);
	
	Curso busquedaPorID(int id);

	void agregarCurso(String nombre, String Categoria, String descripcion, Double precio, String imagen);

	List<Curso> getCursosPorEstado(Estado estado);

	void agregarCurso(Curso curso);

	void cambiarEstadoCurso(Curso curso_obtenido, Estado estado);

	void actualizarCurso(int idCurso, String nombre, String categoria, String descripcion, Double precio);

}
