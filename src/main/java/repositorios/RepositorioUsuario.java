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

	Boolean cancelarCurso(Usuario_Curso usuarioCurso);
	
	Usuario_Curso obtenerUsuarioCurso(Curso curso_obtenido, Usuario usuario);

	void eliminarCurso(Curso curso_obtenido, Usuario usuario);
	
	void cambiarEstadoCurso(Usuario_Curso usuarioCurso, Estado estado);

	List<Usuario_Curso> obtenerCursosDelUsuario(Usuario usuario);

	void actualizarUsuario(Usuario usuario);

	void guardarGiftcardDeUsuario(Giftcard gift);

	void actualizarCursoDelUsuario(Usuario_Curso usuarioCurso);

}
