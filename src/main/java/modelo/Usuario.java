package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Identificador")
	private int id;
	
	@Column(name="Nombre")
	private String nombre;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="NumeroTarjeta")
	private Integer nroTarjeta;
	
	@Column(name="Rol")
	private String rol;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_curso",
			   joinColumns = @JoinColumn(name = "usuario_id"),
			   inverseJoinColumns = @JoinColumn(name = "curso_id"))
	private List<Curso> misCursos = new ArrayList<Curso>();
	
	public Usuario() { }

	public Usuario(String nombre, String email, String password, String rol) {
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.rol  = rol;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Curso> getMisCursos() {
		return misCursos;
	}

	public void setMisCursos(List<Curso> misCursos) {
		this.misCursos = misCursos;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password
				+ ", nroTarjeta=" + nroTarjeta + ", rol=" + rol + "]";
	}
	
}
