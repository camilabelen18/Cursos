package servicios;

import java.util.List;
import java.util.Set;

import modelo.Carrito;
import modelo.Curso;
import modelo.Usuario;

public interface ServicioUsuario {

	Boolean validarTarjeta(Integer nroTarjeta, String email);

	Usuario buscarUsuarioPorEmail(String email);

	void guardarCursoEnListaUsuario(Curso curso_obtenido, Usuario usuario);

	Usuario consultarUsuario(String email, String password);

	void registrar(String nombre, String email, String contrasenia);

	Usuario buscarUsuarioPorID(int id_user);

	boolean existeCursoEnListaUsuario(int idCurso, Usuario usuario);

	void cancelarCurso(Curso curso_obtenido, Usuario usuario);

	void eliminarCurso(Curso curso_obtenido, Usuario usuario);

	void finalizarCurso(Curso curso_obtenido, Usuario usuario);

	void agregarCursoAlCarrito(Curso curso_obtenido, Carrito carrito);

	double getTotalDePrecios(Set<Curso> cursos);

}
