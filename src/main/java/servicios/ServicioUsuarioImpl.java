package servicios;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.*;
import repositorios.*;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario repositorioUsuario;
	private RepositorioCarrito repositorioCarrito;
	private RepositorioCurso repositorioCurso;

	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario, RepositorioCarrito repositorioCarrito, RepositorioCurso repositorioCurso) {
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioCarrito = repositorioCarrito;
		this.repositorioCurso = repositorioCurso;
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
		Tarjeta tarjeta = new Tarjeta(222, 0, 0.0);

		// Se comprueba si las contraseñas ingresadas son iguales
		if (datosRegistro.getContrasenia().equals(datosRegistro.getRepetirContrasenia())) {

			nuevoUsuario.setNombre(datosRegistro.getNombre());
			nuevoUsuario.setEmail(datosRegistro.getEmail());
			nuevoUsuario.setPassword(datosRegistro.getContrasenia());
			nuevoUsuario.setRol("cliente");
			nuevoUsuario.setImagen("default-user.png");
			nuevoUsuario.setTarjeta(tarjeta);
			carrito.setUsuario(nuevoUsuario);

			repositorioUsuario.guardarTarjetaDeUsuario(tarjeta);
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
		List<Usuario_Curso> cursosUsuario = repositorioUsuario.obtenerCursosDelUsuario(usuario);

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
			Tarjeta tarjeta = user.getTarjeta();

			Double saldoActual = tarjeta.getSaldoActual();
			Integer puntosActuales = tarjeta.getMisPuntos();

			saldoActual = saldoActual + curso_obtenido.getPrecio();

			Double puntos = curso_obtenido.getPrecio() * 10;
			puntosActuales += puntos.intValue();

			tarjeta.setSaldoActual(saldoActual);
			tarjeta.setMisPuntos(puntosActuales);

			repositorioUsuario.actualizarTarjeta(tarjeta);

			return true;
		}
		else {
			throw new CancelacionCursoException();
		}
	}

	@Override
	public void eliminarCursoDelUsuario(Curso curso_obtenido, Usuario usuario) {
		repositorioUsuario.eliminarCursoDelUsuario(curso_obtenido, usuario);
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
		String passwordNueva, HttpSession session) {
		Usuario usuario = repositorioUsuario.buscarUsuarioPorID(idUsuario);
		if (nombre != "") {
			usuario.setNombre(nombre);
			session.setAttribute("nombreUsuario", nombre);
		}
		if (email != "") {
			usuario.setEmail(email);
		}
		if (usuario.getPassword().equals(passwordAterior) && passwordAterior != passwordNueva) {
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
	public void enviarNotificacion(Usuario usuario, String msj, HttpSession session) {

		Notificacion noti = new Notificacion(msj);
		repositorioUsuario.guardarNotificacionDelUsuario(noti, usuario);
		session.setAttribute("notificaciones", repositorioUsuario.obtenerNotificaciones(usuario));
	}

	@Override
	public List<Usuario_Notificacion> obtenerNotificaciones(Usuario usuario) {
		return repositorioUsuario.obtenerNotificaciones(usuario);
	}

	@Override
	public Notificacion obtenerNotificacionPorId(int idNotif) {
		return repositorioUsuario.obtenerNotificacionPorId(idNotif);
	}

	@Override
	public void eliminarNotificacion(Notificacion notificacion, Usuario usuario, HttpSession session) {

		repositorioUsuario.eliminarNotificacion(notificacion);
		session.setAttribute("notificaciones", repositorioUsuario.obtenerNotificaciones(usuario));
	}

	@Override
	public void enviarPuntos(Usuario usuario1, Usuario usuario2, Integer puntos) {
		Tarjeta gc1 = usuario1.getTarjeta();
		Tarjeta gc2 = usuario1.getTarjeta();

		gc1.setMisPuntos(gc1.getMisPuntos() - puntos);
		gc1.setMisPuntos(gc2.getMisPuntos() + puntos);

		repositorioUsuario.actualizarTarjeta(gc2);
		repositorioUsuario.actualizarTarjeta(gc1);
		repositorioUsuario.actualizarUsuario(usuario1);
		repositorioUsuario.actualizarUsuario(usuario2);
	}

	@Override
	public void verificarUsuario(Usuario usuario) {

		if (usuario != null) {

		}
		else {
			throw new UsuarioInexistenteException();
		}
	}
	
	@Override
	public void enviarNotificacion(Usuario usuario, String msj) {

		Notificacion noti = new Notificacion(msj);
		repositorioUsuario.guardarNotificacionDelUsuario(noti, usuario);
	}

	@Override
	public void quitarNotificacion(Usuario_Notificacion usuarioNotificacion, Usuario usuario, HttpSession sesion) {
		
		repositorioUsuario.quitarNotificacion(usuarioNotificacion);
		sesion.setAttribute("notificaciones", repositorioUsuario.obtenerNotificaciones(usuario));
	}

	@Override
	public Usuario_Notificacion obtenerNotificacionUsuario(Usuario usuario, Notificacion notificacion) {
		return repositorioUsuario.obtenerNotificacionUsuario(usuario, notificacion);
	}

	@Override
	public void marcarNotificacionLeida(Usuario_Notificacion usuarioNotificacion, Usuario usuario, HttpSession sesion) {
		repositorioUsuario.marcarNotificacionLeida(usuarioNotificacion);
		sesion.setAttribute("notificaciones", repositorioUsuario.obtenerNotificaciones(usuario));
	}

}
