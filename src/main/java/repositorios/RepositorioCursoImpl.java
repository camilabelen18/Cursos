package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import modelo.*;

@Repository("repositorioCurso")
@Transactional
public class RepositorioCursoImpl implements RepositorioCurso{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Curso> obtenerListaCursosPorNombre(String nombreCurso) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		// Se obtiene una lista de cursos por su nombre haciendo uso del operador "LIKE"
		
		List<Curso> lista_cursos = sesion.createCriteria(Curso.class)
				                   .add(Restrictions.like("nombre","%" + nombreCurso + "%"))
				                   .list();

		return lista_cursos;
	}

	@Override
    public Curso obtenerCursoPorID(int id) {
        
		Session sesion = sessionFactory.getCurrentSession();
		
		// Se obtiene un curso por su id
		
		Curso curso = sesion.get(Curso.class, id);
		
		return curso;
    }

	@Override
	public List<Curso> obtenerListaTotalCursos() {
		
		Session sesion = sessionFactory.getCurrentSession();

		// Se obtiene la lista de todos los cursos

		List<Curso> lista_cursos = sesion.createCriteria(Curso.class).list();

		return lista_cursos;
	}

	@Override
	public List<Curso> obtenerListaCursosPorCategoria(String categoria) {

		Session sesion = sessionFactory.getCurrentSession();

		// Se obtiene una lista de cursos por su categoria
		
		List<Curso> lista_cursos = sesion.createCriteria(Curso.class)
				                   .add(Restrictions.eq("categoria", categoria))
				                   .list();

		return lista_cursos;
	}

	@Override
	public void agregarCurso(Curso curso) {
		sessionFactory.getCurrentSession().save(curso);
	}

	@Override
	public List<Usuario_Curso> obtenerListaCursosPorEstado(Estado estado, Usuario usuario) {
		
		Session sesion = sessionFactory.getCurrentSession();

		// Se obtiene una lista de cursos por estado
		List<Usuario_Curso> lista_cursos = sesion.createCriteria(Usuario_Curso.class)
										   .add(Restrictions.eq("usuario", usuario))
										   .add(Restrictions.eq("estado", estado))
				                   		   .list();

		return lista_cursos;
	}

	@Override
	public List<Unidad> obtenerUnidadesDelCurso(Curso curso) {
		
		Session sesion = sessionFactory.getCurrentSession();

		List<Curso_Unidad> curso_unidades = sesion.createCriteria(Curso_Unidad.class)
											.add(Restrictions.eq("curso", curso))
											.list();

		List<Unidad> unidades = new ArrayList<Unidad>();
		
		for (Curso_Unidad cursoUnidad : curso_unidades) {
			
			unidades.add(cursoUnidad.getUnidad());
		}
		
		return unidades;
	}

	@Override
	public Unidad obtenerUnidadPorID(Integer unidad_id) {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		Unidad unidad = sesion.get(Unidad.class, unidad_id);
		
		return unidad;
	}

	@Override
	public void actualizarUnidad(Unidad unidad) {
		sessionFactory.getCurrentSession().update(unidad);
	}

	@Override
	public void actualizarCurso(Curso curso) {
		sessionFactory.getCurrentSession().update(curso);
	}

	@Override
	public Examen obtenerExamenPorCurso(Curso curso_obtenido) {
		
        Session sesion = sessionFactory.getCurrentSession();
        
		Curso_Examen curso_examen1 = (Curso_Examen) sesion.createCriteria(Curso_Examen.class)
				                    .add(Restrictions.eq("curso", curso_obtenido))
				                    .uniqueResult();
		
		Examen examen_conseguido = curso_examen1.getExamen();
		
		
		return examen_conseguido;
	}

	@Override
	public List<Pregunta> obtenerPreguntasDelExamen(Examen examen) {
		
		Session sesion = sessionFactory.getCurrentSession();

		List<Curso_Examen> curso_examenes = sesion.createCriteria(Curso_Examen.class)
											.add(Restrictions.eq("examen", examen))
											.list();

		List<Pregunta> preguntas = new ArrayList<Pregunta>();
		
		for (Curso_Examen cursoExamen : curso_examenes) {
			
			preguntas.add(cursoExamen.getExamen().getPregunta_1());
			preguntas.add(cursoExamen.getExamen().getPregunta_2());
			preguntas.add(cursoExamen.getExamen().getPregunta_3());
			preguntas.add(cursoExamen.getExamen().getPregunta_4());
			preguntas.add(cursoExamen.getExamen().getPregunta_5());
		}
		
		return preguntas;
	}

	@Override
	public Pregunta buscarPreguntaPorId(int pregunta_id) {
        Session sesion = sessionFactory.getCurrentSession();
		
		Pregunta pregunta = sesion.get(Pregunta.class, pregunta_id);
		
		return pregunta;
	}

	@Override
	public Respuesta buscarRespuestaPorId(int respuesta_id) {
        Session sesion = sessionFactory.getCurrentSession();
		
         Respuesta respuesta = sesion.get(Respuesta.class, respuesta_id);
		
		return respuesta;
	}

	@Override
	public void actualizarExamenAaprobado(Examen examen) {
		Session sesion = sessionFactory.getCurrentSession();
		
		examen.setAprobado(true);
		
		sesion.update(examen);
		
		
	}

	

	





}
