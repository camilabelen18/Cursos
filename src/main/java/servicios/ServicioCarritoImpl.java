package servicios;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Carrito;
import modelo.Curso;
import repositorios.RepositorioCarrito;

@Service("servicioCarrito")
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito {
	
	@Autowired
	private RepositorioCarrito repositorioCarrito;
	

	@Override
	public Carrito buscarCarritoPorId(int id_carrito) {
		return repositorioCarrito.buscarCarritoPorID(id_carrito);
	}


	@Override
	public double getTotalDePrecios(Set<Curso> cursos) {
		
		double resultadoTotal=0;
		
		for (Curso curso : cursos) {
			resultadoTotal +=curso.getPrecio();
		}
		
		return resultadoTotal;
	}


	@Override
	public void calcularTotal(Carrito carrito) {
		
		Double resultadoTotal = 0.0;
		Set<Curso> cursos = carrito.getCursosDelCarrito();
		
		for (Curso curso : cursos) {
			
			resultadoTotal += curso.getPrecio();
		}
		
		carrito.setTotal(resultadoTotal);
		repositorioCarrito.actualizarCarrito(carrito);
	}


	@Override
	public void agregarCursoAlCarrito(Curso curso_obtenido, Carrito carrito) {
		repositorioCarrito.agregarCursoALista(curso_obtenido, carrito);
	}

}
