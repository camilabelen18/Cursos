package servicios;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Curso;
import modelo.Estado;
import modelo.Usuario;
import repositorios.RepositorioCurso;

@Service("servicioCurso")
@Transactional
public class ServicioCursoImpl implements ServicioCurso {

	@Autowired
	private RepositorioCurso repositorioCurso;

	@Override
	public List<Curso> getCursos() {
		
		List<Curso> lista_cursos = repositorioCurso.obtenerListaTotalCursos();
		
		return lista_cursos;
	}

	@Override
	public List<Curso> getCursosPorCategoria(String categoria) {
		
		List<Curso> lista_cursos = repositorioCurso.obtenerListaCursosPorCategoria(categoria);
		
		return lista_cursos;
	}
	
	@Override
	public List<Curso> getCursosPorNombre(String nombreCurso) {
		
		return repositorioCurso.obtenerListaCursosPorNombre(nombreCurso);
	}
	
	@Override
	public Curso busquedaPorID(int id) {
		
		// Crear excepcion controlada del curso
		return repositorioCurso.obtenerCursoPorID(id);
	}

	@Override
	public void agregarCurso(Curso curso) {
		repositorioCurso.agregarCurso(curso);
	}

	@Override
	public List<Curso> getCursosPorEstado(Estado estado) {

		return repositorioCurso.obtenerListaCursosPorEstado(estado);
	}

}
