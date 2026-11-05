# EduManager Pro — Sistema de Gestión Escolar (JSF / Jakarta EE 10)

[![Java 17](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Jakarta EE 10](https://img.shields.io/badge/Jakarta%20EE-10.0-F37024?style=for-the-badge&logo=eclipse-ide&logoColor=white)](https://jakarta.ee/)
[![PrimeFaces 14](https://img.shields.io/badge/PrimeFaces-14.0.9-2E86AB?style=for-the-badge)](https://www.primefaces.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.x%20%7C%20MariaDB-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![GlassFish 7](https://img.shields.io/badge/Server-GlassFish%207.x-006699?style=for-the-badge)](https://glassfish.org/)
[![Maven](https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)

**EduManager Pro** es una plataforma web integral de gestión académica y administrativa escolar desarrollada bajo el ecosistema empresarial **Jakarta EE 10 (Web Profile)** y **Jakarta Server Faces (JSF 4.0 / Facelets)**, con componentes enriquecidos **PrimeFaces 14**.

Diseñada para centralizar y optimizar la administración de instituciones educativas mediante control de acceso basado en roles (**RBAC**), gestión de matrículas, seguimiento de calificaciones, tesorería y analítica en tiempo real.

---

## 🚀 Características Principales

### 👑 Módulo Administrador
* **Dashboard Analítico:** Indicadores KPI en tiempo real (alumnos matriculados, docentes activos, cursos, pagos pendientes) con gráficos dinámicos (barras y pie) filtrables por periodo (Semanal, Mensual, Anual).
* **Gestión de Comunidad Escolar:** Registro y control de estudiantes y docentes con generación automática de credenciales de acceso institucional.
* **Control Académico:** Administración de grados, secciones, periodos lectivos y catálogo de cursos.
* **Matrículas Anuales:** Asignación de estudiantes a grados y secciones con validación de estado activo.
* **Registro de Calificaciones:** Control de notas por unidades con exportación directa a **PDF** y **Excel (XLSX)**.
* **Control de Tesorería:** Registro de conceptos de pago (matrículas, pensiones, materiales), montos y estados (`PAGADO`, `PENDIENTE`, `VENCIDO`) con número de operación único.
* **Seguridad y Usuarios:** Control de cuentas de usuario, asignación de roles y estados (`ACTIVO`/`INACTIVO`).

### 👨‍🏫 Portal Docente
* **Carga Académica:** Visualización de cursos, grados y secciones asignadas.
* **Listado de Alumnos:** Nómina de estudiantes por aula en modo consulta.
* **Calificaciones:** Registro y edición de notas de sus estudiantes asignados.
* **Perfil Institucional:** Actualización de datos de contacto y cambio de contraseña.

### 🎓 Portal Estudiantil
* **Historial Académico:** Consulta de calificaciones por unidad con opción de **descarga de libreta en PDF**.
* **Estado de Cuenta:** Monitoreo transparente de pagos realizados y cuotas pendientes/vencidas con exportación de reporte.
* **Perfil Personal:** Consulta de datos escolares y actualización de credenciales.

---

## 🛠️ Stack Tecnológico

| Capa / Componente | Tecnología | Versión |
| :--- | :--- | :--- |
| **Lenguaje** | Java (LTS) | Java 17 |
| **Plataforma Empresarial** | Jakarta EE Web Profile | 10.0.0 |
| **Framework Web** | Jakarta Faces (JSF) | 4.0 |
| **Componentes UI** | PrimeFaces (Jakarta) | 14.0.9 |
| **Servicios / Lógica** | EJB (Enterprise Java Beans) | 4.0 (`@Stateless`, `@Singleton`) |
| **Persistencia (ORM)** | Jakarta Persistence (JPA) / EclipseLink | 3.0 |
| **Base de Datos** | MySQL / MariaDB | 8.x / 10.4 |
| **Generación de Reportes** | LibrePDF OpenPDF & Apache POI | 2.0.3 / 5.2.5 |
| **Servidor de Aplicaciones** | Eclipse GlassFish | 7.x |
| **Gestión de Compilación** | Apache Maven | 3.x |

---

## 📋 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de contar con el siguiente software instalado:

1. **JDK 17** (Temurin, OpenJDK u Oracle JDK).
2. **Servidor de Aplicaciones GlassFish 7.x** (probado con GlassFish 7.0.x compatible con Jakarta EE 10).
3. **MySQL 8.x** o **MariaDB 10.4+** (vía XAMPP, WAMP o servicio independiente).
4. **IDE recomendado:** Apache NetBeans 18+ o IntelliJ IDEA Ultimate.
5. **Apache Maven 3.8+** (integrado en NetBeans).

---

## ⚙️ Instalación y Puesta en Marcha

### 1. Clonar el Repositorio
```bash
git clone https://github.com/XavierEstefanoVasquezYataco/sistema-gestion-escolar.git
cd sistema-gestion-escolar
```

### 2. Configurar la Base de Datos
1. Inicia el servicio de MySQL / MariaDB (puerto por defecto: `3306`).
2. Abre tu gestor de base de datos preferido (phpMyAdmin, MySQL Workbench, DBeaver o línea de comandos).
3. Ejecuta el script reproducible ubicado en `database/db_colegio.sql`:
```sql
SOURCE database/db_colegio.sql;
```
Esto creará la base de datos `db_colegio`, sus 16 tablas relacionales, restricciones de integridad y datos demo listos para operar.

### 3. Configuración del DataSource
La aplicación utiliza configuración automática mediante anotación empresarial `@DataSourceDefinition` en la clase:
`src/main/java/pe/edu/utp/sistemagestionescolarjsf/config/DataSourceConfig.java`

Verifica que las credenciales coincidan con tu servidor MySQL local:
```java
@DataSourceDefinition(
    name = "java:app/jdbc/ColegioDS",
    className = "com.mysql.cj.jdbc.MysqlDataSource",
    url = "jdbc:mysql://localhost:3306/db_colegio?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
    user = "root",
    password = ""
)
```
*(Modifica `user` y `password` únicamente si tu instalación local tiene contraseña de root).*

### 4. Compilación y Despliegue

#### Desde Apache NetBeans:
1. Abre NetBeans y ve a **File > Open Project**.
2. Selecciona la carpeta del proyecto.
3. Asegúrate de tener agregado el servidor **GlassFish Server 7.x** en la pestaña **Services > Servers**.
4. Haz clic derecho en el proyecto y selecciona **Clean and Build**.
5. Haz clic derecho en el proyecto y selecciona **Run**.

#### Desde línea de comandos con Maven:
```bash
mvn clean package
```
El artefacto resultante `target/SistemaGestionEscolarJSF.war` puede ser desplegado directamente en la consola de administración de GlassFish (`http://localhost:4848`).

### 5. Acceso al Sistema
Una vez desplegado, ingresa desde tu navegador a:
```
http://localhost:8080/SistemaGestionEscolarJSF/
```
*(La aplicación redireccionará automáticamente a `login.xhtml`).*

---

## 🔐 Cuentas de Prueba Preconfiguradas

El script `database/db_colegio.sql` incluye los siguientes usuarios de prueba:

| Rol | Usuario | Contraseña | Descripción de Acceso |
| :--- | :--- | :--- | :--- |
| **Administrador** | `admin` | `admin123` | Control total del sistema, dashboard, configuración de usuarios y tesorería. |
| **Docente** | `juan.perez` | `docente123` | Portal Docente: asignación de cursos, listado de alumnos y registro de notas. |
| **Estudiante** | `carlos.ruiz` | `estudiante123` | Portal Estudiante: consulta de calificaciones en PDF y estado de cuenta financiero. |

---

## 📁 Estructura del Proyecto

```
SistemaGestionEscolarJSF/
├── database/
│   └── db_colegio.sql          # Script DDL + Seed reproducible y saneado
├── src/
│   ├── main/
│   │   ├── java/pe/edu/utp/
│   │   │   ├── config/         # Configuración de DataSource y persistencia
│   │   │   ├── controller/     # Beans CDI (@Named @ViewScoped / @SessionScoped)
│   │   │   ├── dao/            # Capa de acceso a datos JPA (GenericDAO y DAOs)
│   │   │   ├── entity/         # Entidades JPA mapeadas a tablas relacionales
│   │   │   ├── service/        # Lógica de negocio (EJB @Stateless)
│   │   │   └── util/           # AuthFilter, SessionUtils, PasswordUtil
│   │   ├── resources/
│   │   │   └── META-INF/       # persistence.xml (Unidad SistemaEscolarPU)
│   │   └── webapp/
│   │       ├── resources/      # Estilos CSS, imágenes y recursos estáticos
│   │       ├── templates/      # Plantilla maestra template.xhtml
│   │       ├── WEB-INF/        # web.xml, beans.xml, glassfish-web.xml
│   │       └── *.xhtml         # Vistas Facelets por módulo
├── .gitignore                  # Exclusión de artefactos target/ e IDEs
├── pom.xml                     # Gestión de dependencias Jakarta EE y plugins
└── README.md                   # Documentación principal
```

---

## 🛡️ Seguridad Implementada

* **Filtro de Autenticación (`AuthFilter`):** Intercepta todas las peticiones a páginas Facelets (`*.xhtml`), bloqueando accesos no autenticados y redirigiendo al login.
* **Control de Acceso por Roles (RBAC):** Restringe las vistas administrativas (`/usuarios.xhtml`, `/roles.xhtml`, `/matriculas.xhtml`) a usuarios con rol `ADMIN`, y segmenta las vistas exclusivas de `DOCENTE` y `ESTUDIANTE`.
* **Consultas Parametrizadas:** Toda la interacción con la base de datos se realiza a través de JPQL con `TypedQuery` y parámetros seguros, previniendo ataques de Inyección SQL.
* **Confirmación Modal:** Cuadros de diálogo `p:confirmDialog` antes de cualquier operación destructiva de registros.

---

## 📄 Licencia

Este proyecto se distribuye bajo la licencia **MIT**. Consulta el archivo `LICENSE` para más información.

---

## 👨‍💻 Autor

Desarrollado por **[Xavier Estefano Vásquez Yataco](https://github.com/XavierEstefanoVasquezYataco)**  
Estudiante de Ingeniería de Sistemas e Informática — UTP.
