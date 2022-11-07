package servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositorios.*;

@Service("servicioGiftcard")
@Transactional
public class ServicioGiftcardImpl implements ServicioGiftcard {
	
	private RepositorioGiftcard repositorioGiftcard;

	@Autowired
	public ServicioGiftcardImpl(RepositorioGiftcard repositorioGiftcard) {
		this.repositorioGiftcard = repositorioGiftcard;
	}
	
	
	

}
