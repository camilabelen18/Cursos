package modelo;

	import javax.persistence.Column;

	public class DatosActualizarCurso {
		
		private int id_curso;
		private String nombre;
		private String categoria;
		private String descripcion;
		private Double precio;
		private String imagen;
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getCategoria() {
			return categoria;
		}
		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public Double getPrecio() {
			return precio;
		}
		public void setPrecio(Double precio) {
			this.precio = precio;
		}
		public int getId_curso() {
			return id_curso;
		}
		public void setId_curso(int id_curso) {
			this.id_curso = id_curso;
		}

}
