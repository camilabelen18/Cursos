package repositorios;

import modelo.Carrito;
import modelo.Curso;

public interface RepositorioCarrito {

	Carrito buscarCarritoPorID(int id_carrito);

	void actualizarCarrito(Carrito carrito);

	void agregarCursoALista(Curso curso_obtenido, Carrito carrito);

}
