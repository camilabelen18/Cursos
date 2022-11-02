package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "curso_unidad")
public class Curso_Unidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "unidad_id")
	private Unidad unidad;
	
	
	public Curso_Unidad() {}

	
	public Curso_Unidad(Curso curso, Unidad unidad) {
		this.curso = curso;
		this.unidad = unidad;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Curso getCurso() {
		return curso;
	}

	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	
	public Unidad getUnidad() {
		return unidad;
	}


	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}


	@Override
	public String toString() {
		return "Curso_Unidad [id=" + id + ", curso=" + curso + ", unidad=" + unidad + "]";
	}

}
