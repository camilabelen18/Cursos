package modelo;

public class DatosRegistro {

	private String nombre;
    private String email;
    private String contrasenia;
    private String repetirContrasenia;
    
    public DatosRegistro() {}

    public DatosRegistro(String nombre, String email, String contrasenia, String repetirContrasenia) {
		this.nombre = nombre;
		this.email = email;
		this.contrasenia = contrasenia;
		this.repetirContrasenia = repetirContrasenia;
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
    
    public String getContrasenia() {
        return contrasenia;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public String getRepetirContrasenia() {
        return repetirContrasenia;
    }
    
    public void setRepetirContrasenia(String repetirContrasenia) {
        this.repetirContrasenia = repetirContrasenia;
    }

}
