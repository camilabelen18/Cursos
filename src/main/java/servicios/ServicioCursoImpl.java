package servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Curso;
import modelo.Curso_Unidad;
import modelo.DatosExamen;
import modelo.DatosPregunta;
import modelo.Estado;
import modelo.Examen;
import modelo.Pregunta;
import modelo.Respuesta;
import modelo.Unidad;
import modelo.Usuario;
import modelo.Usuario_Curso;
import modelo.Usuario_Examen;
import repositorios.RepositorioCurso;
import repositorios.RepositorioUsuario;

@Service("servicioCurso")
@Transactional
public class ServicioCursoImpl implements ServicioCurso {
	
	static Scanner input = new Scanner(System.in);

	private RepositorioCurso repositorioCurso;
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	public ServicioCursoImpl(RepositorioCurso repositorioCurso, RepositorioUsuario repositorioUsuario) {
		this.repositorioCurso = repositorioCurso;
		this.repositorioUsuario = repositorioUsuario;
	}
	

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
		
		if (repositorioCurso.obtenerCursoPorID(id) != null) {
			
			return repositorioCurso.obtenerCursoPorID(id);
		}
		else {
			throw new CursoInexistenteException();
		}
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
		
		repositorioCurso.actualizarCurso(curso);
	}

	@Override
	public Examen obtenerExamenPorCurso(Curso curso_obtenido) {
		
		return repositorioCurso.obtenerExamenPorCurso(curso_obtenido);
	}


	@Override
	public List<Pregunta> obtenerPreguntasDelExamen(Examen examen) {
		
		return repositorioCurso.obtenerPreguntasDelExamen(examen);
	}


	@Override
	public Pregunta buscarPreguntaPorId(int pregunta_id) {
		return repositorioCurso.buscarPreguntaPorId( pregunta_id);
	}


	@Override
	public Respuesta buscarRespuestaPorId(int respuesta_id) {
		return repositorioCurso.buscarRespuestaPorId( respuesta_id);
	}


	@Override
	public List<Pregunta> obtenerPreguntasYrespuestas(List<DatosPregunta> listaDp) {
		
		//sacamos la lista de preguntas 
				
				int pregunta_id = 0;
			
				
				List<Pregunta> listaPrObtenidas = new ArrayList<Pregunta>();
				
				for (DatosPregunta datosPregunta : listaDp) {
					pregunta_id=datosPregunta.getPreguntaId();
					
					Pregunta pregunta_ =buscarPreguntaPorId(pregunta_id);
			
					listaPrObtenidas.add(pregunta_);
				
					
				}
		return listaPrObtenidas;
	}


	@Override
	public List<Respuesta> obtenerRespuestas(List<DatosPregunta> listaDp) {
		//sacamos la lista de respuestas
		
		int respuesta_id = 0;
	
		
		List<Respuesta> listaRobtenida = new ArrayList<Respuesta>();
		
		for (DatosPregunta datosPregunta : listaDp) {
			respuesta_id=datosPregunta.getRespuestaElegida();
			
			Respuesta respuesta_=buscarRespuestaPorId(respuesta_id);
	
			listaRobtenida.add(respuesta_);
		
			
		}
         return listaRobtenida;
         
	}


	@Override
	public DatosExamen guardarPreguntasEnDatosExamen(List<Pregunta> preguntas) {
		DatosExamen datosExamen = new DatosExamen();
		
		for (Pregunta pregunta : preguntas) {
			DatosPregunta datosPregunta = new DatosPregunta();
			datosPregunta.setDescripcion(pregunta.getDescripcion());
			datosPregunta.setPregunta(pregunta);
			datosPregunta.setRespuesta_1(pregunta.getRespuesta_1());
			datosPregunta.setRespuesta_2(pregunta.getRespuesta_2());
			datosPregunta.setRespuesta_3(pregunta.getRespuesta_3());
			datosPregunta.setPreguntaId(pregunta.getId());
			datosExamen.getDatosPregunta().add(datosPregunta); 
		}
		
		return datosExamen;
	}


	@Override
	public List<Pregunta> PreguntasAzar(List<Pregunta> preguntas) {
		
  
		List<Pregunta> listPreguntasAzar = new ArrayList<Pregunta>();
		
		
		 int numero = (int) (Math.random()*3);
		// int numero = 0;
	     System.out.println("Como sale esto: " + numero);
	     
	     switch (numero) {
		case 0:
			for (int i = 0; i < 1; i++) {
				
				listPreguntasAzar.add(0, preguntas.get(4));
				listPreguntasAzar.add(1, preguntas.get(3));
				listPreguntasAzar.add(2, preguntas.get(2));
				listPreguntasAzar.add(3, preguntas.get(1));
				listPreguntasAzar.add(4, preguntas.get(0));
				
				
			}
			
	         System.out.println(" MIRA ACA:  ");
	         System.out.println(listPreguntasAzar);
	         System.out.println(" ");

			for (int j = 0; j < 1; j++) {
				Pregunta pregunta1 = new Pregunta();
				Pregunta pregunta2 = new Pregunta();
				Pregunta pregunta3 = new Pregunta();
				Pregunta pregunta4 = new Pregunta();
				Pregunta pregunta5 = new Pregunta();
				
				pregunta1.setRespuesta_1(listPreguntasAzar.get(0).getRespuesta_3());
				pregunta1.setRespuesta_2(listPreguntasAzar.get(0).getRespuesta_2());
				pregunta1.setRespuesta_3(listPreguntasAzar.get(0).getRespuesta_1());
				
				pregunta2.setRespuesta_1(listPreguntasAzar.get(1).getRespuesta_3());
				pregunta2.setRespuesta_2(listPreguntasAzar.get(1).getRespuesta_2());
				pregunta2.setRespuesta_3(listPreguntasAzar.get(1).getRespuesta_1());
				
				pregunta3.setRespuesta_1(listPreguntasAzar.get(2).getRespuesta_3());
				pregunta3.setRespuesta_2(listPreguntasAzar.get(2).getRespuesta_2());
				pregunta3.setRespuesta_3(listPreguntasAzar.get(2).getRespuesta_1());
				
				pregunta4.setRespuesta_1(listPreguntasAzar.get(3).getRespuesta_3());
				pregunta4.setRespuesta_2(listPreguntasAzar.get(3).getRespuesta_2());
				pregunta4.setRespuesta_3(listPreguntasAzar.get(3).getRespuesta_1());
				
				pregunta5.setRespuesta_1(listPreguntasAzar.get(4).getRespuesta_3());
				pregunta5.setRespuesta_2(listPreguntasAzar.get(4).getRespuesta_2());
				pregunta5.setRespuesta_3(listPreguntasAzar.get(4).getRespuesta_1());
				
			
				listPreguntasAzar.get(0).setRespuesta_1(pregunta1.getRespuesta_1());
				listPreguntasAzar.get(0).setRespuesta_2(pregunta1.getRespuesta_2());
				listPreguntasAzar.get(0).setRespuesta_3(pregunta1.getRespuesta_3());
				
				listPreguntasAzar.get(1).setRespuesta_1(pregunta2.getRespuesta_1());
				listPreguntasAzar.get(1).setRespuesta_2(pregunta2.getRespuesta_2());
				listPreguntasAzar.get(1).setRespuesta_3(pregunta2.getRespuesta_3());
				
				listPreguntasAzar.get(2).setRespuesta_1(pregunta3.getRespuesta_1());
				listPreguntasAzar.get(2).setRespuesta_2(pregunta3.getRespuesta_2());
				listPreguntasAzar.get(2).setRespuesta_3(pregunta3.getRespuesta_3());
				
				listPreguntasAzar.get(3).setRespuesta_1(pregunta4.getRespuesta_1());
				listPreguntasAzar.get(3).setRespuesta_2(pregunta4.getRespuesta_2());
				listPreguntasAzar.get(3).setRespuesta_3(pregunta4.getRespuesta_3());
				
				listPreguntasAzar.get(4).setRespuesta_1(pregunta5.getRespuesta_1());
				listPreguntasAzar.get(4).setRespuesta_2(pregunta5.getRespuesta_2());
				listPreguntasAzar.get(4).setRespuesta_3(pregunta5.getRespuesta_3());
				
			
			}
			 
			 System.out.println(" MIRA ACA (se cambiaron de lugar las respuestas ):  ");
	         System.out.println(listPreguntasAzar);    
			     
			
			break;
        case 1:
        	
            for (int i = 0; i < 1; i++) {
				
				listPreguntasAzar.add(0, preguntas.get(1));
				listPreguntasAzar.add(1, preguntas.get(4));
				listPreguntasAzar.add(2, preguntas.get(3));
				listPreguntasAzar.add(3, preguntas.get(2));
				listPreguntasAzar.add(4, preguntas.get(0));
				
				
			}
			
	         System.out.println(" MIRA ACA:  ");
	         System.out.println(listPreguntasAzar);
	         System.out.println(" ");

			for (int j = 0; j < 1; j++) {
				Pregunta pregunta1 = new Pregunta();
				Pregunta pregunta2 = new Pregunta();
				Pregunta pregunta3 = new Pregunta();
				Pregunta pregunta4 = new Pregunta();
				Pregunta pregunta5 = new Pregunta();
				
				pregunta1.setRespuesta_1(listPreguntasAzar.get(0).getRespuesta_2());
				pregunta1.setRespuesta_2(listPreguntasAzar.get(0).getRespuesta_3());
				pregunta1.setRespuesta_3(listPreguntasAzar.get(0).getRespuesta_1());
				
				pregunta2.setRespuesta_1(listPreguntasAzar.get(1).getRespuesta_1());
				pregunta2.setRespuesta_2(listPreguntasAzar.get(1).getRespuesta_3());
				pregunta2.setRespuesta_3(listPreguntasAzar.get(1).getRespuesta_2());
				
				pregunta3.setRespuesta_1(listPreguntasAzar.get(2).getRespuesta_2());
				pregunta3.setRespuesta_2(listPreguntasAzar.get(2).getRespuesta_1());
				pregunta3.setRespuesta_3(listPreguntasAzar.get(2).getRespuesta_3());
				
				pregunta4.setRespuesta_1(listPreguntasAzar.get(3).getRespuesta_3());
				pregunta4.setRespuesta_2(listPreguntasAzar.get(3).getRespuesta_2());
				pregunta4.setRespuesta_3(listPreguntasAzar.get(3).getRespuesta_1());
				
				pregunta5.setRespuesta_1(listPreguntasAzar.get(4).getRespuesta_1());
				pregunta5.setRespuesta_2(listPreguntasAzar.get(4).getRespuesta_2());
				pregunta5.setRespuesta_3(listPreguntasAzar.get(4).getRespuesta_3());
				
			
				listPreguntasAzar.get(0).setRespuesta_1(pregunta1.getRespuesta_1());
				listPreguntasAzar.get(0).setRespuesta_2(pregunta1.getRespuesta_2());
				listPreguntasAzar.get(0).setRespuesta_3(pregunta1.getRespuesta_3());
				
				listPreguntasAzar.get(1).setRespuesta_1(pregunta2.getRespuesta_1());
				listPreguntasAzar.get(1).setRespuesta_2(pregunta2.getRespuesta_2());
				listPreguntasAzar.get(1).setRespuesta_3(pregunta2.getRespuesta_3());
				
				listPreguntasAzar.get(2).setRespuesta_1(pregunta3.getRespuesta_1());
				listPreguntasAzar.get(2).setRespuesta_2(pregunta3.getRespuesta_2());
				listPreguntasAzar.get(2).setRespuesta_3(pregunta3.getRespuesta_3());
				
				listPreguntasAzar.get(3).setRespuesta_1(pregunta4.getRespuesta_1());
				listPreguntasAzar.get(3).setRespuesta_2(pregunta4.getRespuesta_2());
				listPreguntasAzar.get(3).setRespuesta_3(pregunta4.getRespuesta_3());
				
				listPreguntasAzar.get(4).setRespuesta_1(pregunta5.getRespuesta_1());
				listPreguntasAzar.get(4).setRespuesta_2(pregunta5.getRespuesta_2());
				listPreguntasAzar.get(4).setRespuesta_3(pregunta5.getRespuesta_3());
				
			
			}
			 
			 System.out.println(" MIRA ACA (se cambiaron de lugar las respuestas ):  ");
	         System.out.println(listPreguntasAzar);  
			
			break;
        case 2:
        	
            for (int i = 0; i < 1; i++) {
				
				listPreguntasAzar.add(0, preguntas.get(0));
				listPreguntasAzar.add(1, preguntas.get(4));
				listPreguntasAzar.add(2, preguntas.get(3));
				listPreguntasAzar.add(3, preguntas.get(2));
				listPreguntasAzar.add(4, preguntas.get(1));
				
				
			}
			
	         System.out.println(" MIRA ACA:  ");
	         System.out.println(listPreguntasAzar);
	         System.out.println(" ");

			for (int j = 0; j < 1; j++) {
				Pregunta pregunta1 = new Pregunta();
				Pregunta pregunta2 = new Pregunta();
				Pregunta pregunta3 = new Pregunta();
				Pregunta pregunta4 = new Pregunta();
				Pregunta pregunta5 = new Pregunta();
				
				pregunta1.setRespuesta_1(listPreguntasAzar.get(0).getRespuesta_3());
				pregunta1.setRespuesta_2(listPreguntasAzar.get(0).getRespuesta_2());
				pregunta1.setRespuesta_3(listPreguntasAzar.get(0).getRespuesta_1());
				
				pregunta2.setRespuesta_1(listPreguntasAzar.get(1).getRespuesta_2());
				pregunta2.setRespuesta_2(listPreguntasAzar.get(1).getRespuesta_3());
				pregunta2.setRespuesta_3(listPreguntasAzar.get(1).getRespuesta_1());
				
				pregunta3.setRespuesta_1(listPreguntasAzar.get(2).getRespuesta_1());
				pregunta3.setRespuesta_2(listPreguntasAzar.get(2).getRespuesta_3());
				pregunta3.setRespuesta_3(listPreguntasAzar.get(2).getRespuesta_2());
				
				pregunta4.setRespuesta_1(listPreguntasAzar.get(3).getRespuesta_2());
				pregunta4.setRespuesta_2(listPreguntasAzar.get(3).getRespuesta_3());
				pregunta4.setRespuesta_3(listPreguntasAzar.get(3).getRespuesta_1());
				
				pregunta5.setRespuesta_1(listPreguntasAzar.get(4).getRespuesta_3());
				pregunta5.setRespuesta_2(listPreguntasAzar.get(4).getRespuesta_2());
				pregunta5.setRespuesta_3(listPreguntasAzar.get(4).getRespuesta_1());
				
			
				listPreguntasAzar.get(0).setRespuesta_1(pregunta1.getRespuesta_1());
				listPreguntasAzar.get(0).setRespuesta_2(pregunta1.getRespuesta_2());
				listPreguntasAzar.get(0).setRespuesta_3(pregunta1.getRespuesta_3());
				
				listPreguntasAzar.get(1).setRespuesta_1(pregunta2.getRespuesta_1());
				listPreguntasAzar.get(1).setRespuesta_2(pregunta2.getRespuesta_2());
				listPreguntasAzar.get(1).setRespuesta_3(pregunta2.getRespuesta_3());
				
				listPreguntasAzar.get(2).setRespuesta_1(pregunta3.getRespuesta_1());
				listPreguntasAzar.get(2).setRespuesta_2(pregunta3.getRespuesta_2());
				listPreguntasAzar.get(2).setRespuesta_3(pregunta3.getRespuesta_3());
				
				listPreguntasAzar.get(3).setRespuesta_1(pregunta4.getRespuesta_1());
				listPreguntasAzar.get(3).setRespuesta_2(pregunta4.getRespuesta_2());
				listPreguntasAzar.get(3).setRespuesta_3(pregunta4.getRespuesta_3());
				
				listPreguntasAzar.get(4).setRespuesta_1(pregunta5.getRespuesta_1());
				listPreguntasAzar.get(4).setRespuesta_2(pregunta5.getRespuesta_2());
				listPreguntasAzar.get(4).setRespuesta_3(pregunta5.getRespuesta_3());
				
			
			}
			 
			 System.out.println(" MIRA ACA (se cambiaron de lugar las respuestas ):  ");
	         System.out.println(listPreguntasAzar);  
 	
	     break;
           case 3:
	
        	   for (int i = 0; i < 1; i++) {
   				
   				listPreguntasAzar.add(0, preguntas.get(2));
   				listPreguntasAzar.add(1, preguntas.get(4));
   				listPreguntasAzar.add(2, preguntas.get(3));
   				listPreguntasAzar.add(3, preguntas.get(0));
   				listPreguntasAzar.add(4, preguntas.get(1));
   				
   				
   			}
   			
   	         System.out.println(" MIRA ACA:  ");
   	         System.out.println(listPreguntasAzar);
   	         System.out.println(" ");

   			for (int j = 0; j < 1; j++) {
   				Pregunta pregunta1 = new Pregunta();
   				Pregunta pregunta2 = new Pregunta();
   				Pregunta pregunta3 = new Pregunta();
   				Pregunta pregunta4 = new Pregunta();
   				Pregunta pregunta5 = new Pregunta();
   				
   				pregunta1.setRespuesta_1(listPreguntasAzar.get(0).getRespuesta_1());
   				pregunta1.setRespuesta_2(listPreguntasAzar.get(0).getRespuesta_2());
   				pregunta1.setRespuesta_3(listPreguntasAzar.get(0).getRespuesta_3());
   				
   				pregunta2.setRespuesta_1(listPreguntasAzar.get(1).getRespuesta_3());
   				pregunta2.setRespuesta_2(listPreguntasAzar.get(1).getRespuesta_2());
   				pregunta2.setRespuesta_3(listPreguntasAzar.get(1).getRespuesta_1());
   				
   				pregunta3.setRespuesta_1(listPreguntasAzar.get(2).getRespuesta_2());
   				pregunta3.setRespuesta_2(listPreguntasAzar.get(2).getRespuesta_3());
   				pregunta3.setRespuesta_3(listPreguntasAzar.get(2).getRespuesta_1());
   				
   				pregunta4.setRespuesta_1(listPreguntasAzar.get(3).getRespuesta_1());
   				pregunta4.setRespuesta_2(listPreguntasAzar.get(3).getRespuesta_3());
   				pregunta4.setRespuesta_3(listPreguntasAzar.get(3).getRespuesta_2());
   				
   				pregunta5.setRespuesta_1(listPreguntasAzar.get(4).getRespuesta_1());
   				pregunta5.setRespuesta_2(listPreguntasAzar.get(4).getRespuesta_2());
   				pregunta5.setRespuesta_3(listPreguntasAzar.get(4).getRespuesta_3());
   				
   			
   				listPreguntasAzar.get(0).setRespuesta_1(pregunta1.getRespuesta_1());
   				listPreguntasAzar.get(0).setRespuesta_2(pregunta1.getRespuesta_2());
   				listPreguntasAzar.get(0).setRespuesta_3(pregunta1.getRespuesta_3());
   				
   				listPreguntasAzar.get(1).setRespuesta_1(pregunta2.getRespuesta_1());
   				listPreguntasAzar.get(1).setRespuesta_2(pregunta2.getRespuesta_2());
   				listPreguntasAzar.get(1).setRespuesta_3(pregunta2.getRespuesta_3());
   				
   				listPreguntasAzar.get(2).setRespuesta_1(pregunta3.getRespuesta_1());
   				listPreguntasAzar.get(2).setRespuesta_2(pregunta3.getRespuesta_2());
   				listPreguntasAzar.get(2).setRespuesta_3(pregunta3.getRespuesta_3());
   				
   				listPreguntasAzar.get(3).setRespuesta_1(pregunta4.getRespuesta_1());
   				listPreguntasAzar.get(3).setRespuesta_2(pregunta4.getRespuesta_2());
   				listPreguntasAzar.get(3).setRespuesta_3(pregunta4.getRespuesta_3());
   				
   				listPreguntasAzar.get(4).setRespuesta_1(pregunta5.getRespuesta_1());
   				listPreguntasAzar.get(4).setRespuesta_2(pregunta5.getRespuesta_2());
   				listPreguntasAzar.get(4).setRespuesta_3(pregunta5.getRespuesta_3());
   				
   			
   			}
   			 
   			 System.out.println(" MIRA ACA (se cambiaron de lugar las respuestas ):  ");
   	         System.out.println(listPreguntasAzar);  
   	         
         	break;

		default:
			break;
		}

		
		
		     
		     
		    // return preguntas;
		     return listPreguntasAzar;
	}


	
















}
