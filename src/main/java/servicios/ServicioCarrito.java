package servicios;

import java.util.Set;

import modelo.Carrito;
import modelo.Curso;

public interface ServicioCarrito {

    Carrito buscarCarritoPorId(int id_carrito);

    double getTotalDePrecios(Set<Curso> cursos);

	void calcularTotal(Carrito carrito);

	void agregarCursoAlCarrito(Curso curso_obtenido, Carrito carrito);
    
}
