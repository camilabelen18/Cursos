package servicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServicioSubirImagen {
	
	private String directorio = System.getProperty("catalina.base");
	
	// Carpeta en la que se van a guardar las imagenes
	private String folder = directorio + "\\wtpwebapps\\proyecto-limpio-spring\\uploads\\";
	
	public String guardarImagen(MultipartFile foto) {
		
		String nombreArchivo;
		
		// Se verifica que el objeto 'foto' no este vacio
		if (!foto.isEmpty()) {
			
			// Se obtiene el nombre original del archivo
			nombreArchivo = foto.getOriginalFilename();
			
			try {
				// Se forma el nombre completo del archivo a guardar
				File imageFile = new File(folder + "/" + nombreArchivo);
			
				System.out.println(imageFile.getAbsolutePath());
			
				//guardamos fisicamente
				foto.transferTo(imageFile);
			}
			catch (IOException e) {
				
				System.out.println("Error: " + e.getMessage());
				nombreArchivo = "default-user.png";
			}
		}
		else {
			// Se establece el nombre de la imagen por defecto
			nombreArchivo = "default-user.png";
		}
		
		// Si no se envia ninguna imagen, entonces se devuelve el nombre de la imagen por defecto
		return nombreArchivo;
	}
	
	public void eliminarImagen(String nombre) {
		
		// Se establece la ruta de ubicacion de la imagen
		File imagen= new File(folder + "/" + nombre);
		
		// Se elimina la imagen del servidor apache
		imagen.delete();
	}

}