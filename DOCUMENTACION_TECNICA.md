# SISTEMA WEB DE GESTIÓN ESCOLAR - DOCUMENTACIÓN TÉCNICA INTEGRAL

**Institución:** Colegio Andino  
**Proyecto:** Sistema Web de Gestión Escolar Integrado  
**Código del Curso:** 100000I59N  
**Ciclo:** 2026 - Ciclo 1 (Marzo)  
**Fecha de Entrega:** Semana 10  
**Versión del Documento:** 1.0  
**Fecha de Creación:** 24 de Mayo de 2026

---

## TABLA DE CONTENIDOS

1. [INTRODUCCIÓN](#introducción)
2. [OBJETIVOS](#objetivos)
3. [ANÁLISIS DE REQUISITOS](#análisis-de-requisitos)
4. [ARQUITECTURA DEL SISTEMA](#arquitectura-del-sistema)
5. [ESPECIFICACIONES TÉCNICAS](#especificaciones-técnicas)
6. [BASE DE DATOS](#base-de-datos)
7. [MANUAL DE INSTALACIÓN](#manual-de-instalación)
8. [MANUAL DE USUARIO](#manual-de-usuario)
9. [SEGURIDAD Y VALIDACIONES](#seguridad-y-validaciones)
10. [CONCLUSIONES](#conclusiones)

---

## INTRODUCCIÓN

### 1.1 Propósito del Documento

Este documento técnico describe el desarrollo integral del **Sistema Web de Gestión Escolar** para el Colegio Andino. Proporciona especificaciones detalladas, arquitectura, requisitos técnicos, procedimientos de instalación y guías operacionales para administradores y usuarios finales.

### 1.2 Alcance del Proyecto

El sistema abarca la gestión completa de procesos escolares incluyendo:

- **Gestión de Estudiantes:** Registro, actualización y eliminación de datos
- **Gestión de Docentes:** Administración del personal académico
- **Gestión de Cursos:** Configuración de cursos y asignaciones
- **Matrículas:** Proceso de inscripción de estudiantes en cursos
- **Registro de Notas:** Sistema de calificación y evaluación
- **Control de Pagos:** Gestión financiera de pensiones y aranceles
- **Reportes:** Generación de reportes académicos y administrativos
- **Dashboard:** Panel de control con estadísticas en tiempo real

### 1.3 Usuarios Objetivo

- **Administradores:** Control total del sistema
- **Directores:** Supervisión de operaciones académicas
- **Docentes:** Registro de calificaciones y seguimiento de estudiantes
- **Personal Administrativo:** Gestión de matrículas y pagos
- **Padres de Familia:** Acceso a información del estudiante (versión futura)

---

## OBJETIVOS

### Objetivo General

Desarrollar una plataforma web integral que automatice y centralice los procesos administrativos y académicos del Colegio Andino, mejorando la eficiencia operacional, facilitando el acceso a información y proporcionando herramientas de análisis para la toma de decisiones.

### Objetivos Específicos

1. **Automatización de Procesos:** Eliminar trámites manuales mediante formularios digitales
2. **Acceso Centralizado:** Consolidar información en una base de datos única y confiable
3. **Interfaz Intuitiva:** Proporcionar una experiencia de usuario clara y de fácil navegación
4. **Reportes Dinámicos:** Generar informes automáticos para análisis institucional
5. **Seguridad de Datos:** Implementar autenticación y control de acceso robusto
6. **Escalabilidad:** Diseñar arquitectura preparada para crecimiento futuro
7. **Mantenibilidad:** Código limpio y modular para facilitar actualizaciones

---

## ANÁLISIS DE REQUISITOS

### 2.1 Requisitos Funcionales

#### RF-001: Autenticación y Autorización
- El sistema debe validar credenciales de usuario (correo y contraseña)
- Diferentes roles con permisos específicos (Administrador, Director, Docente, Personal)
- Sesiones seguras con tiempo de expiración
- Recuperación de contraseña

#### RF-002: Gestión de Estudiantes
- Crear, leer, actualizar y eliminar registros de estudiantes
- Almacenar: nombres, apellidos, DNI, datos de apoderado, contacto
- Búsqueda y filtrado de estudiantes
- Historial de cambios

#### RF-003: Gestión de Docentes
- Administración completa de datos de docentes
- Asignación de cursos y secciones
- Datos de contacto y especialidades
- Estado de actividad (activo/inactivo)

#### RF-004: Gestión de Cursos
- Crear y gestionar cursos y secciones
- Asignar docentes responsables
- Configurar horarios y aulas
- Establecer períodos académicos

#### RF-005: Proceso de Matrícula
- Inscribir estudiantes en cursos
- Validar disponibilidad de vacantes
- Generar comprobantes de matrícula
- Gestionar cambios de sección

#### RF-006: Registro de Calificaciones
- Ingreso de notas por docente
- Cálculo automático de promedios
- Validación de rangos de calificación (0-20)
- Historial de modificaciones

#### RF-007: Control de Pagos
- Registrar pagos de pensiones y aranceles
- Generar comprobantes
- Seguimiento de pagos pendientes
- Reportes de deuda

#### RF-008: Dashboard y Reportes
- Estadísticas en tiempo real
- Estudiantes activos, matrículas, pagos pendientes
- Reportes en PDF y Excel
- Gráficos estadísticos

#### RF-009: Notificaciones y Alertas
- Alertas de pagos vencidos
- Recordatorios de eventos académicos
- Notificaciones de cambios importantes

### 2.2 Requisitos No Funcionales

#### RNF-001: Rendimiento
- Tiempo de respuesta < 2 segundos para operaciones CRUD
- Sistema capaz de manejar 500 usuarios concurrentes
- Optimización de consultas de base de datos

#### RNF-002: Seguridad
- Encriptación de contraseñas (hash SHA-256 o bcrypt)
- Validación de entrada para prevenir SQL Injection
- HTTPS obligatorio en producción
- Registro de auditoría para cambios críticos

#### RNF-003: Disponibilidad
- Uptime objetivo: 99.5%
- Backup diario automático
- Recuperación de desastres en 4 horas

#### RNF-004: Escalabilidad
- Arquitectura modular permitiendo expansión
- Base de datos optimizada para 10,000+ registros
- Posibilidad de migración a cloud

#### RNF-005: Mantenibilidad
- Código comentado y documentado
- Seguir patrones SOLID
- Estructura de carpetas organizada
- Versionado con Git

#### RNF-006: Usabilidad
- Interfaz responsive (móvil, tablet, desktop)
- Texto claro y instrucciones visibles
- Soporte para idioma español
- Acceso desde navegadores modernos

---

## ARQUITECTURA DEL SISTEMA

### 3.1 Patrón Arquitectónico

El sistema implementa una arquitectura **Three-Tier (Tres Capas)**:

```
┌─────────────────────────────────────────┐
│        CAPA DE PRESENTACIÓN (UI)        │
│   HTML5 / CSS3 / Bootstrap / JavaScript │
│    (Interfaz Web Responsiva)            │
└─────────────────────────────────────────┘
                    ↕
┌─────────────────────────────────────────┐
│      CAPA DE LÓGICA DE NEGOCIO          │
│      JSP / Servlets / Java              │
│    (Procesamiento y Validaciones)       │
└─────────────────────────────────────────┘
                    ↕
┌─────────────────────────────────────────┐
│      CAPA DE DATOS (Persistencia)       │
│         MySQL 8.0+                      │
│    (Almacenamiento e Integridad)        │
└─────────────────────────────────────────┘
```

### 3.2 Componentes Principales

#### 3.2.1 Capa de Presentación
- **JSP (JavaServer Pages):** Páginas dinámicas
- **Bootstrap 5.3.3:** Framework CSS responsivo
- **CSS Personalizado:** Estilos específicos de la institución
- **JavaScript:** Validación del lado del cliente

#### 3.2.2 Capa de Lógica de Negocio
- **Apache Tomcat 10+:** Servidor de aplicaciones
- **Servlets Java:** Controladores de solicitudes
- **JavaBeans:** Encapsulación de objetos de negocio
- **Validaciones:** Reglas de negocio en servidor

#### 3.2.3 Capa de Datos
- **MySQL 8.0+:** Sistema de gestión de base de datos
- **JDBC:** Conexión Java-MySQL
- **Connection Pool:** Gestión eficiente de conexiones

### 3.3 Diagrama de Flujo General

```
Usuario
   ↓
[Navegador Web]
   ↓
[JSP - Interfaz]
   ↓
[Servlet - Controlador]
   ↓
[Validaciones - Reglas de Negocio]
   ↓
[DAO - Acceso a Datos]
   ↓
[MySQL - Base de Datos]
   ↓
[Respuesta → JSP → Usuario]
```

### 3.4 Módulos del Sistema

```
SISTEMA DE GESTIÓN ESCOLAR
│
├── MÓDULO DE AUTENTICACIÓN
│   ├── Login
│   ├── Recuperación de contraseña
│   └── Gestión de sesiones
│
├── MÓDULO DE ADMINISTRACIÓN
│   ├── Gestión de usuarios
│   ├── Gestión de roles y permisos
│   └── Configuración del sistema
│
├── MÓDULO ACADÉMICO
│   ├── Gestión de estudiantes
│   ├── Gestión de docentes
│   ├── Gestión de cursos
│   └── Matrículas
│
├── MÓDULO DE CALIFICACIONES
│   ├── Registro de notas
│   ├── Cálculo de promedios
│   └── Reportes académicos
│
├── MÓDULO FINANCIERO
│   ├── Control de pagos
│   ├── Generación de boletas
│   └── Reportes de deuda
│
└── MÓDULO DE REPORTES
    ├── Reportes académicos
    ├── Reportes financieros
    ├── Reportes administrativos
    └── Exportación de datos
```

---

## ESPECIFICACIONES TÉCNICAS

### 4.1 Entorno de Desarrollo

| Componente | Especificación |
|-----------|-----------------|
| **IDE** | NetBeans 20+ / Eclipse / IntelliJ IDEA |
| **Lenguaje Backend** | Java 11+ (JDK) |
| **Framework Web** | Apache Tomcat 10+ |
| **Lenguaje Frontend** | HTML5, CSS3, JavaScript (ES6+) |
| **Framework CSS** | Bootstrap 5.3.3 |
| **Base de Datos** | MySQL 8.0+ / MariaDB 10.5+ |
| **Control de Versiones** | Git / GitHub |
| **Build Tool** | Maven / Gradle (Opcional) |

### 4.2 Stack Tecnológico Completo

```
Frontend:
├── HTML5
├── CSS3 (Personalizado + Bootstrap)
├── JavaScript (Validación, Interactividad)
└── Bootstrap 5.3.3 (Componentes Responsivos)

Backend:
├── Java 11+
├── JSP (JavaServer Pages)
├── Servlets
├── JavaBeans
└── JDBC

Base de Datos:
├── MySQL 8.0+
├── Stored Procedures (Opcional)
└── Triggers (Opcional)

Servidor:
├── Apache Tomcat 10+
└── Hosting: Servidor propio o Cloud (AWS, Azure, DigitalOcean)

Herramientas:
├── Git (Control de versiones)
├── Maven/Gradle (Gestión de dependencias)
└── JUnit (Testing - Opcional)
```

### 4.3 Dependencias de Java

```xml
<!-- JDBC MySQL -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>

<!-- Apache Commons (Utilidades) -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.12.0</version>
</dependency>

<!-- JUnit (Testing) -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

---

## BASE DE DATOS

### 5.1 Diseño Entidad-Relación (ER)

```
USUARIOS (Tabla Principal)
├── usuarios
│   ├── id_usuario (PK)
│   ├── correo (UNIQUE)
│   ├── contraseña (Hash)
│   ├── rol (FK → roles)
│   ├── estado (activo/inactivo)
│   └── fecha_registro
│
├── estudiantes
│   ├── id_estudiante (PK)
│   ├── id_usuario (FK)
│   ├── nombres
│   ├── apellidos
│   ├── dni (UNIQUE)
│   ├── fecha_nacimiento
│   ├── apoderado_nombre
│   ├── apoderado_telefono
│   ├── apoderado_email
│   └── fecha_inscripcion
│
├── docentes
│   ├── id_docente (PK)
│   ├── id_usuario (FK)
│   ├── nombres
│   ├── apellidos
│   ├── dni (UNIQUE)
│   ├── especialidad
│   ├── telefono
│   ├── estado
│   └── fecha_ingreso
│
├── cursos
│   ├── id_curso (PK)
│   ├── nombre_curso
│   ├── codigo_curso (UNIQUE)
│   ├── descripcion
│   ├── estado
│   └── fecha_creacion
│
├── secciones
│   ├── id_seccion (PK)
│   ├── id_curso (FK)
│   ├── id_docente (FK)
│   ├── nombre_seccion (A, B, C...)
│   ├── grado
│   ├── aula
│   ├── horario
│   ├── capacidad
│   ├── estado
│   └── periodo_academico
│
├── matriculas
│   ├── id_matricula (PK)
│   ├── id_estudiante (FK)
│   ├── id_seccion (FK)
│   ├── estado (activa/cancelada)
│   ├── fecha_matricula
│   └── observaciones
│
├── calificaciones
│   ├── id_calificacion (PK)
│   ├── id_matricula (FK)
│   ├── bimestre (1, 2, 3, 4)
│   ├── nota (0-20)
│   ├── fecha_ingreso
│   ├── id_docente_ingreso (FK)
│   └── observaciones
│
├── pagos
│   ├── id_pago (PK)
│   ├── id_estudiante (FK)
│   ├── concepto (pensión, arancel, etc.)
│   ├── monto (DECIMAL)
│   ├── estado (pagado/pendiente)
│   ├── fecha_vencimiento
│   ├── fecha_pago
│   ├── comprobante_numero
│   └── observaciones
│
├── roles
│   ├── id_rol (PK)
│   ├── nombre_rol
│   └── descripcion
│
└── auditoria
    ├── id_auditoria (PK)
    ├── id_usuario (FK)
    ├── accion (INSERT, UPDATE, DELETE)
    ├── tabla_afectada
    ├── registro_id
    ├── valores_anteriores
    ├── valores_nuevos
    ├── fecha_hora
    └── ip_usuario
```

### 5.2 Script SQL Inicial

```sql
-- Crear Base de Datos
CREATE DATABASE IF NOT EXISTS gestion_escolar;
USE gestion_escolar;

-- Tabla de Roles
CREATE TABLE roles (
    id_rol INT PRIMARY KEY AUTO_INCREMENT,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT
);

-- Tabla de Usuarios
CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    id_rol INT NOT NULL,
    estado ENUM('activo', 'inactivo') DEFAULT 'activo',
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);

-- Tabla de Estudiantes
CREATE TABLE estudiantes (
    id_estudiante INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni VARCHAR(15) NOT NULL UNIQUE,
    fecha_nacimiento DATE,
    apoderado_nombre VARCHAR(100),
    apoderado_telefono VARCHAR(15),
    apoderado_email VARCHAR(100),
    fecha_inscripcion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Tabla de Docentes
CREATE TABLE docentes (
    id_docente INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni VARCHAR(15) NOT NULL UNIQUE,
    especialidad VARCHAR(100),
    telefono VARCHAR(15),
    estado ENUM('activo', 'inactivo') DEFAULT 'activo',
    fecha_ingreso DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Tabla de Cursos
CREATE TABLE cursos (
    id_curso INT PRIMARY KEY AUTO_INCREMENT,
    nombre_curso VARCHAR(100) NOT NULL,
    codigo_curso VARCHAR(20) NOT NULL UNIQUE,
    descripcion TEXT,
    estado ENUM('activo', 'inactivo') DEFAULT 'activo',
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Secciones
CREATE TABLE secciones (
    id_seccion INT PRIMARY KEY AUTO_INCREMENT,
    id_curso INT NOT NULL,
    id_docente INT NOT NULL,
    nombre_seccion VARCHAR(10) NOT NULL,
    grado VARCHAR(50),
    aula VARCHAR(20),
    horario VARCHAR(100),
    capacidad INT DEFAULT 30,
    estado ENUM('activo', 'inactivo') DEFAULT 'activo',
    periodo_academico VARCHAR(20),
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso),
    FOREIGN KEY (id_docente) REFERENCES docentes(id_docente)
);

-- Tabla de Matrículas
CREATE TABLE matriculas (
    id_matricula INT PRIMARY KEY AUTO_INCREMENT,
    id_estudiante INT NOT NULL,
    id_seccion INT NOT NULL,
    estado ENUM('activa', 'cancelada') DEFAULT 'activa',
    fecha_matricula DATETIME DEFAULT CURRENT_TIMESTAMP,
    observaciones TEXT,
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante),
    FOREIGN KEY (id_seccion) REFERENCES secciones(id_seccion)
);

-- Tabla de Calificaciones
CREATE TABLE calificaciones (
    id_calificacion INT PRIMARY KEY AUTO_INCREMENT,
    id_matricula INT NOT NULL,
    bimestre INT NOT NULL,
    nota DECIMAL(3, 1) NOT NULL,
    fecha_ingreso DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_docente_ingreso INT,
    observaciones TEXT,
    FOREIGN KEY (id_matricula) REFERENCES matriculas(id_matricula),
    FOREIGN KEY (id_docente_ingreso) REFERENCES docentes(id_docente)
);

-- Tabla de Pagos
CREATE TABLE pagos (
    id_pago INT PRIMARY KEY AUTO_INCREMENT,
    id_estudiante INT NOT NULL,
    concepto VARCHAR(100) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    estado ENUM('pagado', 'pendiente', 'vencido') DEFAULT 'pendiente',
    fecha_vencimiento DATE,
    fecha_pago DATE,
    comprobante_numero VARCHAR(50),
    observaciones TEXT,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante)
);

-- Tabla de Auditoría
CREATE TABLE auditoria (
    id_auditoria INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    accion ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
    tabla_afectada VARCHAR(50) NOT NULL,
    registro_id INT,
    valores_anteriores JSON,
    valores_nuevos JSON,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    ip_usuario VARCHAR(15),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Índices para optimización
CREATE INDEX idx_dni_estudiante ON estudiantes(dni);
CREATE INDEX idx_dni_docente ON docentes(dni);
CREATE INDEX idx_correo_usuario ON usuarios(correo);
CREATE INDEX idx_id_usuario_estudiante ON estudiantes(id_usuario);
CREATE INDEX idx_id_usuario_docente ON docentes(id_usuario);
CREATE INDEX idx_id_estudiante_matricula ON matriculas(id_estudiante);
CREATE INDEX idx_id_seccion_matricula ON matriculas(id_seccion);
CREATE INDEX idx_id_estudiante_pago ON pagos(id_estudiante);

-- Insertar Roles
INSERT INTO roles (nombre_rol, descripcion) VALUES
('Administrador', 'Control total del sistema'),
('Director', 'Supervisión académica'),
('Docente', 'Gestión de calificaciones'),
('Personal', 'Gestión administrativa');

-- Usuario de prueba
INSERT INTO usuarios (correo, contraseña, id_rol, estado) VALUES
('admin@colegio.com', SHA2('admin123', 256), 1, 'activo');
```

---

## MANUAL DE INSTALACIÓN

### 6.1 Requisitos Previos

- **Java Development Kit (JDK)** 11 o superior
- **Apache Tomcat** versión 10 o superior
- **MySQL** versión 8.0 o superior
- **Git** para control de versiones
- **NetBeans/Eclipse/IntelliJ** como IDE (opcional pero recomendado)
- **Maven** (opcional, para gestión de dependencias)

### 6.2 Pasos de Instalación

#### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/XavierEstefanoVasquezYataco/sistema-gestion-escolar.git
cd sistema-gestion-escolar
```

#### Paso 2: Crear Base de Datos

```bash
# Acceder a MySQL
mysql -u root -p

# Ejecutar el script SQL
source scripts/database_init.sql
```

#### Paso 3: Configurar Conexión a Base de Datos

Crear archivo `src/config/DatabaseConfig.java`:

```java
public class DatabaseConfig {
    public static final String URL = "jdbc:mysql://localhost:3306/gestion_escolar";
    public static final String USER = "root";
    public static final String PASSWORD = "tu_contraseña";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
}
```

#### Paso 4: Descargar Dependencias

```bash
# Si usa Maven
mvn clean install

# O descarga manualmente el JAR de MySQL Connector
# y colócalo en: WebContent/WEB-INF/lib/
```

#### Paso 5: Configurar Tomcat en NetBeans

1. Abrir NetBeans
2. Ir a Tools → Servers
3. Añadir nueva instancia de Tomcat
4. Seleccionar la carpeta de instalación de Tomcat

#### Paso 6: Desplegar la Aplicación

```bash
# Opción 1: Desde NetBeans
# Click derecho en el proyecto → Deploy

# Opción 2: Manual
# Copiar archivo WAR a: $TOMCAT_HOME/webapps/
# O descomprimir el WAR en esa carpeta
```

#### Paso 7: Iniciar el Servidor

```bash
# En Linux/Mac
$TOMCAT_HOME/bin/startup.sh

# En Windows
$TOMCAT_HOME\bin\startup.bat
```

#### Paso 8: Acceder a la Aplicación

```
URL: http://localhost:8080/gestion_escolar
Usuario: admin@colegio.com
Contraseña: admin123
```

### 6.3 Estructura de Carpetas

```
sistema-gestion-escolar/
│
├── src/
│   ├── config/
│   │   └── DatabaseConfig.java
│   ├── servlets/
│   │   ├── LoginServlet.java
│   │   ├── EstudianteServlet.java
│   │   ├── DocenteServlet.java
│   │   ├── CursoServlet.java
│   │   ├── MatriculaServlet.java
│   │   ├── CalificacionServlet.java
│   │   └── PagoServlet.java
│   ├── models/
│   │   ├── Usuario.java
│   │   ├── Estudiante.java
│   │   ├── Docente.java
│   │   ├── Curso.java
│   │   ├── Seccion.java
│   │   ├── Matricula.java
│   │   ├── Calificacion.java
│   │   └── Pago.java
│   ├── dao/
│   │   ├── UsuarioDAO.java
│   │   ├── EstudianteDAO.java
│   │   ├── DocenteDAO.java
│   │   ├── CursoDAO.java
│   │   ├── MatriculaDAO.java
│   │   ├── CalificacionDAO.java
│   │   └── PagoDAO.java
│   ├── services/
│   │   ├── AutenticacionService.java
│   │   ├── ReporteService.java
│   │   └── ValidacionService.java
│   └── utils/
│       ├── DatabaseConnection.java
│       ├── PasswordUtil.java
│       └── DateUtil.java
│
├── WebContent/
│   ├── login.jsp
│   ├── dashboard.jsp
│   ├── registroEstudiante.jsp
│   ├── docentes.jsp
│   ├── cursos.jsp
│   ├── matricula.jsp
│   ├── notas.jsp
│   ├── pagos.jsp
│   ├── reportes.jsp
│   ├── assets/
│   │   ├── css/
│   │   │   └── styles.css
│   │   ├── js/
│   │   │   ├── validaciones.js
│   │   │   └── interactividad.js
│   │   └── images/
│   │       └── logo.png
│   └── WEB-INF/
│       ├── lib/
│       │   └── mysql-connector-java-8.0.33.jar
│       └── web.xml
│
├── scripts/
│   ├── database_init.sql
│   ├── inserts_iniciales.sql
│   └── backup.sh
│
├── docs/
│   ├── DOCUMENTACION_TECNICA.md (este archivo)
│   ├── API_ENDPOINTS.md
│   ├── DIAGRAMA_ER.sql
│   └── MANUAL_USUARIO.md
│
├── tests/
│   ├── EstudianteTest.java
│   ├── DocenteTest.java
│   └── MatriculaTest.java
│
├── README.md
├── .gitignore
├── pom.xml (Si usa Maven)
└── build.xml (Si usa Ant)
```

---

## MANUAL DE USUARIO

### 7.1 Acceso al Sistema

#### Credenciales de Acceso (por Rol)

| Rol | Usuario | Contraseña |
|-----|---------|------------|
| Administrador | admin@colegio.com | admin123 |
| Director | director@colegio.com | director123 |
| Docente | docente@colegio.com | docente123 |
| Personal | personal@colegio.com | personal123 |

**Nota:** Las contraseñas deben cambiarse en el primer acceso.

#### Proceso de Acceso

1. Abrir navegador web
2. Ingresar URL: `http://localhost:8080/gestion_escolar`
3. Completar formulario de login
4. Hacer clic en "Iniciar Sesión"

### 7.2 Módulos y Funcionalidades

#### 7.2.1 Dashboard

**Ubicación:** Panel de inicio después del login

**Funcionalidades:**
- Vista general de estadísticas
- Estudiantes activos
- Matrículas del mes
- Pagos pendientes
- Docentes activos
- Alertas recientes

**Usuarios autorizados:** Administrador, Director

#### 7.2.2 Gestión de Estudiantes

**Ubicación:** Menú → Estudiantes

**Crear Nuevo Estudiante:**
1. Click en botón "Nuevo Estudiante"
2. Completar formulario:
   - Nombres y Apellidos
   - DNI (único)
   - Fecha de Nacimiento
   - Nombre del Apoderado
   - Teléfono del Apoderado
   - Email del Apoderado
3. Click en "Guardar"

**Buscar Estudiante:**
1. Usar campo de búsqueda por DNI o Nombre
2. Click en magnifying glass
3. Ver resultados en tabla

**Editar Estudiante:**
1. Buscar estudiante
2. Click en botón "Editar"
3. Modificar datos requeridos
4. Click en "Actualizar"

**Eliminar Estudiante:**
1. Buscar estudiante
2. Click en botón "Eliminar"
3. Confirmar eliminación

**Usuarios autorizados:** Administrador, Personal

#### 7.2.3 Gestión de Docentes

**Ubicación:** Menú → Docentes

**Funcionalidades:** Similar a gestión de estudiantes
- Crear, leer, actualizar, eliminar
- Asignar especialidad
- Estado activo/inactivo

**Usuarios autorizados:** Administrador, Director

#### 7.2.4 Gestión de Cursos

**Ubicación:** Menú → Cursos

**Crear Nuevo Curso:**
1. Click en "Nuevo Curso"
2. Completar:
   - Nombre del Curso
   - Código (único)
   - Descripción
3. Click en "Guardar"

**Asignar Docente:**
1. Click en curso
2. Click en "Asignar Docente"
3. Seleccionar docente de lista
4. Click en "Confirmar"

**Usuarios autorizados:** Administrador, Director

#### 7.2.5 Proceso de Matrícula

**Ubicación:** Menú → Matrículas

**Matricular Estudiante:**
1. Seleccionar estudiante del dropdown
2. Seleccionar grado/sección
3. Seleccionar sección (A, B, C, etc.)
4. Click en "Matricular"
5. Sistema genera comprobante (descargar en PDF)

**Cambiar Sección:**
1. Buscar matrícula
2. Click en "Modificar"
3. Seleccionar nueva sección
4. Click en "Actualizar"

**Cancelar Matrícula:**
1. Buscar matrícula
2. Click en "Cancelar"
3. Confirmar acción

**Usuarios autorizados:** Administrador, Personal

#### 7.2.6 Registro de Calificaciones

**Ubicación:** Menú → Notas

**Ingresar Calificaciones:**
1. Seleccionar curso/sección
2. Seleccionar bimestre (1, 2, 3, 4)
3. Tabla aparece con estudiantes matriculados
4. Ingresa nota en rango 0-20
5. Click en "Guardar Notas"

**Ver Historial:**
1. Seleccionar estudiante
2. Click en "Ver Historial"
3. Se muestran todas las calificaciones por bimestre

**Modificar Calificación:**
1. Ubicar calificación en tabla
2. Click en "Editar"
3. Modificar nota
4. Click en "Actualizar"

**Generar Reportes:**
1. Click en "Generar Reporte"
2. Seleccionar período
3. Click en "Descargar PDF" o "Descargar Excel"

**Usuarios autorizados:** Docente, Administrador

#### 7.2.7 Control de Pagos

**Ubicación:** Menú → Pagos

**Registrar Pago:**
1. Click en "Nuevo Pago"
2. Completar:
   - Estudiante (búsqueda)
   - Concepto (Pensión, Arancel, etc.)
   - Monto
   - Comprobante (número de recibidor)
3. Click en "Registrar Pago"
4. Sistema genera comprobante

**Ver Estado de Pagos:**
1. Tabla muestra todos los pagos
2. Filtrar por estado (Pagado, Pendiente, Vencido)
3. Click en estudiante para ver detalles

**Generar Reporte de Deuda:**
1. Click en "Reporte de Deuda"
2. Seleccionar período
3. Descargar en PDF o Excel

**Enviar Recordatorio:**
1. Click en pago pendiente
2. Click en "Enviar Recordatorio"
3. Sistema envía email al apoderado

**Usuarios autorizados:** Administrador, Personal

#### 7.2.8 Reportes

**Ubicación:** Menú → Reportes

**Tipos de Reportes Disponibles:**
1. Reporte de Matrículas
2. Reporte de Pagos
3. Reporte de Notas
4. Reporte de Docentes
5. Reporte de Ingresos/Egresos

**Generar Reporte:**
1. Click en tipo de reporte
2. Seleccionar rango de fechas
3. Click en "Generar"
4. Opción para: Ver en pantalla, Descargar PDF, Descargar Excel

**Usuarios autorizados:** Administrador, Director

### 7.3 Navegación General

```
MENÚ LATERAL
├── Inicio (Dashboard)
├── Estudiantes
│   ├── Nuevo Estudiante
│   ├── Listar Estudiantes
│   └── Importar (Excel)
├── Docentes
│   ├── Nuevo Docente
│   ├── Listar Docentes
│   └── Asignaciones
├── Cursos
│   ├── Nuevo Curso
│   ├── Listar Cursos
│   └── Secciones
├── Matrículas
│   ├── Nueva Matrícula
│   ├── Listar Matrículas
│   └── Cambios
├── Notas
│   ├── Registrar Notas
│   ├── Ver Calificaciones
│   └── Reportes
├── Pagos
│   ├── Registrar Pago
│   ├── Ver Estado
│   └── Reportes
├── Reportes
├── Configuración (Admin)
└── Cerrar Sesión
```

---

## SEGURIDAD Y VALIDACIONES

### 8.1 Medidas de Seguridad

#### Autenticación

- **Hash de Contraseñas:** SHA-256 o bcrypt
- **Sesiones Seguras:** Session timeout de 30 minutos
- **Recuperación:** Email de verificación para reset de contraseña

#### Autorización

- **Control de Acceso Basado en Roles (RBAC)**
- Cada ruta verificar rol del usuario
- Restricción de funcionalidades según permisos

#### Protección de Datos

```java
// Ejemplo: Validación de entrada
public class ValidacionService {
    
    // Prevenir SQL Injection
    public static boolean validarDNI(String dni) {
        return dni.matches("^[0-9]{8}$");
    }
    
    // Validar formato email
    public static boolean validarEmail(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }
    
    // Encriptar contraseña
    public static String encriptarContraseña(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    // Validar contraseña
    public static boolean validarContraseña(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
    
    // Prevenir XSS
    public static String sanitizarHTML(String input) {
        return input.replaceAll("[<>\"']", "");
    }
}
```

#### HTTPS

- Obligatorio en producción
- Certificado SSL/TLS válido

#### Auditoría

```sql
-- Tabla de auditoría registra:
- Quién (id_usuario)
- Qué (acción: INSERT, UPDATE, DELETE)
- Dónde (tabla_afectada)
- Cuándo (fecha_hora)
- Desde dónde (ip_usuario)
- Valores anteriores y nuevos
```

### 8.2 Validaciones de Negocio

#### Validación de Estudiantes

```java
public class EstudianteValidador {
    
    public static boolean validarRegistro(Estudiante e) {
        if (e.getNombres() == null || e.getNombres().trim().isEmpty())
            throw new IllegalArgumentException("Nombres requeridos");
        
        if (e.getDni() == null || !e.getDni().matches("^[0-9]{8}$"))
            throw new IllegalArgumentException("DNI inválido");
        
        if (e.getFechaNacimiento() == null)
            throw new IllegalArgumentException("Fecha de nacimiento requerida");
        
        // Verificar edad mínima (6 años)
        int edad = calcularEdad(e.getFechaNacimiento());
        if (edad < 6)
            throw new IllegalArgumentException("Estudiante debe tener mínimo 6 años");
        
        return true;
    }
}
```

#### Validación de Calificaciones

```java
public class CalificacionValidador {
    
    public static boolean validarNota(double nota) {
        if (nota < 0 || nota > 20)
            throw new IllegalArgumentException("Nota debe estar entre 0 y 20");
        
        return true;
    }
    
    public static boolean validarBimestre(int bimestre) {
        if (bimestre < 1 || bimestre > 4)
            throw new IllegalArgumentException("Bimestre inválido");
        
        return true;
    }
}
```

#### Validación de Matrículas

```java
public class MatriculaValidador {
    
    public static boolean validarCapacidad(Seccion seccion, int estudiantesActuales) {
        if (estudiantesActuales >= seccion.getCapacidad())
            throw new IllegalArgumentException("Sección completa");
        
        return true;
    }
    
    public static boolean validarDuplicado(int idEstudiante, int idSeccion) {
        // Verificar que el estudiante no esté ya matriculado
        if (verificarMatricula(idEstudiante, idSeccion))
            throw new IllegalArgumentException("Estudiante ya está matriculado");
        
        return true;
    }
}
```

### 8.3 Validaciones de Entrada (Frontend)

```javascript
// validaciones.js

// Validar DNI (formato)
function validarDNI(dni) {
    return /^[0-9]{8}$/.test(dni);
}

// Validar Email
function validarEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

// Validar Teléfono
function validarTelefono(telefono) {
    return /^[0-9]{9}$/.test(telefono);
}

// Validar Nota
function validarNota(nota) {
    const n = parseFloat(nota);
    return !isNaN(n) && n >= 0 && n <= 20;
}

// Prevenir campos vacíos
function validarFormulario(formulario) {
    const campos = formulario.querySelectorAll('[required]');
    for (let campo of campos) {
        if (campo.value.trim() === '') {
            alert(`${campo.name} es requerido`);
            return false;
        }
    }
    return true;
}
```

---

## CONCLUSIONES

### 9.1 Logros del Proyecto

1. ✅ **Sistema Integral:** Cobertura completa de procesos escolares
2. ✅ **Interfaz Moderna:** Diseño responsive con Bootstrap 5
3. ✅ **Seguridad Robuusta:** Múltiples capas de protección
4. ✅ **Mantenibilidad:** Código modular y bien documentado
5. ✅ **Escalabilidad:** Arquitectura preparada para crecimiento
6. ✅ **Eficiencia:** Operaciones CRUD optimizadas

### 9.2 Recomendaciones Futuras

#### Corto Plazo (Semanas 11-14)
- [ ] Implementar portales para padres de familia
- [ ] Sistema de notificaciones por email automático
- [ ] Generación de certificados digitales
- [ ] Backups automáticos diarios

#### Mediano Plazo (Próximos 3-6 meses)
- [ ] Aplicación móvil (Android/iOS)
- [ ] Integración con proveedores de pago online
- [ ] Sistema de biblioteca digital
- [ ] Chat de soporte en tiempo real

#### Largo Plazo (6-12 meses)
- [ ] Migración a arquitectura microservicios
- [ ] Machine Learning para predicción de deserción
- [ ] Analytics avanzado con Big Data
- [ ] Integración con ministerio de educación

### 9.3 Mantenimiento y Soporte

**Responsabilidades:**

| Aspecto | Frecuencia | Responsable |
|--------|-----------|------------|
| Backups | Diariamente | Sistema automático |
| Actualizaciones de seguridad | Mensualmente | Administrador |
| Limpieza de logs | Semanalmente | Script automático |
| Revisión de auditoría | Mensualmente | Director/Admin |
| Testing | Antes de cada release | QA Team |

### 9.4 Contacto y Soporte

Para reportes de errores, solicitudes de mejoras o dudas técnicas:

**Email:** soporte@colegiandino.edu.pe  
**Teléfono:** +51 (1) XXXX-XXXX  
**Horario:** Lunes a Viernes, 8:00 AM - 5:00 PM

---

## APÉNDICES

### A. Glosario de Términos

- **JSP:** JavaServer Pages
- **CRUD:** Create, Read, Update, Delete
- **DAO:** Data Access Object
- **SQL:** Structured Query Language
- **JDBC:** Java Database Connectivity
- **HTML:** HyperText Markup Language
- **CSS:** Cascading Style Sheets
- **API:** Application Programming Interface
- **HTTP/HTTPS:** Hypertext Transfer Protocol (Secure)
- **JSON:** JavaScript Object Notation

### B. Referencias Técnicas

- [Java Documentation](https://docs.oracle.com/javase/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Bootstrap 5 Documentation](https://getbootstrap.com/docs/5.0/)
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-10.0-doc/)
- [OWASP Security Guidelines](https://owasp.org/)

### C. Versionado del Documento

| Versión | Fecha | Autor | Cambios |
|---------|-------|-------|---------|
| 1.0 | 2026-05-24 | Xavier Estefano Vásquez Yataco | Documento inicial |

---

**FIN DEL DOCUMENTO**

*Este documento es confidencial y está destinado únicamente al uso interno de Colegio Andino. Su reproducción sin autorización está prohibida.*
