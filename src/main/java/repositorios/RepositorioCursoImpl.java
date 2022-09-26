package repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import modelo.Curso;

@Repository("repositorioCurso")
public class RepositorioCursoImpl implements RepositorioCurso{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Curso> obtenerListaCursos() {
		return sessionFactory.getCurrentSession()
                .createCriteria(Curso.class)
                .list();
	}
	
@Override
public Curso añadirCurso(Curso curso) {
	sessionFactory.getCurrentSession().save(curso);
	return null;
}

}
