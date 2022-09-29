package repositorios;

import java.util.List;

import javax.inject.Inject;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import modelo.Curso;

@Repository("repositorioCurso")
@Transactional
public class RepositorioCursoImpl implements RepositorioCurso{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Curso> obtenerListaCursosPorNombre(String nombreCurso) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		// Se obtiene una lista de cursos por su nombre haciendo uso del operador "LIKE"
		
		List<Curso> lista_cursos = sesion.createCriteria(Curso.class)
				                   .add(Restrictions.like("nombre","%" + nombreCurso + "%"))
				                   .list();

		return lista_cursos;
	}

	@Override
    public Curso obtenerCursoPorID(int id) {
        
		Session sesion = sessionFactory.getCurrentSession();
		
		// Se obtiene un curso por su id
		
		Curso curso = sesion.get(Curso.class, id);
		
		return curso;
    }

	@Override
	public List<Curso> obtenerListaTotalCursos() {
		
		Session sesion = sessionFactory.getCurrentSession();

		// Se obtiene la lista de todos los cursos

		List<Curso> lista_cursos = sesion.createCriteria(Curso.class).list();

		return lista_cursos;
	}

	@Override
	public List<Curso> obtenerListaCursosPorCategoria(String categoria) {

		Session sesion = sessionFactory.getCurrentSession();

		// Se obtiene una lista de cursos por su categoria
		
		List<Curso> lista_cursos = sesion.createCriteria(Curso.class)
				                   .add(Restrictions.eq("categoria", categoria))
				                   .list();

		return lista_cursos;
	}

	@Override
	public void agregarCurso(Curso curso) {
		sessionFactory.getCurrentSession().save(curso);
	}

}
