# Documentación Técnica de Arquitectura — EduManager Pro

## 1. Visión General de la Arquitectura

**EduManager Pro** implementa una arquitectura multicapa desacoplada y orientada al estándar empresarial **Jakarta EE 10**, siguiendo los principios de la arquitectura en capas (*Layered Architecture*) y el patrón **MVC (Modelo - Vista - Controlador)**.

```mermaid
graph TD
    subgraph Capa de Presentacion [Capa de Presentación - JSF 4.0 / PrimeFaces 14]
        Vistas["Vistas Facelets (.xhtml)"]
        Beans["Managed Beans CDI (@Named @ViewScoped)"]
        Filtro["Filtro de Seguridad HTTP (AuthFilter)"]
    end

    subgraph Capa de Negocio [Capa de Negocio - EJB 4.0]
        Services["Servicios Empresariales (@Stateless EJB)"]
    end

    subgraph Capa de Persistencia [Capa de Acceso a Datos - JPA 3.0]
        GenericDAO["GenericDAOImpl<T, ID>"]
        DAOs["DAOs Específicos (@Stateless)"]
        Entidades["Entidades JPA (@Entity)"]
    end

    subgraph Capa de Datos [Almacenamiento Relacional]
        MySQL[("MySQL 8.x / MariaDB 10.4")]
    end

    Vistas <--> Beans
    Filtro --> Vistas
    Beans --> Services
    Services --> DAOs
    DAOs --> GenericDAO
    GenericDAO --> Entidades
    DAOs <--> MySQL
```

---

## 2. Descripción de Capas

### 2.1. Capa de Presentación (Frontend & Controladores)
* **Facelets (XHTML):** Motor de plantillas declarativo que compone la interfaz mediante composición (`<ui:composition>`, `<ui:define>`, `<ui:insert>`). Todas las páginas administrativas heredan de `templates/template.xhtml`.
* **PrimeFaces 14 (Jakarta Classifier):** Conjunto de componentes interactivos con soporte AJAX integrado (`p:dataTable`, `p:dialog`, `p:toolbar`, `p:selectOneMenu`, `p:barChart`, `p:pieChart`).
* **CDI Managed Beans:** Controladores anotados con `@Named` y `@ViewScoped` (o `@SessionScoped` para el contexto de login). Gestionan el estado de la vista, la navegación y orquestan la invocación hacia la capa de servicios.

### 2.2. Capa de Negocio (Servicios EJB)
* **Enterprise Java Beans Stateless (`@Stateless`):** Implementan las reglas de negocio y transaccionalidad JTA.
* **Inyección de Dependencias (`@Inject`):** Comunicación desacoplada entre controladores y servicios, facilitando pruebas y mantenimiento.
* **Lógica de Generación de Identidad:** En `EstudianteServiceImpl` y `DocenteServiceImpl` se generan automáticamente los identificadores de usuario (`nombre.apellido` o `d.nombre.apellido`) vinculados a sus respectivos registros.

### 2.3. Capa de Persistencia (DAOs & JPA)
* **GenericDAO Pattern:** `GenericDAO<T, ID>` define las operaciones CRUD fundamentales (`create`, `update`, `delete`, `findById`, `findAll`).
* **EntityManager:** Inyectado mediante `@PersistenceContext(unitName = "SistemaEscolarPU")`.
* **Consultas Parametrizadas:** Búsquedas especializadas resueltas con `TypedQuery<T>` y parámetros nombrados para evitar vulnerabilidades de inyección SQL.

---

## 3. Modelo Relacional y Entidades

El sistema gestiona 16 entidades relacionales agrupadas por dominios:

```mermaid
erDiagram
    ROLES ||--o{ USUARIOS : "asigna rol"
    USUARIOS ||--o| DOCENTES : "cuenta de acceso"
    USUARIOS ||--o| ESTUDIANTES : "cuenta de acceso"
    
    GRADOS ||--o{ CURSOS : "pertenece a"
    PERIODOS ||--o{ DOCENTE_CURSO : "periodo lectivo"
    SECCIONES ||--o{ DOCENTE_CURSO : "seccion asignada"
    DOCENTES ||--o{ DOCENTE_CURSO : "dicta"
    CURSOS ||--o{ DOCENTE_CURSO : "impartido en"
    
    ESTUDIANTES ||--o{ MATRICULAS : "registra"
    PERIODOS ||--o{ MATRICULAS : "periodo lectivo"
    GRADOS ||--o{ MATRICULAS : "grado"
    SECCIONES ||--o{ MATRICULAS : "seccion"
    
    MATRICULAS ||--o{ NOTAS : "calificaciones"
    CURSOS ||--o{ NOTAS : "curso evaluado"
    
    MATRICULAS ||--o{ PAGOS : "comprobantes"
    TIPOS_PAGO ||--o{ PAGOS : "concepto de cobro"
    
    DOCENTE_CURSO ||--o{ HORARIOS : "horario de clase"
    AULAS ||--o{ HORARIOS : "aula asignada"
    MATRICULAS ||--o{ ASISTENCIAS : "asistencia"
    HORARIOS ||--o{ ASISTENCIAS : "sesion"
```

