package repositorios;

import modelo.Usuario;

public interface RepositorioUsuario {

	Boolean buscarTarjetaEmail(Integer nroTarjeta, String email);

	Usuario buscarUsuarioPorEmail(String email);

	void agregarUsuario(Usuario usuario);

}
