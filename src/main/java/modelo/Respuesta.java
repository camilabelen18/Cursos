package modelo;

import javax.persistence.*;

@Entity
@Table(name = "Respuesta")
public class Respuesta {
	
	//El modelo de respuesta tiene un valor de id, una descripcion de la respuesta y si quieren un booleano para indicar que la respuesta es la correcta.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "respuesta_correcta")
	private Boolean respuesta_correcta;

	public Respuesta() {
		
	}

	public Respuesta(String descripcion, Boolean respuesta_correcta) {
		this.descripcion = descripcion;
		this.respuesta_correcta = respuesta_correcta;
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

	public Boolean getRespuesta_correcta() {
		return respuesta_correcta;
	}

	public void setRespuesta_correcta(Boolean respuesta_correcta) {
		this.respuesta_correcta = respuesta_correcta;
	}
	
	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", descripcion=" + descripcion + ", respuesta_correcta=" + respuesta_correcta
				+ "]";
	}
	

}
