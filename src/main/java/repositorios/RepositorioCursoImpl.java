package repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import modelo.Curso;

@Repository
@Transactional
public class RepositorioCursoImpl implements RepositorioCurso{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Curso> obtenerListaCursos() {
		return sessionFactory.getCurrentSession()
                .createCriteria(Curso.class)
                .list();
	}

}
