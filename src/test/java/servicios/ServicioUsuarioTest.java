package servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import modelo.*;
import repositorios.RepositorioCarrito;
import repositorios.RepositorioCurso;
import repositorios.RepositorioUsuario;

public class ServicioUsuarioTest {
	
	RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
	RepositorioCarrito repositorioCarrito = mock(RepositorioCarrito.class);
	RepositorioCurso repositorioCurso = mock(RepositorioCurso.class);
	ServicioUsuario servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario, repositorioCarrito, repositorioCurso);
	
	
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
	}

}
