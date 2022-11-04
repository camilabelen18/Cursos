package servicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServicioSubirImagen {
	
	// Carpeta en la que se van a guardar las imagenes
	//private String folder = "\\Documents\\GitHub\\Cursos\\subidas\\";
	private String folder = "\\subidas\\";
	
	public String guardarImagen(MultipartFile foto) throws IOException {
		
		// Se verifica que el objeto 'foto' no este vacio
		if (!foto.isEmpty()) {
			
			// Se obtiene la imagen en bytes
			byte bytes[] = foto.getBytes();
		
			//String ubicacionProyecto = System.getProperty("user.home");
			String ubicacionProyecto = System.getProperty("user.dir");
			
			// Ruta de como se van a guardar las imagenes
			Path path = Paths.get(ubicacionProyecto + folder + foto.getOriginalFilename());
			
			System.out.println(path.toAbsolutePath());
			
			// Se guarda la imagen en la carpeta '/subidas/'
			Files.write(path, bytes);
			
			// Devuelve el nombre de la imagen
			return foto.getOriginalFilename();
		}
		
		// Si no se envia ninguna imagen, entonces se devuelve el nombre de la imagen por defecto
		return "default-user.png";
	}
	
	public void eliminarImagen(String nombre) {
		
		// Se establece la ruta de ubicacion de la imagen
		File imagen= new File(folder + nombre);
		
		// Se elimina la imagen del servidor apache
		imagen.delete();
	}

}
