# 🛒 E-commerce Microservices Backend

**Generated:** 2025-07-07  
**Technologies:** Java, Spring Boot, Spring Security, Spring Cloud, Feign, Eureka, JWT, Maven, Docker

---

## 📐 Architecture Overview

This project follows a **microservices-based architecture** using the following services:

- **auth-service**: Handles authentication and issues JWT tokens.
- **client-service**: Manages client data (CRUD).
- **detail-service**: Handles order detail records.
- **order-service**: Manages order creation and retrieval, integrates client and product data.
- **product-service**: Manages product catalog.
- **eureka-server**: Service discovery using Netflix Eureka.
- **security-common**: Shared module for JWT validation, filters, and Feign token propagation.

Each service is self-contained and secured via JWT. Communication is achieved using **Spring Cloud OpenFeign** and tokens are passed through using a shared security interceptor.

---

## ⚙️ Technologies & Libraries

- **Java 17**
- **Spring Boot 3.2+**
- **Spring Cloud (Eureka, Feign)**
- **Spring Security + JWT**
- **Lombok**
- **Postman (for testing)**
- **Maven** (multi-module)
- **Docker** (optional for runtime)
- **IntelliJ IDEA**

---

## 🔌 Endpoints by Service

### `auth-service`
```http
POST   /auth/register
POST   /auth/login
```

### `client-service`
```http
GET    /api/v1/clients
GET    /api/v1/clients/<built-in function id>
POST   /api/v1/clients
PUT    /api/v1/clients/<built-in function id>
DELETE /api/v1/clients/<built-in function id>
```

### `detail-service`
```http
GET    /api/v1/order-details
GET    /api/v1/order-details/<built-in function id>
POST   /api/v1/order-details
PUT    /api/v1/order-details/<built-in function id>
DELETE /api/v1/order-details/<built-in function id>
```

### `order-service`
```http
GET    /api/v1/orders
GET    /api/v1/orders/<built-in function id>
GET    /api/v1/orders/with-client
POST   /api/v1/orders
PUT    /api/v1/orders/<built-in function id>
DELETE /api/v1/orders/<built-in function id>
```

### `product-service`
```http
GET    /api/v1/products
GET    /api/v1/products/<built-in function id>
```

---

## 🛠 How to Open & Run in IntelliJ IDEA

### 1. 🧾 Prerequisites

- Java 17 installed
- Maven installed
- IntelliJ IDEA (Ultimate recommended)

### 2. 📥 Import Project

- Open IntelliJ IDEA
- Click on `File > Open`
- Select the root folder: `/ecommerce`
- Let IntelliJ import all Maven modules

### 3. 🚀 Run Services

Each service has its own main class, e.g.:

```java
com.mreyes.ecommerce.productservice.ProductServiceApplication
```

Right-click on the main class and select `Run`.

### 4. 🧪 Test with Postman

- First call: `POST /auth/login` → receive JWT token
- Include `Authorization: Bearer <token>` in headers
- Call protected endpoints

---

## 🧱 Design Pattern Highlights

- **Domain-Driven Design (DDD)**
- **Separation of Concerns** via `dto`, `entity`, `service`, `controller`
- **Feign Interceptor** for token propagation
- **Centralized JWT Validation** via `security-common`
- **Decentralized Authorization** at each service

---

## ✅ Ready to Use

You're now ready to develop, test, and extend your Spring Boot microservice e-commerce backend.



---

## 🔐 Flujo de Autenticación con JWT

### 1. 📥 Registro

```http
POST /auth/register
Content-Type: application/json

{
  "username": "user1",
  "password": "password123"
}
```

✔️ Crea un nuevo user y lo guarda en base de datos.

---

### 2. 🔑 Login

```http
POST /auth/login
Content-Type: application/json

{
  "username": "user1",
  "password": "password123"
}
```

✔️ Si las credenciales son válidas, el sistema responde con un token JWT:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### 3. 🔒 Calling protected services

Al consumir cualquier endpoint protegido (por ejemplo `/api/v1/products`), debes agregar el encabezado:

```http
Authorization: Bearer <token>
```

---

## 📬 Ejemplos de uso en Postman

### ▶️ Step 1: Login y obtener token

- Método: `POST`
- URL: `http://localhost:8080/auth/login`
- Body (raw JSON):

```json
{
  "username": "admin",
  "password": "admin123"
}
```

### ▶️ Step 2: Llamar a un servicio protegido

- Método: `GET`
- URL: `http://localhost:8082/api/v1/products`
- Headers:

```
Authorization: Bearer <token_obtained>
```

> Replace `<token_obtained>` por el JWT retornado en el login.

Puedes usar esta autenticación en `order-service`, `detail-service`, `product-service`, etc.

---



---

## 🗄️ Persistencia con H2 en Memoria

El sistema utiliza **H2 Database en memoria** como motor de persistencia durante la ejecución de cada microservicio. Esto facilita pruebas rápidas sin necesidad de una base de datos externa.

Cada microservicio incluye su propio esquema y configuración aislada.

---

## 📦 Carga Inicial de Datos

### 👥 Cliente

El microservicio `client-service` contiene un archivo `data.sql` que se ejecuta automáticamente al arrancar la aplicación. This file:

- Inserta **100 clientes de prueba** con datos simulados
- Permite realizar pruebas de órdenes y relaciones sin crear users manualmente

You can find this file in:

```
client-service/src/main/resources/data.sql
```

This strategy is useful for local testing, demos, or functional simulations.

---



---

## 🗄️ Persistence with In-Memory H2 Database

This project uses **H2 in-memory database** as the persistence engine for each microservice. It allows for fast local testing without requiring external infrastructure.

Each microservice maintains its own independent schema and configuration.

---

## 📦 Initial Data Load

### 👥 Client Service

The `client-service` module includes a `data.sql` script that is automatically executed on application startup. This script:

- Inserts **100 mock clients** into the database
- Enables realistic testing scenarios for orders and client relationships

You can find this file at:

```
client-service/src/main/resources/data.sql
```

This preloading approach is ideal for functional testing, demo environments, or simulations.

---
