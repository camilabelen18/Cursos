package repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import modelo.Curso;

@Repository
@Transactional
public class RepositorioCursoImpl implements RepositorioCurso{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Curso> obtenerListaCursosPorDescripcion(String descripcion) {
		return sessionFactory.getCurrentSession()
                .createCriteria(Curso.class)
                .add(Restrictions.eq("descripcion",descripcion))
                .list();
	}

	@Override
	public Curso obtenerListaCursosPorID(Long id) {
		// TODO Auto-generated method stub
		return (Curso) sessionFactory.getCurrentSession()
                .createCriteria(Curso.class)
                .add(Restrictions.eq("id",id))
                .uniqueResult();
	}

}
