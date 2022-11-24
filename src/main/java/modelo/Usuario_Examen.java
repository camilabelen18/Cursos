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
@Table(name = "usuario_examen")
public class Usuario_Examen {
	
	//modelo resultadoExamen en donde esté el id del usuario, el id del examen que realizó, la fecha de cuando lo hizo y el puntaje que sacó. 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "usuario_id")
	private  Usuario usuario;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "examen_id")
	private Examen examen;
	
	@Column(name = "puntaje_final")
	private int puntaje_final;
	
	private LocalDate fecha_finalizacion_examen;
	private LocalTime hora_finalizacion_examen;
	
	public Usuario_Examen() {
		
	}
	
	public Usuario_Examen( Usuario usuario, Examen examen) {
		this.usuario = usuario;
		this.examen = examen;
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

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public int getPuntaje_final() {
		return puntaje_final;
	}

	public void setPuntaje_final(int puntaje_final) {
		this.puntaje_final = puntaje_final;
	}

	public LocalDate getFecha_finalizacion_examen() {
		return fecha_finalizacion_examen;
	}

	public void setFecha_finalizacion_examen(LocalDate fecha_finalizacion_examen) {
		this.fecha_finalizacion_examen = fecha_finalizacion_examen;
	}

	public LocalTime getHora_finalizacion_examen() {
		return hora_finalizacion_examen;
	}

	public void setHora_finalizacion_examen(LocalTime hora_finalizacion_examen) {
		this.hora_finalizacion_examen = hora_finalizacion_examen;
	}

	@Override
	public String toString() {
		return "Usuario_Examen [id=" + id + ", usuario=" + usuario + ", examen=" + examen + ", puntaje_final="
				+ puntaje_final + ", fecha_finalizacion_examen=" + fecha_finalizacion_examen
				+ ", hora_finalizacion_examen=" + hora_finalizacion_examen + "]";
	}
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
}
