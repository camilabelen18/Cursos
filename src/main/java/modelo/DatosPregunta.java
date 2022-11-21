package modelo;

/* Esta clase se va a utilizar para enviar datos de una vista a un controlador mediante un objeto */

public class DatosPregunta {
	
	private String descripcion;
	private Pregunta pregunta;
	private Respuesta respuesta_1;
    private Respuesta respuesta_2;
	private Respuesta respuesta_3;
	private int preguntaId;
	private int respuestaElegida; //id de la respuesta
	
	public DatosPregunta() { 
		
	}
	
	public DatosPregunta(String descripcion, Respuesta respuesta_1, Respuesta respuesta_2, Respuesta respuesta_3) {
		this.descripcion = descripcion;
		this.respuesta_1 = respuesta_1;
		this.respuesta_2 = respuesta_2;
		this.respuesta_3 = respuesta_3;
	}

	public DatosPregunta(Pregunta pregunta, Respuesta respuesta_1, Respuesta respuesta_2, Respuesta respuesta_3) {
		this.pregunta = pregunta;
		this.respuesta_1 = respuesta_1;
		this.respuesta_2 = respuesta_2;
		this.respuesta_3 = respuesta_3;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
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

	public int getPreguntaId() {
		return preguntaId;
	}

	public void setPreguntaId(int preguntaId) {
		this.preguntaId = preguntaId;
	}

	public int getRespuestaElegida() {
		return respuestaElegida;
	}

	public void setRespuestaElegida(int respuestaElegida) {
		this.respuestaElegida = respuestaElegida;
	}

	@Override
	public String toString() {
		return "DatosPregunta [descripcion=" + descripcion + ", respuesta_1=" + respuesta_1 + ", respuesta_2="
				+ respuesta_2 + ", respuesta_3=" + respuesta_3 + ", preguntaId=" + preguntaId + ", respuestaElegida="
				+ respuestaElegida + "]";
	}

	

	
	
	
}
