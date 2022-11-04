package modelo;

import javax.persistence.*;


@Entity
@Table(name = "curso_examen")
public class Curso_Examen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "examen_id")
	private Examen examen;

	public Curso_Examen() {
	}

	public Curso_Examen(Curso curso, Examen examen) {
		this.curso = curso;
		this.examen = examen;
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

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	@Override
	public String toString() {
		return "Curso_Examen [id=" + id + ", curso=" + curso + ", examen=" + examen + "]";
	}
	
	
	
	
	
}
