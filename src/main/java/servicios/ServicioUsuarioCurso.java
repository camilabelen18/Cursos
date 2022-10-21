package servicios;

import java.util.List;

import modelo.Curso;
import modelo.Usuario;

public interface ServicioUsuarioCurso {

	List<Curso> obtenerCursosDelUsuario(Usuario usuario);

}
