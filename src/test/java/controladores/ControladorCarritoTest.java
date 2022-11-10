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

import modelo.Carrito;
import modelo.Carrito_Curso;
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
		Integer user_id = 1;
		// Ejecucion
		when(servicioCurso.buscarCursoPorId(curso_id)).thenReturn(new Curso());
		when(servicioCarrito.obtenerCarritoPorIdUsuario(user_id)).thenReturn(new Carrito());
		when(session.getAttribute("idUsuario")).thenReturn(user_id);
		ModelAndView mav = controladorCarrito.agregarCursoAlCarrito(curso_id, session);

		// Comprobacion

		assertThat(mav.getViewName()).isEqualTo("redirect:/verListaCursos");

	}

	@Test
	public void testQueElimineCursoDelCarrito() {

		// Preparacion

		Integer curso_id = 1;
		Integer user_id = 1;
		Carrito carrito = new Carrito();
		Curso curso1 = new Curso("Curso php", "Programacion", "Curso de programacion php", 1000.0, Estado.EN_CURSO,
				"cursophp.png");
		Carrito_Curso cc = new Carrito_Curso();
		// Ejecucion

		when(servicioCurso.buscarCursoPorId(curso_id)).thenReturn(curso1);
		when(servicioCarrito.obtenerCarritoPorIdUsuario(user_id)).thenReturn(carrito);
		when(servicioCarrito.obtenerCarritoCurso(carrito, curso1)).thenReturn(cc);
		when(session.getAttribute("idUsuario")).thenReturn(user_id);
		ModelAndView mav = controladorCarrito.eliminarCursoDeListaCarrito(curso_id, session);

		// Comprobacion

		assertThat(mav.getViewName()).isEqualTo("redirect:/vistaCarrito");

	}

	@Test
	public void testQueDejeIrACarritoConSesionIniciada() {

		// Preparacion
		Integer user_id = 1;
		// Ejecucion

		when(session.getAttribute("idUsuario")).thenReturn(user_id);

		ModelAndView mav = controladorCarrito.vistaCarrito(session);

		// Comprobacion

		assertThat(mav.getViewName()).isEqualTo("carrito");
	}

	@Test
	public void testQueNoDejeIrACarritoSinIniciarSesion() {

		Integer user_id = null;
		// Ejecucion

		when(session.getAttribute("idUsuario")).thenReturn(user_id);
		ModelAndView mav = controladorCarrito.vistaCarrito(session);

		// Comprobacion

		assertThat(mav.getViewName()).isEqualTo("redirect:/");
	}

}
