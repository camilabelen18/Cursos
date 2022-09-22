CREATE DATABASE proyecto;

USE proyecto;

INSERT INTO curso(Descripcion,Estado,Nombre,Precio)
VALUES
('diseño',1,'php',100.5),
('diseño',1,'java',100.5);

SELECT Nombre FROM curso;