package servicios;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import modelo.*;

public interface ServicioUsuario {

	Boolean validarTarjeta(Integer nroTarjeta, String email);

	Usuario buscarUsuarioPorEmail(String email);

	void guardarCursoEnListaUsuario(Curso curso_obtenido, Usuario usuario);

	Usuario consultarUsuario(String email, String password);

	Usuario registrar(DatosRegistro datosRegistro);

	Usuario buscarUsuarioPorID(int id_user);

	boolean existeCursoEnListaUsuario(int idCurso, Usuario usuario);

	List<Usuario_Curso> obtenerCursosDelUsuario(Usuario usuario);
	
	Boolean cancelarCurso(Curso curso_obtenido, Usuario_Curso usuarioCurso);

	void eliminarCurso(Curso curso_obtenido, Usuario usuario);

	void finalizarCurso(Usuario_Curso usuarioCurso);
	
	void actualizarUsuario(int idUsuario,String nombre, String email,String passwordAterior, String passwordNueva, String repeticionPasswordNueva, HttpSession session);

	Usuario_Curso obtenerUsuarioCurso(Curso curso_obtenido, Usuario usuario);

	void actualizarFotoPerfil(Usuario usuario, String nombreImagen);

	Integer verificarTarjetaUsuario(Usuario usuario, Integer nroTarjeta);

	Usuario actualizarUsuarioPrueba(int idUsuario,String nombre, String email,String passwordAterior, String passwordNueva, String repeticionPasswordNueva, HttpSession session);

	void enviarNotificacion(Usuario usuario, String msj, HttpSession session);
	
	List<Notificacion> obtenerNotificaciones(Usuario usuario);

	Notificacion obtenerNotificacionPorId(int idNotif);

	void eliminarNotificacion(Notificacion notificacion, Usuario usuario, HttpSession session);
	
}
