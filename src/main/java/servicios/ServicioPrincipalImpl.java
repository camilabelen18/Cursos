package servicios;

import org.springframework.transaction.annotation.Transactional;

import modelo.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
		
		Curso c1 = new Curso("Curso PHP/MySql desde 0", "programacion", "En este curso se vera como crear aplicaciones y sitios web desde cero con PHP y MYSQL.", 3000.0, "php-desde-cero.jpg");
		Curso c2 = new Curso("Spring Framework 5", "programacion", "Construye aplicaciones web con Spring Framework 5 & Spring Boot: Thymeleaf, JPA, Security, REST, MySQL, Angular, WebFlux.", 2500.0, "curso-spring.jpg");
		Curso c3 = new Curso("Python 2022", "programacion", "En este curso aprenderas desde las bases de Python hacia temas más avanzados del lenguaje.", 2600.0, "curso-phyton.jpg");
		Curso c4 = new Curso("Adobe Photoshop: Curso completo", "diseno", "Aprende las herramientas esenciales de Adobe Photoshop para comenzar a diseñar hermosos graficos y fotos en Photoshop.", 1000.0, "adobe-photoshop.jpg");
		Curso c5 = new Curso("Curso completo de WordPress", "diseno", "El Mejor Curso de WordPress para aprender desde cero... ¡Mas de 5.000 alumnos satisfechos!", 800.0, "wordpress.jpg");
		Curso c6 = new Curso("Modelado y diseño para videojuegos", "diseno", "Aprende DESDE CERO a Modelar, Texturizar, Iluminacion y Render de modelos 2D 3D Assets Videojuegos MagicaVoxel y Unity", 3200.0, "diseño-videojuegos.jpg");
		Curso c7 = new Curso("Curso de guitarra practico para principiantes", "musica", "Aprende paso a paso a tocar la guitarra con este curso practico. Aprende tocando y olvídate de ejercicios aburridos.", 1650.0, "curso-guitarra.jpg");
		Curso c8 = new Curso("Curso de piano completo", "musica", "Aprendelo Todo: Armonia , Composicion, Improvisacion , Acompañar con acordes, Lectura & Solfeo, Tecnica y Relajacion.", 2000.0, "curso-piano.jpg");
		Curso c9 = new Curso("Curso de canto para principiantes", "musica", "¡Encuentra tu voz de canto de forma natural y diviértete haciéndolo! Un enfoque moderno para las clases de canto.", 2000.0, "curso-canto.jpg");
		
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
		
		Giftcard gift1 = new Giftcard(555, 0, 0.0);
		Giftcard gift2 = new Giftcard(555, 0, 0.0);
		Giftcard gift3 = new Giftcard(555, 10000, 1000.0);
		
		Usuario admin = new Usuario("Juan", "admin@gmail.com","123", "admin");
		admin.setNroTarjeta(555);
		admin.setGiftcard(gift1);
		admin.setImagen("default-user.png");
		
		Usuario cliente1 = new Usuario("Ana", "ana@gmail.com","111", "cliente");
		cliente1.setNroTarjeta(4407);
		cliente1.setGiftcard(gift2);
		cliente1.setImagen("default-user.png");
		
		Usuario cliente2 = new Usuario("Ale", "ale@gmail.com","123", "cliente");
		cliente2.setNroTarjeta(5809);
		cliente2.setGiftcard(gift3);
		cliente2.setImagen("default-user.png");
		
		Carrito car1 = new Carrito();
		car1.setUsuario(cliente1);
		
		Carrito car2 = new Carrito();
		car2.setUsuario(cliente2);

		sesion.save(gift1);
		sesion.save(gift2);
		sesion.save(gift3);
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
		un11.setCompletado(true);
		un12.setCompletado(true);
		un13.setCompletado(true);
		
		Unidad un14 = new Unidad("Unidad 1: Introducción", "https://www.youtube.com/embed/OAWTixdQjqM");
		Unidad un15 = new Unidad("Unidad 2: Instalación en windows", "https://www.youtube.com/embed/BbRYgUiAsEQ");
		Unidad un16 = new Unidad("Unidad 3: Párrafos", "https://www.youtube.com/embed/48aCNffCJTU");
		un14.setCompletado(true);
		un15.setCompletado(true);
		un16.setCompletado(true);
		
		Unidad un17 = new Unidad("Unidad 1: Introducción", "https://www.youtube.com/embed/Vf9PyoXPkjI");
		Unidad un18 = new Unidad("Unidad 2: Ejercicios", "https://www.youtube.com/embed/hdcCtgeKHqI");
		Unidad un19 = new Unidad("Unidad 3: Navegación Básica", "https://www.youtube.com/embed/HE_OXqM2ks8");
		
		Unidad un20 = new Unidad("Unidad 1: Las partes de la guitarra", "https://www.youtube.com/embed/L3_EGxOLcv0");
		Unidad un21 = new Unidad("Unidad 2: La postura correcta", "https://www.youtube.com/embed/QqB1vaicNxU");
		Unidad un22 = new Unidad("Unidad 3: Como tocar", "https://www.youtube.com/embed/yJ1Q_FK6pwY");
		un20.setCompletado(true);
		un21.setCompletado(true);
		un22.setCompletado(true);
		
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
		

		
		
		//Registro de preguntas y respuestas PhP MySql
		
		  //Registro de respuestas	de php mysql 	
				Respuesta resp1 = new Respuesta("Si, se ejecuta en un servidor ", true);
				Respuesta resp2 = new Respuesta("No, se ejecuta en el lado del cliente", false);
				Respuesta resp3 = new Respuesta("Se puede ejecutar en el lado cliente como en el lado servidor", true);
				Respuesta resp4 = new Respuesta("Si, se ejecuta en el lado del cliente", true);
				Respuesta resp5 = new Respuesta("No, se ejecuta en el lado del servidor", false);
				Respuesta resp6 = new Respuesta("Se puede ejecutar en el lado cliente como en el lado servidor", true);
				Respuesta resp7 = new Respuesta("Si, en la carpeta que guarde por defecto el programa utilizado", true);
				Respuesta resp8 = new Respuesta("No, lo podes guardar donde quieras ", false);
				Respuesta resp9 = new Respuesta("Si, en la carpeta www ", true);
				Respuesta resp10 = new Respuesta("Si, ya que es el primero que quieres que vean los visitantes al ingresar a tu pag", true);
				Respuesta resp11 = new Respuesta("No, es indiferente ", false);
				Respuesta resp12 = new Respuesta("Si, pero lo podes llamar como quieras igual ", true);
				Respuesta resp13 = new Respuesta("Si, puede ser de cualquier tipo de valor", true);
				Respuesta resp14 = new Respuesta("Si, solo puede almacer numeros enteros ", false);
				Respuesta resp15 = new Respuesta("Si, puede almacenar tipos de datos como int, boolean, String y float", true);

				
				sesion.save(resp1);
				sesion.save(resp2);
				sesion.save(resp3);
				sesion.save(resp4);
				sesion.save(resp5);
				sesion.save(resp6);
				sesion.save(resp7);
				sesion.save(resp8);
				sesion.save(resp9);
				sesion.save(resp10);
				sesion.save(resp11);
				sesion.save(resp12);
				sesion.save(resp13);
				sesion.save(resp14);
				sesion.save(resp15);
				
		
		//Registro de preguntas PhP MySql
		Pregunta preg1 = new Pregunta("Php es un lenguaje que se ejecuta en un servidor ?",resp1,resp2,resp3);
		Pregunta preg2 = new Pregunta("JavaScript se ejecuta en el lado del cliente ?",resp4,resp5,resp6);
		Pregunta preg3 = new Pregunta("Las paginas php se guardan en una carpeta especifica ?",resp7,resp8,resp9);
		Pregunta preg4 = new Pregunta("El nombre de index se lo da a una archivo cuando se lo considera pagina principal ?",resp10,resp11,resp12);
		Pregunta preg5 = new Pregunta("La variable es un espacio  en la memoria del ordenador donde se almacena un valor ?",resp13,resp14,resp15);
		
		sesion.save(preg1);
		sesion.save(preg2);
		sesion.save(preg3);   
		sesion.save(preg4);  
		sesion.save(preg5);  
		
	  
		//Registro de examen
	     Examen examen1 = new Examen(preg1,preg2,preg3,preg4,preg5);
	     
	     sesion.save(examen1);

		//Registro de Curso con su examen correspondiente
	    //Registro de PhpMySql y su examen
		sesion.save(new Curso_Examen(c1,examen1 ));

		//Registro del usuario y su examen ??? 
       //  Usuario_Examen usuario_examen1 = new Usuario_Examen(cliente1, examen1);
		
       //     sesion.save(usuario_examen1);
		
		
		
		//Registro de respuestas de spring framework 5	
		Respuesta resp16 = new Respuesta("Si, y busca facilitarnos la creaci�n de proyectos",true );
		Respuesta resp17 = new Respuesta("No, son diferentes ",false );
		Respuesta resp18 = new Respuesta("Si, podes crear proyectos que funcionen fuera de Spring Framework", true);
		Respuesta resp19 = new Respuesta("Si, ya que Spring boot provee configuraci�n para las tecnolog�as mas usadas",true );
		Respuesta resp20 = new Respuesta("No, uno tiene que configurar todo ",false  );
		Respuesta resp21 = new Respuesta("Si, puede que tenga para algunas cosas pero no para las mas importantes", true);
		Respuesta resp22 = new Respuesta("Si, utiliza Stand Alone ",true );
		Respuesta resp23 = new Respuesta("No, es necesario conectar un servidor ",false  );
		Respuesta resp24 = new Respuesta("Podes usar el tomCat ",true );
		Respuesta resp25 = new Respuesta("Si", true);
		Respuesta resp26 = new Respuesta("No", false );
		Respuesta resp27 = new Respuesta("Es tradicional por eso es parecido ",true );
		Respuesta resp28 = new Respuesta("Si", true);
		Respuesta resp29 = new Respuesta("No", false );
		Respuesta resp30 = new Respuesta("Tiene algunas cosas que son de eclipse ",true );
		
		sesion.save(resp16);
		sesion.save(resp17);
		sesion.save(resp18);
		sesion.save(resp19);
		sesion.save(resp20);
		sesion.save(resp21);
		sesion.save(resp22);
		sesion.save(resp23);
		sesion.save(resp24);
		sesion.save(resp25);
		sesion.save(resp26);
		sesion.save(resp27);
		sesion.save(resp28);
		sesion.save(resp29);
		sesion.save(resp30);
	
	 	
	 	//Registro de preguntas Spring FrameWork 5
	 	Pregunta preg6 = new Pregunta("Spring boot es un subproyecto de Spring Framework  ?",resp16,resp17,resp18);
	 	Pregunta preg7 = new Pregunta("Con Spring boot una ya empieza con configuraciones por defecto ?",resp19,resp20,resp21);
	 	Pregunta preg8 = new Pregunta("Podes crear aplicaciones independientes sin usar servidor web ?",resp22,resp23,resp24);
	 	Pregunta preg9 = new Pregunta("Spring boot provee un modelo de programaci�n parecido a las aplicaciones java ?",resp25,resp26,resp27);
	 	Pregunta preg10 = new Pregunta("Spring tool es un ide basado en eclipse ?",resp28,resp29,resp30);
		
	 	sesion.save(preg6);
		sesion.save(preg7);
		sesion.save(preg8);   
		sesion.save(preg9);  
		sesion.save(preg10);  
		

		
		//Registro de examenes, la union de preguntas, respuestas 
	     Examen examen6 = new Examen(preg6,preg7,preg8,preg9,preg10);

	    sesion.save(examen6);

		
	  //Registro de Curso con su examen correspondiente
	    //Registro de Spring FrameWork 5 y sus examenes
		sesion.save(new Curso_Examen(c2,examen6 ));
	
		//Registro de respuestas de python	
			    Respuesta resp31 = new Respuesta("Si, se ejecuta sin necesidad de ser procesado por el compilador",true );
				Respuesta resp32 = new Respuesta("No ",false );
				Respuesta resp33 = new Respuesta("Puede ser interpretado ", true);
				Respuesta resp34 = new Respuesta("Es un traductor de codigo fuente a lenguaje maquina ",true );
				Respuesta resp35 = new Respuesta(" Es un programa que pasa el lenguaje maquina a codigo fuente  ",false  );
				Respuesta resp36 = new Respuesta(" Es algo que permite ejecutar el codigo fuente ", true);
				Respuesta resp37 = new Respuesta("Si, es aconsejable ponerla aunque ya sea de ese tipo ",true );
				Respuesta resp38 = new Respuesta("No, sin la extensi�n esta bien ",false  );
				Respuesta resp39 = new Respuesta("No es necesario ya que es de tipo python ",true );
				Respuesta resp40 = new Respuesta("Es un espacio en memoria donde se guardan y recuperan los datos que se utiliza", true);
				Respuesta resp41 = new Respuesta(" Es una ejecucion dentro de un programa", false );
				Respuesta resp42 = new Respuesta("Es algo que es necesario este en la memoria de un programa ",true );
				Respuesta resp43 = new Respuesta("Si", true);
				Respuesta resp44 = new Respuesta("No", false );
				Respuesta resp45 = new Respuesta("Tal vez si las separa un car�cter diferente",true );
				
				sesion.save(resp31);
				sesion.save(resp32);
				sesion.save(resp33);
				sesion.save(resp34);
				sesion.save(resp35);
				sesion.save(resp36);
				sesion.save(resp37);
				sesion.save(resp38);
				sesion.save(resp39);
				sesion.save(resp40);
				sesion.save(resp41);
				sesion.save(resp42);
				sesion.save(resp43);
				sesion.save(resp44);
				sesion.save(resp45);
		
		//Registro de preguntas Python 22
	 	Pregunta preg11 = new Pregunta("Python es interpretado  ?",resp31,resp32,resp33);
	 	Pregunta preg12 = new Pregunta("Que es un compilador ?",resp34,resp35,resp36);
	 	Pregunta preg13 = new Pregunta("Es correcto poner la extension de python al archivo guardado ?",resp37,resp38,resp39);
	 	Pregunta preg14 = new Pregunta("Que es una variable ?",resp40,resp41,resp42);
	 	Pregunta preg15 = new Pregunta("Python puede diferenciar mayusculas de minusculas  ?",resp43,resp44,resp45);
		
	 	sesion.save(preg11);
		sesion.save(preg12);
		sesion.save(preg13);   
		sesion.save(preg14);  
		sesion.save(preg15);  
		
		
		
		//Registro de examenes, la union de preguntas, respuestas 
	     Examen examen11 = new Examen(preg11,preg12,preg13,preg14,preg15);
	
	  
	   
	    sesion.save(examen11);
	
		
	  //Registro de Curso con su examen correspondiente
	    //Registro de Python y sus examenes
		sesion.save(new Curso_Examen(c3,examen11 ));

		
		
		//Registro de respuestas de adobe photoshop	
		Respuesta resp46 = new Respuesta("Si, estan por defecto en la izquierda",true );
		Respuesta resp47 = new Respuesta("No, estan abajo  ",false );
		Respuesta resp48 = new Respuesta("Aparecen en la derecha ", true);
		Respuesta resp49 = new Respuesta("Si, es agregarle una nueva pagina",true );
		Respuesta resp50 = new Respuesta("No, las capas no funcionan as� ",false  );
		Respuesta resp51 = new Respuesta("Puede que las capas sean temporales ", true);
		Respuesta resp52 = new Respuesta("Si, sirve como una lupa  ",true );
		Respuesta resp53 = new Respuesta("No, es solo para tener la imagen quieta ",false  );
		Respuesta resp54 = new Respuesta("Se puede usar para hacer zoom y modificar desde ahi ",true );
		Respuesta resp55 = new Respuesta("Si, al seleccionar una parte te copia ese color", true);
		Respuesta resp56 = new Respuesta("No, modifica el color", false );
		Respuesta resp57 = new Respuesta("Copia el color y lo  puede alterar",true );
		Respuesta resp58 = new Respuesta("No, se queda en el zoom que le pusiste antes de utilizarla", true);
		Respuesta resp59 = new Respuesta("Si, te permite modificar haciendo zoom", false );
		Respuesta resp60 = new Respuesta("No, pero podes usar otra herramienta en conjunto ",true );
		
		sesion.save(resp46);
		sesion.save(resp47);
		sesion.save(resp48);
		sesion.save(resp49);
		sesion.save(resp50);
		sesion.save(resp51);
		sesion.save(resp52);
		sesion.save(resp53);
		sesion.save(resp54);
		sesion.save(resp55);
		sesion.save(resp56);
		sesion.save(resp57);
		sesion.save(resp58);
		sesion.save(resp59);
		sesion.save(resp60);
		
		//Registro de preguntas Adobe Photoshop
	 	Pregunta preg16 = new Pregunta("En la parte izquierda de photoshop tenes las herramientas  ?",resp46,resp47,resp48);
	 	Pregunta preg17 = new Pregunta("Generar capas es como generar una nueva hoja en un libro?",resp49,resp50,resp51);
	 	Pregunta preg18 = new Pregunta("Usar el navegador es para hacer zoom  ?",resp52,resp53,resp54);
	 	Pregunta preg19 = new Pregunta("La herramienta tampon de clonar sirve para copiar colores ?",resp55,resp56,resp57);
	 	Pregunta preg20 = new Pregunta("Con la herramienta lazo se puede usar zoom  ?",resp58,resp59,resp60);
		
	 	sesion.save(preg16);
		sesion.save(preg17);
		sesion.save(preg18);   
		sesion.save(preg19);  
		sesion.save(preg20);  
		
	
		
		//Registro de examenes, la union de preguntas, respuestas 
	     Examen examen16 = new Examen(preg16,preg17,preg18,preg19,preg20);
	  
	    sesion.save(examen16);
	
		
	  //Registro de Curso con su examen correspondiente
	    //Registro de Adobe Photoshop y sus examenes
		sesion.save(new Curso_Examen(c4,examen16 ));
	
		   //Registro de respuestas	de wordpress
				Respuesta resp61 = new Respuesta("-2000",false );
				Respuesta resp62 = new Respuesta(" -2003",false );
				Respuesta resp63 = new Respuesta("-2004", true);
				Respuesta resp64 = new Respuesta("-JAVA",false );
				Respuesta resp65 = new Respuesta("-C++ ",false  );
				Respuesta resp66 = new Respuesta("-PHP", true);
				Respuesta resp67 = new Respuesta("-SEGUN SU USO ",false );
				Respuesta resp68 = new Respuesta("-NO ",false  );
				Respuesta resp69 = new Respuesta("-SI ",true );
				Respuesta resp70 = new Respuesta("-VISUAL COMPOSER", false);
				Respuesta resp71 = new Respuesta("-DIVI", false );
				Respuesta resp72 = new Respuesta("-AMBOS SON PAGE BUILDER",true );
				Respuesta resp73 = new Respuesta("-Restriccion",false);
				Respuesta resp74 = new Respuesta("-Funcionalidad", false );
				Respuesta resp75 = new Respuesta("-Utilidad",true );
				
				sesion.save(resp61);
				sesion.save(resp62);
				sesion.save(resp63);
				sesion.save(resp64);
				sesion.save(resp65);
				sesion.save(resp66);
				sesion.save(resp67);
				sesion.save(resp68);
				sesion.save(resp69);
				sesion.save(resp70);
				sesion.save(resp71);
				sesion.save(resp72);
				sesion.save(resp73);
				sesion.save(resp74);
				sesion.save(resp75);
		
	 	//Registro de preguntas Wordpress
	 	Pregunta preg21 = new Pregunta("En que ano se lanzo Wordpress ?",resp61,resp62,resp63);
	 	Pregunta preg22 = new Pregunta("Cual es el lenguaje de programacion ?",resp64,resp65,resp66);
	 	Pregunta preg23 = new Pregunta("Es una aplicacion de Open Source(libre de uso y desarrollo)  ?",resp67,resp68,resp69);
	 	Pregunta preg24 = new Pregunta("Cual de estos es un Page Builder ?",resp70,resp71,resp72);
	 	Pregunta preg25 = new Pregunta("Que es un pluggin  ?",resp73,resp74,resp75);
		
	 	sesion.save(preg21);
		sesion.save(preg22);
		sesion.save(preg23);   
		sesion.save(preg24);  
		sesion.save(preg25);  
		
	
		
		//Registro de examenes, la union de preguntas, respuestas 
	     Examen examen21 = new Examen(preg21,preg22,preg23,preg24,preg25);

	    sesion.save(examen21);

		
	  //Registro de Curso con su examen correspondiente
	    //Registro de Wordpress y sus examenes
		sesion.save(new Curso_Examen(c5,examen21 ));
		
		
		// Respuestas de diseño de videojuegos
		Respuesta resp201 = new Respuesta("-8", false);
		Respuesta resp202 = new Respuesta("-4 ", false);
		Respuesta resp203 = new Respuesta("-6", true);
		Respuesta resp204 = new Respuesta("-", false);
		Respuesta resp205 = new Respuesta("-No ", false);
		Respuesta resp206 = new Respuesta("-Si", true);
		Respuesta resp207 = new Respuesta("-Talvez ", false);
		Respuesta resp208 = new Respuesta("-No ", false);
		Respuesta resp209 = new Respuesta("-Si", true);
		Respuesta resp210 = new Respuesta("-No", false);
		Respuesta resp211 = new Respuesta("-Talvez", false);
		Respuesta resp212 = new Respuesta("-Si", true);
		Respuesta resp213 = new Respuesta("-No", false);
		Respuesta resp214 = new Respuesta("-Talvez", false);
		Respuesta resp215 = new Respuesta("-Si", true);

		sesion.save(resp201);
		sesion.save(resp202);
		sesion.save(resp203);
		sesion.save(resp204);
		sesion.save(resp205);
		sesion.save(resp206);
		sesion.save(resp207);
		sesion.save(resp208);
		sesion.save(resp209);
		sesion.save(resp210);
		sesion.save(resp211);
		sesion.save(resp212);
		sesion.save(resp213);
		sesion.save(resp214);
		sesion.save(resp215);

		// Registro de preguntas diseño de videojuegos
		Pregunta preg41 = new Pregunta("Un personaje puede ser ficticio ?", resp201, resp202, resp203);
		Pregunta preg42 = new Pregunta("Se necesita saber diseño grafico  ?", resp204, resp205, resp206);
		Pregunta preg43 = new Pregunta("Tenes que saber dibujar para el diseño de videojuegos ?", resp207, resp208, resp209);
		Pregunta preg44 = new Pregunta("Es actual ?", resp210, resp211, resp212);
		Pregunta preg45 = new Pregunta("Es necesario saber usar Blender ?", resp213, resp214, resp215);

		sesion.save(preg41);
		sesion.save(preg42);
		sesion.save(preg43);
		sesion.save(preg44);
		sesion.save(preg45);

		// Registro de examenes, la union de preguntas, respuestas
		Examen examen39 = new Examen(preg41, preg42, preg43, preg44, preg45);

		sesion.save(examen39);
		
		sesion.save(new Curso_Examen(c6,examen39));
		
		
		// Respuestas de guitarra
		Respuesta resp91 = new Respuesta("-8",false );
		Respuesta resp92 = new Respuesta("-4 ",false );
		Respuesta resp93 = new Respuesta("-6", true);
		Respuesta resp94 = new Respuesta("-Caja de resonancia",false );
		Respuesta resp95 = new Respuesta("-Puente ",false  );
		Respuesta resp96 = new Respuesta("-Clavijero", true);
		Respuesta resp97 = new Respuesta("-Plastico ",false );
		Respuesta resp98 = new Respuesta("-Metal ",false  );
		Respuesta resp99 = new Respuesta("-Madera",true );
		Respuesta resp100 = new Respuesta("-Guitarra rusa", false);
		Respuesta resp101 = new Respuesta("-Guitarra italiana", false );
		Respuesta resp102 = new Respuesta("-Guitarra francesa",true );
		Respuesta resp103 = new Respuesta("-Silbato", false);
		Respuesta resp104 = new Respuesta("-Rejilla", false );
		Respuesta resp105 = new Respuesta("-Cejilla",true );
		
		sesion.save(resp91);
		sesion.save(resp92);
		sesion.save(resp93);
		sesion.save(resp94);
		sesion.save(resp95);
		sesion.save(resp96);
		sesion.save(resp97);
		sesion.save(resp98);
		sesion.save(resp99);
		sesion.save(resp100);
		sesion.save(resp101);
		sesion.save(resp102);
		sesion.save(resp103);
		sesion.save(resp104);
		sesion.save(resp105);
	 	
		//Registro de preguntas Guitarra
	 	Pregunta preg31 = new Pregunta("Cuantas cuerdas tiene la guitarra  ?",resp91,resp92,resp93);
	 	Pregunta preg32 = new Pregunta("Porque parte se afina la guitarra  ?",resp94,resp95,resp96);
	 	Pregunta preg33 = new Pregunta("De que material estan compuestas las guitarras criollas ?",resp97,resp98,resp99);
	 	Pregunta preg34 = new Pregunta("Cual de estos NO es un tipo de guitarra ?",resp100,resp101,resp102);
	 	Pregunta preg35 = new Pregunta("Que elemento se le coloca a la guitarra para formar un acorde ?",resp103,resp104,resp105);
		
	 	sesion.save(preg31);
		sesion.save(preg32);
		sesion.save(preg33);   
		sesion.save(preg34);  
		sesion.save(preg35);  
		
	
		//Registro de examenes, la union de preguntas, respuestas 
	     Examen examen31 = new Examen(preg31,preg32,preg33,preg34,preg35);
	  
	    sesion.save(examen31);
	
		
	  //Registro de Curso con su examen correspondiente
	    //Registro de Guitarra y sus examenes
		sesion.save(new Curso_Examen(c7,examen31 ));

		
		//Registro de respuestas de piano
		Respuesta resp76 = new Respuesta("-Solo de percucion",false );
		Respuesta resp77 = new Respuesta("-No ",false );
		Respuesta resp78 = new Respuesta("-Si", true);
		Respuesta resp79 = new Respuesta("-58",false );
		Respuesta resp80 = new Respuesta("-78 ",false  );
		Respuesta resp81 = new Respuesta("-88", true);
		Respuesta resp82 = new Respuesta("-4 ",false );
		Respuesta resp83 = new Respuesta("-6 ",false  );
		Respuesta resp84 = new Respuesta("-8 ",true );
		Respuesta resp85 = new Respuesta("-Verdadero", false);
		Respuesta resp86 = new Respuesta("-Falso", false );
		Respuesta resp87 = new Respuesta("-Usualmente con 2",true );
		Respuesta resp88 = new Respuesta("-Solo los pianos antiguos se solian afinar", false);
		Respuesta resp89 = new Respuesta("-No", false );
		Respuesta resp90 = new Respuesta("-Si",true );
		
		sesion.save(resp76);
		sesion.save(resp77);
		sesion.save(resp78);
		sesion.save(resp79);
		sesion.save(resp80);
		sesion.save(resp81);
		sesion.save(resp82);
		sesion.save(resp83);
		sesion.save(resp84);
		sesion.save(resp85);
		sesion.save(resp86);
		sesion.save(resp87);
		sesion.save(resp88);
		sesion.save(resp89);
		sesion.save(resp90);
		
	 	//Registro de preguntas Piano
	 	Pregunta preg26 = new Pregunta("El piano es un instrumento de cuerda percutida ?",resp76,resp77,resp78);
	 	Pregunta preg27 = new Pregunta("Cuantas teclas tiene un piano estandar ?",resp79,resp80,resp81);
	 	Pregunta preg28 = new Pregunta("Cuantas octavas tiene un piano estandar ?",resp82,resp83,resp84);
	 	Pregunta preg29 = new Pregunta("Los pianos cuentan con 4 pedales ?",resp85,resp86,resp87);
	 	Pregunta preg30 = new Pregunta("Es verdad que los pianos se afinan ?",resp88,resp89,resp90);
		
	 	sesion.save(preg26);
		sesion.save(preg27);
		sesion.save(preg28);   
		sesion.save(preg29);  
		sesion.save(preg30);  
		
	
		
		//Registro de examenes, la union de preguntas, respuestas 
	     Examen examen26 = new Examen(preg26,preg27,preg28,preg29,preg30);
	    

	    sesion.save(examen26);
	 
		
	  //Registro de Curso con su examen correspondiente
	    //Registro de Piano y sus examenes
		sesion.save(new Curso_Examen(c8,examen26 ));
	
		
		//Registro de respuestas de canto	
				Respuesta resp106 = new Respuesta("-La distancia entre la boca y la laringe no tiene nada que ver",false );
				Respuesta resp107 = new Respuesta("-No ",false );
				Respuesta resp108 = new Respuesta("-Si", true);
				Respuesta resp109 = new Respuesta("-Elongacion",false );
				Respuesta resp110 = new Respuesta("-Articulacion ",false  );
				Respuesta resp111 = new Respuesta("-Respiracion", true);
				Respuesta resp112 = new Respuesta("-Una oscilaci�n regular de la voz ",false );
				Respuesta resp113 = new Respuesta("-Subir y bajar en una misma nota ",false  );
				Respuesta resp114 = new Respuesta("-Ambas son correctas",true );
				Respuesta resp115 = new Respuesta("-El soplado no produce sonido", false);
				Respuesta resp116 = new Respuesta("-Falso", false );
				Respuesta resp117 = new Respuesta("-Verdadero",true );
				Respuesta resp118 = new Respuesta("-Obtener un sonido diferente cuando se desee", false);
				Respuesta resp119 = new Respuesta("-Producir modificaciones al sonido emitido por sus pliegues vocales", false );
				Respuesta resp120 = new Respuesta("-Ambas son correctas(VERDADERO)",true );
				
				sesion.save(resp106);
				sesion.save(resp107);
				sesion.save(resp108);
				sesion.save(resp109);
				sesion.save(resp110);
				sesion.save(resp111);
				sesion.save(resp112);
				sesion.save(resp113);
				sesion.save(resp114);
				sesion.save(resp115);
				sesion.save(resp116);
				sesion.save(resp117);
				sesion.save(resp118);
				sesion.save(resp119);
				sesion.save(resp120);
	 	
	 	
		//Registro de preguntas Canto
	 	Pregunta preg36 = new Pregunta("Es verdad que a los hombres se les vuelve mas grave la voz durante su adolecencia porque La distancia entre la laringe y la boca se hace m�s larga  ?",resp106,resp107,resp108);
	 	Pregunta preg37 = new Pregunta("Cual de estos elementos es esencial para cantar ?",resp109,resp110,resp111);
	 	Pregunta preg38 = new Pregunta("Que es el vibrato  ?",resp112,resp113,resp114);
	 	Pregunta preg39 = new Pregunta("El soplado consiste en dejar pasar aire por las cuerdas vocales mientras se va produciendo sonido ?",resp115,resp116,resp117);
	 	Pregunta preg40 = new Pregunta("Para que sirven los resonadores?",resp118,resp119,resp120);
		
	 	sesion.save(preg36);
		sesion.save(preg37);
		sesion.save(preg38);   
		sesion.save(preg39);  
		sesion.save(preg40);  
		
		
		
		//Registro de examenes, la union de preguntas, respuestas 
	     Examen examen36 = new Examen(preg36, preg37, preg38, preg39 ,preg40);
	
	    sesion.save(examen36);
	
		
	  //Registro de Curso con su examen correspondiente
	    //Registro de Canto y sus examenes
		sesion.save(new Curso_Examen(c9,examen36 ));
	
	 	
	 	// CASOS
		
		/* ANA (EXAMEN) */
		// Caso de examen aprobado (primera vez)
		Usuario_Curso us1 = new Usuario_Curso(cliente1, c8);
		us1.setEstado(Estado.EN_CURSO);
		us1.setFecha_incio_compra(LocalDate.now());
		us1.setHora(LocalTime.now());
		
		// Caso de examen desaprobado (primera vez)
		Usuario_Curso us2 = new Usuario_Curso(cliente1, c5);
		us2.setEstado(Estado.FINALIZADO);
		us2.setFecha_incio_compra(LocalDate.now());
		us2.setHora(LocalTime.now());
		us2.setCursoTerminado(true);
		us2.setProgreso(100.0);
		
		// Caso de examen aprobado pero no ganas puntos
		Usuario_Curso us3 = new Usuario_Curso(cliente1, c4);
		us3.setEstado(Estado.FINALIZADO);
		us3.setFecha_incio_compra(LocalDate.now());
		us3.setHora(LocalTime.now());
		us3.setCursoTerminado(true);
		us3.setProgreso(100.0);
		
		Usuario_Examen exs1 = new Usuario_Examen(cliente1, examen16);
		exs1.setPuntaje_final(0);
		exs1.setFecha_finalizacion_examen(LocalDate.now());
		exs1.setHora_finalizacion_examen(LocalTime.now());
		
		Usuario_Examen exs2 = new Usuario_Examen(cliente1, examen16);
		exs2.setPuntaje_final(2);
		exs2.setFecha_finalizacion_examen(LocalDate.now());
		exs2.setHora_finalizacion_examen(LocalTime.now());
		
		Usuario_Examen exs3 = new Usuario_Examen(cliente1, examen16);
		exs3.setPuntaje_final(4);
		exs3.setFecha_finalizacion_examen(LocalDate.now());
		exs3.setHora_finalizacion_examen(LocalTime.now());
		sesion.save(exs1);
		sesion.save(exs2);
		sesion.save(exs3);
		
		
		/* ALE (CURSOS) */
		Usuario_Curso us4 = new Usuario_Curso(cliente2, c6);
		us4.setEstado(Estado.EN_CURSO);
		us4.setFecha_incio_compra(LocalDate.now());
		us4.setHora(LocalTime.now());
		
		Usuario_Curso us5 = new Usuario_Curso(cliente2, c7);
		us5.setEstado(Estado.FINALIZADO);
		us5.setFecha_incio_compra(LocalDate.now());
		us5.setHora(LocalTime.now());
		us5.setCursoTerminado(true);
		us5.setProgreso(100.0);
		
		Usuario_Curso us6 = new Usuario_Curso(cliente2, c9);
		us6.setEstado(Estado.CANCELADO);
		
		sesion.save(us1);
		sesion.save(us2);
		sesion.save(us3);
		sesion.save(us4);
		sesion.save(us5);
		sesion.save(us6);
	 	
	}

}
