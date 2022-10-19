package repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import modelo.Carrito;
import modelo.Curso;

@Repository("repositorioCarrito")
@Transactional
public class RepositorioCarritoImpl implements RepositorioCarrito {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public Carrito buscarCarritoPorID(int id_carrito) {

		Session sesion = sessionFactory.getCurrentSession();
		
		// Se obtiene un carrito por su id
		
		Carrito carrito = sesion.get(Carrito.class, id_carrito);
		
		return carrito;
	}

	
	@Override
	public void actualizarCarrito(Carrito carrito) {
		sessionFactory.getCurrentSession().update(carrito);
	}

	
	@Override
	public void agregarCursoALista(Curso curso_obtenido, Carrito carrito ) {
		
        Session sesion = sessionFactory.getCurrentSession();
		
        carrito.getCursosDelCarrito().add(curso_obtenido);
        
		sesion.update(carrito);
	}

}
