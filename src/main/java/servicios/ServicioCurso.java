package servicios;

import java.util.List;

import modelo.*;

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
	
}
