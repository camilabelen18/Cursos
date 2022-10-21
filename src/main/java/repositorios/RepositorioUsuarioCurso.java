package repositorios;

import java.util.List;

import modelo.Curso;
import modelo.Usuario;

public interface RepositorioUsuarioCurso {

	List<Curso> obtenerCursosDelUsuario(Usuario usuario);

}
