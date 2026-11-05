-- ========================================================
-- Script de Base de Datos Reproducible: db_colegio
-- Sistema de Gestión Escolar (EduManager Pro) - JSF / Jakarta EE
-- ========================================================

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

DROP DATABASE IF EXISTS `db_colegio`;
CREATE DATABASE IF NOT EXISTS `db_colegio`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE `db_colegio`;

-- --------------------------------------------------------
-- 1. Tabla: roles
-- --------------------------------------------------------
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_rol_nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `roles` (`id`, `nombre`, `descripcion`) VALUES
(1, 'ADMIN', 'Administrador del sistema con acceso total'),
(2, 'DOCENTE', 'Profesor con acceso a cursos y calificaciones'),
(3, 'ESTUDIANTE', 'Alumno con acceso a notas y estado de cuenta');

-- --------------------------------------------------------
-- 2. Tabla: usuarios
-- --------------------------------------------------------
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol_id` int(11) NOT NULL,
  `activo` tinyint(1) DEFAULT 1,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_usuario_username` (`username`),
  KEY `fk_usuario_rol` (`rol_id`),
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `usuarios` (`id`, `username`, `password`, `rol_id`, `activo`) VALUES
(1, 'admin', 'admin123', 1, 1),
(2, 'juan.perez', 'docente123', 2, 1),
(3, 'carlos.ruiz', 'estudiante123', 3, 1),
(4, 'maria.gomez', 'docente123', 2, 1),
(5, 'lucia.mendoza', 'estudiante123', 3, 1);

-- --------------------------------------------------------
-- 3. Tabla: periodos
-- --------------------------------------------------------
CREATE TABLE `periodos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `estado` varchar(20) DEFAULT 'ACTIVO',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_periodo_nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `periodos` (`id`, `nombre`, `fecha_inicio`, `fecha_fin`, `estado`) VALUES
(1, '2026-I', '2026-03-01', '2026-07-15', 'ACTIVO'),
(2, '2026-II', '2026-08-15', '2026-12-20', 'INACTIVO');

-- --------------------------------------------------------
-- 4. Tabla: grados
-- --------------------------------------------------------
CREATE TABLE `grados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `nivel` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_grado_nivel` (`nombre`, `nivel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `grados` (`id`, `nombre`, `nivel`) VALUES
(1, '1er Año', 'Secundaria'),
(2, '2do Año', 'Secundaria'),
(3, '3er Año', 'Secundaria'),
(4, '4to Año', 'Secundaria'),
(5, '5to Año', 'Secundaria'),
(6, '1er Grado', 'Primaria'),
(7, '2do Grado', 'Primaria');

-- --------------------------------------------------------
-- 5. Tabla: secciones
-- --------------------------------------------------------
CREATE TABLE `secciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) NOT NULL,
  `capacidad` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_seccion_nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `secciones` (`id`, `nombre`, `capacidad`) VALUES
(1, 'A', 30),
(2, 'B', 30),
(3, 'C', 25);

-- --------------------------------------------------------
-- 6. Tabla: aulas
-- --------------------------------------------------------
CREATE TABLE `aulas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) NOT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  `capacidad` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_aula_codigo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `aulas` (`id`, `codigo`, `ubicacion`, `capacidad`) VALUES
(1, 'A-101', 'Pabellón Principal - Piso 1', 30),
(2, 'A-102', 'Pabellón Principal - Piso 1', 30),
(3, 'B-201', 'Pabellón Ciencias - Piso 2', 35);

