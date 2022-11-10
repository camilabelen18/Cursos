package controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import modelo.Curso;
import modelo.DatosCreacionCurso;
import modelo.Estado;
import modelo.Usuario;
import servicios.ServicioCarrito;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;

public class ControladorCarritoTest {

	ServicioCurso servicioCurso = mock(ServicioCurso.class);
	ServicioCarrito servicioCarrito = mock(ServicioCarrito.class);
	ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	ControladorCarrito controladorCarrito = new ControladorCarrito(servicioCurso, servicioCarrito);
	HttpSession session = mock(HttpSession.class);

	@Test
	public void testQueAgregaCursoAlCarrito() {

		// Preparacion
		
		Integer curso_id = 1;
		
		// Ejecucion
		
		when(session.getAttribute("idUsuario")).thenReturn(1);
		ModelAndView mav = controladorCarrito.agregarCursoAlCarrito(curso_id, session);
		
		// Comprobacion
		
		assertThat(mav.getViewName()).isEqualTo("redirect:/verListaCursos");

	}
	
	
	

}
