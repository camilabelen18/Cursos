package repositorios;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import modelo.Carrito;
import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;
import modelo.UsuarioCurso;
import servicios.ServicioUsuarioCurso;

@Repository
@Transactional
public class RepositorioUsuarioImpl implements RepositorioUsuario{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ServicioUsuarioCurso servicioUsuarioCurso;
	
	@Override
	public Boolean buscarTarjetaEmail(Integer nroTarjeta, String email) {
		
		if(sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .add(Restrictions.eq("nroTarjeta",nroTarjeta))
                .add(Restrictions.eq("email", email))!=null) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public Usuario buscarUsuarioPorEmail(String email) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		// Se obtiene un usuario por su 'email'
		
		Usuario usuario = (Usuario) sesion.createCriteria(Usuario.class)
				          .add(Restrictions.eq("email", email))
				          .uniqueResult();

		return usuario;
	}

	@Override
	public void guardarCursoDelUsuario(Curso curso_obtenido, Usuario usuario) {
			
		actualizarEstado(curso_obtenido,Estado.EN_CURSO);
		
		Session sesion = sessionFactory.getCurrentSession();
		
		UsuarioCurso usuarioCurso = new UsuarioCurso();
		usuarioCurso.setCurso(curso_obtenido);
		usuarioCurso.setUsuario(usuario);
		
		sesion.save(usuarioCurso);
	}
	
	
	@Override
	public Usuario buscarUsuario(String email, String password) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Usuario usuario = (Usuario) session.createCriteria(Usuario.class)
						  .add(Restrictions.eq("email", email))
						  .add(Restrictions.eq("password", password))
						  .uniqueResult();
		
		return usuario;
	}

	@Override
	public void guardarUsuario(Usuario nuevoUsuario) {
		sessionFactory.getCurrentSession().save(nuevoUsuario);
	}

	@Override
	public Usuario buscarUsuarioPorID(int id_user) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		// Se obtiene un usuario por su id
		
		Usuario curso = sesion.get(Usuario.class, id_user);
		
		return curso;
	}

	
	@Override
	public void cancelarCurso(Curso curso_obtenido, Usuario usuario) {
		
		actualizarEstado(curso_obtenido,Estado.CANCELADO);
	}
	
	private void actualizarEstado(Curso curso_obtenido, Estado estado) {
		
		curso_obtenido.setEstado(estado);
		sessionFactory.getCurrentSession().update(curso_obtenido);
	}

	@Override
	public void eliminarCurso(Curso curso_obtenido, Usuario usuario) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		List<Curso> lista_cursos = servicioUsuarioCurso.obtenerCursosDelUsuario(usuario);

		for (int i = 0; i < lista_cursos.size(); i++) {
			
			if(lista_cursos.get(i).getId() == curso_obtenido.getId()) {
				
				servicioUsuarioCurso.obtenerCursosDelUsuario(usuario).remove(i);
				sesion.update(usuario);
			}
		}
	
	}

	@Override
	public void finalizarCurso(Curso curso_obtenido, Usuario usuario) {
		
		actualizarEstado(curso_obtenido,Estado.FINALIZADO);

//		Session sesion = sessionFactory.getCurrentSession();
//
//		usuario.getMisCursos().add(curso_obtenido);
//
//		sesion.update(usuario);
//		
	}

	@Override
	public void modoficarUsuario(Usuario usuario) {

		sessionFactory.getCurrentSession().update(usuario);
		
	}

	

}
