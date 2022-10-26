package modelo;

import java.time.*;

import javax.persistence.*;


@Entity
@Table(name="usuario_curso")
public class UsuarioCurso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	// Almacena el id del usuario
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	// Almacena el id del curso
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "curso_id")
	private Curso curso;
	

	private LocalDate fecha_incio_compra;
	private LocalTime hora;
	
	
	public UsuarioCurso() {}

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

	@Override
	public String toString() {
		return "UsuarioCurso [id=" + id + ", usuario=" + usuario + ", curso=" + curso + "]";
	}

}
