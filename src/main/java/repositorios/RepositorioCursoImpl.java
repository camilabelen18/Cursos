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
import org.springframework.stereotype.Service;

import modelo.Curso;

@Repository("repositorioCurso")
@Transactional
public class RepositorioCursoImpl implements RepositorioCurso{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Curso> obtenerListaCursosPorNombre(String nombreCurso) {
		
		Session sesion = sessionFactory.getCurrentSession();

		// Se obtienen los cursos por su nombre
		
		Query<Curso> consulta = sesion.createQuery("from Curso cursito where cursito.nombre LIKE '%" + nombreCurso + "%'", Curso.class);

		List<Curso> lista_cursos = consulta.getResultList();

		return lista_cursos;
	}

	@Override
	public Curso obtenerCursoPorID(int id) {

		Session sesion = sessionFactory.getCurrentSession();

		// Se obtiene un curso por id
		Curso curso = sesion.get(Curso.class, id);
		
		return curso;
	}

	@Override
	public List<Curso> obtenerListaTotalCursos() {
		
		// Se obtiene la sesion con sessionFactory

		Session sesion = sessionFactory.getCurrentSession();

		// Se crea una consulta con HQL

		Query<Curso> consulta = sesion.createQuery("from Curso", Curso.class);

		// Se ejecuta la consulta y se obtienen los registros de la tabla "curso"

		List<Curso> lista_cursos = consulta.getResultList();

		return lista_cursos;
	}

	@Override
	public List<Curso> obtenerListaCursosPorCategoria(String categoria) {

		Session sesion = sessionFactory.getCurrentSession();

		// Se obtienen los cursos por categoria
		
		Query<Curso> consulta = sesion.createQuery("from Curso cursito where cursito.categoria = '" + categoria + "'", Curso.class);

		List<Curso> lista_cursos = consulta.getResultList();

		return lista_cursos;
	}

	@Override
	public void agregarCurso(Curso curso) {
		sessionFactory.getCurrentSession().save(curso);
	}
	
 /* public List<Curso> obtenerListaCursosPorDescripcion(String descripcion) {
		
		return sessionFactory.getCurrentSession()
                .createCriteria(Curso.class)
                .add(Restrictions.eq("descripcion",descripcion))
                .list();
    }
    
    public Curso obtenerListaCursosPorID(Long id) {
    
		return (Curso) sessionFactory.getCurrentSession()
                .createCriteria(Curso.class)
                .add(Restrictions.eq("id",id))
                .uniqueResult();
    }
 */

}
