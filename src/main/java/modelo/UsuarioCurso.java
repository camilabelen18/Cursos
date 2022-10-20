package modelo;

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

	@Override
	public String toString() {
		return "UsuarioCurso [id=" + id + ", usuario=" + usuario + ", curso=" + curso + "]";
	}

}
