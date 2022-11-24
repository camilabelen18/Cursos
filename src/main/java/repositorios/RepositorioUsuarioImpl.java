package repositorios;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
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
import modelo.Notificacion;
import modelo.Usuario;
import modelo.Usuario_Curso;
import modelo.Usuario_Notificacion;
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
		
		Session sesion = sessionFactory.getCurrentSession();
		
		Usuario_Curso usuarioCurso = new Usuario_Curso(usuario, curso_obtenido);
		usuarioCurso.setEstado(Estado.EN_CURSO);
		usuarioCurso.setFecha_incio_compra(LocalDate.now());
		usuarioCurso.setHora(LocalTime.now());
		
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
	public Boolean cancelarCurso(Usuario_Curso usuarioCurso) {
		
		if (restarFechas(usuarioCurso) == true) {
			
			actualizarEstado(usuarioCurso, Estado.CANCELADO);
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
	public void cambiarEstadoCurso(Usuario_Curso usuarioCurso, Estado estado) {
		actualizarEstado(usuarioCurso, estado);
	}

	private void actualizarEstado(Usuario_Curso usuarioCurso, Estado estado) {
		usuarioCurso.setEstado(estado);
		sessionFactory.getCurrentSession().update(usuarioCurso);
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
	public List<Usuario_Curso> obtenerCursosDelUsuario(Usuario usuario) {
		
		Session sesion = sessionFactory.getCurrentSession();

		List<Usuario_Curso> usuario_cursos = sesion.createCriteria(Usuario_Curso.class)
											 .add(Restrictions.eq("usuario", usuario))
											 .list();

		return usuario_cursos;
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
	public void actualizarCursoDelUsuario(Usuario_Curso usuarioCurso) {
		sessionFactory.getCurrentSession().update(usuarioCurso);
	}

	@Override
	public List<Notificacion> obtenerNotificaciones(Usuario usuario) {
		
		Session sesion  = sessionFactory.getCurrentSession();
		
		List<Usuario_Notificacion> usuarioNotificaciones = sesion.createCriteria(Usuario_Notificacion.class)
														   .add(Restrictions.eq("usuario", usuario))
													       .list();

		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		
		for (Usuario_Notificacion usuarioNotificacion : usuarioNotificaciones) {
			
			notificaciones.add(usuarioNotificacion.getNotificacion());
		}
		
		return notificaciones;
	}

	@Override
	public void guardarNotificacionDelUsuario(Notificacion notif, Usuario usuario) {
		
		Session sesion  = sessionFactory.getCurrentSession();
		
		sesion.save(notif);
		sesion.save(new Usuario_Notificacion(usuario, notif));
	}

	@Override
	public Notificacion obtenerNotificacionPorId(int idNotif) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		Notificacion notificacion = sesion.get(Notificacion.class, idNotif);
		
		return notificacion;
	}

	@Override
	public void eliminarNotificacion(Notificacion notificacion) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		Usuario_Notificacion usuarioNotif = (Usuario_Notificacion)sesion.createCriteria(Usuario_Notificacion.class)
										    .add(Restrictions.eq("notificacion", notificacion))
										    .uniqueResult();
		
		sesion.delete(usuarioNotif);
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
	public boolean cancelarExamen(Usuario_Examen usuarioExamen,Examen examen) {
        
		if (restarFechasExamen(usuarioExamen) == true) {
			
			examen.setEstadoHabilitado(true);
			sessionFactory.getCurrentSession().update(examen);
			
			return true;
		}
		
		return false;
     
	}
	
	private Boolean restarFechasExamen(Usuario_Examen usuarioExamen) {
		
		   Long diferencia_dias = ChronoUnit.DAYS.between(usuarioExamen.getFecha_finalizacion_examen(), LocalDate.now());
			
			if (diferencia_dias == 1) {	
				
				Long minuto = ChronoUnit.MINUTES.between(usuarioExamen.getHora_finalizacion_examen(),LocalTime.now());
				
				if(minuto <= 2) {  
					return true;
				}
			}
			return false;
	}

	@Override
	public void verificarFechaDeExamen(Usuario_Examen usuarioExamen) {
		
		if (usuarioExamen != null) {
			  if (restarFechasExamen(usuarioExamen) == false) {
					
					usuarioExamen.getExamen().setEstadoHabilitado(false);
					sessionFactory.getCurrentSession().update(usuarioExamen.getExamen());
					
					
				}
		}
       
		
		
	}
	
	
	

	
	
	@Override
	public boolean verificarSiHizoElExamenCuatroVecesOmas(Usuario usuario, Examen examen) {
		Session sesion = sessionFactory.getCurrentSession();
		

		List<Usuario_Examen> usuario_examenes = sesion.createCriteria(Usuario_Examen.class)
											 .add(Restrictions.eq("usuario", usuario)) 
											 .add(Restrictions.eq("examen", examen)) 
											 .list();
		if(usuario_examenes != null) { 
			if(usuario_examenes.size() > 3) { 
				return true;
			}
			
		}
		

		return false;
	}

	@Override
	public List<Usuario_Examen> obtenerExamenesDelUsuario(Usuario usuario,Examen examen) {
		
     Session sesion = sessionFactory.getCurrentSession();
		

		List<Usuario_Examen> usuario_examenes = sesion.createCriteria(Usuario_Examen.class)
											 .add(Restrictions.eq("usuario", usuario))
											 .add(Restrictions.eq("examen", examen))
											 .list();
		
	/*	List<Usuario_Examen> listaUsuarioExamen = new ArrayList<Usuario_Examen>();
		
		for (Usuario_Examen usuario_Examen : usuario_examenes) {
			listaUsuarioExamen.add(usuario_Examen);
		} */

		return usuario_examenes;

	}




}
