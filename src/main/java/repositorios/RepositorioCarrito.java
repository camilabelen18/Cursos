package repositorios;

import modelo.Carrito;
import modelo.Curso;

public interface RepositorioCarrito {


	void guardarCursoDelCarrito(Curso curso_obtenido);

	Carrito buscarCarritoPorID(int id_carrito);

}
