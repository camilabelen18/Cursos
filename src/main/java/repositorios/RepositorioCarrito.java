package repositorios;

import java.util.List;

import modelo.*;

public interface RepositorioCarrito {

	Carrito buscarCarritoPorID(int id_carrito);

	void agregarCursoALista(Curso curso_obtenido, Carrito carrito);

	Carrito obtenerCarritoPorIdUsuario(int id_user);

	void guardarCarrito(Carrito carrito);

	List<Curso> obtenerCursosDelCarrito(Carrito carrito);

	Carrito_Curso obtenerCarritoCurso(Carrito carrito, Curso curso);

	void eliminarCursoDelCarrito(Carrito_Curso carritoCurso);

	List<Carrito_Curso> obtenerCarritoCursos(Carrito carrito);

}
