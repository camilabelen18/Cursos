package servicios;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import modelo.Carrito;
import modelo.Curso;
import modelo.Usuario;
import modelo.UsuarioCurso;

public interface ServicioUsuario {

	Boolean validarTarjeta(Integer nroTarjeta, String email);

	Usuario buscarUsuarioPorEmail(String email);

	void guardarCursoEnListaUsuario(Curso curso_obtenido, Usuario usuario);

	Usuario consultarUsuario(String email, String password);

	void registrar(String nombre, String email, String contrasenia);

	Usuario buscarUsuarioPorID(int id_user);

	boolean existeCursoEnListaUsuario(int idCurso, Usuario usuario);

	Boolean cancelarCurso(Curso curso_obtenido, UsuarioCurso usuarioCurso);

	void eliminarCurso(Curso curso_obtenido, Usuario usuario);

	void finalizarCurso(Curso curso_obtenido, Usuario usuario);
	
	void actualizarUsuario(int idUsuario,String nombre, String email,String password, HttpSession session);
	

	UsuarioCurso obtenerUsuarioCurso(Curso curso_obtenido, Usuario usuario);

}
