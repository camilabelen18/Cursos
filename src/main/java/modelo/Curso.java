package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Cursos")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Identificador")
	private int id;
	
	@Column(name = "Nombre")
	private String nombre;
	
	// Crear entidad "categoria"
	@Column(name = "Categoria")
	private String categoria;
	
	@Column(name = "Descripcion")
	private String descripcion;
	
	@Column(name = "Precio")
	private Double precio;
	
	// Crear entidad "estado"
	@Column(name = "Estado")
	private Estado estado;
	
	@Column(name = "Imagen")
	private String imagen;
	
	@Column(name = "progreso")
	private Double progreso;
	
	@Column(name = "curso_completado")
	private Boolean cursoTerminado;
	
	public Curso() { 
		this.progreso = 0.00;
		this.cursoTerminado = false;
	}

	public Curso(String nombre, String categoria, String descripcion, Double precio, Estado estado, String imagen) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.imagen = imagen;
		this.progreso = 0.00;
		this.cursoTerminado = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getProgreso() {
		return progreso;
	}

	public void setProgreso(Double progreso) {
		this.progreso = progreso;
	}

	public Boolean getCursoTerminado() {
		return cursoTerminado;
	}

	public void setCursoTerminado(Boolean cursoTerminado) {
		this.cursoTerminado = cursoTerminado;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", estado=" + estado + ", imagen=" + imagen + ", progreso=" + progreso
				+ ", cursoTerminado=" + cursoTerminado + "]";
	}
	
}
