package modelo;

import javax.persistence.*;

@Entity
@Table(name = "Examen")
public class Examen {
	
	//El examen está formado por N preguntas, es decir que el modelo de examen deberia tener un id y una lista de preguntas. 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "pregunta_id_1")
	private Pregunta pregunta_1;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "pregunta_id_2")
	private Pregunta pregunta_2;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "pregunta_id_3")
	private Pregunta pregunta_3;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "pregunta_id_4")
	private Pregunta pregunta_4;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "pregunta_id_5")
	private Pregunta pregunta_5;
	
	public Examen () {
		
	}

	public Examen(Pregunta pregunta_1, Pregunta pregunta_2, Pregunta pregunta_3, Pregunta pregunta_4,
			Pregunta pregunta_5) {
		this.pregunta_1 = pregunta_1;
		this.pregunta_2 = pregunta_2;
		this.pregunta_3 = pregunta_3;
		this.pregunta_4 = pregunta_4;
		this.pregunta_5 = pregunta_5;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pregunta getPregunta_1() {
		return pregunta_1;
	}

	public void setPregunta_1(Pregunta pregunta_1) {
		this.pregunta_1 = pregunta_1;
	}

	public Pregunta getPregunta_2() {
		return pregunta_2;
	}

	public void setPregunta_2(Pregunta pregunta_2) {
		this.pregunta_2 = pregunta_2;
	}

	public Pregunta getPregunta_3() {
		return pregunta_3;
	}

	public void setPregunta_3(Pregunta pregunta_3) {
		this.pregunta_3 = pregunta_3;
	}

	public Pregunta getPregunta_4() {
		return pregunta_4;
	}

	public void setPregunta_4(Pregunta pregunta_4) {
		this.pregunta_4 = pregunta_4;
	}

	public Pregunta getPregunta_5() {
		return pregunta_5;
	}

	public void setPregunta_5(Pregunta pregunta_5) {
		this.pregunta_5 = pregunta_5;
	}

	@Override
	public String toString() {
		return "Examen [id=" + id + ", pregunta_1=" + pregunta_1 + ", pregunta_2=" + pregunta_2 + ", pregunta_3="
				+ pregunta_3 + ", pregunta_4=" + pregunta_4 + ", pregunta_5=" + pregunta_5 + "]";
	}
	
	

	

}
