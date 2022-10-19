package modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Carrito")
public class Carrito {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name="total")
	private Double total;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "carrito_curso",
			   joinColumns = @JoinColumn(name = "carrito_id"),
			   inverseJoinColumns = @JoinColumn(name = "curso_ident"))
	private Set<Curso> cursosDelCarrito = new HashSet<Curso>(); 
	

	public Carrito() { 
		this.cantidad = 0;
		this.total = 0.0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Set<Curso> getCursosDelCarrito() {
		return cursosDelCarrito;
	}

	public void setCursosDelCarrito(Set<Curso> cursosCarrito) {
		this.cursosDelCarrito = cursosCarrito;
	}

	@Override
	public String toString() {
		return "Carrito [id=" + id + ", cantidad=" + cantidad + ", total=" + total + "]";
	}

}
