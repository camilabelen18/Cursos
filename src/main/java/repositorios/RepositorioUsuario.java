package repositorios;

import java.util.List;

import modelo.Carrito;
import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;
import modelo.UsuarioCurso;

public interface RepositorioUsuario {

	Boolean buscarTarjetaEmail(Integer nroTarjeta, String email);

	Usuario buscarUsuarioPorEmail(String email);
	
	void guardarCursoDelUsuario(Curso curso_obtenido, Usuario usuario);

	Usuario buscarUsuario(String email, String password);

	void guardarUsuario(Usuario nuevoUsuario);

	Usuario buscarUsuarioPorID(int id_user);

	Boolean cancelarCurso(Curso curso_obtenido, UsuarioCurso usuarioCurso);
	
	UsuarioCurso obtenerUsuarioCurso(Curso curso_obtenido, Usuario usuario);

	void eliminarCurso(Curso curso_obtenido, Usuario usuario);

	void finalizarCurso(Curso curso_obtenido, Usuario usuario);

	void cambiarEstadoCurso(Curso curso_obtenido, Estado estado);

}
