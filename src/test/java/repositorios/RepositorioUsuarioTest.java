package repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import archivos.SpringTest;
import modelo.*;

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
	
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaGuardarUnUsuario() {
		
		// Preparacion
		Usuario user = new Usuario("test", "test@test", "122", "cliente");
		
		// Ejecucion
		repositorioUsuario.guardarUsuario(user);
		
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
		
		Usuario usuarioObtenido = repositorioUsuario.buscarUsuarioPorEmail("prueba@prueba");
		
		// Comprobacion
		assertThat(usuarioObtenido).isNotNull();
	}
	
}
