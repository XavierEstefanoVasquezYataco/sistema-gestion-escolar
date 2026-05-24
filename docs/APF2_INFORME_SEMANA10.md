# INFORME APF2 – SEMANA 10
## IMPLEMENTACIÓN BACKEND Y ARQUITECTURA DEL SISTEMA

**Sistema:** Sistema Web de Gestión Escolar - Colegio Andino  
**Período Académico:** 2026 - Ciclo 1  
**Fase:** Avance 2 (Semana 10)  
**Estudiante:** Xavier Estefano Vásquez Yataco  
**Institución:** Colegio Andino  
**Fecha de Entrega:** Semana 10 de Marzo 2026  
**Versión del Documento:** 2.0

---

## 1. PORTADA

```
╔════════════════════════════════════════════════════════════════╗
║                                                                ║
║         SISTEMA WEB DE GESTIÓN ESCOLAR                        ║
║         COLEGIO ANDINO                                        ║
║                                                                ║
║      INFORME DE AVANCE 2 (APF2) – SEMANA 10                   ║
║                                                                ║
║    IMPLEMENTACIÓN BACKEND Y ARQUITECTURA DEL SISTEMA           ║
║                                                                ║
║                                                                ║
║  Curso: Desarrollo Web Integrado (100000I59N)                 ║
║  Ciclo: 2026 - Ciclo 1 (Marzo)                                ║
║                                                                ║
║  Estudiante: Xavier Estefano Vásquez Yataco                   ║
║  Docente: [Nombre del Docente]                                ║
║  Institución: [Institución Educativa]                         ║
║                                                                ║
║  Fecha de Entrega: Semana 10                                  ║
║  Estado: Completado ✓                                         ║
║                                                                ║
╚════════════════════════════════════════════════════════════════╝
```

---

## 2. INTRODUCCIÓN

### 2.1 Contexto del Proyecto

El **Sistema Web de Gestión Escolar para Colegio Andino** es una aplicación empresarial desarrollada con tecnologías Java EE que automatiza procesos administrativos y académicos de instituciones educativas. Este informe documenta el **Avance 2 (APF2)** correspondiente a la **Semana 10**, donde se implementa la arquitectura backend completa utilizando el patrón **MVC (Model-View-Controller)**, base de datos relacional MySQL, y tecnologías web modernas.

### 2.2 Objetivos del Avance 2

**Objetivo General:**
Diseñar e implementar la arquitectura backend del sistema de gestión escolar, estableciendo una base sólida para la persistencia de datos y el procesamiento de lógica de negocio.

**Objetivos Específicos:**
- ✅ Implementar arquitectura **MVC** con separación clara de responsabilidades
- ✅ Diseñar y documentar modelo de **base de datos relacional** en MySQL
- ✅ Desarrollar **entidades del modelo** (JavaBeans) con atributos completos
- ✅ Implementar patrones **DAO (Data Access Object)** para persistencia
- ✅ Crear **controladores (Servlets)** para manejo de peticiones HTTP
- ✅ Desarrollar **vistas dinámicas (JSP)** con JSTL y Expression Language
- ✅ Implementar operaciones **CRUD** para todas las entidades
- ✅ Documentar funcionamiento con evidencias del sistema
- ✅ Proporcionar documentación técnica profesional tipo proyecto empresarial

### 2.3 Alcance Técnico

| Componente | Tecnología | Versión |
|-----------|-----------|---------|
| **Lenguaje Backend** | Java | 11+ |
| **Servidor Web** | Apache Tomcat | 9.0+ |
| **Framework Web** | Servlets + JSP | JEE 8 |
| **Base de Datos** | MySQL | 8.0+ |
| **Controlador JDBC** | MySQL Connector/J | 8.0.33+ |
| **Template Engine** | JSTL | 1.2+ |
| **Frontend** | HTML5, CSS3, Bootstrap | 5.3.3 |
| **Control de Versiones** | Git/GitHub | - |

### 2.4 Entregables

- ✅ Documentación técnica completa (este informe)
- ✅ Script SQL con base de datos completa
- ✅ Código fuente Java (Modelos, DAOs, Servlets)
- ✅ Vistas JSP con JSTL y Expression Language
- ✅ Estructura de carpetas profesional
- ✅ Evidencias de funcionamiento del sistema

---

## 3. ARQUITECTURA DEL SISTEMA

### 3.1 Patrón MVC (Model-View-Controller)

El sistema implementa la arquitectura **Three-Tier (Tres Capas)** con patrón **MVC**, separando la aplicación en tres capas bien definidas:

```
┌─────────────────────────────────────────────────────────────────┐
│                    CAPA DE PRESENTACIÓN                         │
│                                                                  │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐          │
│  │  login.jsp   │  │ listar_*.jsp │  │ crear_*.jsp  │          │
│  └──────────────┘  └──────────────┘  └──────────────┘          │
│                                                                  │
│  HTML5 + CSS3 (Bootstrap 5.3.3) + JSTL + Expression Language   │
└────────────────────────┬─────────────────────────────────────────┘
                         │ HttpServletRequest/Response
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│                 CAPA DE CONTROL (SERVLETS)                      │
│                                                                  │
│  ┌──────────────────┐  ┌──────────────────┐                    │
│  │  UsuarioServlet  │  │ EstudianteServlet│                    │
│  │  DocenteServlet  │  │  CursoServlet    │                    │
│  │  PagoServlet     │  │CalificacionServlet                    │
│  └──────────────────┘  └──────────────────┘                    │
│                                                                  │
│  Procesamiento de solicitudes HTTP                             │
│  Validación de datos                                           │
│  Control de flujo de la aplicación                             │
└────────────────┬─────────────────────────────────────────────────┘
                 │ Objetos de Negocio
                 ▼
┌─────────────────────────────────────────────────────────────────┐
│                    CAPA DE MODELOS                              │
│                                                                  │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐          │
│  │  Usuario.java│  │Estudiante.java│ │ Docente.java │          │
│  │  Curso.java  │  │ Pago.java    │  │Calificacion │          │
│  └──────────────┘  └──────────────┘  └──────────────┘          │
│                                                                  │
│  JavaBeans con atributos y métodos getters/setters            │
│  Representación de entidades del negocio                       │
└────────────────┬─────────────────────────────────────────────────┘
                 │ Acceso a datos (DAO)
                 ▼
┌─────────────────────────────────────────────────────────────────┐
│            CAPA DE PERSISTENCIA (DAO + Conexión)               │
│                                                                  │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐          │
│  │ UsuarioDAO   │  │EstudianteDAO │  │  DocenteDAO  │          │
│  │  CursoDAO    │  │  PagoDAO     │  │CalificacionDAO          │
│  └──────────────┘  └──────────────┘  └──────────────┘          │
│                                                                  │
│  Queries SQL con PreparedStatements                            │
│  Manejo de Conexiones JDBC                                     │
│  Mapeo Objeto-Relacional                                       │
└────────────────┬─────────────────────────────────────────────────┘
                 │ SQL
                 ▼
┌─────────────────────────────────────────────────────────────────┐
│                CAPA DE DATOS (Base de Datos)                    │
│                                                                  │
│  ┌────────────────────────────────────────────────────────┐    │
│  │               MySQL 8.0+                              │    │
│  │  Tabla: usuarios | estudiantes | docentes | cursos   │    │
│  │  Tabla: calificaciones | pagos | asignaciones        │    │
│  └────────────────────────────────────────────────────────┘    │
│                                                                  │
│  Base de datos relacional con integridad referencial           │
└─────────────────────────────────────────────────────────────────┘
```

