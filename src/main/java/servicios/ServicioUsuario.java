package servicios;

import modelo.Usuario;

public interface ServicioUsuario {

	Boolean validarTarjeta(Integer nroTarjeta, String email);

	Usuario buscarUsuarioPorEmail(String email);

	void agregarUsuario(Usuario usuario);
	
	

}
