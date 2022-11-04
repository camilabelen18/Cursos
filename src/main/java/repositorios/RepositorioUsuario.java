package repositorios;

import java.util.List;

import modelo.*;

public interface RepositorioUsuario {

	Boolean buscarTarjetaEmail(Integer nroTarjeta, String email);

	Usuario buscarUsuarioPorEmail(String email);
	
	void guardarCursoDelUsuario(Curso curso_obtenido, Usuario usuario);

	Usuario buscarUsuario(String email, String password);

	void guardarUsuario(Usuario nuevoUsuario);

	Usuario buscarUsuarioPorID(int id_user);

	Boolean cancelarCurso(Curso curso_obtenido, Usuario_Curso usuarioCurso);
	
	Usuario_Curso obtenerUsuarioCurso(Curso curso_obtenido, Usuario usuario);

	void eliminarCurso(Curso curso_obtenido, Usuario usuario);
	
	void cambiarEstadoCurso(Curso curso_obtenido, Estado estado);

	List<Curso> obtenerCursosDelUsuario(Usuario usuario);

	void actualizarUsuario(Usuario usuario);

}