-- --------------------------------------------------------
-- 7. Tabla: cursos
-- --------------------------------------------------------
CREATE TABLE `cursos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `grado_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_curso_grado` (`grado_id`),
  CONSTRAINT `fk_curso_grado` FOREIGN KEY (`grado_id`) REFERENCES `grados` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `cursos` (`id`, `nombre`, `descripcion`, `grado_id`) VALUES
(1, 'Matemática', 'Álgebra, Geometría y Aritmética básica', 1),
(2, 'Comunicación', 'Lenguaje, Literatura y Redacción', 1),
(3, 'Ciencia y Tecnología', 'Biología y Física introductoria', 1),
(4, 'Inglés Técnico', 'Comprensión y expresión oral', 1),
(5, 'Historia y Geografía', 'Historia del Perú y Universal', 2);

-- --------------------------------------------------------
-- 8. Tabla: docentes
-- --------------------------------------------------------
CREATE TABLE `docentes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `dni` varchar(15) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `especialidad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_docente_dni` (`dni`),
  UNIQUE KEY `uq_docente_usuario` (`usuario_id`),
  UNIQUE KEY `uq_docente_email` (`email`),
  CONSTRAINT `fk_docente_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `docentes` (`id`, `usuario_id`, `dni`, `nombres`, `apellidos`, `email`, `telefono`, `especialidad`) VALUES
(1, 2, '41238901', 'Juan', 'Pérez', 'jperez@colegio.edu.pe', '987654321', 'Matemáticas'),
(2, 4, '41238902', 'María', 'Gómez', 'mgomez@colegio.edu.pe', '987654322', 'Comunicación');

-- --------------------------------------------------------
-- 9. Tabla: docente_curso
-- --------------------------------------------------------
CREATE TABLE `docente_curso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `docente_id` int(11) NOT NULL,
  `curso_id` int(11) NOT NULL,
  `periodo_id` int(11) NOT NULL,
  `seccion_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_docente_curso` (`curso_id`, `periodo_id`, `seccion_id`),
  KEY `fk_dc_docente` (`docente_id`),
  KEY `fk_dc_periodo` (`periodo_id`),
  KEY `fk_dc_seccion` (`seccion_id`),
  CONSTRAINT `fk_dc_curso` FOREIGN KEY (`curso_id`) REFERENCES `cursos` (`id`),
  CONSTRAINT `fk_dc_docente` FOREIGN KEY (`docente_id`) REFERENCES `docentes` (`id`),
  CONSTRAINT `fk_dc_periodo` FOREIGN KEY (`periodo_id`) REFERENCES `periodos` (`id`),
  CONSTRAINT `fk_dc_seccion` FOREIGN KEY (`seccion_id`) REFERENCES `secciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `docente_curso` (`id`, `docente_id`, `curso_id`, `periodo_id`, `seccion_id`) VALUES
(1, 1, 1, 1, 1),
(2, 2, 2, 1, 1);

-- --------------------------------------------------------
-- 10. Tabla: estudiantes
-- --------------------------------------------------------
CREATE TABLE `estudiantes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `dni` varchar(15) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `apoderado_nombre` varchar(150) DEFAULT NULL,
  `apoderado_telefono` varchar(20) DEFAULT NULL,
  `nivel` varchar(20) DEFAULT NULL,
  `seccion` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_estudiante_dni` (`dni`),
  UNIQUE KEY `uq_estudiante_usuario` (`usuario_id`),
  CONSTRAINT `fk_estudiante_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `estudiantes` (`id`, `usuario_id`, `dni`, `nombres`, `apellidos`, `fecha_nacimiento`, `direccion`, `apoderado_nombre`, `apoderado_telefono`, `nivel`, `seccion`) VALUES
(1, 3, '71230001', 'Carlos', 'Ruiz', '2010-05-15', 'Av. Las Palmeras 123, Lima', 'Elena Ruiz', '991122334', 'Secundaria', 'A'),
(2, 5, '71230002', 'Lucía', 'Mendoza', '2010-09-22', 'Jr. Los Cedros 456, Lima', 'Jorge Mendoza', '991122335', 'Secundaria', 'A'),
(3, NULL, '71230003', 'Pedro', 'Castillo', '2010-02-10', 'Av. Arequipa 789, Lima', 'Rosa Castillo', '991122336', 'Secundaria', 'B');

