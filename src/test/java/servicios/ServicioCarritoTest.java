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
		//Preparacion
		Curso curso1 = new Curso("Curso php","Programacion","Curso de programacion php", 1000.0,Estado.EN_CURSO, "cursophp.png");
		Curso curso2= new Curso("Curso php","Programacion","Curso de programacion php", 1000.0,Estado.EN_CURSO, "cursophp.png");
		List <Curso> cursos = new ArrayList();
		cursos.add(curso1);
		cursos.add(curso2);
		
		//Ejecucion
System.out.println(curso1.getPrecio());
System.out.println(curso2.getPrecio());
System.out.println(servicioCarrito.getTotalDePrecios(cursos));
		//Comprobacion
//		assertSame(2000.0, total);
		
		
	}
}