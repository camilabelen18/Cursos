package servicios;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void agregarUsuario(Usuario usuario) {
		repositorioUsuario.agregarUsuario(usuario);
		
	}

}
