CREATE DATABASE IF NOT EXISTS GymBuddy;

USE GymBuddy;

CREATE TABLE IF NOT EXISTS usuario (
    usuario_id INT PRIMARY KEY,
    entrenador_id INT,
    resena_id INT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50),
    email VARCHAR(100) NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    estatura FLOAT NOT NULL,
    peso FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS resenas (
    resena_id INT PRIMARY KEY,
    entrenamiento_id INT,
    ejercicio_id INT,
    usuario_id INT,
    descripcion TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS entrenador (
    entrenador_id INT PRIMARY KEY,
    entrenamiento_id INT,
    usuario_id INT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50),
    email VARCHAR(100) NOT NULL,
	contrasena VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS entrenamiento (
    entrenamiento_id INT PRIMARY KEY,
    resena_id INT,
    usuario_id INT,
    admin_id INT,
    dificultad ENUM('Facil', 'Intermedio', 'Dificil') NOT NULL
);

CREATE TABLE IF NOT EXISTS ejercicio (
    ejercicio_id INT PRIMARY KEY,
    entrenamiento_id INT,
    descripcion TEXT NOT NULL,
    maquinaria VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS admin (
    admin_id INT PRIMARY KEY,
    entrenamiento_id INT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50),
    email VARCHAR(100) NOT NULL,
	contrasena VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS dificultad (
    entrenamiento_id INT PRIMARY KEY,
    dificultad ENUM('Facil', 'Intermedio', 'Dificil') NOT NULL,
    FOREIGN KEY (entrenamiento_id) REFERENCES entrenamiento(entrenamiento_id)
);

ALTER TABLE usuario
    ADD CONSTRAINT fk_usuario_entrenador FOREIGN KEY (entrenador_id) REFERENCES entrenador(entrenador_id),
    ADD CONSTRAINT fk_usuario_resena FOREIGN KEY (resena_id) REFERENCES resenas(resena_id);

ALTER TABLE resenas
    ADD CONSTRAINT fk_resenas_entrenamiento FOREIGN KEY (entrenamiento_id) REFERENCES entrenamiento(entrenamiento_id),
    ADD CONSTRAINT fk_resenas_ejercicio FOREIGN KEY (ejercicio_id) REFERENCES ejercicio(ejercicio_id),
    ADD CONSTRAINT fk_resenas_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id);

ALTER TABLE entrenador
    ADD CONSTRAINT fk_entrenador_entrenamiento FOREIGN KEY (entrenamiento_id) REFERENCES entrenamiento(entrenamiento_id),
    ADD CONSTRAINT fk_entrenador_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id);

ALTER TABLE entrenamiento
    ADD CONSTRAINT fk_entrenamiento_resena FOREIGN KEY (resena_id) REFERENCES resenas(resena_id),
    ADD CONSTRAINT fk_entrenamiento_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id),
    ADD CONSTRAINT fk_entrenamiento_admin FOREIGN KEY (admin_id) REFERENCES admin(admin_id);

ALTER TABLE ejercicio
    ADD CONSTRAINT fk_ejercicio_entrenamiento FOREIGN KEY (entrenamiento_id) REFERENCES entrenamiento(entrenamiento_id);

ALTER TABLE admin
    ADD CONSTRAINT fk_admin_entrenamiento FOREIGN KEY (entrenamiento_id) REFERENCES entrenamiento(entrenamiento_id);
