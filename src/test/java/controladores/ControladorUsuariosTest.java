package controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import modelo.*;
import servicios.ServicioSubirImagen;
import servicios.ServicioUsuario;

public class ControladorUsuariosTest {
	
	ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	ServicioSubirImagen servicioSubirImagen = mock(ServicioSubirImagen.class);
	ControladorUsuarios controladorUsuarios = new ControladorUsuarios(servicioUsuario, servicioSubirImagen);
	
	
	@Test
	public void queSePuedaRegistrarUnUsuario() {
		
		// Preparación
		DatosRegistro datosRegistro = new DatosRegistro("Martin", "martin@gmail.com", "123", "123");
		
		// Ejecución
		ModelAndView mav = controladorUsuarios.registrarNuevoUsuario(datosRegistro);
		
		// Comprobación
		assertThat(mav.getViewName()).isEqualTo("login");
	}
	
	
	@Test
	public void queNoSePuedaRegistrarUnUsuarioConClavesDistintas() {
		
		// Preparación
		DatosRegistro datosRegistro = new DatosRegistro("Martin", "martin@gmail.com", "123", "12345");
		String msjEsperado = "Las contraseñas no son iguales";
		
		// Ejecución
		doThrow(Exception.class).when(servicioUsuario).registrar(datosRegistro);
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

}
