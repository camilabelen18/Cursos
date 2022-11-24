package modelo;

import java.time.*;

import javax.persistence.*;


@Entity
@Table(name="usuario_curso")
public class Usuario_Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	// Almacena el id del usuario
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	// Almacena el id del curso
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@Column(name = "progreso")
	private Double progreso;
	
	@Column(name = "curso_completado")
	private Boolean cursoTerminado;
	
	@Column(name = "Estado")
	private Estado estado;
	
	@Column(name = "fecha_compra")
	private LocalDate fecha_incio_compra;
	
	@Column(name = "hora_compra")
	private LocalTime hora;
	
	
	public Usuario_Curso() {
		this.progreso = 0.0;
		this.cursoTerminado = false;
	}

	public Usuario_Curso(Usuario usuario, Curso curso) {
		this.usuario = usuario;
		this.curso = curso;
		this.progreso = 0.0;
		this.cursoTerminado = false;
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
		

	public LocalDate getFecha_incio_compra() {
		return fecha_incio_compra;
	}

	public void setFecha_incio_compra(LocalDate fecha_incio_compra) {
		this.fecha_incio_compra = fecha_incio_compra;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Double getProgreso() {
		return progreso;
	}

	public void setProgreso(Double progreso) {
		this.progreso = progreso;
	}

	public Boolean getCursoTerminado() {
		return cursoTerminado;
	}

	public void setCursoTerminado(Boolean cursoTerminado) {
		this.cursoTerminado = cursoTerminado;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Usuario_Curso [id=" + id + ", usuario=" + usuario + ", curso=" + curso + ", progreso=" + progreso
				+ ", cursoTerminado=" + cursoTerminado + ", estado=" + estado + ", fecha_incio_compra="
				+ fecha_incio_compra + ", hora=" + hora + "]";
	}

}
