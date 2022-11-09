package repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import modelo.Giftcard;
import modelo.Usuario;

@Repository("repositorioGiftcard")
@Transactional
public class RepositorioGiftcardImpl implements RepositorioGiftcard {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void actualizarGiftcard(Giftcard giftcard) {
		sessionFactory.getCurrentSession().update(giftcard);
	}

	/*
	@Override
	public Giftcard obtenerGiftcard(Usuario usuario) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		Giftcard gift = sesion.createCriteria(Giftcard.class)
				        .add(Restrictions.eq("", usuario));
		
		return null;
	}*/

}