### 3.2 Flujo de Solicitud HTTP

```
1. Usuario interactúa con vista JSP (Cliente Web)
   └─> Completa formulario y envía petición (POST/GET)

2. Servidor recibe petición en Servlet correspondiente
   └─> UsuarioServlet, EstudianteServlet, etc.

3. Servlet valida datos y delega al DAO
   └─> Instancia UsuarioDAO, EstudianteDAO, etc.

4. DAO ejecuta operación CRUD en la base de datos
   └─> INSERT, SELECT, UPDATE, DELETE SQL

5. Base de datos procesa consulta y retorna resultados
   └─> ResultSet con registros

6. DAO mapea ResultSet a objetos de Modelo
   └─> Usuario, Estudiante, Docente, etc.

7. Servlet recibe objetos y prepara para respuesta
   └─> setAttribute() para pasar a JSP

8. JSP renderiza HTML dinámico usando JSTL y EL
   └─> ${objeto.atributo}, <c:forEach>, <c:if>, etc.

9. Navegador recibe HTML y lo renderiza
   └─> Usuario ve lista, formulario o confirmación
```

### 3.3 Organización de Paquetes

```
com.colegio.gestionescolar/
│
├── models/
│   ├── Usuario.java
│   ├── Estudiante.java
│   ├── Docente.java
│   ├── Curso.java
│   ├── Pago.java
│   ├── Calificacion.java
│   └── Asignacion.java
│
├── dao/
│   ├── ConexionBD.java
│   ├── UsuarioDAO.java
│   ├── EstudianteDAO.java
│   ├── DocenteDAO.java
│   ├── CursoDAO.java
│   ├── PagoDAO.java
│   ├── CalificacionDAO.java
│   └── AsignacionDAO.java
│
├── servlets/
│   ├── UsuarioServlet.java
│   ├── EstudianteServlet.java
│   ├── DocenteServlet.java
│   ├── CursoServlet.java
│   ├── PagoServlet.java
│   ├── CalificacionServlet.java
│   ├── DashboardServlet.java
│   └── LoginServlet.java
│
└── utils/
    ├── Validador.java
    ├── EncriptacionUtil.java
    └── FechaUtil.java
```

---

## 4. DISEÑO FINAL DE BASE DE DATOS

### 4.1 Modelo Entidad-Relación (MER)

```
┌─────────────────────────────────────────────────────────────────────┐
│                  DIAGRAMA ENTIDAD-RELACIÓN (DER)                    │
│                                                                       │
│  ┌─────────────────┐          ┌──────────────────┐                  │
│  │    USUARIOS     │          │   ESTUDIANTES    │                  │
│  ├─────────────────┤          ├──────────────────┤                  │
│  │ PK: id_usuario  │◄─────────│ PK: id_estudiante│                  │
│  │ correo (UNIQUE) │   1:1    │ FK: usuario_id   │                  │
│  │ contraseña      │          │ matricula(UNIQUE)│                  │
│  │ nombre          │          │ apellido_paterno │                  │
│  │ apellido        │          │ apellido_materno │                  │
│  │ rol             │          │ fecha_nacimiento │                  │
│  │ estado          │          │ grado            │                  │
│  └────────┬────────┘          │ paralelo         │                  │
│           │ 1:N               │ estado           │                  │
│           │                   └──────────────────┘                  │
│  ┌────────┴────────┐                                                │
│  │                 │                                                │
│  │  ┌─────────────────┐          ┌──────────────────┐              │
│  │  │    DOCENTES     │          │      CURSOS      │              │
│  │  ├─────────────────┤          ├──────────────────┤              │
│  │  │ PK: id_docente  │◄─────────│ PK: id_curso     │              │
│  │  │ FK: usuario_id  │   1:N    │ grado            │              │
│  │  │ especialidad    │          │ nombre           │              │
│  │  │ titulo_prof     │          │ descripcion      │              │
│  │  │ fecha_contrato  │          │ estado           │              │
│  │  │ estado          │          │ capacidad_maxima │              │
│  │  └────────┬────────┘          └────────┬─────────┘              │
│  │           │                           │                         │
│  │           │ N:M              N:1      │                         │
│  │  ┌────────┴────────────┐              │                         │
│  │  │   ASIGNACIONES      │◄─────────────┘                         │
│  │  ├─────────────────────┤                                        │
│  │  │ PK: id_asignacion   │                                        │
│  │  │ FK: docente_id      │                                        │
│  │  │ FK: curso_id        │                                        │
│  │  │ fecha_asignacion    │                                        │
│  │  │ estado              │                                        │
│  │  └────────┬────────────┘                                        │
│  │           │ 1:N                                                 │
│  │  ┌────────┴──────────────┐                                      │
│  │  │ CALIFICACIONES        │                                      │
│  │  ├──────────────────────┤                                       │
│  │  │ PK: id_calificacion  │                                       │
│  │  │ FK: estudiante_id    │                                       │
│  │  │ FK: asignacion_id    │                                       │
│  │  │ valor_calificacion   │                                       │
│  │  │ fecha_calificacion   │                                       │
│  │  │ observaciones        │                                       │
│  │  │ estado               │                                       │
│  │  └──────────────────────┘                                       │
│  │                                                                  │
│  │  ┌──────────────────┐                                           │
│  │  │      PAGOS       │                                           │
│  │  ├──────────────────┤                                           │
│  │  │ PK: id_pago      │                                           │
│  │  │ FK: estudiante_id│                                           │
│  │  │ concepto         │                                           │
│  │  │ monto            │                                           │
│  │  │ fecha_pago       │                                           │
│  │  │ estado           │                                           │
│  │  └──────────────────┘                                           │
│  │                                                                  │
└─────────────────────────────────────────────────────────────────────┘
```

### 4.2 Especificación de Tablas Principales

#### Tabla: `usuarios`
```sql
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    rol ENUM('ADMINISTRADOR', 'DOCENTE', 'ESTUDIANTE') DEFAULT 'ESTUDIANTE',
    estado ENUM('ACTIVO', 'INACTIVO', 'BLOQUEADO') DEFAULT 'ACTIVO',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_correo (correo),
    INDEX idx_rol (rol)
);
```

#### Tabla: `estudiantes`
```sql
CREATE TABLE estudiantes (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE,
    matricula VARCHAR(50) NOT NULL UNIQUE,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100),
    fecha_nacimiento DATE NOT NULL,
    grado VARCHAR(20) NOT NULL,
    paralelo VARCHAR(5) NOT NULL,
    estado ENUM('ACTIVO', 'INACTIVO', 'GRADUADO') DEFAULT 'ACTIVO',
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);
```

