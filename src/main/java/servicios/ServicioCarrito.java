package servicios;

import java.util.List;
import java.util.Set;

import modelo.Carrito;
import modelo.Carrito_Curso;
import modelo.Curso;
import modelo.Usuario;

public interface ServicioCarrito {

    Carrito buscarCarritoPorId(int id_carrito);

    double getTotalDePrecios(List<Curso> cursos);

	void agregarCursoAlCarrito(Curso curso_obtenido, Carrito carrito);

	Carrito obtenerCarritoPorIdUsuario(int id_user);

	List<Curso> obtenerCursosDelCarrito(Carrito carrito);

	Carrito_Curso obtenerCarritoCurso(Carrito carrito, Curso curso);

	void comprarCursosDelCarrito(List<Curso> cursos, Usuario usuario);

	List<Carrito_Curso> obtenerCarritoCursos(Carrito carrito);
    
	void eliminarCursoDelCarrito(Carrito_Curso carritoCurso);

	void vaciarCursosDelCarrito(List<Carrito_Curso> cursosCarrito);
	
}
