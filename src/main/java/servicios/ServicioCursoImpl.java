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
	public List<Curso> busqueda(String descripcion) {
		
		
		return repositorioCurso.obtenerListaCursosPorDescripcion(descripcion);

	}

	@Override
	public Curso busquedaPorID(Long id) {
		
		return repositorioCurso.obtenerListaCursosPorID(id);
	}
	
	

}