---

## 4. Flujo de Autenticación y Autorización (RBAC)

```mermaid
sequenceDiagram
    autonumber
    actor Usuario
    participant Browser as Navegador Web
    participant Filter as AuthFilter (@WebFilter)
    participant LoginBean as LoginBean (@SessionScoped)
    participant Service as UsuarioService
    participant Session as HttpSession

    Usuario->>Browser: Ingresa a una ruta *.xhtml
    Browser->>Filter: HTTP GET /dashboard.xhtml
    alt No hay sesión activa y no es login
        Filter-->>Browser: Redirección 302 a /login.xhtml
    else Hay sesión activa
        Filter->>Filter: Evalúa Rol (ADMIN, DOCENTE, ESTUDIANTE)
        alt Ruta permitida para el Rol
            Filter-->>Browser: Continúa cadena (chain.doFilter)
        else Ruta no autorizada
            Filter-->>Browser: Redirección 302 a /dashboard.xhtml
        end
    end

    Usuario->>Browser: Envia credenciales (login.xhtml)
    Browser->>LoginBean: login()
    LoginBean->>Service: autenticar(usuario, password)
    Service-->>LoginBean: Retorna Usuario válido
    LoginBean->>Session: setAttribute("usuarioLogueado", usuario)
    LoginBean-->>Browser: Redirección a dashboard.xhtml
```

---

## 5. Matriz de Permisos por Rol

| Módulo / Vista | Administrador (`ADMIN`) | Docente (`DOCENTE`) | Estudiante (`ESTUDIANTE`) |
| :--- | :---: | :---: | :---: |
| `/login.xhtml` | Público | Público | Público |
| `/dashboard.xhtml` | Acceso Total (Gráficos y Métricas) | Banner de bienvenida | Banner de bienvenida |
| `/usuarios.xhtml` | CRUD Completo | Denegado | Denegado |
| `/roles.xhtml` | CRUD Completo | Denegado | Denegado |
| `/estudiantes.xhtml` | CRUD Completo | Denegado | Denegado |
| `/docentes.xhtml` | CRUD Completo | Denegado | Denegado |
| `/cursos.xhtml` | CRUD Completo | Denegado | Denegado |
| `/matriculas.xhtml` | CRUD Completo | Denegado | Denegado |
| `/notas.xhtml` | CRUD Completo / Exportación | CRUD Completo / Exportación | Denegado |
| `/pagos.xhtml` | CRUD Completo / Exportación | CRUD Completo / Exportación | Denegado |
| `/perfil-docente.xhtml` | Denegado | Consulta y Edición Propia | Denegado |
| `/mis-cursos.xhtml` | Denegado | Visualización y Lista de Alumnos | Denegado |
| `/mi-perfil.xhtml` | Denegado | Denegado | Consulta y Edición Propia |
| `/mis-notas.xhtml` | Denegado | Denegado | Consulta y Descarga PDF |
| `/mis-pagos.xhtml` | Denegado | Denegado | Consulta de Estado de Cuenta |

---

## 6. Configuración de Base de Datos y Persistencia

### Unidad de Persistencia (`persistence.xml`)
* **Nombre de Unidad:** `SistemaEscolarPU`
* **Tipo de Transacción:** `JTA` (manejada por GlassFish)
* **JNDI DataSource:** `java:app/jdbc/ColegioDS`
* **Estrategia DDL:** `none` (el esquema es administrado mediante el script reproducible SQL `database/db_colegio.sql`).

---

## 7. Pruebas de Compilación y Verificación

La compilación se verifica mediante Maven contra el JDK 17:
```bash
mvn clean test-compile
```
* **Módulos compilados:** 91 clases Java.
* **Resultado:** `BUILD SUCCESS`.
