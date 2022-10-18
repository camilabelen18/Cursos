package servicios;

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
	public void guardarCursoEnListaDeCarrito(Curso curso_obtenido) {
		repositorioCarrito.guardarCursoDelCarrito(curso_obtenido);
		
	}


	@Override
	public Carrito buscarCarritoPorId(int id_carrito) {
		return repositorioCarrito.buscarCarritoPorID(id_carrito);
	}

}
