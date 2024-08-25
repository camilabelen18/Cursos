package modelo;

public class DatosEditarUsuario {

    private String nombre;
    private String email;
    private String passwordAnterior;
    private String passwordNueva;

    public DatosEditarUsuario() { }

    public DatosEditarUsuario(String nombre, String email, String passwordAnterior, String passwordNueva) {
        this.nombre = nombre;
        this.email = email;
        this.passwordAnterior = passwordAnterior;
        this.passwordNueva = passwordNueva;
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

    @Override
    public String toString() {
        return "DatosEditarUsuario [nombre=" + nombre + ", email=" + email + ", passwordAnterior=" + passwordAnterior
                + ", passwordNueva=" + passwordNueva + "]";
    }

}
