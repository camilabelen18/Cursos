package modelo;

import javax.persistence.*;

@Entity
@Table(name="Curso")
public class Curso {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Identificador")
	private Long id;
	@Column(name="Nombre")
	private String nombre;
	@Column(name="Descripcion")
	private String descripcion;
	@Column(name="Precio")
	private Double precio;
	@Column(name="Estado")
	private Estado estado;
	
	public Curso() {
	}

	public Curso(String nombre, String descripcion, Double precio, Estado estado) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", estado=" + estado + "]";
	}

	

}
