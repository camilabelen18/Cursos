package servicios;

import java.util.List;

import javax.servlet.http.HttpSession;

import modelo.*;

public interface ServicioUsuario {

	Usuario buscarUsuarioPorEmail(String email);

	void guardarCursoEnListaUsuario(Curso curso_obtenido, Usuario usuario);

	Usuario consultarUsuario(String email, String password);

	Usuario registrar(DatosRegistro datosRegistro);

	Usuario buscarUsuarioPorID(int id_user);

	boolean existeCursoEnListaUsuario(int idCurso, Usuario usuario);

	List<Usuario_Curso> obtenerCursosDelUsuario(Usuario usuario);
	
	Boolean cancelarCurso(Curso curso_obtenido, Usuario_Curso usuarioCurso);

	void eliminarCursoDelUsuario(Curso curso_obtenido, Usuario usuario);

	void finalizarCurso(Usuario_Curso usuarioCurso);
	
	void actualizarUsuario(int idUsuario, String nombre, String email,String passwordAterior, String passwordNueva, HttpSession session);

	Usuario_Curso obtenerUsuarioCurso(Curso curso_obtenido, Usuario usuario);

	void actualizarFotoPerfil(Usuario usuario, String nombreImagen);

	void enviarNotificacion(Usuario usuario, String msj, HttpSession session);
	
	List<Usuario_Notificacion> obtenerNotificaciones(Usuario usuario);

	Notificacion obtenerNotificacionPorId(int idNotif);

	void eliminarNotificacion(Notificacion notificacion, Usuario usuario, HttpSession session);

	void enviarPuntos(Usuario usuario1, Usuario usuario2, Integer puntos);

	void verificarUsuario(Usuario usuario);

	void enviarNotificacion(Usuario usuario, String msj);

	void quitarNotificacion(Usuario_Notificacion usuarioNotificacion, Usuario usuario, HttpSession sesion);

	Usuario_Notificacion obtenerNotificacionUsuario(Usuario usuario, Notificacion notificacion);

	void marcarNotificacionLeida(Usuario_Notificacion usuarioNotificacion, Usuario usuario, HttpSession sesion);

}
