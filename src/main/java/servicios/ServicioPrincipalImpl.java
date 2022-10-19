package servicios;

import org.springframework.transaction.annotation.Transactional;

import modelo.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("servicioPrincipal")
@Transactional
public class ServicioPrincipalImpl implements ServicioPrincipal {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertarRegistros() {
		
		Session sesion = sessionFactory.getCurrentSession();
		
		/* SE INSERTAN LOS REGISTROS DE TODOS LOS CURSOS DEL SISTEMA */
		
		sesion.save(new Curso("Curso PHP/MySql desde 0", "programacion", "En este curso se vera como crear aplicaciones y sitios web desde cero con PHP y MYSQL.", 3000.0, Estado.EN_VENTA, "php-desde-cero.jpg"));
		sesion.save(new Curso("Spring Framework 5", "programacion", "Construye aplicaciones web con Spring Framework 5 & Spring Boot: Thymeleaf, JPA, Security, REST, MySQL, Angular, WebFlux.", 2500.0, Estado.EN_VENTA, "curso-spring.jpg"));
		sesion.save(new Curso("Python 2022", "programacion", "En este curso aprenderas desde las bases de Python hacia temas más avanzados del lenguaje.", 2600.0, Estado.EN_VENTA, "curso-phyton.jpg"));
		
		sesion.save(new Curso("Adobe Photoshop: Curso completo", "diseno", "Aprende las herramientas esenciales de Adobe Photoshop para comenzar a diseñar hermosos graficos y fotos en Photoshop.", 1000.0, Estado.EN_VENTA, "adobe-photoshop.jpg"));
		sesion.save(new Curso("Curso completo de WordPress", "diseno", "El Mejor Curso de WordPress para aprender desde cero... ¡Mas de 5.000 alumnos satisfechos!", 800.0, Estado.EN_VENTA, "wordpress.jpg"));
		sesion.save(new Curso("Modelado y diseño para videojuegos", "diseno", "Aprende DESDE CERO a Modelar, Texturizar, Iluminacion y Render de modelos 2D 3D Assets Videojuegos MagicaVoxel y Unity", 3200.0, Estado.EN_VENTA, "diseño-videojuegos.jpg"));
		
		sesion.save(new Curso("Curso de guitarra practico para principiantes", "musica", "Aprende paso a paso a tocar la guitarra con este curso practico. Aprende tocando y olvídate de ejercicios aburridos.", 1650.0, Estado.EN_VENTA, "curso-guitarra.jpg"));
		sesion.save(new Curso("Curso de piano completo", "musica", "Aprendelo Todo: Armonia , Composicion, Improvisacion , Acompañar con acordes, Lectura & Solfeo, Tecnica y Relajacion.", 2000.0, Estado.EN_VENTA, "curso-piano.jpg"));
		sesion.save(new Curso("Curso de canto para principiantes", "musica", "¡Encuentra tu voz de canto de forma natural y diviértete haciéndolo! Un enfoque moderno para las clases de canto.", 2000.0, Estado.EN_VENTA, "curso-canto.jpg"));
		
		
		/* SE INSERTAN LOS REGISTROS DE TODOS LOS USUARIOS DEL SISTEMA */
		
		Usuario admin = new Usuario("Juan", "hola@hola.com","1234", "admin");
		admin.setNroTarjeta(555);
		
		Usuario cliente1 = new Usuario("Ana", "ana@gmail.com","111", "cliente");
		cliente1.setNroTarjeta(4407);
		cliente1.setCarrito(new Carrito());
		
		Usuario cliente2 = new Usuario("Ale", "ale@gmail.com","123", "cliente");
		cliente2.setNroTarjeta(5809);
		cliente2.setCarrito(new Carrito());
		
		sesion.save(admin);
		sesion.save(cliente1);
		sesion.save(cliente2);
	}

}
