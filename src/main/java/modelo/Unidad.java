package modelo;

import javax.persistence.*;

@Entity
@Table(name = "Unidades")
public class Unidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "video_url")
	private String video_url;
	
	@Column(name = "unidad_completada")
	private Boolean completado;
	
	
	public Unidad() {}

	public Unidad(String descripcion, String video_url) {
		this.descripcion = descripcion;
		this.video_url = video_url;
		this.completado = false;
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

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public Boolean getCompletado() {
		return completado;
	}

	public void setCompletado(Boolean completado) {
		this.completado = completado;
	}

	@Override
	public String toString() {
		return "Unidad [id=" + id + ", descripcion=" + descripcion + ", video_url=" + video_url + ", completado="
				+ completado + "]";
	}

}
