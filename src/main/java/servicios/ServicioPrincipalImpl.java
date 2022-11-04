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

	// Crear respositorio
	
	@Override
	public void insertarRegistros() {
		
		Session sesion = sessionFactory.getCurrentSession();

		/* SE INSERTAN LOS REGISTROS DE TODOS LOS CURSOS DEL SISTEMA */
		
		Curso c1 = new Curso("Curso PHP/MySql desde 0", "programacion", "En este curso se vera como crear aplicaciones y sitios web desde cero con PHP y MYSQL.", 3000.0, Estado.EN_VENTA, "php-desde-cero.jpg");
		Curso c2 = new Curso("Spring Framework 5", "programacion", "Construye aplicaciones web con Spring Framework 5 & Spring Boot: Thymeleaf, JPA, Security, REST, MySQL, Angular, WebFlux.", 2500.0, Estado.EN_VENTA, "curso-spring.jpg");
		Curso c3 = new Curso("Python 2022", "programacion", "En este curso aprenderas desde las bases de Python hacia temas más avanzados del lenguaje.", 2600.0, Estado.EN_VENTA, "curso-phyton.jpg");
		Curso c4 = new Curso("Adobe Photoshop: Curso completo", "diseno", "Aprende las herramientas esenciales de Adobe Photoshop para comenzar a diseñar hermosos graficos y fotos en Photoshop.", 1000.0, Estado.EN_VENTA, "adobe-photoshop.jpg");
		Curso c5 = new Curso("Curso completo de WordPress", "diseno", "El Mejor Curso de WordPress para aprender desde cero... ¡Mas de 5.000 alumnos satisfechos!", 800.0, Estado.EN_VENTA, "wordpress.jpg");
		Curso c6 = new Curso("Modelado y diseño para videojuegos", "diseno", "Aprende DESDE CERO a Modelar, Texturizar, Iluminacion y Render de modelos 2D 3D Assets Videojuegos MagicaVoxel y Unity", 3200.0, Estado.EN_VENTA, "diseño-videojuegos.jpg");
		Curso c7 = new Curso("Curso de guitarra practico para principiantes", "musica", "Aprende paso a paso a tocar la guitarra con este curso practico. Aprende tocando y olvídate de ejercicios aburridos.", 1650.0, Estado.EN_VENTA, "curso-guitarra.jpg");
		Curso c8 = new Curso("Curso de piano completo", "musica", "Aprendelo Todo: Armonia , Composicion, Improvisacion , Acompañar con acordes, Lectura & Solfeo, Tecnica y Relajacion.", 2000.0, Estado.EN_VENTA, "curso-piano.jpg");
		Curso c9 = new Curso("Curso de canto para principiantes", "musica", "¡Encuentra tu voz de canto de forma natural y diviértete haciéndolo! Un enfoque moderno para las clases de canto.", 2000.0, Estado.EN_VENTA, "curso-canto.jpg");
		
		sesion.save(c1);
		sesion.save(c2);
		sesion.save(c3);
		sesion.save(c4);
		sesion.save(c5);
		sesion.save(c6);
		sesion.save(c7);
		sesion.save(c8);
		sesion.save(c9);
		
		
		/* SE INSERTAN LOS REGISTROS DE TODOS LOS USUARIOS DEL SISTEMA */
		
		Usuario admin = new Usuario("Juan", "hola@hola.com","1234", "admin");
		admin.setNroTarjeta(555);
		
		Usuario cliente1 = new Usuario("Ana", "ana@gmail.com","111", "cliente");
		cliente1.setNroTarjeta(4407);
		Carrito car1 = new Carrito();
		car1.setUsuario(cliente1);
		
		Usuario cliente2 = new Usuario("Ale", "ale@gmail.com","123", "cliente");
		cliente2.setNroTarjeta(5809);
		Carrito car2 = new Carrito();
		car2.setUsuario(cliente2);

		sesion.save(admin);
		sesion.save(cliente1);
		sesion.save(cliente2);
		sesion.save(car1);
		sesion.save(car2);
		
		
		/* SE INSERTAN LOS REGISTROS DE TODAS LAS UNIDADES DE LOS CURSOS */
		
		Unidad un1 = new Unidad("Unidad 1: Presentación", "https://www.youtube.com/embed/I75CUdSJifw");
		Unidad un2 = new Unidad("Unidad 2: Instalación software del curso", "https://www.youtube.com/embed/tXxOAXP-gkg");
		Unidad un3 = new Unidad("Unidad 3: Primera página PHP", "https://www.youtube.com/embed/Ja9UVEgAzEw");
		Unidad un4 = new Unidad("Unidad 4: Variables y comentarios", "https://www.youtube.com/embed/IOdmCo_7U6s");
		
		Unidad un5 = new Unidad("Unidad 1: Introducción", "https://www.youtube.com/embed/eyWmkbReWLI");
		Unidad un6 = new Unidad("Unidad 2: ¿Qué es Spring Boot?", "https://www.youtube.com/embed/UvXktTs2bx0");
		Unidad un7 = new Unidad("Unidad 3: Instalación del Spring Tools IDE", "https://www.youtube.com/embed/9yAt3hAJ-vg");
		
		Unidad un8 = new Unidad("Unidad 1: Introducción", "https://www.youtube.com/embed/DAdRO6ByBoU");
		Unidad un9 = new Unidad("Unidad 2: Ejecución de un programa", "https://www.youtube.com/embed/UzHFkEdnRuc");
		Unidad un10 = new Unidad("Unidad 3: Variables en Python", "https://www.youtube.com/embed/w8VZxo1TlnU");
		
		Unidad un11 = new Unidad("Unidad 1: Introducción", "https://www.youtube.com/embed/4-VMuLs99Ss");
		Unidad un12 = new Unidad("Unidad 2: Tapon de Clonar y Parche", "https://www.youtube.com/embed/Y9yKFzACEHg");
		Unidad un13 = new Unidad("Unidad 3: Lazos y Tono/Saturación", "https://www.youtube.com/embed/HVBg6Fn2Rc0");
		
		Unidad un14 = new Unidad("Unidad 1: Introducción", "https://www.youtube.com/embed/OAWTixdQjqM");
		Unidad un15 = new Unidad("Unidad 2: Instalación en windows", "https://www.youtube.com/embed/BbRYgUiAsEQ");
		Unidad un16 = new Unidad("Unidad 3: Párrafos", "https://www.youtube.com/embed/48aCNffCJTU");
		
		Unidad un17 = new Unidad("Unidad 1: Introducción", "https://www.youtube.com/embed/Vf9PyoXPkjI");
		Unidad un18 = new Unidad("Unidad 2: Ejercicios", "https://www.youtube.com/embed/hdcCtgeKHqI");
		Unidad un19 = new Unidad("Unidad 3: Navegación Básica", "https://www.youtube.com/embed/HE_OXqM2ks8");
		
		Unidad un20 = new Unidad("Unidad 1: Las partes de la guitarra", "https://www.youtube.com/embed/L3_EGxOLcv0");
		Unidad un21 = new Unidad("Unidad 2: La postura correcta", "https://www.youtube.com/embed/QqB1vaicNxU");
		Unidad un22 = new Unidad("Unidad 3: Como tocar", "https://www.youtube.com/embed/yJ1Q_FK6pwY");
		
		Unidad un23 = new Unidad("Unidad 1: Introducción", "https://www.youtube.com/embed/kAALQ4JEY6c");
		Unidad un24 = new Unidad("Unidad 2: Leccion 2", "https://www.youtube.com/embed/LYuZIZ_LDKk");
		Unidad un25 = new Unidad("Unidad 3: Leccion 3", "https://www.youtube.com/embed/rkP-S0U7O1w");
		
		Unidad un26 = new Unidad("Unidad 1: Introducción", "https://www.youtube.com/embed/ciHP0DGppX4");
		Unidad un27 = new Unidad("Unidad 2: Respiración", "https://www.youtube.com/embed/utAoguWXYTc");
		Unidad un28 = new Unidad("Unidad 3: Ejercicios", "https://www.youtube.com/embed/hU89zIsQ9UE");
		
		sesion.save(un1);
		sesion.save(un2);
		sesion.save(un3);
		sesion.save(un4);
		sesion.save(un5);
		sesion.save(un6);
		sesion.save(un7);
		sesion.save(un8);
		sesion.save(un9);
		sesion.save(un10);
		sesion.save(un11);
		sesion.save(un12);
		sesion.save(un13);
		sesion.save(un14);
		sesion.save(un15);
		sesion.save(un16);
		sesion.save(un17);
		sesion.save(un18);
		sesion.save(un19);
		sesion.save(un20);
		sesion.save(un21);
		sesion.save(un22);
		sesion.save(un23);
		sesion.save(un24);
		sesion.save(un25);
		sesion.save(un26);
		sesion.save(un27);
		sesion.save(un28);
		
		sesion.save(new Curso_Unidad(c1, un1));
		sesion.save(new Curso_Unidad(c1, un2));
		sesion.save(new Curso_Unidad(c1, un3));
		sesion.save(new Curso_Unidad(c1, un4));
		sesion.save(new Curso_Unidad(c2, un5));
		sesion.save(new Curso_Unidad(c2, un6));
		sesion.save(new Curso_Unidad(c2, un7));
		sesion.save(new Curso_Unidad(c3, un8));
		sesion.save(new Curso_Unidad(c3, un9));
		sesion.save(new Curso_Unidad(c3, un10));
		sesion.save(new Curso_Unidad(c4, un11));
		sesion.save(new Curso_Unidad(c4, un12));
		sesion.save(new Curso_Unidad(c4, un13));
		sesion.save(new Curso_Unidad(c5, un14));
		sesion.save(new Curso_Unidad(c5, un15));
		sesion.save(new Curso_Unidad(c5, un16));
		sesion.save(new Curso_Unidad(c6, un17));
		sesion.save(new Curso_Unidad(c6, un18));
		sesion.save(new Curso_Unidad(c6, un19));
		sesion.save(new Curso_Unidad(c7, un20));
		sesion.save(new Curso_Unidad(c7, un21));
		sesion.save(new Curso_Unidad(c7, un22));
		sesion.save(new Curso_Unidad(c8, un23));
		sesion.save(new Curso_Unidad(c8, un24));
		sesion.save(new Curso_Unidad(c8, un25));
		sesion.save(new Curso_Unidad(c9, un26));
		sesion.save(new Curso_Unidad(c9, un27));
		sesion.save(new Curso_Unidad(c9, un28));
		
		
		// Nota: se insertan en total 70 registros en la base de datos al ejecutar el proyecto
		
		
	
		//NOTA Modificar si no funciona 
		//Registro de preguntas
		
		Pregunta preg1 = new Pregunta("�Permite php los atributos nulos ?");
		Pregunta preg2 = new Pregunta("�existen los boolean en php ?");
		Pregunta preg3 = new Pregunta("�el creador es steve jobs ?");
		
		sesion.save(preg1);
		sesion.save(preg2);
		sesion.save(preg3);      
		
	    //Registro de respuestas		
		Respuesta resp1 = new Respuesta("Si", true);
		Respuesta resp2 = new Respuesta("No", false);
		Respuesta resp3 = new Respuesta("Tal vez", false);
		Respuesta resp4 = new Respuesta("Si", true);
		Respuesta resp5 = new Respuesta("no", false);
		Respuesta resp6 = new Respuesta("tal ves", true);
		Respuesta resp7 = new Respuesta("Si", true);
		Respuesta resp8 = new Respuesta("no", false);
		Respuesta resp9 = new Respuesta("tal ves", false);
		
		sesion.save(resp1);
		sesion.save(resp2);
		sesion.save(resp3);
		sesion.save(resp4);
		sesion.save(resp5);
		sesion.save(resp6);
		sesion.save(resp7);
		sesion.save(resp8);
		sesion.save(resp9);
		
		//Registro de examenes, la union de preguntas, respuestas y el puntaje 
		Examen ex1 = new Examen(preg1,resp1);

		//Los guardados 
		sesion.save(ex1);
		
	    sesion.save(new Examen( preg1, resp2));
	    sesion.save(new Examen( preg1, resp3));
		sesion.save(new Examen( preg2, resp4));
		sesion.save(new Examen( preg2, resp5));
		sesion.save(new Examen( preg2, resp6));
		sesion.save(new Examen( preg3, resp7));
		sesion.save(new Examen( preg3, resp8));
		sesion.save(new Examen( preg3, resp9)); 

		//Registro de Curso con su examen correspondiente
		sesion.save(new Curso_Examen(c1,ex1 ));
		
		
		
		
		
		
		
		
		
		
	}

}