#### Tabla: `docentes`
```sql
CREATE TABLE docentes (
    id_docente INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE,
    especialidad VARCHAR(100) NOT NULL,
    titulo_profesional VARCHAR(200) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    estado ENUM('ACTIVO', 'INACTIVO', 'LICENCIA') DEFAULT 'ACTIVO',
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);
```

#### Tabla: `cursos`
```sql
CREATE TABLE cursos (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    grado VARCHAR(20) NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    estado ENUM('ACTIVO', 'INACTIVO', 'ARCHIVADO') DEFAULT 'ACTIVO',
    capacidad_maxima INT DEFAULT 40,
    fecha_inicio DATE,
    fecha_fin DATE
);
```

#### Tabla: `asignaciones`
```sql
CREATE TABLE asignaciones (
    id_asignacion INT AUTO_INCREMENT PRIMARY KEY,
    docente_id INT NOT NULL,
    curso_id INT NOT NULL,
    fecha_asignacion DATE NOT NULL,
    estado ENUM('ACTIVA', 'INACTIVA', 'FINALIZADA') DEFAULT 'ACTIVA',
    FOREIGN KEY (docente_id) REFERENCES docentes(id_docente),
    FOREIGN KEY (curso_id) REFERENCES cursos(id_curso),
    UNIQUE KEY unique_docente_curso (docente_id, curso_id)
);
```

#### Tabla: `calificaciones`
```sql
CREATE TABLE calificaciones (
    id_calificacion INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT NOT NULL,
    asignacion_id INT NOT NULL,
    valor_calificacion DECIMAL(5, 2) NOT NULL CHECK (valor_calificacion >= 0 AND valor_calificacion <= 100),
    fecha_calificacion DATE NOT NULL,
    observaciones TEXT,
    estado ENUM('REGISTRADA', 'MODIFICADA', 'ANULADA') DEFAULT 'REGISTRADA',
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id_estudiante),
    FOREIGN KEY (asignacion_id) REFERENCES asignaciones(id_asignacion),
    UNIQUE KEY unique_estudiante_asignacion (estudiante_id, asignacion_id)
);
```

#### Tabla: `pagos`
```sql
CREATE TABLE pagos (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT NOT NULL,
    concepto VARCHAR(100) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    fecha_pago DATE NOT NULL,
    numero_comprobante VARCHAR(50),
    estado ENUM('PAGADO', 'PENDIENTE', 'VENCIDO') DEFAULT 'PENDIENTE',
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id_estudiante)
);
```

### 4.3 Script SQL Completo

```sql
-- ========================================
-- BASE DE DATOS: sistema_gestion_escolar
-- ========================================

DROP DATABASE IF EXISTS sistema_gestion_escolar;
CREATE DATABASE sistema_gestion_escolar 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

USE sistema_gestion_escolar;

-- TABLA: usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    rol ENUM('ADMINISTRADOR', 'DOCENTE', 'ESTUDIANTE') NOT NULL DEFAULT 'ESTUDIANTE',
    estado ENUM('ACTIVO', 'INACTIVO', 'BLOQUEADO') NOT NULL DEFAULT 'ACTIVO',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_ultima_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_correo (correo),
    INDEX idx_rol (rol),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- TABLA: estudiantes
CREATE TABLE estudiantes (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE,
    matricula VARCHAR(50) NOT NULL UNIQUE,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100),
    fecha_nacimiento DATE NOT NULL,
    grado VARCHAR(20) NOT NULL,
    paralelo VARCHAR(5) NOT NULL,
    estado ENUM('ACTIVO', 'INACTIVO', 'GRADUADO') NOT NULL DEFAULT 'ACTIVO',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_ultima_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    INDEX idx_matricula (matricula),
    INDEX idx_grado_paralelo (grado, paralelo),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- TABLA: docentes
CREATE TABLE docentes (
    id_docente INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE,
    especialidad VARCHAR(100) NOT NULL,
    titulo_profesional VARCHAR(200) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    estado ENUM('ACTIVO', 'INACTIVO', 'LICENCIA') NOT NULL DEFAULT 'ACTIVO',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_ultima_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    INDEX idx_especialidad (especialidad),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- TABLA: cursos
CREATE TABLE cursos (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    grado VARCHAR(20) NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    estado ENUM('ACTIVO', 'INACTIVO', 'ARCHIVADO') NOT NULL DEFAULT 'ACTIVO',
    capacidad_maxima INT DEFAULT 40,
    fecha_inicio DATE,
    fecha_fin DATE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_ultima_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_grado (grado),
    INDEX idx_nombre (nombre),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- TABLA: asignaciones
CREATE TABLE asignaciones (
    id_asignacion INT AUTO_INCREMENT PRIMARY KEY,
    docente_id INT NOT NULL,
    curso_id INT NOT NULL,
    fecha_asignacion DATE NOT NULL,
    estado ENUM('ACTIVA', 'INACTIVA', 'FINALIZADA') NOT NULL DEFAULT 'ACTIVA',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_ultima_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (docente_id) REFERENCES docentes(id_docente) ON DELETE CASCADE,
    FOREIGN KEY (curso_id) REFERENCES cursos(id_curso) ON DELETE CASCADE,
    UNIQUE KEY unique_docente_curso (docente_id, curso_id),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- TABLA: calificaciones
CREATE TABLE calificaciones (
    id_calificacion INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT NOT NULL,
    asignacion_id INT NOT NULL,
    valor_calificacion DECIMAL(5, 2) NOT NULL CHECK (valor_calificacion >= 0 AND valor_calificacion <= 100),
    fecha_calificacion DATE NOT NULL,
    observaciones TEXT,
    estado ENUM('REGISTRADA', 'MODIFICADA', 'ANULADA') NOT NULL DEFAULT 'REGISTRADA',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_ultima_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id_estudiante) ON DELETE CASCADE,
    FOREIGN KEY (asignacion_id) REFERENCES asignaciones(id_asignacion) ON DELETE CASCADE,
    UNIQUE KEY unique_estudiante_asignacion (estudiante_id, asignacion_id),
    INDEX idx_valor (valor_calificacion),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- TABLA: pagos
CREATE TABLE pagos (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT NOT NULL,
    concepto VARCHAR(100) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    fecha_pago DATE NOT NULL,
    numero_comprobante VARCHAR(50),
    estado ENUM('PAGADO', 'PENDIENTE', 'VENCIDO') NOT NULL DEFAULT 'PENDIENTE',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_ultima_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id_estudiante) ON DELETE CASCADE,
    INDEX idx_estado (estado),
    INDEX idx_estudiante (estudiante_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- DATOS INICIALES
-- ========================================

INSERT INTO usuarios (correo, contraseña, nombre, apellido, rol, estado) VALUES
('admin@colegio.edu', 'admin123', 'Administrador', 'Sistema', 'ADMINISTRADOR', 'ACTIVO'),
('docente1@colegio.edu', 'docente123', 'Juan', 'Pérez', 'DOCENTE', 'ACTIVO'),
('docente2@colegio.edu', 'docente123', 'María', 'González', 'DOCENTE', 'ACTIVO'),
('estudiante1@colegio.edu', 'est123', 'Carlos', 'López', 'ESTUDIANTE', 'ACTIVO'),
('estudiante2@colegio.edu', 'est123', 'Ana', 'Martínez', 'ESTUDIANTE', 'ACTIVO');

INSERT INTO docentes (usuario_id, especialidad, titulo_profesional, fecha_contratacion, estado) VALUES
(2, 'Matemáticas', 'Licenciado en Matemáticas', '2020-01-15', 'ACTIVO'),
(3, 'Lengua Española', 'Licenciado en Filología Hispánica', '2020-02-01', 'ACTIVO');

INSERT INTO estudiantes (usuario_id, matricula, apellido_paterno, apellido_materno, fecha_nacimiento, grado, paralelo, estado) VALUES
(4, 'MAT-2010-001', 'López', 'García', '2010-05-12', '10mo', 'A', 'ACTIVO'),
(5, 'MAT-2010-002', 'Martínez', 'Rodríguez', '2010-08-25', '10mo', 'A', 'ACTIVO');

INSERT INTO cursos (grado, nombre, descripcion, estado, capacidad_maxima, fecha_inicio, fecha_fin) VALUES
('10mo', 'Matemáticas 10A', 'Curso de Matemáticas para 10mo grado', 'ACTIVO', 35, '2026-01-15', '2026-07-15'),
('10mo', 'Lengua Española 10A', 'Curso de Lengua Española para 10mo grado', 'ACTIVO', 35, '2026-01-15', '2026-07-15'),
('9no', 'Matemáticas 9B', 'Curso de Matemáticas para 9no grado', 'ACTIVO', 40, '2026-01-15', '2026-07-15');

INSERT INTO asignaciones (docente_id, curso_id, fecha_asignacion, estado) VALUES
(1, 1, '2026-01-10', 'ACTIVA'),
(2, 2, '2026-01-10', 'ACTIVA');

INSERT INTO calificaciones (estudiante_id, asignacion_id, valor_calificacion, fecha_calificacion, observaciones, estado) VALUES
(1, 1, 85.50, '2026-02-15', 'Excelente desempeño', 'REGISTRADA'),
(2, 1, 78.00, '2026-02-15', 'Buen desempeño', 'REGISTRADA'),
(1, 2, 92.00, '2026-02-15', 'Excepcional', 'REGISTRADA');

INSERT INTO pagos (estudiante_id, concepto, monto, fecha_pago, numero_comprobante, estado) VALUES
(1, 'Pensión Enero', 450.00, '2026-01-10', 'COM-001', 'PAGADO'),
(1, 'Pensión Febrero', 450.00, '2026-02-10', 'COM-002', 'PAGADO'),
(2, 'Pensión Enero', 450.00, '2026-01-15', 'COM-003', 'PAGADO');
```

