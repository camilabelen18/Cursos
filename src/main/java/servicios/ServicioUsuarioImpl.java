package servicios;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Curso;
import modelo.Usuario;
import repositorios.RepositorioUsuario;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario{
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@Override
	public Boolean validarTarjeta(Integer nroTarjeta, String email) {
		return repositorioUsuario.buscarTarjetaEmail(nroTarjeta,email);
	}

	@Override
	public Usuario buscarUsuarioPorEmail(String email) {
		return repositorioUsuario.buscarUsuarioPorEmail(email);
	}

	@Override
	public void guardarCursoEnListaUsuario(Curso curso_obtenido, Usuario usuario) {
		repositorioUsuario.guardarCursoDelUsuario(curso_obtenido, usuario);
	}

	@Override
	public Usuario consultarUsuario(String email, String password) {
		return repositorioUsuario.buscarUsuario(email, password);
	}

	@Override
	public void registrar(String nombre, String email, String contrasenia) {
		
		Usuario nuevoUsuario = new Usuario();
		
		nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(contrasenia);
        nuevoUsuario.setRol("cliente");

        repositorioUsuario.guardarUsuario(nuevoUsuario);
	}

}
