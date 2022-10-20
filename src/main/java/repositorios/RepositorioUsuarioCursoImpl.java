package repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import modelo.Curso;
import modelo.Usuario;
import modelo.UsuarioCurso;

@Repository("repositorioUsuarioCurso")
@Transactional
public class RepositorioUsuarioCursoImpl implements RepositorioUsuarioCurso {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Curso> obtenerCursosDelUsuario(Usuario usuario) {
		
		Session sesion = sessionFactory.getCurrentSession();

		List<UsuarioCurso> lista_usuario_curso = sesion.createCriteria(UsuarioCurso.class).list();
		
		List<Curso> lista_curso = new ArrayList<Curso>();
		
		for (UsuarioCurso usuarioCurso : lista_usuario_curso) {
			
			if (usuarioCurso.getUsuario().getId() == usuario.getId()) {
				
				lista_curso.add(usuarioCurso.getCurso());
			}
		}

		return lista_curso;
	}

}
