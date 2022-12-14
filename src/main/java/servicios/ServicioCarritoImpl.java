package servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.*;
import repositorios.RepositorioCarrito;
import repositorios.RepositorioUsuario;

@Service("servicioCarrito")
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito {
	
	private RepositorioCarrito repositorioCarrito;
	private RepositorioUsuario repositorioUsuario;
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	public ServicioCarritoImpl(RepositorioCarrito repositorioCarrito, RepositorioUsuario repositorioUsuario, ServicioUsuario servicioUsuario) {
		this.repositorioCarrito = repositorioCarrito;
		this.repositorioUsuario = repositorioUsuario;
		this.servicioUsuario = servicioUsuario;
	}


	@Override
	public Carrito buscarCarritoPorId(int id_carrito) {
		return repositorioCarrito.buscarCarritoPorID(id_carrito);
	}


	@Override
	public double getTotalDePrecios(List<Curso> cursos) {
		
		double resultadoTotal = 0;
		
		for (Curso curso : cursos) {
			resultadoTotal += curso.getPrecio();
		}
		
		return resultadoTotal;
	}


	@Override
	public void agregarCursoAlCarrito(Curso curso_obtenido, Carrito carrito) {
		repositorioCarrito.agregarCursoALista(curso_obtenido, carrito);
	}


	@Override
	public Carrito obtenerCarritoPorIdUsuario(int id_user) {
		return repositorioCarrito.obtenerCarritoPorIdUsuario(id_user);
	}


	@Override
	public List<Curso> obtenerCursosDelCarrito(Carrito carrito) {
		
		List<Curso> cursosCarrito = repositorioCarrito.obtenerCursosDelCarrito(carrito);
		
		if (!cursosCarrito.isEmpty()) {
			
			return cursosCarrito;
		}
		else {
			throw new ListaCarritoException();
		}
	}


	@Override
	public void eliminarCursoDelCarrito(Carrito_Curso carritoCurso) {
		repositorioCarrito.eliminarCursoDelCarrito(carritoCurso);
	}


	@Override
	public Carrito_Curso obtenerCarritoCurso(Carrito carrito, Curso curso) {
		return repositorioCarrito.obtenerCarritoCurso(carrito, curso);
	}


	@Override
	public void comprarCursosDelCarrito(List<Curso> cursos, Usuario usuario) {
		
		for (Curso curso : cursos) {
			
			if (!servicioUsuario.existeCursoEnListaUsuario(curso.getId(), usuario)) {
				
				repositorioUsuario.guardarCursoDelUsuario(curso, usuario);
			}
		}
	}


	@Override
	public List<Carrito_Curso> obtenerCarritoCursos(Carrito carrito) {
		return repositorioCarrito.obtenerCarritoCursos(carrito);
	}


	@Override
	public void vaciarCursosDelCarrito(List<Carrito_Curso> cursosCarrito) {
		
		for (Carrito_Curso cursoCarrito : cursosCarrito) {
			
			repositorioCarrito.eliminarCursoDelCarrito(cursoCarrito);
		}
	}


	@Override
	public boolean existeCursoEnListaCarrito(Curso curso_obtenido, Carrito carrito) {
		
		boolean yaExisteElCurso = false;
		List<Curso> cursos = repositorioCarrito.obtenerCursosDelCarrito(carrito);

		for (Curso curso : cursos) {

			if (curso.getId() == curso_obtenido.getId()) {

				yaExisteElCurso = true;
				break;
			}
		}

		return yaExisteElCurso;
	}

}