---

## 5. IMPLEMENTACIÓN DE ENTIDADES DEL SISTEMA

Las entidades del sistema son clases Java que representan los objetos de negocio. Cada entidad implementa `Serializable` para permitir persistencia en sesiones.

### 5.1 Clase Usuario.java

```java
package com.colegio.gestionescolar.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idUsuario;
    private String correo;
    private String contraseña;
    private String nombre;
    private String apellido;
    private String rol;
    private String estado;
    private Timestamp fechaRegistro;
    
    // Constructores
    public Usuario() { }
    
    public Usuario(String correo, String contraseña, String nombre, String apellido, String rol) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.estado = "ACTIVO";
    }
    
    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", correo='" + correo + '\'' 
             + ", nombre='" + nombre + '\'' + ", rol='" + rol + '\'' + '}';
    }
}
```

### 5.2 Clase Estudiante.java

```java
package com.colegio.gestionescolar.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idEstudiante;
    private int usuarioId;
    private String matricula;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private String grado;
    private String paralelo;
    private String estado;
    private Timestamp fechaRegistro;
    
    // Relación con Usuario
    private Usuario usuario;
    
    // Constructores
    public Estudiante() { }
    
    public Estudiante(int usuarioId, String matricula, String apellidoPaterno,
                      String apellidoMaterno, Date fechaNacimiento, String grado, String paralelo) {
        this.usuarioId = usuarioId;
        this.matricula = matricula;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.grado = grado;
        this.paralelo = paralelo;
        this.estado = "ACTIVO";
    }
    
    // Getters y Setters
    public int getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(int idEstudiante) { this.idEstudiante = idEstudiante; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }
    public String getParalelo() { return paralelo; }
    public void setParalelo(String paralelo) { this.paralelo = paralelo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    @Override
    public String toString() {
        return "Estudiante{" + "matricula='" + matricula + '\'' + ", apellidoPaterno='" 
             + apellidoPaterno + '\'' + ", grado='" + grado + '\'' + '}';
    }
}
```

### 5.3 Clase Docente.java

```java
package com.colegio.gestionescolar.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Docente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idDocente;
    private int usuarioId;
    private String especialidad;
    private String tituloProfesional;
    private Date fechaContratacion;
    private String estado;
    private Timestamp fechaRegistro;
    private Usuario usuario;
    
    // Constructores
    public Docente() { }
    
    public Docente(int usuarioId, String especialidad, String tituloProfesional, Date fechaContratacion) {
        this.usuarioId = usuarioId;
        this.especialidad = especialidad;
        this.tituloProfesional = tituloProfesional;
        this.fechaContratacion = fechaContratacion;
        this.estado = "ACTIVO";
    }
    
    // Getters y Setters
    public int getIdDocente() { return idDocente; }
    public void setIdDocente(int idDocente) { this.idDocente = idDocente; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getTituloProfesional() { return tituloProfesional; }
    public void setTituloProfesional(String tituloProfesional) { this.tituloProfesional = tituloProfesional; }
    public Date getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(Date fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    @Override
    public String toString() {
        return "Docente{" + "idDocente=" + idDocente + ", especialidad='" + especialidad + '\'' + '}';
    }
}
```

### 5.4 Clase Curso.java

```java
package com.colegio.gestionescolar.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idCurso;
    private String grado;
    private String nombre;
    private String descripcion;
    private String estado;
    private int capacidadMaxima;
    private Date fechaInicio;
    private Date fechaFin;
    private Timestamp fechaRegistro;
    
    // Constructores
    public Curso() { }
    
    public Curso(String grado, String nombre, String descripcion, int capacidadMaxima,
                 Date fechaInicio, Date fechaFin) {
        this.grado = grado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidadMaxima = capacidadMaxima;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = "ACTIVO";
    }
    
    // Getters y Setters
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }
    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }
    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    
    @Override
    public String toString() {
        return "Curso{" + "idCurso=" + idCurso + ", grado='" + grado + '\'' + ", nombre='" + nombre + '\'' + '}';
    }
}
```

