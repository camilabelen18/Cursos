package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "curso_examen")
public class Curso_Examen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "examen_id")
	private Examen examen;
	
	/*
	@Column(name = "puntaje_final")
	private int puntaje_final;
	
	private LocalDate fecha_incio_examen;
	private LocalTime hora_inicio_examen;
    */
	public Curso_Examen() {
	}

	public Curso_Examen(Curso curso, Examen examen) {
		this.curso = curso;
		this.examen = examen;
	}
	
 /*
	public int getPuntaje_final() {
		return puntaje_final;
	}

	public void setPuntaje_final(int puntaje_final) {
		this.puntaje_final = puntaje_final;
	}
	*/

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
