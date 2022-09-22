package servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Curso;
import repositorios.RepositorioCurso;

@Service
@Transactional
public class ServicioCursoImpl implements ServicioCurso{
	
	@Autowired
	private RepositorioCurso repositorioCurso;

	@Override
	public List<Curso> busqueda() {
		
		return repositorioCurso.obtenerListaCursos();
	}
	
	

}
