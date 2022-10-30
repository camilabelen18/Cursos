package repositorios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("repositorioGiftcard")
@Transactional
public class RepositorioGiftcardImpl implements RepositorioGiftcard {
	
	@Autowired
	private SessionFactory sessionFactory;

}