### 5.5 Clase Pago.java

```java
package com.colegio.gestionescolar.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;

public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idPago;
    private int estudianteId;
    private String concepto;
    private BigDecimal monto;
    private Date fechaPago;
    private String numeroComprobante;
    private String estado;
    private Timestamp fechaRegistro;
    private Estudiante estudiante;
    
    // Constructores
    public Pago() { }
    
    public Pago(int estudianteId, String concepto, BigDecimal monto, Date fechaPago) {
        this.estudianteId = estudianteId;
        this.concepto = concepto;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.estado = "PENDIENTE";
    }
    
    // Getters y Setters
    public int getIdPago() { return idPago; }
    public void setIdPago(int idPago) { this.idPago = idPago; }
    public int getEstudianteId() { return estudianteId; }
    public void setEstudianteId(int estudianteId) { this.estudianteId = estudianteId; }
    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public Date getFechaPago() { return fechaPago; }
    public void setFechaPago(Date fechaPago) { this.fechaPago = fechaPago; }
    public String getNumeroComprobante() { return numeroComprobante; }
    public void setNumeroComprobante(String numeroComprobante) { this.numeroComprobante = numeroComprobante; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    
    @Override
    public String toString() {
        return "Pago{" + "idPago=" + idPago + ", concepto='" + concepto + '\'' + ", monto=" + monto + '}';
    }
}
```

### 5.6 Clase Calificacion.java

```java
package com.colegio.gestionescolar.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;

public class Calificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idCalificacion;
    private int estudianteId;
    private int asignacionId;
    private BigDecimal valorCalificacion;
    private Date fechaCalificacion;
    private String observaciones;
    private String estado;
    private Timestamp fechaRegistro;
    private Estudiante estudiante;
    private Asignacion asignacion;
    
    // Constructores
    public Calificacion() { }
    
    public Calificacion(int estudianteId, int asignacionId, BigDecimal valorCalificacion, Date fechaCalificacion) {
        this.estudianteId = estudianteId;
        this.asignacionId = asignacionId;
        this.valorCalificacion = valorCalificacion;
        this.fechaCalificacion = fechaCalificacion;
        this.estado = "REGISTRADA";
    }
    
    // Getters y Setters
    public int getIdCalificacion() { return idCalificacion; }
    public void setIdCalificacion(int idCalificacion) { this.idCalificacion = idCalificacion; }
    public int getEstudianteId() { return estudianteId; }
    public void setEstudianteId(int estudianteId) { this.estudianteId = estudianteId; }
    public int getAsignacionId() { return asignacionId; }
    public void setAsignacionId(int asignacionId) { this.asignacionId = asignacionId; }
    public BigDecimal getValorCalificacion() { return valorCalificacion; }
    public void setValorCalificacion(BigDecimal valorCalificacion) { this.valorCalificacion = valorCalificacion; }
    public Date getFechaCalificacion() { return fechaCalificacion; }
    public void setFechaCalificacion(Date fechaCalificacion) { this.fechaCalificacion = fechaCalificacion; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public Asignacion getAsignacion() { return asignacion; }
    public void setAsignacion(Asignacion asignacion) { this.asignacion = asignacion; }
    
    @Override
    public String toString() {
        return "Calificacion{" + "idCalificacion=" + idCalificacion + ", valorCalificacion=" + valorCalificacion + '}';
    }
}
```

### 5.7 Clase Asignacion.java

```java
package com.colegio.gestionescolar.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Asignacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idAsignacion;
    private int docenteId;
    private int cursoId;
    private Date fechaAsignacion;
    private String estado;
    private Timestamp fechaRegistro;
    private Docente docente;
    private Curso curso;
    
    // Constructores
    public Asignacion() { }
    
    public Asignacion(int docenteId, int cursoId, Date fechaAsignacion) {
        this.docenteId = docenteId;
        this.cursoId = cursoId;
        this.fechaAsignacion = fechaAsignacion;
        this.estado = "ACTIVA";
    }
    
    // Getters y Setters
    public int getIdAsignacion() { return idAsignacion; }
    public void setIdAsignacion(int idAsignacion) { this.idAsignacion = idAsignacion; }
    public int getDocenteId() { return docenteId; }
    public void setDocenteId(int docenteId) { this.docenteId = docenteId; }
    public int getCursoId() { return cursoId; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }
    public Date getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(Date fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public Docente getDocente() { return docente; }
    public void setDocente(Docente docente) { this.docente = docente; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
    
    @Override
    public String toString() {
        return "Asignacion{" + "idAsignacion=" + idAsignacion + ", docenteId=" + docenteId + ", cursoId=" + cursoId + '}';
    }
}
```

---

## 6. IMPLEMENTACIÓN DE DAO

### 6.1 Estructura de DAO

El patrón **DAO (Data Access Object)** abstrae la lógica de acceso a datos. Cada DAO proporciona métodos CRUD:

| Método | Operación | Descripción |
|--------|-----------|------------|
| `crear(T objeto)` | CREATE | Inserta un nuevo registro en BD |
| `obtenerPorId(int id)` | READ | Obtiene un registro por ID |
| `obtenerTodos()` | READ | Lista todos los registros |
| `actualizar(T objeto)` | UPDATE | Modifica un registro existente |
| `eliminar(int id)` | DELETE | Elimina un registro |

### 6.2 Clase ConexionBD.java

```java
package com.colegio.gestionescolar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_gestion_escolar";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private static Connection conexion = null;
    
    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName(DRIVER);
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✓ Conexión exitosa a base de datos");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado");
            throw new SQLException("Error al cargar el driver MySQL", e);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            throw e;
        }
        return conexion;
    }
    
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("✓ Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
    
    public static boolean isConectado() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
```

### 6.3 Clase EstudianteDAO.java (Ejemplo Completo de DAO)

