package servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;

import modelo.*;
import repositorios.RepositorioCarrito;
import repositorios.RepositorioCurso;
import repositorios.RepositorioGiftcard;
import repositorios.RepositorioUsuario;

public class ServicioUsuarioTest {

	RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
	RepositorioCarrito repositorioCarrito = mock(RepositorioCarrito.class);
	RepositorioCurso repositorioCurso = mock(RepositorioCurso.class);
	RepositorioGiftcard repositorioGiftcard = mock(RepositorioGiftcard.class);
	ServicioUsuario servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario, repositorioCarrito, repositorioCurso, repositorioGiftcard);
	
	
	@Test
	public void queSePuedaRegistrarUnUsuario() {

		// Preparación
		DatosRegistro datosRegistro = new DatosRegistro("test", "test@test.com", "123", "123");

		// Ejecución
		Usuario usuarioRegistrado = servicioUsuario.registrar(datosRegistro);

		// Comprobación
		assertThat(usuarioRegistrado).isNotNull();
	}

	@Test(expected = ClavesNoSonIgualesException.class)
	public void queNoSePuedaRegistrarUnUsuarioConClavesDistintas() {

		// Preparación
		DatosRegistro datosRegistro = new DatosRegistro("test", "test@test.com", "12345", "11");

		// Ejecución
		servicioUsuario.registrar(datosRegistro);
	}

	@Test
	public void queSePuedaObtenerUnUsuarioRegistrado() {

		// Preparación
		String email = "test@test.com";
		String password = "123";

		// Ejecución
		when(repositorioUsuario.buscarUsuario(email, password)).thenReturn(new Usuario());
		Usuario usuarioObtenido = servicioUsuario.consultarUsuario(email, password);

		// Comprobación
		assertThat(usuarioObtenido).isNotNull();
	}

	@Test(expected = UsuarioInexistenteException.class)
	public void queNoSePuedaObtenerUnUsuarioNoRegistrado() {

		// Preparación
		String email = "test@test.com";
		String password = "123";

		// Ejecución
		when(repositorioUsuario.buscarUsuario(email, password)).thenReturn(null);
		servicioUsuario.consultarUsuario(email, password);
		
		// Comprobación
		verify(repositorioUsuario, times(1)).buscarUsuario(email, password);
	}

	@Test
	public void queSePuedaBuscarUnUsuarioPorEmail() {
		// preparacion
		Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");
		String email = "hola@hola.com";

		// ejecucion
		when(repositorioUsuario.buscarUsuarioPorEmail(email)).thenReturn(usuario);
		Usuario usuarioBuscadoPorEmail = servicioUsuario.buscarUsuarioPorEmail(email);

		// comprobacion
		assertThat(usuarioBuscadoPorEmail).isNotNull();
	}

	@Test
	public void queSePuedaBuscarUnUsuarioPorId() {
		// preparacion
		Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");
		String email = usuario.getEmail();
		int idUsuario = 1;

		// ejecucion
		when(repositorioUsuario.buscarUsuarioPorID(idUsuario)).thenReturn(usuario);
		Usuario usuarioObtenido = servicioUsuario.buscarUsuarioPorID(idUsuario);

		// comprobacion
		assertThat(usuarioObtenido).isNotNull();
	}

	@Test
	public void queSePuedaConsultarUnUsuario() {
		// preparacion
		Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");
		String email = usuario.getEmail();
		String password = usuario.getPassword();

		// ejecucion
		when(repositorioUsuario.buscarUsuario(email, password)).thenReturn(usuario);
		Usuario usuarioObtenido = servicioUsuario.consultarUsuario(email, password);

		// comprobacion
		assertThat(usuarioObtenido).isNotNull();

	}
	@Test(expected = UsuarioInexistenteException.class)
	public void queSeQuieraConsultarUnUsuarioQueNoExiste() {
		Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");
		String email = usuario.getEmail();
		String password = usuario.getPassword();

		// ejecucion
		when(repositorioUsuario.buscarUsuario(email, password)).thenReturn(usuario);
		Usuario usuarioObtenido = servicioUsuario.consultarUsuario("www", "000");

		// comprobacion
		assertThat(usuarioObtenido).isNotNull();
		
	}
	
	
}
