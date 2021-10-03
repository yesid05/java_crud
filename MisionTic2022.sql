-- DROP DATABASE concurso;
CREATE DATABASE concurso;
USE concurso;

CREATE TABLE if NOT EXISTS cantante(
	id INT PRIMARY KEY,
	nombre VARCHAR(100),
	apellido VARCHAR(100),
	nacionalidad VARCHAR(100)
);
INSERT INTO cantante VALUES(101,'Miguel','Bose','español');
INSERT INTO cantante VALUES(102,'Silvestre','Dangond','colombiano');
INSERT INTO cantante VALUES(103,'Jeisón','Jimenez','colombiano');
INSERT INTO cantante VALUES(104,'Carin','León','mexicano');
INSERT INTO cantante VALUES(105,'Rocio','Durcal','española');

CREATE TABLE if NOT EXISTS cancion(
	id INT PRIMARY KEY,
	nombre VARCHAR(100),
	genero VARCHAR(100),
	anio INT,
	id_cantante INTEGER,
	FOREIGN KEY (id_cantante) REFERENCES cantante (id)
);
INSERT INTO cancion VALUES(201,'Amiga','Balada',1990,101);
INSERT INTO cancion VALUES(202,'Las locuras mias','vallenato',2019,102);
INSERT INTO cancion VALUES(203,'Gracias a ti','popular',2020,103);
INSERT INTO cancion VALUES(204,'Tu','popular',2019,104);
INSERT INTO cancion VALUES(205,'La gata bajo la lluvia','Balada',1981,105);

CREATE TABLE if NOT EXISTS baile(
	id INT PRIMARY KEY,
	nombre VARCHAR(100),
	antiguedad INT,
	duracion INT
);
INSERT INTO baile VALUES(301,'Cumbia',40,5);
INSERT INTO baile VALUES(302,'Samba',50,5);
INSERT INTO baile VALUES(303,'Break dance',30,4);
INSERT INTO baile VALUES(304,'Salsa',30,4);
INSERT INTO baile VALUES(305,'Danza irlandesa',34,7);
INSERT INTO baile VALUES(306,'Arrow',35,6);
INSERT INTO baile VALUES(307,'Polca',50,6);
INSERT INTO baile VALUES(308,'Danza clasica',56,5);

CREATE TABLE if NOT EXISTS participante(
	alias VARCHAR(10) PRIMARY KEY,
	nombre VARCHAR(100),
	apellido VARCHAR(100),
	email VARCHAR(100),
	celular VARCHAR(50),
	contrasena VARCHAR(50),
	fecha_nto DATE
);
INSERT INTO participante VALUES('rortiz','Roberto','Ortiz','correo@correo.co','3203333','123','1993-11-17');
INSERT INTO participante VALUES('paploper','Pablo','Perez','correo@correo.co','3203333','123','1993-11-17');
INSERT INTO participante VALUES('nataper','Natali','Perez','correo@correo.co','3203333','123','1993-11-17');
INSERT INTO participante VALUES('jaironi','Jairo','Nieto','correo@correo.co','3203333','123','1993-11-17');
INSERT INTO participante VALUES('Jairve','Jair','Velasco','correo@correo.co','3203333','123','1993-11-17');
INSERT INTO participante VALUES('luisco','Luis','Cortez','correo@correo.co','3203333','123','1993-11-17');
INSERT INTO participante VALUES('renegó','Rene','Gómez','correo@correo.co','3203333','123','1993-11-17');
INSERT INTO participante VALUES('camiru','Camilo','Rueda','correo@correo.co','3203333','123','1993-11-17');

CREATE TABLE if NOT EXISTS participacion(
	id INT PRIMARY KEY,
	id_alias VARCHAR(10),
	id_concurso INT,
	fecha DATETIME,
	FOREIGN KEY (id_alias) REFERENCES participante (alias)
);
INSERT INTO participacion VALUES(401,'rortiz',201,'2021-08-10 20:00');
INSERT INTO participacion VALUES(402,'rortiz',204,'2021-08-15 18:30');
INSERT INTO participacion VALUES(403,'rortiz',304,'2021-08-20 20:30');
INSERT INTO participacion VALUES(404,'paploper',201,'2021-08-10 20:30');
INSERT INTO participacion VALUES(405,'nataper',202,'2021-08-14 20:30');
INSERT INTO participacion VALUES(406,'nataper',301,'2018-08-15 21:30');
INSERT INTO participacion VALUES(407,'nataper',301,'2021-08-14 15:30');
INSERT INTO participacion VALUES(408,'jaironi',301,'2021-08-14 15:30');
INSERT INTO participacion VALUES(409,'jaironi',302,'2021-08-13 18:30');
INSERT INTO participacion VALUES(410,'jairve',303,'2021-08-16 20:30');
INSERT INTO participacion VALUES(411,'jairve',304,'2021-08-14 18:30:20');
INSERT INTO participacion VALUES(412,'jairve',306,'2021-08-14 18:30');
INSERT INTO participacion VALUES(413,'renego',305,'2021-08-12 21:30');
INSERT INTO participacion VALUES(414,'camiru',202,'2021-08-13 17:30');
INSERT INTO participacion VALUES(415,'camiru',204,'2021-08-15 18:30');
INSERT INTO participacion VALUES(416,'camiru',205,'2021-08-16 18:30');


-- Código SQL para Modificar  los siguientes datos:
UPDATE cancion SET anio = 1988 WHERE nombre = 'Amiga';
UPDATE participante SET celular = '3115678432' WHERE alias = 'Jairve';
DELETE FROM participacion 
WHERE id_alias = (SELECT alias FROM participante WHERE alias = 'camiru') 
AND id_concurso = (SELECT id FROM cancion WHERE nombre = 'Tu');

-- Código SQL para realizar las siguientes consultas:
SELECT 'Consulta 1';
SELECT nombre FROM cancion 
UNION
SELECT nombre FROM baile ORDER BY nombre;
SELECT 'Consulta 2';
SELECT nombre, genero, anio FROM cancion WHERE anio > 2000 ORDER BY nombre;
SELECT 'Consulta 3';
SELECT cn.nombre FROM cancion AS cn 
JOIN cantante AS ct 
ON cn.id_cantante = ct.id 
WHERE ct.nombre = 'Rocio' AND ct.apellido = 'Durcal';
SELECT 'Consulta 4';
SELECT nombre FROM participacion AS pn
JOIN baile AS bl ON pn.id_concurso = bl.id
WHERE pn.id_alias = 'rortiz'
UNION
SELECT nombre FROM participacion AS pn
JOIN cancion AS cn ON pn.id_concurso = cn.id
WHERE pn.id_alias = 'rortiz'
ORDER BY nombre;
SELECT 'Consulta 5';
SELECT pt.alias, pt.nombre, pt.apellido FROM participante AS pt
JOIN participacion AS pn ON pt.alias = pn.id_alias
WHERE pn.id_concurso = (SELECT id FROM cancion WHERE nombre = 'Las locuras mias');
SELECT 'Consulta 6';
SELECT COUNT(id) FROM cancion WHERE anio <= 2020;
