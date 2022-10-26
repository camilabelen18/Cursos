package servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Curso;
import modelo.Curso_Unidad;
import modelo.Estado;
import modelo.Unidad;
import modelo.Usuario;
import modelo.Usuario_Curso;
import repositorios.RepositorioCurso;
import repositorios.RepositorioUsuario;

@Service("servicioCurso")
@Transactional
public class ServicioCursoImpl implements ServicioCurso {

	@Autowired
	private RepositorioCurso repositorioCurso;
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;

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
	public Curso buscarCursoPorId(int id) {
		
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

	@Override
	public void agregarCurso(String nombre, String categoria, String descripcion, Double precio, String imagen) {
		Curso curso = new Curso();
		curso.setNombre(nombre);
		curso.setCategoria(categoria);
		curso.setDescripcion(descripcion);
		curso.setPrecio(precio);
		curso.setImagen(imagen);
		
		repositorioCurso.agregarCurso(curso);
	}

	@Override
	public void cambiarEstadoCurso(Curso curso_obtenido, Estado estado) {
		repositorioUsuario.cambiarEstadoCurso(curso_obtenido,estado);
	}

	@Override
	public List<Unidad> obtenerUnidades(Curso curso) {
		return repositorioCurso.obtenerUnidadesDelCurso(curso);
	}

	@Override
	public Unidad obtenerUnidadPorID(Integer unidad_id) {
		return repositorioCurso.obtenerUnidadPorID(unidad_id);
	}

	@Override
	public void completarUnidad(Unidad unidad, Curso curso, List<Unidad> unidades) {
		
		unidad.setCompletado(true);
		repositorioCurso.actualizarUnidad(unidad);
		
		actualizarProgresoCurso(curso, unidades);
	}

	private void actualizarProgresoCurso(Curso curso, List<Unidad> unidades) {;
		
		double cant_registros = unidades.size();
		Double progreso_unidad = 100.00 / cant_registros;
	
		// Se establece la cantidad maxima de decimales del progreso
		progreso_unidad = Math.round(progreso_unidad * 100) / 100d;
		
		Double progreso_actual = curso.getProgreso() + progreso_unidad;
		
		curso.setProgreso(progreso_actual);
		repositorioCurso.actualizarCurso(curso);
	}

	@Override
	public void actualizarCurso(int idCurso, String nombre, String categoria, String descripcion, Double precio) {
		Curso curso = repositorioCurso.obtenerCursoPorID(idCurso);
		curso.setNombre(nombre);
		curso.setCategoria(categoria);
		curso.setDescripcion(descripcion);
		curso.setPrecio(precio);
		
	}
}