-- --------------------------------------------------------
-- 11. Tabla: matriculas
-- --------------------------------------------------------
CREATE TABLE `matriculas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `estudiante_id` int(11) NOT NULL,
  `periodo_id` int(11) NOT NULL,
  `grado_id` int(11) NOT NULL,
  `seccion_id` int(11) NOT NULL,
  `fecha_matricula` date NOT NULL,
  `estado` varchar(20) DEFAULT 'ACTIVA',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_matricula_estudiante_periodo` (`estudiante_id`, `periodo_id`),
  KEY `fk_matricula_periodo` (`periodo_id`),
  KEY `fk_matricula_grado` (`grado_id`),
  KEY `fk_matricula_seccion` (`seccion_id`),
  CONSTRAINT `fk_matricula_estudiante` FOREIGN KEY (`estudiante_id`) REFERENCES `estudiantes` (`id`),
  CONSTRAINT `fk_matricula_periodo` FOREIGN KEY (`periodo_id`) REFERENCES `periodos` (`id`),
  CONSTRAINT `fk_matricula_grado` FOREIGN KEY (`grado_id`) REFERENCES `grados` (`id`),
  CONSTRAINT `fk_matricula_seccion` FOREIGN KEY (`seccion_id`) REFERENCES `secciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `matriculas` (`id`, `estudiante_id`, `periodo_id`, `grado_id`, `seccion_id`, `fecha_matricula`, `estado`) VALUES
(1, 1, 1, 1, 1, '2026-03-01', 'ACTIVA'),
(2, 2, 1, 1, 1, '2026-03-02', 'ACTIVA'),
(3, 3, 1, 1, 2, '2026-03-03', 'ACTIVA');

-- --------------------------------------------------------
-- 12. Tabla: notas
-- --------------------------------------------------------
CREATE TABLE `notas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricula_id` int(11) NOT NULL,
  `curso_id` int(11) NOT NULL,
  `unidad` int(11) NOT NULL,
  `calificacion` decimal(5,2) NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_nota_unidad` (`matricula_id`, `curso_id`, `unidad`),
  KEY `fk_nota_curso` (`curso_id`),
  CONSTRAINT `fk_nota_matricula` FOREIGN KEY (`matricula_id`) REFERENCES `matriculas` (`id`),
  CONSTRAINT `fk_nota_curso` FOREIGN KEY (`curso_id`) REFERENCES `cursos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `notas` (`id`, `matricula_id`, `curso_id`, `unidad`, `calificacion`, `fecha_registro`) VALUES
(1, 1, 1, 1, 18.50, '2026-04-10 10:00:00'),
(2, 1, 2, 1, 16.00, '2026-04-10 11:30:00'),
(3, 2, 1, 1, 15.00, '2026-04-10 10:00:00');

-- --------------------------------------------------------
-- 13. Tabla: tipos_pago
-- --------------------------------------------------------
CREATE TABLE `tipos_pago` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `monto_referencial` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `tipos_pago` (`id`, `nombre`, `descripcion`, `monto_referencial`) VALUES
(1, 'Matrícula Anual', 'Derecho de inscripción y matrícula escolar', 350.00),
(2, 'Pensión Mensual', 'Cuota ordinaria de servicio educativo mensual', 450.00),
(3, 'Materiales y Talleres', 'Cuota de laboratorios y materiales didácticos', 150.00);

-- --------------------------------------------------------
-- 14. Tabla: pagos
-- --------------------------------------------------------
CREATE TABLE `pagos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricula_id` int(11) NOT NULL,
  `tipo_pago_id` int(11) NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `fecha_pago` datetime DEFAULT NULL,
  `estado` enum('PENDIENTE','PAGADO','VENCIDO') DEFAULT 'PENDIENTE',
  `numero_operacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_pago_operacion` (`numero_operacion`),
  KEY `fk_pago_matricula` (`matricula_id`),
  KEY `fk_pago_tipo` (`tipo_pago_id`),
  CONSTRAINT `fk_pago_matricula` FOREIGN KEY (`matricula_id`) REFERENCES `matriculas` (`id`),
  CONSTRAINT `fk_pago_tipo` FOREIGN KEY (`tipo_pago_id`) REFERENCES `tipos_pago` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `pagos` (`id`, `matricula_id`, `tipo_pago_id`, `monto`, `fecha_pago`, `estado`, `numero_operacion`) VALUES
(1, 1, 1, 350.00, '2026-03-01 09:15:00', 'PAGADO', 'OP-20260301-001'),
(2, 1, 2, 450.00, '2026-04-05 14:20:00', 'PAGADO', 'OP-20260405-014'),
(3, 1, 2, 450.00, NULL, 'PENDIENTE', NULL),
(4, 2, 1, 350.00, '2026-03-02 11:00:00', 'PAGADO', 'OP-20260302-005'),
(5, 3, 1, 350.00, NULL, 'VENCIDO', NULL);

-- --------------------------------------------------------
-- 15. Tabla: horarios
-- --------------------------------------------------------
CREATE TABLE `horarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `docente_curso_id` int(11) NOT NULL,
  `aula_id` int(11) NOT NULL,
  `dia_semana` enum('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO') NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_horario_dc` (`docente_curso_id`),
  KEY `fk_horario_aula` (`aula_id`),
  CONSTRAINT `fk_horario_dc` FOREIGN KEY (`docente_curso_id`) REFERENCES `docente_curso` (`id`),
  CONSTRAINT `fk_horario_aula` FOREIGN KEY (`aula_id`) REFERENCES `aulas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------
-- 16. Tabla: asistencias
-- --------------------------------------------------------
CREATE TABLE `asistencias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricula_id` int(11) NOT NULL,
  `horario_id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `estado` enum('PRESENTE','FALTA','TARDANZA','JUSTIFICADO') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_asistencia` (`matricula_id`, `horario_id`, `fecha`),
  KEY `fk_asistencia_horario` (`horario_id`),
  CONSTRAINT `fk_asistencia_matricula` FOREIGN KEY (`matricula_id`) REFERENCES `matriculas` (`id`),
  CONSTRAINT `fk_asistencia_horario` FOREIGN KEY (`horario_id`) REFERENCES `horarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
