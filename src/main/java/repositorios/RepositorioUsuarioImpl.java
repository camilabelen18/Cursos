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
import modelo.Examen;
import modelo.Giftcard;
import modelo.Usuario;
import modelo.Usuario_Curso;
import modelo.Usuario_Examen;

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
		
		Usuario_Curso usuarioCurso = new Usuario_Curso();
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
		
		Usuario curso = sesion.get(Usuario.class, id_user);
		
		return curso;
	}
	
	@Override
	public Boolean cancelarCurso(Curso curso_obtenido, Usuario_Curso usuarioCurso) {
		
		if (restarFechas(usuarioCurso) == true) {
			
			actualizarEstado(curso_obtenido,Estado.CANCELADO);
			return true;
		}
		
		return false;
	}
	
	private Boolean restarFechas(Usuario_Curso usuarioCurso) {
		
		Long diferencia_dias = ChronoUnit.DAYS.between(usuarioCurso.getFecha_incio_compra(), LocalDate.now());
		
		if (diferencia_dias == 1) {	
			
			Long minuto = ChronoUnit.MINUTES.between(usuarioCurso.getHora(),LocalTime.now());
			
			if(minuto <= 2) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Usuario_Curso obtenerUsuarioCurso(Curso curso_obtenido, Usuario usuario) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		Usuario_Curso usuarioCurso = (Usuario_Curso) sesion.createCriteria(Usuario_Curso.class)
									 .add(Restrictions.eq("usuario", usuario))
									 .add(Restrictions.eq("curso", curso_obtenido))
									 .uniqueResult();
		
		return usuarioCurso;
	}
	
	@Override
	public void cambiarEstadoCurso(Curso curso_obtenido, Estado estado) {
		actualizarEstado(curso_obtenido, estado);
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
		
		List<Curso> lista_curso = new ArrayList<Curso>();
		
		for (Usuario_Curso usuarioCurso : usuario_cursos) {
			
			lista_curso.add(usuarioCurso.getCurso());
		}

		return lista_curso;
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		sessionFactory.getCurrentSession().update(usuario);
	}

	@Override
	public void guardarGiftcardDeUsuario(Giftcard gift) {
		sessionFactory.getCurrentSession().save(gift);
	}

	@Override
	public void guardarExamenDeUsuario(Examen examen, Usuario usuario, int notaSacada) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		Usuario_Examen usuarioExamen = new Usuario_Examen();
		usuarioExamen.setFecha_finalizacion_examen(LocalDate.now());
		usuarioExamen.setHora_finalizacion_examen(LocalTime.now());
	    usuarioExamen.setExamen(examen);
	    usuarioExamen.setPuntaje_final(notaSacada);
        usuarioExamen.setUsuario(usuario);

		
		sesion.save(usuarioExamen);
		
	}

	@Override
	public Usuario_Examen obtenerExamenUsuario(Examen examen, Usuario usuario) {
	
        Session sesion = sessionFactory.getCurrentSession();
		
		Usuario_Examen usuarioExamen = (Usuario_Examen) sesion.createCriteria(Usuario_Examen.class)
									 .add(Restrictions.eq("usuario", usuario))
									 .add(Restrictions.eq("examen", examen))
									 .setMaxResults(1).uniqueResult();
		
		return usuarioExamen;
	}

	@Override
	public boolean cancelarExamen(Usuario_Examen usuarioExamen) {
		
        Long diferencia_dias = ChronoUnit.DAYS.between(usuarioExamen.getFecha_finalizacion_examen(), LocalDate.now());
		
		if (diferencia_dias == 1) {	
			
			Long minuto = ChronoUnit.MINUTES.between(usuarioExamen.getHora_finalizacion_examen(),LocalTime.now());
			
			if(minuto >= 2) { //Fijarse si el condicional esta bien 
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean verificarSiHizoElExamenCuatroVecesOmas(Usuario usuario) {
		Session sesion = sessionFactory.getCurrentSession();
		

		List<Usuario_Examen> usuario_examenes = sesion.createCriteria(Usuario_Examen.class)
											 .add(Restrictions.eq("usuario", usuario))
											 .list();
		
		for (int i = 0; i < usuario_examenes.size(); i++) {
			if(i>=3) {
				return true;
			}
		}

		return false;
	}

}
