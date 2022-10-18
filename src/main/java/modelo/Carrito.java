package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Carrito")
public class Carrito {
	
	//Atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "carrito_curso",
			   joinColumns = @JoinColumn(name = "carrito_id"),
			   inverseJoinColumns = @JoinColumn(name = "curso_id"))
	private Set<Curso> cursosDelCarrito = new HashSet<Curso>(); 
	
	//Constructor
	public Carrito() { }
	
	public Carrito(String nombre) {
		this.nombre=nombre;
	}
	
   //Metodos
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

	public Set<Curso> getCursosDelCarrito() {
		return cursosDelCarrito;
	}

	public void setCursosDelCarrito(Set<Curso> cursosCarrito) {
		this.cursosDelCarrito = cursosCarrito;
	}
	

	@Override
	public String toString() {
		return "Carrito [id=" + id + ", nombre=" + nombre + "]";
	}
	

}
