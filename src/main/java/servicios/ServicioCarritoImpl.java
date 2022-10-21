package servicios;

import java.util.List;
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
	public double getTotalDePrecios(List<Curso> cursos) {
		
		double resultadoTotal = 0;
		
		for (Curso curso : cursos) {
			resultadoTotal += curso.getPrecio();
		}
		
		return resultadoTotal;
	}


	@Override
	public void agregarCursoAlCarrito(Curso curso_obtenido, Carrito carrito) {
		repositorioCarrito.agregarCursoALista(curso_obtenido, carrito);
	}


	@Override
	public Carrito obtenerCarritoPorIdUsuario(int id_user) {
		return repositorioCarrito.obtenerCarritoPorIdUsuario(id_user);
	}


	@Override
	public List<Curso> obtenerCursosDelCarrito(Carrito carrito) {
		return repositorioCarrito.obtenerCursosDelCarrito(carrito);
	}

}
