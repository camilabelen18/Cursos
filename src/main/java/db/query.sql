CREATE DATABASE proyecto_cursos;

USE proyecto_cursos;

INSERT INTO curso(Nombre, Categoria, Descripcion, Precio, Estado, Imagen)
VALUES
('Curso PHP/MySql desde 0', 'programacion', 'En este curso se vera como crear aplicaciones y sitios web desde cero con PHP y MYSQL.',3000.0 , 3, 'php-desde-cero.jpg'),
('Spring Framework 5', 'programacion', 'Construye aplicaciones web con Spring Framework 5 & Spring Boot: Thymeleaf, JPA, Security, REST, MySQL, Angular, WebFlux', 2500.0, 3, 'curso-spring.jpg'),
('Python 2022', 'programacion', 'En este curso aprenderas desde las bases de Python hacia temas más avanzados del lenguaje', 2600.0, 3, 'curso-phyton.jpg'),
('Adobe Photoshop: Curso completo', 'diseno', 'Aprende las herramientas esenciales de Adobe Photoshop para comenzar a diseñar hermosos graficos y fotos en Photoshop.',1000.0 , 3, 'adobe-photoshop.jpg'),
('Curso completo de WordPress', 'diseno', 'El Mejor Curso de WordPress para aprender desde cero... ¡Mas de 5.000 alumnos satisfechos!', 800.0, 3, 'wordpress.jpg'),
('Modelado y diseño para videojuegos', 'diseno', 'Aprende DESDE CERO a Modelar, Texturizar, Iluminacion y Render de modelos 2D 3D Assets Videojuegos MagicaVoxel y Unity', 3200.0, 3, 'diseño-videojuegos.jpg'),
('Curso de de guitarra practico para principiantes', 'musica', 'Aprende paso a paso a tocar la guitarra con este curso practico. Aprende tocando y olvídate de ejercicios aburridos.',1650.0 , 3, 'curso-guitarra.jpg'),
('Curso de piano completo', 'musica', 'Aprendelo Todo: Armonia , Composicion, Improvisacion , Acompañar con acordes, Lectura & Solfeo, Tecnica y Relajacion.', 2000.0, 3, 'curso-piano.jpg'),
('Curso de canto para principiantes', 'musica', '¡Encuentra tu voz de canto de forma natural y diviértete haciéndolo! Un enfoque moderno para las clases de canto.', 2000.0, 3, 'curso-canto.jpg');

INSERT INTO usuario(Nombre, Email, Password, NumeroTarjeta, Rol)
VALUES ('Juan','hola@hola.com','1234', 555, 'admin'),
       ('Ana','ana@gmail.com','111', 4407, 'cliente')
       ('Ale','ale@gmail.com','123', 5809, 'cliente');