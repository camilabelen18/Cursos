package servicios;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Carrito;
import modelo.Curso;
import modelo.Usuario;
import repositorios.RepositorioCarrito;
import repositorios.RepositorioUsuario;
import repositorios.RepositorioUsuarioCurso;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	private RepositorioCarrito repositorioCarrito;

	@Autowired
	private RepositorioUsuarioCurso repositorioUsuarioCurso;

	@Override
	public Boolean validarTarjeta(Integer nroTarjeta, String email) {
		return repositorioUsuario.buscarTarjetaEmail(nroTarjeta, email);
	}

	@Override
	public Usuario buscarUsuarioPorEmail(String email) {
		return repositorioUsuario.buscarUsuarioPorEmail(email);
	}

	@Override
	public void guardarCursoEnListaUsuario(Curso curso_obtenido, Usuario usuario) {
		repositorioUsuario.guardarCursoDelUsuario(curso_obtenido, usuario);
	}

	@Override
	public Usuario consultarUsuario(String email, String password) {
		return repositorioUsuario.buscarUsuario(email, password);
	}

	@Override
	public void registrar(String nombre, String email, String contrasenia) {

		Usuario nuevoUsuario = new Usuario();
		Carrito carrito = new Carrito();

		nuevoUsuario.setNombre(nombre);
		nuevoUsuario.setEmail(email);
		nuevoUsuario.setPassword(contrasenia);
		nuevoUsuario.setRol("cliente");
		nuevoUsuario.setNroTarjeta(999);
		carrito.setUsuario(nuevoUsuario);

		repositorioUsuario.guardarUsuario(nuevoUsuario);
		repositorioCarrito.guardarCarrito(carrito);
	}

	@Override
	public Usuario buscarUsuarioPorID(int id_user) {
		return repositorioUsuario.buscarUsuarioPorID(id_user);
	}

	@Override
	public boolean existeCursoEnListaUsuario(int idCurso, Usuario usuario) {

		boolean yaExisteElCurso = false;
		List<Curso> cursos = repositorioUsuarioCurso.obtenerCursosDelUsuario(usuario);

		// Se recorre la lista de los cursos del usuario y se verifica si ya existe un
		// curso
		// con el id del curso seleccionado
		for (Curso curso : cursos) {

			if (curso.getId() == idCurso) {

				yaExisteElCurso = true;
				break;
			}
		}

		return yaExisteElCurso;
	}

	@Override
	public void cancelarCurso(Curso curso_obtenido, Usuario usuario) {
		repositorioUsuario.cancelarCurso(curso_obtenido, usuario);
	}

	@Override
	public void eliminarCurso(Curso curso_obtenido, Usuario usuario) {
		repositorioUsuario.eliminarCurso(curso_obtenido, usuario);
	}

	@Override
	public void finalizarCurso(Curso curso_obtenido, Usuario usuario) {
		repositorioUsuario.finalizarCurso(curso_obtenido, usuario);
	}

	@Override
	public void actualizarUsuario(int idUsuario, String nombre, String email, String password, HttpSession session) {
		Usuario usuario = repositorioUsuario.buscarUsuarioPorID(idUsuario);
		if (nombre != "") {
			usuario.setNombre(nombre);
			session.setAttribute("nombreUsuario", nombre);
		}
		if (email != "") {
			usuario.setEmail(email);
		}
		if (password != "") {
			usuario.setPassword(password);
		}

	}



}
