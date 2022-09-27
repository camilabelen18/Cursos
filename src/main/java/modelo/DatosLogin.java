package modelo;

import javax.persistence.*;


public class DatosLogin {
	

	private Long id;
	private String nombre;
	private Integer nroTarjeta;
	private String password;
	private String email;
	
	public DatosLogin() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

	
	
	

}
