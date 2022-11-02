package servicios;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import modelo.Carrito;
import modelo.Curso;
import modelo.Usuario;
import modelo.Usuario_Curso;

public interface ServicioUsuario {

	Boolean validarTarjeta(Integer nroTarjeta, String email);

	Usuario buscarUsuarioPorEmail(String email);

	void guardarCursoEnListaUsuario(Curso curso_obtenido, Usuario usuario);

	Usuario consultarUsuario(String email, String password);

	void registrar(String nombre, String email, String contrasenia);

	Usuario buscarUsuarioPorID(int id_user);

	boolean existeCursoEnListaUsuario(int idCurso, Usuario usuario);

	List<Curso> obtenerCursosDelUsuario(Usuario usuario);
	
	Boolean cancelarCurso(Curso curso_obtenido, Usuario_Curso usuarioCurso);

	void eliminarCurso(Curso curso_obtenido, Usuario usuario);

	void finalizarCurso(Curso curso_obtenido);
	
	void actualizarUsuario(int idUsuario,String nombre, String email,String password, HttpSession session);

	Usuario_Curso obtenerUsuarioCurso(Curso curso_obtenido, Usuario usuario);

	void actualizarFotoPerfil(Usuario usuario, String nombreImagen);


}
