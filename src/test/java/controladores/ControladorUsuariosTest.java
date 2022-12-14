package controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import modelo.*;
import servicios.ClavesNoSonIgualesException;
import servicios.ServicioSubirImagen;
import servicios.ServicioUsuario;

public class ControladorUsuariosTest {

	ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	ServicioSubirImagen servicioSubirImagen = mock(ServicioSubirImagen.class);
	ControladorUsuarios controladorUsuarios = new ControladorUsuarios(servicioUsuario, servicioSubirImagen);
	HttpSession session = mock(HttpSession.class);

	@Test
	public void queSePuedaRegistrarUnUsuario() {

		// Preparacion
		DatosRegistro datosRegistro = new DatosRegistro("Martin", "martin@gmail.com", "123", "123");

		// Ejecucion
		ModelAndView mav = controladorUsuarios.registrarNuevoUsuario(datosRegistro);

		// Comprobacion
		assertThat(mav.getViewName()).isEqualTo("login");
	}

	@Test
	public void queNoSePuedaRegistrarUnUsuarioConClavesDistintas() {

		// Preparación
		DatosRegistro datosRegistro = new DatosRegistro("Martin", "martin@gmail.com", "123", "12345");
		String msjEsperado = "Las contraseñas no son iguales";
		
		when(servicioUsuario.buscarUsuarioPorEmail(datosRegistro.getEmail())).thenReturn(null);

		// Ejecución
		doThrow(ClavesNoSonIgualesException.class).when(servicioUsuario).registrar(datosRegistro);
		ModelAndView mav = controladorUsuarios.registrarNuevoUsuario(datosRegistro);

		// Comprobación
		assertThat(mav.getViewName()).isEqualTo("registroUsuario");
		assertThat(mav.getModel().get("error")).isEqualTo(msjEsperado);
	}

	@Test
	public void queNoSePuedaRegistrarUnUsuarioConUnMismoEmail() {

		// Preparación
		DatosRegistro datosRegistro = new DatosRegistro("Martin", "martin@gmail.com", "123", "123");
		String msjEsperado = "Ya existe un usuario registrado con el email ingresado";

		// Ejecución
		doReturn(new Usuario()).when(servicioUsuario).buscarUsuarioPorEmail(datosRegistro.getEmail());
		ModelAndView mav = controladorUsuarios.registrarNuevoUsuario(datosRegistro);

		// Comprobación
		assertThat(mav.getViewName()).isEqualTo("registroUsuario");
		assertThat(mav.getModel().get("error")).isEqualTo(msjEsperado);
	}

	@Test
	public void queSePuedaIrALogin() {
		// preparacion
		DatosLogin datosLogin = new DatosLogin("hola@gmail.com", "1234");
		// ejecucion
		ModelAndView mav = controladorUsuarios.irALogin();
		// comprobacion
		assertThat(mav.getViewName()).isEqualTo("login");
	}

	@Test
	public void queSePuedaVerPerfil() {
		// preparacion
		Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");
		// ejecucion
		when(session.getAttribute("idUsuario")).thenReturn(1);
		ModelAndView mav = controladorUsuarios.verPerfil(session);
		// comprobacion
		assertThat(mav.getViewName()).isEqualTo("vistaPerfil");
	}

	@Test
	public void queSePuedaEditarPerfil() {
		// preparacion
		Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");
		// ejecucion
		when(session.getAttribute("idUsuario")).thenReturn(1);
		ModelAndView mav = controladorUsuarios.editarPerfil(session);
		// comprobacion
		assertThat(mav.getViewName()).isEqualTo("editarPerfil");
	}

	@Test
	public void queSePuedaActualizarLosCambiosDelPerfil() {

	}

}