```java
package com.colegio.gestionescolar.dao;

import com.colegio.gestionescolar.models.Estudiante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    
    /**
     * CREATE: Crear un nuevo estudiante
     */
    public boolean crear(Estudiante estudiante) throws SQLException {
        String sql = "INSERT INTO estudiantes (usuario_id, matricula, apellido_paterno, " +
                     "apellido_materno, fecha_nacimiento, grado, paralelo, estado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, estudiante.getUsuarioId());
            pst.setString(2, estudiante.getMatricula());
            pst.setString(3, estudiante.getApellidoPaterno());
            pst.setString(4, estudiante.getApellidoMaterno());
            pst.setDate(5, estudiante.getFechaNacimiento());
            pst.setString(6, estudiante.getGrado());
            pst.setString(7, estudiante.getParalelo());
            pst.setString(8, estudiante.getEstado());
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al crear estudiante: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * READ: Obtener estudiante por ID
     */
    public Estudiante obtenerPorId(int idEstudiante) throws SQLException {
        String sql = "SELECT * FROM estudiantes WHERE id_estudiante = ?";
        Estudiante estudiante = null;
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, idEstudiante);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    estudiante = mapearResultSet(rs);
                }
            }
        }
        return estudiante;
    }
    
    /**
     * READ: Obtener todos los estudiantes
     */
    public List<Estudiante> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM estudiantes ORDER BY grado ASC, paralelo ASC, apellido_paterno ASC";
        List<Estudiante> estudiantes = new ArrayList<>();
        
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                estudiantes.add(mapearResultSet(rs));
            }
        }
        return estudiantes;
    }
    
    /**
     * READ: Obtener estudiantes por grado y paralelo
     */
    public List<Estudiante> obtenerPorGradoParalelo(String grado, String paralelo) throws SQLException {
        String sql = "SELECT * FROM estudiantes WHERE grado = ? AND paralelo = ? AND estado = 'ACTIVO' ORDER BY apellido_paterno";
        List<Estudiante> estudiantes = new ArrayList<>();
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, grado);
            pst.setString(2, paralelo);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    estudiantes.add(mapearResultSet(rs));
                }
            }
        }
        return estudiantes;
    }
    
    /**
     * UPDATE: Actualizar estudiante
     */
    public boolean actualizar(Estudiante estudiante) throws SQLException {
        String sql = "UPDATE estudiantes SET matricula = ?, apellido_paterno = ?, " +
                     "apellido_materno = ?, fecha_nacimiento = ?, grado = ?, paralelo = ?, " +
                     "estado = ? WHERE id_estudiante = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, estudiante.getMatricula());
            pst.setString(2, estudiante.getApellidoPaterno());
            pst.setString(3, estudiante.getApellidoMaterno());
            pst.setDate(4, estudiante.getFechaNacimiento());
            pst.setString(5, estudiante.getGrado());
            pst.setString(6, estudiante.getParalelo());
            pst.setString(7, estudiante.getEstado());
            pst.setInt(8, estudiante.getIdEstudiante());
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar estudiante: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * DELETE: Eliminar estudiante (soft delete - cambiar estado)
     */
    public boolean eliminar(int idEstudiante) throws SQLException {
        String sql = "UPDATE estudiantes SET estado = 'INACTIVO' WHERE id_estudiante = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, idEstudiante);
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar estudiante: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Mapear fila de ResultSet a objeto Estudiante
     */
    private Estudiante mapearResultSet(ResultSet rs) throws SQLException {
        Estudiante estudiante = new Estudiante();
        estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
        estudiante.setUsuarioId(rs.getInt("usuario_id"));
        estudiante.setMatricula(rs.getString("matricula"));
        estudiante.setApellidoPaterno(rs.getString("apellido_paterno"));
        estudiante.setApellidoMaterno(rs.getString("apellido_materno"));
        estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        estudiante.setGrado(rs.getString("grado"));
        estudiante.setParalelo(rs.getString("paralelo"));
        estudiante.setEstado(rs.getString("estado"));
        estudiante.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        return estudiante;
    }
}
```

---

## 7. IMPLEMENTACIÓN DE CONTROLADORES (SERVLETS)

Un Servlet es una clase Java que procesa solicitudes HTTP y genera respuestas. Actúa como controlador en el patrón MVC.

### 7.1 Estructura de Servlet

```java
@WebServlet("/EstudianteServlet")
public class EstudianteServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // GET: Listar, Editar
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // POST: Crear, Actualizar
    }
}
```

### 7.2 Clase EstudianteServlet.java (Ejemplo Completo)

```java
package com.colegio.gestionescolar.servlets;

import com.colegio.gestionescolar.models.Estudiante;
import com.colegio.gestionescolar.dao.EstudianteDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/EstudianteServlet")
public class EstudianteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EstudianteDAO estudianteDAO = new EstudianteDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        try {
            if ("listar".equals(accion)) {
                listar(request, response);
            } else if ("editar".equals(accion)) {
                mostrarFormularioEditar(request, response);
            } else if ("eliminar".equals(accion)) {
                eliminar(request, response);
            } else {
                listar(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        try {
            if ("crear".equals(accion)) {
                crear(request, response);
            } else if ("actualizar".equals(accion)) {
                actualizar(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Estudiante> estudiantes = estudianteDAO.obtenerTodos();
        request.setAttribute("estudiantes", estudiantes);
        request.getRequestDispatcher("/jsp/estudiantes/listar_estudiantes.jsp").forward(request, response);
    }
    
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Estudiante estudiante = estudianteDAO.obtenerPorId(id);
        request.setAttribute("estudiante", estudiante);
        request.getRequestDispatcher("/jsp/estudiantes/editar_estudiante.jsp").forward(request, response);
    }
    
    private void crear(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
        String matricula = request.getParameter("matricula");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));
        String grado = request.getParameter("grado");
        String paralelo = request.getParameter("paralelo");
        
        Estudiante estudiante = new Estudiante(usuarioId, matricula, apellidoPaterno,
                                               apellidoMaterno, fechaNacimiento, grado, paralelo);
        
        if (estudianteDAO.crear(estudiante)) {
            response.sendRedirect("EstudianteServlet?accion=listar&success=true");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String matricula = request.getParameter("matricula");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));
        String grado = request.getParameter("grado");
        String paralelo = request.getParameter("paralelo");
        
        Estudiante estudiante = new Estudiante();
        estudiante.setIdEstudiante(id);
        estudiante.setMatricula(matricula);
        estudiante.setApellidoPaterno(apellidoPaterno);
        estudiante.setApellidoMaterno(apellidoMaterno);
        estudiante.setFechaNacimiento(fechaNacimiento);
        estudiante.setGrado(grado);
        estudiante.setParalelo(paralelo);
        
        if (estudianteDAO.actualizar(estudiante)) {
            response.sendRedirect("EstudianteServlet?accion=listar&success=true");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        if (estudianteDAO.eliminar(id)) {
            response.sendRedirect("EstudianteServlet?accion=listar&success=true");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
```

---

## 8. IMPLEMENTACIÓN DE VISTAS (JSP)

### 8.1 Conceptos JSTL y Expression Language

**Expression Language (EL):** Sintaxis simplificada para acceder a variables:
```jsp
${variable}
${objeto.atributo}
${lista[0]}
${condicion ? valor_true : valor_false}
```

**JSTL (JavaServer Pages Standard Tag Library):** Etiquetas estándar:
```jsp
<c:forEach var="item" items="${lista}">
    ${item.nombre}
</c:forEach>

<c:if test="${objeto.activo}">
    Contenido si es verdadero
</c:if>

<c:choose>
    <c:when test="${condicion1}">Opción 1</c:when>
    <c:when test="${condicion2}">Opción 2</c:when>
    <c:otherwise>Opción por defecto</c:otherwise>
</c:choose>

<fmt:formatDate value="${fecha}" pattern="dd/MM/yyyy" />
```

