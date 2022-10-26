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
        
        Carrito_Curso carritoCurso = new Carrito_Curso();
        
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

		List<Carrito_Curso> carrito_cursos = sesion.createCriteria(Carrito_Curso.class)
											.add(Restrictions.eq("carrito", carrito))
											.list();

		List<Curso> cursos = new ArrayList<Curso>();
		
		for (Carrito_Curso carritoCurso : carrito_cursos) {
			
			cursos.add(carritoCurso.getCurso());
		}
		
		return cursos;
	}


	@Override
	public Carrito_Curso obtenerCarritoCurso(Carrito carrito, Curso curso) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		Carrito_Curso carritoCurso = (Carrito_Curso) sesion.createCriteria(Carrito_Curso.class)
				 					 .add(Restrictions.eq("carrito", carrito))
				 					 .add(Restrictions.eq("curso", curso))
				 					 .uniqueResult();
		return carritoCurso;
	}


	@Override
	public void eliminarCursoDelCarrito(Carrito_Curso carritoCurso) {
		sessionFactory.getCurrentSession().delete(carritoCurso);
	}


	@Override
	public List<Carrito_Curso> obtenerCarritoCursos(Carrito carrito) {
		
		Session sesion = sessionFactory.getCurrentSession();

		List<Carrito_Curso> cursosCarrito = sesion.createCriteria(Carrito_Curso.class)
											.add(Restrictions.eq("carrito", carrito))
											.list();
		
		return cursosCarrito;
	}


}
