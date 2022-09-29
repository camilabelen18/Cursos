package repositorios;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import modelo.Usuario;

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
	public void agregarUsuario(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
		
	}

}