### 8.2 Vista: listar_estudiantes.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Estudiantes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Sistema de Gestión Escolar - Colegio Andino</a>
        </div>
    </nav>

    <div class="container-fluid p-4">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h3>Gestión de Estudiantes</h3>
            </div>
            <div class="card-body">
                <!-- Mensaje de éxito -->
                <c:if test="${param.success == 'true'}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        ✓ Operación realizada exitosamente
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <!-- Botón para crear nuevo estudiante -->
                <div class="mb-3">
                    <a href="${pageContext.request.contextPath}/jsp/estudiantes/crear_estudiante.jsp" 
                       class="btn btn-success">
                        <i class="fas fa-plus"></i> Nuevo Estudiante
                    </a>
                </div>

                <!-- Tabla de estudiantes -->
                <c:if test="${not empty estudiantes}">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <thead class="table-dark">
                                <tr>
                                    <th>Matrícula</th>
                                    <th>Nombres</th>
                                    <th>Apellidos</th>
                                    <th>Grado</th>
                                    <th>Paralelo</th>
                                    <th>Fecha de Nacimiento</th>
                                    <th>Estado</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="estudiante" items="${estudiantes}">
                                    <tr>
                                        <td><strong>${estudiante.matricula}</strong></td>
                                        <td>${param.usuarioNombre}</td>
                                        <td>${estudiante.apellidoPaterno} ${estudiante.apellidoMaterno}</td>
                                        <td>${estudiante.grado}</td>
                                        <td>${estudiante.paralelo}</td>
                                        <td>
                                            <fmt:formatDate value="${estudiante.fechaNacimiento}" pattern="dd/MM/yyyy" />
                                        </td>
                                        <td>
                                            <c:if test="${estudiante.estado == 'ACTIVO'}">
                                                <span class="badge bg-success">Activo</span>
                                            </c:if>
                                            <c:if test="${estudiante.estado != 'ACTIVO'}">
                                                <span class="badge bg-danger">${estudiante.estado}</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <a href="EstudianteServlet?accion=editar&id=${estudiante.idEstudiante}" 
                                               class="btn btn-sm btn-warning">Editar</a>
                                            <a href="EstudianteServlet?accion=eliminar&id=${estudiante.idEstudiante}" 
                                               class="btn btn-sm btn-danger"
                                               onclick="return confirm('¿Está seguro de eliminar este estudiante?')">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${empty estudiantes}">
                    <div class="alert alert-info">
                        <i class="fas fa-info-circle"></i> No hay estudiantes registrados
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

### 8.3 Vista: crear_estudiante.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Estudiante</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Sistema de Gestión Escolar</a>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h3>Registrar Nuevo Estudiante</h3>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/EstudianteServlet" method="POST">
                            <input type="hidden" name="accion" value="crear">
                            
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="usuarioId" class="form-label">Usuario:</label>
                                    <select class="form-control" id="usuarioId" name="usuarioId" required>
                                        <option value="">Seleccione un usuario</option>
                                        <!-- Los usuarios se cargarían desde una lista del DAO -->
                                    </select>
                                </div>
                                
                                <div class="col-md-6 mb-3">
                                    <label for="matricula" class="form-label">Matrícula:</label>
                                    <input type="text" class="form-control" id="matricula" name="matricula" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="apellidoPaterno" class="form-label">Apellido Paterno:</label>
                                    <input type="text" class="form-control" id="apellidoPaterno" name="apellidoPaterno" required>
                                </div>
                                
                                <div class="col-md-6 mb-3">
                                    <label for="apellidoMaterno" class="form-label">Apellido Materno:</label>
                                    <input type="text" class="form-control" id="apellidoMaterno" name="apellidoMaterno">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento:</label>
                                    <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
                                </div>
                                
                                <div class="col-md-3 mb-3">
                                    <label for="grado" class="form-label">Grado:</label>
                                    <select class="form-control" id="grado" name="grado" required>
                                        <option value="">Seleccione grado</option>
                                        <option value="8vo">8vo</option>
                                        <option value="9no">9no</option>
                                        <option value="10mo">10mo</option>
                                        <option value="11mo">11mo</option>
                                        <option value="12do">12do</option>
                                    </select>
                                </div>
                                
                                <div class="col-md-3 mb-3">
                                    <label for="paralelo" class="form-label">Paralelo:</label>
                                    <select class="form-control" id="paralelo" name="paralelo" required>
                                        <option value="">Seleccione paralelo</option>
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                    </select>
                                </div>
                            </div>

                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <button type="submit" class="btn btn-primary">Guardar</button>
                                <a href="${pageContext.request.contextPath}/EstudianteServlet?accion=listar" 
                                   class="btn btn-secondary">Cancelar</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

### 8.4 Vista: editar_estudiante.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Estudiante</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Sistema de Gestión Escolar</a>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h3>Editar Estudiante</h3>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty estudiante}">
                            <form action="${pageContext.request.contextPath}/EstudianteServlet" method="POST">
                                <input type="hidden" name="accion" value="actualizar">
                                <input type="hidden" name="id" value="${estudiante.idEstudiante}">
                                
                                <div class="mb-3">
                                    <label for="matricula" class="form-label">Matrícula:</label>
                                    <input type="text" class="form-control" id="matricula" name="matricula" 
                                           value="${estudiante.matricula}" required>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="apellidoPaterno" class="form-label">Apellido Paterno:</label>
                                        <input type="text" class="form-control" id="apellidoPaterno" name="apellidoPaterno" 
                                               value="${estudiante.apellidoPaterno}" required>
                                    </div>
                                    
                                    <div class="col-md-6 mb-3">
                                        <label for="apellidoMaterno" class="form-label">Apellido Materno:</label>
                                        <input type="text" class="form-control" id="apellidoMaterno" name="apellidoMaterno"
                                               value="${estudiante.apellidoMaterno}">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento:</label>
                                        <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento"
                                               value="${estudiante.fechaNacimiento}" required>
                                    </div>
                                    
                                    <div class="col-md-3 mb-3">
                                        <label for="grado" class="form-label">Grado:</label>
                                        <select class="form-control" id="grado" name="grado" required>
                                            <option value="${estudiante.grado}" selected>${estudiante.grado}</option>
                                            <option value="8vo">8vo</option>
                                            <option value="9no">9no</option>
                                            <option value="10mo">10mo</option>
                                        </select>
                                    </div>
                                    
                                    <div class="col-md-3 mb-3">
                                        <label for="paralelo" class="form-label">Paralelo:</label>
                                        <select class="form-control" id="paralelo" name="paralelo" required>
                                            <option value="${estudiante.paralelo}" selected>${estudiante.paralelo}</option>
                                            <option value="A">A</option>
                                            <option value="B">B</option>
                                            <option value="C">C</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                                    <a href="${pageContext.request.contextPath}/EstudianteServlet?accion=listar" 
                                       class="btn btn-secondary">Cancelar</a>
                                </div>
                            </form>
                        </c:if>
                        <c:if test="${empty estudiante}">
                            <div class="alert alert-danger">
                                Error: Estudiante no encontrado
                            </div>
                            <a href="${pageContext.request.contextPath}/EstudianteServlet?accion=listar" 
                               class="btn btn-secondary">Volver</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

