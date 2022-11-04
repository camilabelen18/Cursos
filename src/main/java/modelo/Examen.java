package modelo;

import javax.persistence.*;

@Entity
@Table(name = "Examen")
public class Examen {
	//ESTA ES LA TABLA QUE UNE A LAS PREGUNTAS Y LAS RESPUESTAS, PORQUE ES EL EXAMEN 
	// Tiene las columnas de pregunta y respuesta, es la contenedora 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "puntaje")
	private double puntaje;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "pregunta_id")
	private Pregunta pregunta;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "respuesta_id")
	private Respuesta respuesta;
	
	
	public Examen () {}

	public Examen( Pregunta pregunta, Respuesta respuesta) {
		this.pregunta = pregunta;
		this.respuesta = respuesta;
	}



	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}

	@Override
	public String toString() {
		return "Examen [id=" + id + ", puntaje=" + puntaje + ", pregunta=" + pregunta + ", respuesta=" + respuesta
				+ "]";
	}


	

	
	
	
	
	

}
