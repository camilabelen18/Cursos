USE proyecto_cursos;

INSERT INTO Cursos (Nombre, Categoria, Descripcion, Precio, Imagen)
VALUES
('Curso PHP/MySql desde 0', 'programacion', 'En este curso se vera como crear aplicaciones y sitios web desde cero con PHP y MYSQL.',3000.0 , 'php-desde-cero.jpg'),
('Spring Framework 5', 'programacion', 'Construye aplicaciones web con Spring Framework 5 & Spring Boot: Thymeleaf, JPA, Security, REST, MySQL, Angular, WebFlux', 2500.0, 'curso-spring.jpg'),
('Python 2022', 'programacion', 'En este curso aprenderas desde las bases de Python hacia temas más avanzados del lenguaje', 2600.0, 'curso-phyton.jpg'),
('Adobe Photoshop: Curso completo', 'diseno', 'Aprende las herramientas esenciales de Adobe Photoshop para comenzar a diseñar hermosos graficos y fotos en Photoshop.',1000.0 , 'adobe-photoshop.jpg'),
('Curso completo de WordPress', 'diseno', 'El Mejor Curso de WordPress para aprender desde cero... ¡Mas de 5.000 alumnos satisfechos!', 800.0, 'wordpress.jpg'),
('Modelado y diseño para videojuegos', 'diseno', 'Aprende DESDE CERO a Modelar, Texturizar, Iluminacion y Render de modelos 2D 3D Assets Videojuegos MagicaVoxel y Unity', 3200.0, 'diseño-videojuegos.jpg'),
('Curso de de guitarra practico para principiantes', 'musica', 'Aprende paso a paso a tocar la guitarra con este curso practico. Aprende tocando y olvídate de ejercicios aburridos.',1650.0 , 'curso-guitarra.jpg'),
('Curso de piano completo', 'musica', 'Aprendelo Todo: Armonia , Composicion, Improvisacion , Acompañar con acordes, Lectura & Solfeo, Tecnica y Relajacion.', 2000.0, 'curso-piano.jpg'),
('Curso de canto para principiantes', 'musica', '¡Encuentra tu voz de canto de forma natural y diviértete haciéndolo! Un enfoque moderno para las clases de canto.', 2000.0, 'curso-canto.jpg');

INSERT INTO Giftcards (numero_tarjeta, puntos, saldo)
VALUES (555, 0, 0.0),
       (555, 0, 0.0),
       (555, 80000, 8000.0);
       
INSERT INTO Usuarios (Nombre, Email, Password, Rol, Imagen, NumeroTarjeta, giftcard_Identificador)
VALUES ('Juan', 'admin@gmail.com', '123', 'admin', 'default-user.png', 555, 1),
       ('Ana', 'ana@gmail.com', '111', 'cliente', 'default-user.png', 555, 2),
       ('Ale', 'ale@gmail.com', '123', 'cliente', 'default-user.png', 555, 3);
       
INSERT INTO Carritos (cantidad, usuario_id)
VALUES (0, 2),
       (0, 3);
       
INSERT INTO Unidades (descripcion, video_url, unidad_completada)
VALUES ('Unidad 1: Presentacion', 'https://www.youtube.com/embed/I75CUdSJifw', 0),
	   ('Unidad 2: Instalacion software del curso', 'https://www.youtube.com/embed/tXxOAXP-gkg', 0),
	   ('Unidad 3: Primera pagina PHP', 'https://www.youtube.com/embed/Ja9UVEgAzEw', 0),
	   ('Unidad 4: Variables y comentarios', 'https://www.youtube.com/embed/IOdmCo_7U6s', 0),
	   ('Unidad 1: Introduccion', 'https://www.youtube.com/embed/eyWmkbReWLI', 0),
	   ('Unidad 2: Que es Spring Boot?', 'https://www.youtube.com/embed/UvXktTs2bx0', 0),
	   ('Unidad 3: Instalacion del Spring Tools IDE', 'https://www.youtube.com/embed/9yAt3hAJ-vg', 0),
	   ('Unidad 1: Introduccion', 'https://www.youtube.com/embed/DAdRO6ByBoU', 0),
	   ('Unidad 2: Ejecucion de un programa', 'https://www.youtube.com/embed/UzHFkEdnRuc', 0),
	   ('Unidad 3: Variables en Python', 'https://www.youtube.com/embed/w8VZxo1TlnU', 0),
	   ('Unidad 1: Introduccion', 'https://www.youtube.com/embed/4-VMuLs99Ss', 1),
	   ('Unidad 2: Tapon de Clonar y Parche', 'https://www.youtube.com/embed/Y9yKFzACEHg', 1),
	   ('Unidad 3: Lazos y Tono/Saturacion', 'https://www.youtube.com/embed/HVBg6Fn2Rc0', 1),
	   ('Unidad 1: Introduccion', 'https://www.youtube.com/embed/OAWTixdQjqM', 1),
	   ('Unidad 2: Instalacion en windows', 'https://www.youtube.com/embed/BbRYgUiAsEQ', 1),
	   ('Unidad 3: Parrafos', 'https://www.youtube.com/embed/48aCNffCJTU', 1),
	   ('Unidad 1: Introduccion', 'https://www.youtube.com/embed/Vf9PyoXPkjI', 0),
	   ('Unidad 2: Ejercicios', 'https://www.youtube.com/embed/hdcCtgeKHqI', 0),
	   ('Unidad 3: Navegacion Basica', 'https://www.youtube.com/embed/HE_OXqM2ks8', 0),
	   ('Unidad 1: Las partes de la guitarra', 'https://www.youtube.com/embed/L3_EGxOLcv0', 1),
	   ('Unidad 2: La postura correcta', 'https://www.youtube.com/embed/QqB1vaicNxU', 1),
	   ('Unidad 3: Como tocar', 'https://www.youtube.com/embed/yJ1Q_FK6pwY', 1),
	   ('Unidad 1: Introduccion', 'https://www.youtube.com/embed/kAALQ4JEY6c', 0),
	   ('Unidad 2: Leccion 2', 'https://www.youtube.com/embed/LYuZIZ_LDKk', 0),
	   ('Unidad 3: Leccion 3', 'https://www.youtube.com/embed/rkP-S0U7O1w', 0),
	   ('Unidad 1: Introduccion', 'https://www.youtube.com/embed/ciHP0DGppX4', 0),
	   ('Unidad 2: Respiracion', 'https://www.youtube.com/embed/utAoguWXYTc', 0),
	   ('Unidad 3: Ejercicios', 'https://www.youtube.com/embed/hU89zIsQ9UE', 0);
	   
INSERT INTO Curso_Unidad (curso_id, unidad_id)
VALUES  (1, 1),
	    (1, 2),
	    (1, 3),
		(1, 4),
		(2, 5),
		(2, 6),
		(2, 7),
		(3, 8),
		(3, 9),
		(3, 10),
		(4, 11),
		(4, 12),
		(4, 13),
		(5, 14),
		(5, 15),
		(5, 16),
		(6, 17),
		(6, 18),
		(6, 19),
		(7, 20),
		(7, 21),
		(7, 22),
		(8, 23),
		(8, 24),
		(8, 25),
		(9, 26),
		(9, 27),
		(9, 28);
       