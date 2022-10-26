package repositorios;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import modelo.Carrito;
import modelo.Curso;
import modelo.Curso_Unidad;
import modelo.Estado;
import modelo.Usuario;
import modelo.Usuario_Curso;

@Repository
@Transactional
public class RepositorioUsuarioImpl implements RepositorioUsuario{

	@Autowired
	private SessionFactory sessionFactory;
	
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
		usuarioCurso.setFecha_incio_compra(LocalDate.now());
		usuarioCurso.setHora(LocalTime.now());
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
	public void cambiarEstadoCurso(Curso curso_obtenido, Estado estado) {
		actualizarEstado(curso_obtenido, estado);
	}
	public Boolean cancelarCurso(Curso curso_obtenido, UsuarioCurso usuarioCurso) {
		
		if(restarFechas(usuarioCurso) == true) {
		actualizarEstado(curso_obtenido,Estado.CANCELADO);
		return true;
		}
		
		return false;
	}

	public UsuarioCurso obtenerUsuarioCurso(Curso curso_obtenido, Usuario usuario) {
		return (UsuarioCurso) sessionFactory.getCurrentSession()
				.createCriteria(UsuarioCurso.class)
				.add(Restrictions.eq("usuario", usuario))
				.add(Restrictions.eq("curso", curso_obtenido))
				.uniqueResult();
	}

	private Boolean restarFechas(UsuarioCurso usuarioCurso) {
		Long diferencia_dias = ChronoUnit.DAYS.between(usuarioCurso.getFecha_incio_compra(),LocalDate.now());
		if(diferencia_dias == 1) {		
		Long minuto = ChronoUnit.MINUTES.between(usuarioCurso.getHora(),LocalTime.now());
		if(minuto <= 2) {
			return true;
		}
		}
		return false;
	}

	private void actualizarEstado(Curso curso_obtenido, Estado estado) {
		curso_obtenido.setEstado(estado);
		sessionFactory.getCurrentSession().update(curso_obtenido);
	}

	@Override
	public void eliminarCurso(Curso curso_obtenido, Usuario usuario) {
		
		Session sesion = sessionFactory.getCurrentSession();
				
		Usuario_Curso usuarioCurso = (Usuario_Curso) sesion.createCriteria(Usuario_Curso.class)
				 					 .add(Restrictions.eq("usuario", usuario))
				 					 .add(Restrictions.eq("curso", curso_obtenido))
				 					 .uniqueResult();
		
		sesion.delete(usuarioCurso);
	}
	
	@Override
	public List<Curso> obtenerCursosDelUsuario(Usuario usuario) {
		
		Session sesion = sessionFactory.getCurrentSession();

		List<Usuario_Curso> usuario_cursos = sesion.createCriteria(Usuario_Curso.class)
											 .add(Restrictions.eq("usuario", usuario))
											 .list();
//		Session sesion = sessionFactory.getCurrentSession();
//
//		usuario.getMisCursos().add(curso_obtenido);
//
//		sesion.update(usuario);
//	
		
		List<Curso> lista_curso = new ArrayList<Curso>();
		
		for (Usuario_Curso usuarioCurso : usuario_cursos) {
			
			lista_curso.add(usuarioCurso.getCurso());
		}

		return lista_curso;
	}

	@Override
	public void cambiarEstadoCurso(Curso curso_obtenido, Estado estado) {
		actualizarEstado(curso_obtenido,estado);
	}

	

	

}
