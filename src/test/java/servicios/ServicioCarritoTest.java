package servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import modelo.Carrito;
import modelo.Curso;
import modelo.DatosRegistro;
import modelo.Estado;
import modelo.Usuario;
import repositorios.RepositorioCarrito;
import repositorios.RepositorioUsuario;
import repositorios.RepositorioCurso;
import repositorios.RepositorioGiftcard;

public class ServicioCarritoTest {
	RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
	RepositorioCarrito repositorioCarrito = mock(RepositorioCarrito.class);
	RepositorioCurso repositorioCurso = mock(RepositorioCurso.class);
	RepositorioGiftcard repositorioGiftcard = mock(RepositorioGiftcard.class);
	ServicioCurso servicioCurso=new ServicioCursoImpl(repositorioCurso, repositorioUsuario);
	ServicioUsuario servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario, repositorioCarrito, repositorioCurso, repositorioGiftcard);
	ServicioCarrito servicioCarrito = new ServicioCarritoImpl(repositorioCarrito, repositorioUsuario, servicioUsuario);

	@Test
	public void testQueObtengaElTotalDelCarrito() {
		// Preparacion
		Curso curso1 = new Curso("Curso PHP/MySql desde 0", "programacion", "En este curso se vera como crear aplicaciones y sitios web desde cero con PHP y MYSQL.", 3000.0, Estado.EN_VENTA, "php-desde-cero.jpg");
		Curso curso2 = new Curso("Spring Framework 5", "programacion", "Construye aplicaciones web con Spring Framework 5 & Spring Boot: Thymeleaf, JPA, Security, REST, MySQL, Angular, WebFlux.", 2500.0, Estado.EN_VENTA, "curso-spring.jpg");
		List<Curso> cursos = new ArrayList<>();
		cursos.add(curso1);
		cursos.add(curso2);

		// Ejecucion
		Double total = servicioCarrito.getTotalDePrecios(cursos);
		// Comprobacion
		assertEquals(5500.0, total, 0.1);

	}
	@Test
	public void testQueObtengaLosCursosDelCarrito() {
		// Preparacion
		List<Curso> listaCursos = new ArrayList();
		Curso curso1 = new Curso("Curso PHP/MySql desde 0", "programacion", "En este curso se vera como crear aplicaciones y sitios web desde cero con PHP y MYSQL.", 3000.0, Estado.EN_VENTA, "php-desde-cero.jpg");
		Curso curso2 = new Curso("Spring Framework 5", "programacion", "Construye aplicaciones web con Spring Framework 5 & Spring Boot: Thymeleaf, JPA, Security, REST, MySQL, Angular, WebFlux.", 2500.0, Estado.EN_VENTA, "curso-spring.jpg");
		Carrito carrito = new Carrito();
		listaCursos.add(curso1);
		listaCursos.add(curso2);
		
		// Ejecucion
		
		when(repositorioCarrito.obtenerCursosDelCarrito(carrito)).thenReturn(listaCursos);

		List <Curso> cursosObtenidos = repositorioCarrito.obtenerCursosDelCarrito(carrito);
		
		// Comprobacion
		assertThat(cursosObtenidos).isNotEmpty();

	}
	

	@Test(expected = ListaCarritoException.class)
	public void queNoSePuedaObtenerCursosDeUnCarritoVacio() {

		// Preparación
		Carrito carrito = new Carrito();
		List<Curso> listaCursosVacia = new ArrayList<>();
		// Ejecución
		when(repositorioCarrito.obtenerCursosDelCarrito(carrito)).thenReturn(listaCursosVacia);
		servicioCarrito.obtenerCursosDelCarrito(carrito);
		
	}
}