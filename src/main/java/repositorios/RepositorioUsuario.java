package repositorios;

import modelo.Curso;
import modelo.Usuario;

public interface RepositorioUsuario {

	Boolean buscarTarjetaEmail(Integer nroTarjeta, String email);

	Usuario buscarUsuarioPorEmail(String email);
	
	void guardarCursoDelUsuario(Curso curso_obtenido, Usuario usuario);

	Usuario buscarUsuario(String email, String password);

	void guardarUsuario(Usuario nuevoUsuario);

	Usuario buscarUsuarioPorID(int id_user);

	void cancelarCurso(Curso curso_obtenido, Usuario usuario);

	void eliminarCurso(Curso curso_obtenido, Usuario usuario);

	void finalizarCurso(Curso curso_obtenido, Usuario usuario);

	
}
