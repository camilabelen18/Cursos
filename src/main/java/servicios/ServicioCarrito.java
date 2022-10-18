package servicios;

import modelo.Carrito;
import modelo.Curso;

public interface ServicioCarrito {

  void guardarCursoEnListaDeCarrito(Curso curso_obtenido);

  Carrito buscarCarritoPorId(int id_carrito);
  
}
