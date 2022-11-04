package modelo;

import javax.persistence.Column;

public class DatosCreacionCurso {

	private String nombre;
	private String categoria;
	private String descripcion;
	private Double precio;
	private String imagen;
	
	
	public DatosCreacionCurso() {
		super();
	}

	public DatosCreacionCurso(String nombre, String categoria, String descripcion, Double precio, String imagen) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
