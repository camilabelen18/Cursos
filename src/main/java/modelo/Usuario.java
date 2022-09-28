package modelo;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Identificador")
	private Long id;
	
	@Column(name="Nombre")
	private String nombre;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Nro")
	private Integer nroTarjeta;
	
	@OneToMany
	@Column(name="Cursos")
	private List<Curso> listaCursos;//guarda lo que compro el usuario
	
	public Usuario() { }

	public Usuario(String nombre, String email, String password, Integer nroTarjeta) {
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.nroTarjeta = nroTarjeta;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(Integer nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password
				+ ", nroTarjeta=" + nroTarjeta + "]";
	}
	
}
