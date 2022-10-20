package repositorios;

import java.util.List;
import java.util.Set;

import modelo.Carrito;
import modelo.Curso;

public interface RepositorioCarrito {

	Carrito buscarCarritoPorID(int id_carrito);

	void agregarCursoALista(Curso curso_obtenido, Carrito carrito);

	Carrito obtenerCarritoPorIdUsuario(int id_user);

	void guardarCarrito(Carrito carrito);

	List<Curso> obtenerCursosDelCarrito(Carrito carrito);

}