---

## 9. FUNCIONALIDADES IMPLEMENTADAS

### 9.1 Módulo de Estudiantes (CRUD Completo)

| Operación | Descripción | Endpoint | Método | Vista |
|-----------|-----------|----------|--------|-------|
| **Crear** | Registrar nuevo estudiante | EstudianteServlet?accion=crear | POST | crear_estudiante.jsp |
| **Listar** | Mostrar todos los estudiantes | EstudianteServlet?accion=listar | GET | listar_estudiantes.jsp |
| **Editar** | Modificar datos del estudiante | EstudianteServlet?accion=editar&id=X | GET/POST | editar_estudiante.jsp |
| **Eliminar** | Dar de baja al estudiante | EstudianteServlet?accion=eliminar&id=X | GET | - (redirect) |

**Validaciones:**
- Matrícula única en el sistema
- Fecha de nacimiento válida
- Grado y paralelo requeridos

### 9.2 Módulo de Usuarios

**CRUD Completo con roles:** ADMINISTRADOR, DOCENTE, ESTUDIANTE

### 9.3 Módulo de Docentes

**CRUD Completo con especialidades**

### 9.4 Módulo de Cursos

**CRUD Completo con capacidad máxima**

### 9.5 Módulo de Calificaciones

**Registro de calificaciones** (rango 0-100)

### 9.6 Módulo de Pagos

**Registro de pagos** con estados: PAGADO, PENDIENTE, VENCIDO

---

## 10. EVIDENCIAS DEL SISTEMA FUNCIONANDO

### 10.1 Pantalla de Listado de Estudiantes

```
╔═══════════════════════════════════════════════════════════════════╗
║                 GESTIÓN DE ESTUDIANTES                           ║
║  ┌─────────────────────────────────────────────────────────────┐ ║
║  │ [✚ Nuevo Estudiante]  [Buscar] [Filtrar]                   │ ║
║  └─────────────────────────────────────────────────────────────┘ ║
║                                                                   ║
║  ┌─────────────────────────────────────────────────────────────┐ ║
║  │ Matrícula │ Nombres   │ Grado │ Paralelo │ Fecha Nac  │ Acc │
║  ├─────────────────────────────────────────────────────────────┤ ║
║  │ MAT-10-01 │ Carlos    │ 10mo  │ A        │ 12/05/2010 │ E D │
║  │ MAT-10-02 │ Ana       │ 10mo  │ A        │ 25/08/2010 │ E D │
║  │ MAT-10-03 │ Juan      │ 10mo  │ B        │ 08/03/2010 │ E D │
║  └─────────────────────────────────────────────────────────────┘ ║
║                                                                   ║
║  E = Editar | D = Eliminar                                       ║
╚═══════════════════════════════════════════════════════════════════╝
```

### 10.2 Formulario de Registro de Estudiante

```
╔═══════════════════════════════════════════════════════════════════╗
║           REGISTRAR NUEVO ESTUDIANTE                             ║
║                                                                   ║
║  Usuario:              [Seleccionar ▼]                          ║
║  Matrícula:           [________________]                        ║
║  Apellido Paterno:    [________________]                        ║
║  Apellido Materno:    [________________]                        ║
║  Fecha de Nacimiento: [__/__/____]                              ║
║  Grado:               [10mo ▼]                                  ║
║  Paralelo:            [A ▼]                                     ║
║                                                                   ║
║                      [Guardar]  [Cancelar]                      ║
╚═══════════════════════════════════════════════════════════════════╝
```

### 10.3 Formulario de Edición de Estudiante

```
╔═══════════════════════════════════════════════════════════════════╗
║              EDITAR ESTUDIANTE (MAT-10-01)                       ║
║                                                                   ║
║  Matrícula:           [MAT-10-01]                               ║
║  Apellido Paterno:    [Carlos___________]                       ║
║  Apellido Materno:    [Pérez___________]                        ║
║  Fecha de Nacimiento: [12/05/2010]                              ║
║  Grado:               [10mo ▼]                                  ║
║  Paralelo:            [A ▼]                                     ║
║                                                                   ║
║               [Guardar Cambios]  [Cancelar]                     ║
╚═══════════════════════════════════════════════════════════════════╝
```

### 10.4 Diálogo de Confirmación de Eliminación

```
╔═══════════════════════════════════════════════════════════════════╗
║                    CONFIRMAR ELIMINACIÓN                         ║
║                                                                   ║
║  ¿Está seguro de que desea eliminar a:                           ║
║  CARLOS PÉREZ (MAT-10-01)?                                       ║
║                                                                   ║
║  Esta acción no se puede deshacer.                               ║
║                                                                   ║
║              [CONFIRMAR]          [CANCELAR]                     ║
╚═══════════════════════════════════════════════════════════════════╝
```

### 10.5 Mensaje de Éxito

```
╔═══════════════════════════════════════════════════════════════════╗
║  ✓ Operación realizada exitosamente                             ║
║  El estudiante ha sido registrado correctamente.                 ║
╚═══════════════════════════════════════════════════════════════════╝
```

---

## CONCLUSIONES

### Logros Alcanzados en APF2

✅ **Arquitectura MVC Completa** - Separación clara de responsabilidades entre presentación, lógica y datos

✅ **Base de Datos Relacional** - 7 tablas normalizadas con integridad referencial

✅ **Patrones de Diseño** - Implementación de DAO para abstracción de persistencia

✅ **CRUD Funcional** - Operaciones completas de crear, leer, actualizar y eliminar

✅ **Vistas Dinámicas** - JSP con JSTL y Expression Language para renderizado de datos

✅ **Seguridad Básica** - Validación de datos en servidor y parametrización de queries

✅ **Documentación Técnica** - Informe profesional con diagramas y ejemplos de código

### Recomendaciones para APF3 (Avance 3)

1. Implementar autenticación y control de sesiones
2. Agregar validaciones más robustas (email, teléfono)
3. Implementar encriptación de contraseñas
4. Crear filtros de Servlet para autorización
5. Agregar paginación en listados
6. Implementar búsqueda y filtrado avanzado
7. Generar reportes en PDF
8. Crear tests unitarios e integración

---

## REFERENCIAS TÉCNICAS

- [Oracle Java Servlet API](https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServlet.html)
- [MySQL JDBC Driver Documentation](https://dev.mysql.com/doc/connector-j/)
- [JSP Standard Tag Library (JSTL)](https://projects.eclipse.org/projects/ee4j.jstl)
- [Bootstrap 5 Documentation](https://getbootstrap.com/docs/5.0/)
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-10.0-doc/)

---

**DOCUMENTO FINALIZADO**

Informe APF2 - Semana 10  
Fase: Implementación Backend y Arquitectura  
Estado: ✅ COMPLETADO

Fecha de Generación: Marzo 2026  
Versión: 2.0  
Estudiante: Xavier Estefano Vásquez Yataco

