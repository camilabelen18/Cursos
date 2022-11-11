package controladores;

import servicios.ServicioCarrito;
import servicios.ServicioCurso;
import servicios.ServicioUsuario;
import servicios.TarjetaInvalidaException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;
import modelo.Usuario_Curso;


public class ControladorCompraTest {
	 ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	 ServicioCurso servicioCurso = mock(ServicioCurso.class);
	 ServicioCarrito servicioCarrito = mock(ServicioCarrito.class);
	 HttpSession session = mock(HttpSession.class);
	 ControladorCompra controladorCompra = new ControladorCompra(servicioUsuario, servicioCurso, servicioCarrito);
	
//	 @Test
//	 public void testQuePermitaVerificacionCompra() {
//		 //Preparacion
//		 Usuario usuario = new Usuario("Camila", "camilabelen906@gmail.com", "1234", "admin");
//		 Curso curso = new Curso("Curso php", "Programacion", 
//								"Curso de programacion php",1000.0, 
//								Estado.EN_CURSO, "cursophp.png");
//		 Integer nroTarjeta = 111;
//		 //Ejecucion
//		 when(servicioUsuario.buscarUsuarioPorID(usuario.getId())).thenReturn(usuario);
//		 when(servicioUsuario.existeCursoEnListaUsuario(curso.getId(), usuario)).thenReturn(true);
//		 when(curso.getEstado()).thenReturn(Estado.CANCELADO);
//		 when(session.getAttribute("idUsuario")).thenReturn(usuario.getId());
//		 ModelAndView mav = controladorCompra.verificacionCompra(curso.getId(), curso.getPrecio(), session);
//		 //Comprobacion
//		 assertThat(mav.getViewName()).isEqualTo("redirect:/verListaCursos");
//		 
//	 }
	 
	 @Test
	 public void testQuePermitaVerificarCompra(){
		 //Preparacion
		 Usuario usuario = new Usuario("Camila", "camilabelen906@gmail.com", "1234", "admin");
		 Curso curso = new Curso("Curso php", "Programacion", 
								"Curso de programacion php",1000.0, 
								Estado.EN_CURSO, "cursophp.png");
		 Integer nroTarjeta = 111;
		 //Ejecucion
		 when(servicioUsuario.buscarUsuarioPorID(usuario.getId())).thenReturn(usuario);
		 when(servicioCurso.buscarCursoPorId(curso.getId())).thenReturn(curso);
		 when(servicioUsuario.verificarTarjetaUsuario(usuario, nroTarjeta)).thenReturn(nroTarjeta);
		 when(session.getAttribute("idUsuario")).thenReturn(usuario.getId());
		 ModelAndView mav = controladorCompra.verificarCompra(nroTarjeta, curso.getId(), session);
		 //Comprobacion
		 assertThat(mav.getViewName()).isEqualTo("compraRealizada");
		 }
	 
	 @Test
	 public void testQueNoPermitaVerificarCompra(){
		 //Preparacion
		 Usuario usuario = new Usuario("Camila", "camilabelen906@gmail.com", "1234", "admin");
		 Curso curso = new Curso("Curso php", "Programacion", 
								"Curso de programacion php",1000.0, 
								Estado.CANCELADO, "cursophp.png");
		 Integer nroTarjeta = 111;
		 //Ejecucion
		 when(servicioUsuario.buscarUsuarioPorID(usuario.getId())).thenReturn(usuario);
		 when(servicioCurso.buscarCursoPorId(curso.getId())).thenReturn(curso);
		 doThrow(TarjetaInvalidaException.class).when(servicioUsuario).verificarTarjetaUsuario(usuario, nroTarjeta);
		 when(session.getAttribute("idUsuario")).thenReturn(usuario.getId());
		 ModelAndView mav = controladorCompra.verificarCompra(nroTarjeta, curso.getId(), session);
		 //Comprobacion
		 assertThat(mav.getViewName()).isEqualTo("verificacionCompra");
		 }
	 

	 
	 @Test
	 public void testQuePermitaCancelarCompra(){
		 //Preparacion
		 Usuario usuario = new Usuario("Camila", "camilabelen906@gmail.com", "1234", "admin");
		 Curso curso = new Curso("Curso php", "Programacion", 
								"Curso de programacion php",1000.0, 
								Estado.EN_CURSO, "cursophp.png");
		 Integer nroTarjeta = 111;
		 Usuario_Curso usuarioCurso = new Usuario_Curso(usuario, curso);
		 //Ejecucion
		 when(servicioUsuario.buscarUsuarioPorID(usuario.getId())).thenReturn(usuario);
		 when(servicioCurso.buscarCursoPorId(curso.getId())).thenReturn(curso);
		 when(servicioUsuario.obtenerUsuarioCurso(curso, usuario)).thenReturn(usuarioCurso);
		 when(session.getAttribute("idUsuario")).thenReturn(usuario.getId());
		 ModelAndView mav = controladorCompra.cancelarCompra(curso.getId(), session);
		 //Comprobacion
		 assertThat(mav.getViewName()).isEqualTo("redirect:/misCursos");

	 }
	 
	 @Test
	 public void testQuePermitaEliminarCompra(){
		 //Preparacion
		 Usuario usuario = new Usuario("Camila", "camilabelen906@gmail.com", "1234", "admin");
		 Curso curso = new Curso("Curso php", "Programacion", 
								"Curso de programacion php",1000.0, 
								Estado.EN_CURSO, "cursophp.png");
		 Integer nroTarjeta = 111;
		 Usuario_Curso usuarioCurso = new Usuario_Curso(usuario, curso);
		 //Ejecucion
		 when(servicioUsuario.buscarUsuarioPorID(usuario.getId())).thenReturn(usuario);
		 when(servicioCurso.buscarCursoPorId(curso.getId())).thenReturn(curso);
		 when(session.getAttribute("idUsuario")).thenReturn(usuario.getId());
		 ModelAndView mav = controladorCompra.eliminarCompra(curso.getId(), session);
		 //Comprobacion
		 assertThat(mav.getViewName()).isEqualTo("redirect:/misCursos");

	 }
}
