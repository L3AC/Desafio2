DROP DATABASE IF EXISTS mediateca;
CREATE DATABASE IF NOT EXISTS mediateca;
USE mediateca;

-- Tabla principal: material
CREATE TABLE material (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    titulo VARCHAR(255) NOT NULL,
    tipo ENUM('libro', 'revista', 'cd_audio', 'dvd') NOT NULL,
    unidades_disponibles INT DEFAULT 1 CHECK (unidades_disponibles >= 0),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla: libro
CREATE TABLE libro (
    id_material INT PRIMARY KEY,
    autor VARCHAR(255) NOT NULL,
    num_paginas INT NOT NULL CHECK (num_paginas > 0),
    editorial VARCHAR(255),
    isbn VARCHAR(20),
    año_publicacion YEAR,
    FOREIGN KEY (id_material) REFERENCES material(id) ON DELETE CASCADE
);


-- Tabla: revista
CREATE TABLE revista (
    id_material INT PRIMARY KEY,
    editorial VARCHAR(255),
    periodicidad VARCHAR(50),
    fecha_publicacion DATE,
    FOREIGN KEY (id_material) REFERENCES material(id) ON DELETE CASCADE
);

-- Tabla: cd_audio
CREATE TABLE cd_audio (
    id_material INT PRIMARY KEY,
    artista VARCHAR(255) NOT NULL,
    genero VARCHAR(100),
    duracion TIME,
    num_canciones INT NOT NULL CHECK (num_canciones > 0),
    FOREIGN KEY (id_material) REFERENCES material(id) ON DELETE CASCADE
);

-- Tabla: dvd
CREATE TABLE dvd (
    id_material INT PRIMARY KEY,
    director VARCHAR(255),
    duracion TIME,
    genero VARCHAR(100),
    FOREIGN KEY (id_material) REFERENCES material(id) ON DELETE CASCADE
);

-- Insertar en material y luego en libro
INSERT INTO material (codigo, titulo, tipo, unidades_disponibles) VALUES
('LIB00001', 'Cien años de soledad', 'libro', 3),
('LIB00002', 'Don Quijote de la Mancha', 'libro', 2),
('LIB00003', '1984', 'libro', 5),
('LIB00004', 'La Odisea', 'libro', 1),
('LIB00005', 'El Principito', 'libro', 4);

INSERT INTO libro (id_material, autor, num_paginas, editorial, isbn, año_publicacion) VALUES
(1, 'Gabriel García Márquez', 417, 'Editorial Sudamericana', '978-0307474728', 1967),
(2, 'Miguel de Cervantes', 1072, 'Francisco de Robles', '978-8491050297', 1605),
(3, 'George Orwell', 328, 'Secker & Warburg', '978-0451524935', 1949),
(4, 'Homero', 416, 'Alianza Editorial', '978-8420667518', -700),
(5, 'Antoine de Saint-Exupéry', 96, 'Reynal & Hitchcock', '978-0156012195', 1943);

INSERT INTO material (codigo, titulo, tipo, unidades_disponibles) VALUES
('REV00001', 'National Geographic', 'revista', 10),
('REV00002', 'Time Magazine', 'revista', 7),
('REV00003', 'Scientific American', 'revista', 4),
('REV00004', 'Forbes', 'revista', 6),
('REV00005', 'The Economist', 'revista', 8);

INSERT INTO revista (id_material, editorial, periodicidad, fecha_publicacion) VALUES
(6, 'National Geographic Society', 'Mensual', '2025-09-01'),
(7, 'Time USA, LLC', 'Semanal', '2025-10-15'),
(8, 'Springer Nature', 'Mensual', '2025-08-20'),
(9, 'Forbes Media', 'Quincenal', '2025-10-01'),
(10, 'The Economist Group', 'Semanal', '2025-10-20');

INSERT INTO material (codigo, titulo, tipo, unidades_disponibles) VALUES
('CDA00001', 'Thriller', 'cd_audio', 2),
('CDA00002', 'The Dark Side of the Moon', 'cd_audio', 3),
('CDA00003', 'Back in Black', 'cd_audio', 1),
('CDA00004', 'Rumours', 'cd_audio', 4),
('CDA00005', 'Abbey Road', 'cd_audio', 2);

INSERT INTO cd_audio (id_material, artista, genero, duracion, num_canciones) VALUES
(11, 'Michael Jackson', 'Pop', '00:42:19', 9),
(12, 'Pink Floyd', 'Rock Progresivo', '00:42:56', 10),
(13, 'AC/DC', 'Hard Rock', '00:42:11', 10),
(14, 'Fleetwood Mac', 'Rock', '00:39:43', 11),
(15, 'The Beatles', 'Rock', '00:47:23', 17);

INSERT INTO material (codigo, titulo, tipo, unidades_disponibles) VALUES
('DVD00001', 'El Padrino', 'dvd', 1),
('DVD00002', 'Pulp Fiction', 'dvd', 2),
('DVD00003', 'El Señor de los Anillos: La Comunidad del Anillo', 'dvd', 3),
('DVD00004', 'Matrix', 'dvd', 2),
('DVD00005', 'Interestelar', 'dvd', 1);

INSERT INTO dvd (id_material, director, duracion, genero) VALUES
(16, 'Francis Ford Coppola', '02:55:00', 'Drama'),
(17, 'Quentin Tarantino', '02:34:00', 'Crimen'),
(18, 'Peter Jackson', '02:58:00', 'Fantasía'),
(19, 'Lana y Lilly Wachowski', '02:16:00', 'Ciencia Ficción'),
(20, 'Christopher Nolan', '02:49:00', 'Ciencia Ficción');
