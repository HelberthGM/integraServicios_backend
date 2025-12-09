# 🧩 IntegraServicios — Sistema de Gestión de Recursos Universitarios

**Desarrollado por: Lazarus**

## 📘 Descripción general

**IntegraServicios** es un sistema modular basado en **microservicios** que permite la gestión integral de recursos en entornos universitarios.
El objetivo es ofrecer una solución escalable y flexible para administrar **reservas, préstamos y calificaciones de recursos** (equipos, salas, laboratorios, etc.), con capacidad para expandirse a nuevos servicios en el futuro.

El proyecto se desarrolla dentro del curso **Seminario de Ingeniería de Software**, siguiendo buenas prácticas de desarrollo, documentación y despliegue con **Spring Boot** y **Docker**.

---

## ⚙️ Arquitectura general

IntegraServicios adopta una arquitectura distribuida basada en microservicios que se comunican entre sí mediante **REST APIs** y **Spring Cloud Eureka**.

Cada microservicio es **independiente**, posee su propia base de datos y se registra automáticamente en el **Eureka Server** para el descubrimiento de servicios.

### 🧱 Componentes principales:

| Componente                     | Descripción                                                           |
| ------------------------------ | --------------------------------------------------------------------- |
| **Eureka Server**              | Registro centralizado de servicios (Service Discovery).               |
| **Recursos Service**           | Gestiona los recursos disponibles (equipos, salas, materiales, etc.). |
| **Reservas/Préstamos Service** | Administra las reservas y préstamos de los recursos.                  |
| **Calificaciones Service**     | Permite calificar o valorar los recursos utilizados.                  |

> 🔹 Cada microservicio incluye controladores REST, servicios, repositorios, manejo de migraciones con Flyway y conexión a su propia base de datos SQL.

---
## 🧩​ Endpoints
### Endpoints de Ratings

| Endpoint | Método | Descripción |
|----------|--------|-------------|
| /ratings | POST | Crear rating |
| /ratings/{id} | GET | Traer rating por ID |
| /ratings/reservation/{reservationId} | GET | Traer rating por reservationId |
| /ratings/{id} | PUT | Actualizar rating |
| /ratings/{id} | DELETE | Borrar rating |
| /ratings/average | GET | Promedio global |
| /ratings/average/{reservationId} | GET | Promedio por reservationId |

### Endpoints de Resources

| Endpoint | Método | Descripción |
|----------|--------|-------------|
| /api/resources | GET | Obtener todos los recursos |
| /api/resources/{id} | GET | Obtener recurso por ID |
| /api/resources | POST | Crear un nuevo recurso |
| /api/resources/{id} | PUT | Actualizar recurso existente |
| /api/resources/{id} | DELETE | Eliminar recurso por ID |

## 🧰 Tecnologías utilizadas

| Tipo                                      | Herramienta                               |
| ----------------------------------------- | ----------------------------------------- |
| **Framework principal**                   | Spring Boot                               |
| **Comunicación entre servicios**          | Spring Cloud OpenFeign                    |
| **Descubrimiento de servicios**           | Spring Cloud Netflix Eureka               |
| **Persistencia de datos**                 | Spring Data JPA                           |
| **Migraciones de base de datos**          | Flyway                                    |
| **Seguridad (planeado)**                  | Spring Security + JWT                     |
| **Monitoreo (planeado)**                  | Spring Boot Actuator                      |
| **Cache (planeado)**                      | Spring Boot Starter Cache                 |
| **Contenedores**                          | Docker / Docker Compose                   |
| **Gestor de dependencias**                | Maven                                     |
| **Base de datos** 			    | PostgreSQL		  				        |
| **Utilidades**                            | Lombok, YAML configuration                |

---

## 🗂️ Estructura del repositorio

```
/integra-servicios/
 ├── eureka-server/             # Servidor de registro Eureka
 │    ├── src/
 │    └── pom.xml
 ├── recursos-service/          # Microservicio de gestión de recursos
 │    ├── src/
 │    ├── pom.xml
 │    └── Dockerfile
 ├── reservas-service/          # Microservicio de reservas y préstamos
 │    ├── src/
 │    ├── pom.xml
 │    └── Dockerfile
 ├── calificaciones-service/    # Microservicio de calificaciones
 │    ├── src/
 │    ├── pom.xml
 │    └── Dockerfile
 ├── docker-compose.yml         # Orquestación de servicios y bases de datos
 ├── README.md                  # Este archivo
 └── pom.xml                    # (Opcional) POM padre para versiones comunes
```

---

## 🐳 Ejecución local con Docker (opcional)

1. **Construir las imágenes:**

   ```bash
   docker-compose build
   ```

2. **Levantar todos los servicios:**

   ```bash
   docker-compose up
   ```

3. **Verificar el Eureka Server:**
   Accede a [http://localhost:8761](http://localhost:8761)
   Allí podrás ver registrados los microservicios activos.

---

## 🧩 Cómo agregar un nuevo microservicio

1. **Crea una nueva carpeta** en la raíz del proyecto:

   ```
   mkdir nuevo-servicio
   ```

2. **Inicializa un proyecto Spring Boot** con dependencias mínimas:

   * `spring-boot-starter-web`
   * `spring-boot-starter-data-jpa`
   * `flyway-core`
   * `spring-cloud-starter-netflix-eureka-client`
   * `spring-cloud-starter-openfeign`
   * `lombok`

3. **Configura el `application.yml`:**

   * Define un nombre de aplicación único (`spring.application.name`).
   * Asigna un puerto diferente (`server.port`).
   * Conecta su propia base de datos.
   * Regístralo en Eureka con:

     ```yaml
     eureka:
       client:
         serviceUrl:
           defaultZone: http://localhost:8761/eureka/
     ```

4. **Crea los paquetes básicos:**

   ```
   controller/
   service/
   repository/
   model/
   ```

5. **Agrega el nuevo servicio al `docker-compose.yml`** para levantarlo junto con los demás.

---

## 🧠 Casos de uso principales

1. **Gestión de recursos:** Crear, actualizar y consultar recursos disponibles en la universidad.
2. **Reservas y préstamos:** Permitir a los usuarios reservar o solicitar préstamos de recursos.
3. **Calificación de recursos:** Evaluar la calidad o estado de los recursos tras su uso.

> Cada caso de uso se implementa como endpoints REST en su respectivo microservicio, asegurando independencia y escalabilidad.

---

## 🧾 Autores

**Lazarus** — Empresa desarrolladora del sistema *IntegraServicios*.
Proyecto académico desarrollado para la materia *Seminario de Ingeniería de Software*.

---
