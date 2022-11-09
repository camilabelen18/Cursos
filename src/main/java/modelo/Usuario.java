package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Identificador")
	private int id;
	
	@Column(name = "Nombre")
	private String nombre;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "NumeroTarjeta")
	private Integer nroTarjeta;
	
	@Column(name = "Rol")
	private String rol;
	
	@Column(name = "Imagen")
	private String imagen;
	
	@OneToOne
	private Giftcard giftcard;
	
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Giftcard getGiftcard() {
		return giftcard;
	}

	public void setGiftcard(Giftcard giftcard) {
		this.giftcard = giftcard;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password
				+ ", nroTarjeta=" + nroTarjeta + ", rol=" + rol + ", imagen=" + imagen + ", giftcard=" + giftcard + "]";
	}
	
}
