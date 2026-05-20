# Hotel Paraná - Backend (API REST)

Este repositorio contiene la lógica de negocio y la capa de persistencia del sistema de gestión hotelera, desarrollado en **Java con Spring Boot**.

## Tecnologías utilizadas
* **Java 17**
* **Spring Boot**
* **Maven** (Gestión de dependencias)
* **PostgreSQL** (Base de Datos Relacional)
* **JDBC / Patrón DAO** para la persistencia.

## Persistencia y Seguridad
El sistema ha sido migrado de archivos planos a una base de datos relacional para mayor robustez:
* **PostgreSQL**: Se utiliza para el almacenamiento persistente de Huéspedes, Habitaciones y Reservas.
* **Autenticación**: El sistema cuenta con un endpoint de Login que valida las credenciales contra la base de datos.
* **CORS**: Configurado para permitir la comunicación fluida con el frontend en Next.js.

## Instalación y Ejecución
1. Clonar el repositorio.
2. Configurar la base de datos en el archivo `application.properties` (URL, usuario y contraseña de Postgres).
3. Abrir el proyecto en **NetBeans**.
4. Ejecutar la clase principal `TpsoloApplication.java`.