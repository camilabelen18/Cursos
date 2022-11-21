package modelo;

import javax.persistence.*;

@Entity
@Table(name = "Pregunta")
public class Pregunta {
	
	//El modelo pregunta a su vez tiene N respuestas, es decir que va a tener un id, una descripcion de la pregunta y una lista de respuestas
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "respuesta_id_1")
	private Respuesta respuesta_1;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "respuesta_id_2")
	private Respuesta respuesta_2;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "respuesta_id_3")
	private Respuesta respuesta_3;
	

	public Pregunta() {
	}

	public Pregunta(String descripcion, Respuesta respuesta_1, Respuesta respuesta_2, Respuesta respuesta_3) {
		this.descripcion = descripcion;
		this.respuesta_1 = respuesta_1;
		this.respuesta_2 = respuesta_2;
		this.respuesta_3 = respuesta_3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Respuesta getRespuesta_1() {
		return respuesta_1;
	}

	public void setRespuesta_1(Respuesta respuesta_1) {
		this.respuesta_1 = respuesta_1;
	}

	public Respuesta getRespuesta_2() {
		return respuesta_2;
	}

	public void setRespuesta_2(Respuesta respuesta_2) {
		this.respuesta_2 = respuesta_2;
	}

	public Respuesta getRespuesta_3() {
		return respuesta_3;
	}

	public void setRespuesta_3(Respuesta respuesta_3) {
		this.respuesta_3 = respuesta_3;
	}

	@Override
	public String toString() {
		return "Pregunta [id=" + id + ", descripcion=" + descripcion + ", respuesta_1=" + respuesta_1 + ", respuesta_2="
				+ respuesta_2 + ", respuesta_3=" + respuesta_3 + "]";
	}

	

}
