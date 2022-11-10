package servicios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import servicios.ServicioCarrito;
import modelo.Curso;
import modelo.Estado;

public class ServicioCarritoTest {
	ServicioCarrito servicioCarrito = mock(ServicioCarrito.class);

	@Test
	public void testQueObtengaElTotalDelCarrito() {
		// Preparacion
		Curso curso1 = new Curso("Curso PHP/MySql desde 0", "programacion", "En este curso se vera como crear aplicaciones y sitios web desde cero con PHP y MYSQL.", 3000.0, Estado.EN_VENTA, "php-desde-cero.jpg");
		Curso curso2 = new Curso("Spring Framework 5", "programacion", "Construye aplicaciones web con Spring Framework 5 & Spring Boot: Thymeleaf, JPA, Security, REST, MySQL, Angular, WebFlux.", 2500.0, Estado.EN_VENTA, "curso-spring.jpg");
		List<Curso> cursos = new ArrayList<>();
		cursos.add(curso1);
		cursos.add(curso2);
		double total = servicioCarrito.getTotalDePrecios(cursos);
		// Ejecucion
		System.out.println(curso1.getPrecio());
		System.out.println(curso2.getPrecio());
		System.out.println(total);
		// Comprobacion
//		assertSame(2000.0, total);

	}
}