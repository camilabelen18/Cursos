package repositorios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
		List<Curso> lista_cursos = sesion.createCriteria(Curso.class)
				                   .add(Restrictions.like("nombre","%" + nombreCurso + "%"))
				                   .list();
		return lista_cursos;
	}

	@Override
    public Curso obtenerCursoPorID(int id) {
        
		Session sesion = sessionFactory.getCurrentSession();
		Curso curso = sesion.get(Curso.class, id);
		return curso;
    }

	@Override
	public List<Curso> obtenerListaTotalCursos() {
		
		Session sesion = sessionFactory.getCurrentSession();
		List<Curso> lista_cursos = sesion.createCriteria(Curso.class).list();
		return lista_cursos;
	}

	@Override
	public List<Curso> obtenerListaCursosPorCategoria(String categoria) {

		Session sesion = sessionFactory.getCurrentSession();
		List<Curso> lista_cursos = sesion.createCriteria(Curso.class)
				                   .add(Restrictions.eq("categoria", categoria))
				                   .list();
		return lista_cursos;
	}

	@Override
	public List<Usuario_Curso> obtenerListaCursosPorEstado(Estado estado, Usuario usuario) {
		
		Session sesion = sessionFactory.getCurrentSession();
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
	public void agregarCurso(Curso curso) {
		sessionFactory.getCurrentSession().save(curso);
	}
	
}
