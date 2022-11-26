package modelo;

import javax.persistence.*;

@Entity
@Table(name="Notificaciones")
public class Notificacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Identificador")
	private int id;
	
	@Column(name = "Mensaje")
	private String mensaje;
	
	public Notificacion(String mensaje) {
		this.mensaje = mensaje;
	}

	public Notificacion() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Notificacion [id=" + id + ", mensaje=" + mensaje + "]";
	}
	
}
