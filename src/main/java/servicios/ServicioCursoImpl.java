package servicios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Curso;
import repositorios.RepositorioCurso;

@Service("servicioCurso")
@Transactional
public class ServicioCursoImpl implements ServicioCurso {

	@Autowired
	private RepositorioCurso repositorioCurso;

	@Override
	public List<Curso> busqueda() {

		return repositorioCurso.obtenerListaCursos();
	}

	@Override
	public void añadirCurso(Curso curso) {
		repositorioCurso.añadirCurso(curso);
	}

}
