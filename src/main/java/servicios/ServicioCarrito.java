package servicios;

import java.util.List;
import java.util.Set;

import modelo.Carrito;
import modelo.Curso;

public interface ServicioCarrito {

    Carrito buscarCarritoPorId(int id_carrito);

    double getTotalDePrecios(List<Curso> cursos);

	void agregarCursoAlCarrito(Curso curso_obtenido, Carrito carrito);

	Carrito obtenerCarritoPorIdUsuario(int id_user);

	List<Curso> obtenerCursosDelCarrito(Carrito carrito);
    
}
