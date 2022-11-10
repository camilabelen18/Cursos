package modelo;

public class DatosEditarUsuario {

	private String nombre;
	private String email;
	private String passwordAnterior;
	private String passwordNueva;
	private String repeticionPasswordNueva;
	
	public DatosEditarUsuario() {
	}
	
	

	public DatosEditarUsuario(String nombre, String email, String passwordAnterior, String passwordNueva,
			String repeticionPasswordNueva) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.passwordAnterior = passwordAnterior;
		this.passwordNueva = passwordNueva;
		this.repeticionPasswordNueva = repeticionPasswordNueva;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordAnterior() {
		return passwordAnterior;
	}

	public void setPasswordAnterior(String passwordAnterior) {
		this.passwordAnterior = passwordAnterior;
	}

	public String getPasswordNueva() {
		return passwordNueva;
	}

	public void setPasswordNueva(String passwordNueva) {
		this.passwordNueva = passwordNueva;
	}

	public String getRepeticionPasswordNueva() {
		return repeticionPasswordNueva;
	}

	public void setRepeticionPasswordNueva(String repeticionPasswordNueva) {
		this.repeticionPasswordNueva = repeticionPasswordNueva;
	}

	@Override
	public String toString() {
		return "DatosEditarUsuario [nombre=" + nombre + ", email=" + email + ", passwordAnterior=" + passwordAnterior
				+ ", passwordNueva=" + passwordNueva + ", repeticionPasswordNueva=" + repeticionPasswordNueva + "]";
	}
	
	
	
}
