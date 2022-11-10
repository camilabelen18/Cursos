package repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import archivos.SpringTest;
import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;
import modelo.Usuario_Curso;

public class RepositorioUsuarioTest extends SpringTest{
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@Test
	@Transactional
	@Rollback
	public void queSepuedaGuardarUnUsuario() {
		
		Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");
		
		repositorioUsuario.guardarUsuario(usuario);
		
		assertThat(usuario.getId()).isNotNull();
		
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaActualizarUnUsuario() {
		Usuario usuario = new Usuario("juan", "hola@hola.com", "123", "Cliente");
		
		repositorioUsuario.actualizarUsuario(usuario);
		
		assertThat(usuario.getId()).isNotNull();
		
	}
	
	
	
}
