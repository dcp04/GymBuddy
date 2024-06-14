-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS gymbuddy;

-- Usar la base de datos
USE gymbuddy;

-- Crear tablas
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    apellidos VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    estatura INT,
    nombre VARCHAR(255),
    password VARCHAR(255),
    peso DOUBLE
);

CREATE TABLE usuario_rol (
    usuario_id BIGINT,
    roles_usuario ENUM('ROL_USER', 'ROL_ADMIN', 'ROL_ENTRENADOR'),
    PRIMARY KEY (usuario_id, roles_usuario),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE ejercicio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255),
    imagen_url VARCHAR(255),
    nombre VARCHAR(255),
    creador_id BIGINT,
    FOREIGN KEY (creador_id) REFERENCES usuario(id)
);

CREATE TABLE entrenamiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dificultad ENUM('FACIL', 'MODERADO', 'DIFICIL'),
    imagen_url VARCHAR(255),
    nombre VARCHAR(255),
    creador_id BIGINT,
    FOREIGN KEY (creador_id) REFERENCES usuario(id)
);

CREATE TABLE ejercicio_entrenamiento (
    entrenamiento_id BIGINT,
    ejercicio_id BIGINT,
    PRIMARY KEY (entrenamiento_id, ejercicio_id),
    FOREIGN KEY (entrenamiento_id) REFERENCES entrenamiento(id),
    FOREIGN KEY (ejercicio_id) REFERENCES ejercicio(id)
);

CREATE TABLE usuario_entrenamiento (
    entrenamiento_id BIGINT,
    usuario_id BIGINT,
    PRIMARY KEY (entrenamiento_id, usuario_id),
    FOREIGN KEY (entrenamiento_id) REFERENCES entrenamiento(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);



-- Insertar ejercicios
INSERT INTO ejercicio (nombre, descripcion, imagen_url, creador_id) VALUES
('Press Banca', 'El press de banca es un ejercicio de levantamiento de pesas que se centra en el desarrollo de los músculos pectorales mayores, los tríceps y los deltoides anteriores.', '../../../../../../mediafiles/PressBancaBarra.png', (SELECT id FROM usuario WHERE email = 'david.castro@gmail.com')),
('Sentadilla', 'La sentadilla es un ejercicio de entrenamiento de fuerza que trabaja los músculos de las piernas, incluyendo los cuádriceps, los isquiotibiales y los glúteos.', '../../../../../../mediafiles/Sentadilla.png', (SELECT id FROM usuario WHERE email = 'abel.garcia@gmail.com')),
('Peso Muerto', 'El peso muerto es un ejercicio de levantamiento de pesas que se centra en el desarrollo de los músculos de la espalda baja, los glúteos y los isquiotibiales.', '../../../../../../mediafiles/PesoMuerto.png', (SELECT id FROM usuario WHERE email = 'abel.garcia@gmail.com')),
('Press Militar', 'El press militar es un ejercicio de levantamiento de pesas que se enfoca en el desarrollo de los músculos deltoides y los tríceps.', '../../../../../../mediafiles/PressHombro.png', (SELECT id FROM usuario WHERE email = 'abel.garcia@gmail.com')),
('Flexiones', 'Las flexiones son un ejercicio de peso corporal que trabaja los músculos del pecho, los tríceps y los hombros.', '../../../../../../mediafiles/Flexiones.png', (SELECT id FROM usuario WHERE email = 'david.castro@gmail.com')),
('Plancha', 'La plancha es un ejercicio isométrico que se centra en el fortalecimiento del núcleo, incluyendo los músculos abdominales y la espalda baja.', '../../../../../../mediafiles/Plancha.png', (SELECT id FROM usuario WHERE email = 'david.castro@gmail.com'));

-- Insertar entrenamientos
INSERT INTO entrenamiento (nombre, dificultad, imagen_url, creador_id) VALUES
('Entrenamiento De Fuerza', 'MODERADO', '../../../../../../mediafiles/EntrenamientoFuerza.png', (SELECT id FROM usuario WHERE email = 'david.castro@gmail.com')),
('Entrenamiento de Resistencia', 'FACIL', '../../../../../../mediafiles/EntrenamientoResistencia.png', (SELECT id FROM usuario WHERE email = 'david.castro@gmail.com'));

-- Insertar relaciones entre ejercicios y entrenamientos
INSERT INTO ejercicio_entrenamiento (entrenamiento_id, ejercicio_id) VALUES
((SELECT id FROM entrenamiento WHERE nombre = 'Entrenamiento De Fuerza'), (SELECT id FROM ejercicio WHERE nombre = 'Press Banca')),
((SELECT id FROM entrenamiento WHERE nombre = 'Entrenamiento De Fuerza'), (SELECT id FROM ejercicio WHERE nombre = 'Sentadilla')),
((SELECT id FROM entrenamiento WHERE nombre = 'Entrenamiento De Fuerza'), (SELECT id FROM ejercicio WHERE nombre = 'Peso Muerto')),
((SELECT id FROM entrenamiento WHERE nombre = 'Entrenamiento de Resistencia'), (SELECT id FROM ejercicio WHERE nombre = 'Press Militar')),
((SELECT id FROM entrenamiento WHERE nombre = 'Entrenamiento de Resistencia'), (SELECT id FROM ejercicio WHERE nombre = 'Flexiones')),
((SELECT id FROM entrenamiento WHERE nombre = 'Entrenamiento de Resistencia'), (SELECT id FROM ejercicio WHERE nombre = 'Plancha'));
