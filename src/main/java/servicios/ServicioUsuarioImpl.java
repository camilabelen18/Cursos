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
import modelo.Giftcard;
import modelo.Notificacion;
import modelo.Unidad;
import modelo.Usuario;
import modelo.Usuario_Curso;
import repositorios.RepositorioCarrito;
import repositorios.RepositorioCurso;
import repositorios.RepositorioGiftcard;
import repositorios.RepositorioUsuario;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario repositorioUsuario;
	private RepositorioCarrito repositorioCarrito;
	private RepositorioCurso repositorioCurso;
	private RepositorioGiftcard repositorioGiftcard;
	
	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario, RepositorioCarrito repositorioCarrito, RepositorioCurso repositorioCurso, RepositorioGiftcard repositorioGiftcard) {
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioCarrito = repositorioCarrito;
		this.repositorioCurso = repositorioCurso;
		this.repositorioGiftcard = repositorioGiftcard;
	}

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
		
		Usuario usuarioObtenido = repositorioUsuario.buscarUsuario(email, password);

		if (usuarioObtenido != null) {

			return usuarioObtenido;
		}
		else {
			throw new UsuarioInexistenteException();
		}
	}

	@Override
	public Usuario registrar(DatosRegistro datosRegistro) {

		Usuario nuevoUsuario = new Usuario();
		Carrito carrito = new Carrito();
		Giftcard gift = new Giftcard(222, 0, 0.0);
		
		// Se comprueba si las contraseñas ingresadas son iguales
		if (datosRegistro.getContrasenia().equals(datosRegistro.getRepetirContrasenia())) {

			nuevoUsuario.setNombre(datosRegistro.getNombre());
			nuevoUsuario.setEmail(datosRegistro.getEmail());
			nuevoUsuario.setPassword(datosRegistro.getContrasenia());
			nuevoUsuario.setRol("cliente");
			nuevoUsuario.setNroTarjeta(999);
			nuevoUsuario.setImagen("default-user.png");
			nuevoUsuario.setGiftcard(gift);
			carrito.setUsuario(nuevoUsuario);

			repositorioUsuario.guardarGiftcardDeUsuario(gift);
			repositorioUsuario.guardarUsuario(nuevoUsuario);
			repositorioCarrito.guardarCarrito(carrito);

			return nuevoUsuario;
		} else {
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
		List<Usuario_Curso> cursosUsuario = repositorioUsuario.obtenerCursosDelUsuario(usuario);

		// Se recorre la lista de los cursos del usuario y se verifica si ya existe un
		// curso
		// con el id del curso seleccionado
		for (Usuario_Curso cursoUsuario : cursosUsuario) {

			if (cursoUsuario.getCurso().getId() == idCurso) {

				yaExisteElCurso = true;
				break;
			}
		}

		return yaExisteElCurso;
	}

	@Override
	public Boolean cancelarCurso(Curso curso_obtenido, Usuario_Curso usuarioCurso) {

		if (repositorioUsuario.cancelarCurso(usuarioCurso) == true) {
			
			Usuario user = usuarioCurso.getUsuario();
			Giftcard giftcard = user.getGiftcard();
			
			Double saldoActual = giftcard.getSaldoActual();
			Integer puntosActuales = giftcard.getMisPuntos();
			
			saldoActual = saldoActual + curso_obtenido.getPrecio();
			
			Double puntos = curso_obtenido.getPrecio() * 10;
			puntosActuales += puntos.intValue();
	
			giftcard.setSaldoActual(saldoActual);
			giftcard.setMisPuntos(puntosActuales);
			
			repositorioGiftcard.actualizarGiftcard(giftcard);
			
			return true;
		} else {
			throw new CancelacionCursoException();
		}
	}

	@Override
	public void eliminarCurso(Curso curso_obtenido, Usuario usuario) {
		repositorioUsuario.eliminarCurso(curso_obtenido, usuario);
	}

	@Override
	public void finalizarCurso(Usuario_Curso usuarioCurso) {

		usuarioCurso.setEstado(Estado.FINALIZADO);
		usuarioCurso.setCursoTerminado(true);
		usuarioCurso.setProgreso(100.0);
		repositorioUsuario.actualizarCursoDelUsuario(usuarioCurso);

		List<Unidad> unidades = repositorioCurso.obtenerUnidadesDelCurso(usuarioCurso.getCurso());

		for (Unidad unidad : unidades) {

			unidad.setCompletado(true);
			repositorioCurso.actualizarUnidad(unidad);
		}
	}

	@Override
	public List<Usuario_Curso> obtenerCursosDelUsuario(Usuario usuario) {
		return repositorioUsuario.obtenerCursosDelUsuario(usuario);
	}

	@Override
	public Usuario_Curso obtenerUsuarioCurso(Curso curso_obtenido, Usuario usuario) {
		return repositorioUsuario.obtenerUsuarioCurso(curso_obtenido, usuario);
	}

	public void actualizarUsuario(int idUsuario, String nombre, String email, String passwordAterior,
			String passwordNueva, String repeticionPasswordNueva, HttpSession session) {
		Usuario usuario = repositorioUsuario.buscarUsuarioPorID(idUsuario);
		if (nombre != "") {
			usuario.setNombre(nombre);
			session.setAttribute("nombreUsuario", nombre);
		}
		if (email != "") {
			usuario.setEmail(email);
		}
		if (passwordAterior != passwordNueva && passwordNueva.equals(repeticionPasswordNueva)) {
			usuario.setPassword(passwordNueva);
		}

		repositorioUsuario.actualizarUsuario(usuario);
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
		} else {
			throw new TarjetaInvalidaException();
		}
	}

	@Override
	public Usuario actualizarUsuarioPrueba(int idUsuario, String nombre, String email, String passwordAterior,
			String passwordNueva, String repeticionPasswordNueva, HttpSession session) {
		Usuario usuario = repositorioUsuario.buscarUsuarioPorID(idUsuario);
		if (nombre != "") {
			usuario.setNombre(nombre);
			session.setAttribute("nombreUsuario", nombre);
		}
		if (email != "") {
			usuario.setEmail(email);
		}
		if (passwordAterior != passwordNueva && passwordNueva.equals(repeticionPasswordNueva)) {

			usuario.setPassword(passwordNueva);

		}

		Usuario usuarioPrueba = new Usuario(nombre, email, passwordNueva, "Cliente");
		repositorioUsuario.actualizarUsuario(usuario);

		return usuarioPrueba;
	}

	@Override
	public void enviarNotificacion(Usuario usuario, String msj, HttpSession session) {
		
		Notificacion noti = new Notificacion(msj);
		//repositorioUsuario.guardarNotificacionDelUsuario(noti, usuario);
		//session.setAttribute("notificaciones", servicioUsuario.obtenerNotificaciones(usuario));
	}

}
