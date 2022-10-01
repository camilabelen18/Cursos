package servicios;

import modelo.Curso;
import modelo.Usuario;

public interface ServicioUsuario {

	Boolean validarTarjeta(Integer nroTarjeta, String email);

	Usuario buscarUsuarioPorEmail(String email);

	void agregarUsuario(Usuario usuario);

	void guardarCursoEnListaUsuario(Curso curso_obtenido, Usuario usuario);
	
	

}
