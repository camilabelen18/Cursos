package modelo;

import javax.persistence.*;

@Entity
@Table(name="Usuario_Notificacion")
public class Usuario_Notificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	// Almacena el id del usuario
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	// Almacena el id de notificacion
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
	@JoinColumn(name = "notificacion_id")
	private Notificacion notificacion;
	
	@Column(name = "notificacion_leida")
	private Boolean notificacionLeida;
	
	@Column(name = "notificacion_quitada")
	private Boolean notificacionQuitada;


	public Usuario_Notificacion(Usuario usuario, Notificacion notificacion) {
		this.usuario = usuario;
		this.notificacion = notificacion;
		this.notificacionLeida = false;
		this.notificacionQuitada = false;
	}

	public Usuario_Notificacion() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}
	
	public Boolean getNotificacionLeida() {
		return notificacionLeida;
	}

	public void setNotificacionLeida(Boolean notificacionLeida) {
		this.notificacionLeida = notificacionLeida;
	}

	public Boolean getNotificacionQuitada() {
		return notificacionQuitada;
	}

	public void setNotificacionQuitada(Boolean notificacionQuitada) {
		this.notificacionQuitada = notificacionQuitada;
	}

	@Override
	public String toString() {
		return "Usuario_Notificacion [id=" + id + ", usuario=" + usuario + ", notificacion=" + notificacion
				+ ", notificacionLeida=" + notificacionLeida + ", notificacionQuitada=" + notificacionQuitada + "]";
	}
	
}
