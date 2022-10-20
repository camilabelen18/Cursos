package servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.*;
import repositorios.RepositorioUsuarioCurso;

@Service("servicioUsuarioCurso")
@Transactional
public class ServicioUsuarioCursoImpl implements ServicioUsuarioCurso {
	
	@Autowired
	private RepositorioUsuarioCurso repositorioUsuarioCurso;

	@Override
	public List<Curso> obtenerCursosDelUsuario(Usuario usuario) {
		return repositorioUsuarioCurso.obtenerCursosDelUsuario(usuario);
	}

}
