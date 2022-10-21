package repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import modelo.*;

@Repository("repositorioCarrito")
@Transactional
public class RepositorioCarritoImpl implements RepositorioCarrito {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public Carrito buscarCarritoPorID(int id_carrito) {

		Session sesion = sessionFactory.getCurrentSession();
				
		Carrito carrito = sesion.get(Carrito.class, id_carrito);
		
		return carrito;
	}

	
	@Override
	public void agregarCursoALista(Curso curso_obtenido, Carrito carrito) {
		
        Session sesion = sessionFactory.getCurrentSession();
        
        CarritoCurso carritoCurso = new CarritoCurso();
        
        carritoCurso.setCarrito(carrito);
        carritoCurso.setCurso(curso_obtenido);
        
		sesion.save(carritoCurso);
	}


	@Override
	public Carrito obtenerCarritoPorIdUsuario(int id_user) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		List<Carrito> carritos = sesion.createCriteria(Carrito.class).list();
		Carrito carrito = new Carrito();
		
		for (Carrito car : carritos) {
			
			if (car.getUsuario().getId() == id_user) {
				
				carrito = car;
				break;
			}
		}
		
		return carrito;
	}


	@Override
	public void guardarCarrito(Carrito carrito) {
		sessionFactory.getCurrentSession().save(carrito);
	}


	@Override
	public List<Curso> obtenerCursosDelCarrito(Carrito carrito) {
		

		Session sesion = sessionFactory.getCurrentSession();

		List<CarritoCurso> lista_carrito_curso = sesion.createCriteria(CarritoCurso.class).list();
		
		List<Curso> lista_curso = new ArrayList<Curso>();
		
		for (CarritoCurso carritoCurso : lista_carrito_curso) {
			
			if (carritoCurso.getCarrito().getId() == carrito.getId()) {
				
				lista_curso.add(carritoCurso.getCurso());
			}
		}

		return lista_curso;
	}

}
