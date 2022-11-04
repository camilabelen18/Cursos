package servicios;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import modelo.Carrito;
import modelo.Curso;
import modelo.DatosRegistro;
import modelo.Estado;
import modelo.Unidad;
import modelo.Usuario;
import modelo.Usuario_Curso;
import repositorios.RepositorioCarrito;
import repositorios.RepositorioCurso;
import repositorios.RepositorioUsuario;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	private RepositorioCarrito repositorioCarrito;

	@Autowired
	private RepositorioCurso repositorioCurso;
	
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
		
		if (repositorioUsuario.buscarUsuario(email, password) != null) {
			
			return repositorioUsuario.buscarUsuario(email, password);
		}
		else {
			throw new UsuarioInexistenteException();
		}
	}

	@Override
	public Usuario registrar(DatosRegistro datosRegistro) {

		Usuario nuevoUsuario = new Usuario();
		Carrito carrito = new Carrito();
		
		// Se comprueba si las contraseñas ingresadas son iguales
		if (datosRegistro.getContrasenia().equals(datosRegistro.getRepetirContrasenia())) {
			
			nuevoUsuario.setNombre(datosRegistro.getNombre());
			nuevoUsuario.setEmail(datosRegistro.getEmail());
			nuevoUsuario.setPassword(datosRegistro.getContrasenia());
			nuevoUsuario.setRol("cliente");
			nuevoUsuario.setNroTarjeta(999);
			carrito.setUsuario(nuevoUsuario);

			repositorioUsuario.guardarUsuario(nuevoUsuario);
			repositorioCarrito.guardarCarrito(carrito);
			
			return nuevoUsuario;
		}
		else {
			throw new ClavesNoSonIgualesException();
		}

	}

	@Override
	public Usuario buscarUsuarioPorID(int id_user) {
		return repositorioUsuario.buscarUsuarioPorID(id_user);
	}

	@Override
	public boolean existeCursoEnListaUsuario(int idCurso, Usuario usuario) {

		boolean yaExisteElCurso = false;
		List<Curso> cursos = repositorioUsuario.obtenerCursosDelUsuario(usuario);
		
		// Se recorre la lista de los cursos del usuario y se verifica si ya existe un curso
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
	public Boolean cancelarCurso(Curso curso_obtenido, Usuario_Curso usuarioCurso) {
		
		if (repositorioUsuario.cancelarCurso(curso_obtenido, usuarioCurso) == true) {
			return true;
		}
		else {
			throw new CancelacionCursoException();
		}
	}

	@Override
	public void eliminarCurso(Curso curso_obtenido, Usuario usuario) {
		repositorioUsuario.eliminarCurso(curso_obtenido, usuario);
	}

	@Override
	public void finalizarCurso(Curso curso_obtenido) {
		
		curso_obtenido.setEstado(Estado.FINALIZADO);
		curso_obtenido.setCursoTerminado(true);
		curso_obtenido.setProgreso(100.0);
		repositorioCurso.actualizarCurso(curso_obtenido);
		
		List<Unidad> unidades = repositorioCurso.obtenerUnidadesDelCurso(curso_obtenido);
		
		for (Unidad unidad : unidades) {
			
			unidad.setCompletado(true);
			repositorioCurso.actualizarUnidad(unidad);
		}
		
	}
	
	@Override
	public List<Curso> obtenerCursosDelUsuario(Usuario usuario) {
		return repositorioUsuario.obtenerCursosDelUsuario(usuario);
	}

	@Override
	public Usuario_Curso obtenerUsuarioCurso(Curso curso_obtenido, Usuario usuario) {
		return repositorioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);
	}

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

	@Override
	public void actualizarFotoPerfil(Usuario usuario, String nombreImagen) {
		
		usuario.setImagen(nombreImagen);
		repositorioUsuario.actualizarUsuario(usuario);
	}

	@Override
	public Integer verificarTarjetaUsuario(Usuario usuario, Integer nroTarjeta) {
		
		if (usuario.getNroTarjeta().equals(nroTarjeta)) {
			
			return nroTarjeta;
		}
		else {
			throw new TarjetaInvalidaException();
		}
	}

}
