package repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import archivos.SpringTest;
import modelo.*;

public class RepositorioUsuarioTest extends SpringTest {
	
	@Autowired
	private RepositorioUsuario respositorioUsuario;
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaGuardarUnUsuario() {
		
		// Preparacion
		Usuario user = new Usuario("test", "test@test", "122", "cliente");
		
		// Ejecucion
		respositorioUsuario.guardarUsuario(user);
		
		// Comprobacion
		assertThat(user.getId()).isNotEqualTo(0);
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaBuscarUsuarioPorEmail() {
		
		// Preparacion
		Usuario user1 = new Usuario("test", "test@test", "122", "cliente");
		Usuario user2 = new Usuario("prueba", "prueba@prueba", "1223", "cliente");
		
		// Ejecucion
		session().save(user1);
		session().save(user2);
		
		Usuario usuarioObtenido = respositorioUsuario.buscarUsuarioPorEmail("prueba@prueba");
		
		// Comprobacion
		assertThat(usuarioObtenido).isNotNull();
	}

}
