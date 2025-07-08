# 🛒 E-commerce Microservices Backend Documentation

**Date:** 2025-07-07  
**Technologies:** Java 17, Spring Boot, Spring Cloud, Eureka, Feign, JWT, Maven, H2 Database

---

## 📐 Architecture Overview

This project is built using a **microservices architecture** with the following modules:

- **auth-service**: Issues and validates JWT tokens
- **client-service**: Manages customer data
- **detail-service**: Manages order details (line items)
- **order-service**: Handles order logic and aggregation
- **product-service**: Manages products
- **eureka-server**: Service discovery using Netflix Eureka
- **security-common**: Shared library for security, JWT filters, and Feign interceptors

Each service runs independently and communicates using **OpenFeign** and JWT-based authorization.

---

## ⚙️ Technology Stack

- Java 17
- Spring Boot 3+
- Spring Security + JWT
- Spring Cloud (Eureka, OpenFeign)
- Maven (multi-module)
- Lombok
- H2 in-memory database
- IntelliJ IDEA
- Postman for API testing
- Mapstruct for object mapping

---

## 🔌 REST Endpoints

### auth-service
```http
POST   /auth/register
POST   /auth/login
```

### client-service
```http
GET    /api/v1/clients
GET    /api/v1/clients/{id}
POST   /api/v1/clients
PUT    /api/v1/clients/{id}
DELETE /api/v1/clients/{id}
```

### detail-service
```http
GET    /api/v1/order-details
GET    /api/v1/order-details/{id}
POST   /api/v1/order-details
PUT    /api/v1/order-details/{id}
DELETE /api/v1/order-details/{id}
```

### order-service
```http
GET    /api/v1/orders
GET    /api/v1/orders/{id}
GET    /api/v1/orders/with-client
POST   /api/v1/orders
PUT    /api/v1/orders/{id}
DELETE /api/v1/orders/{id}
```

### product-service
```http
GET    /api/v1/products
GET    /api/v1/products/{id}
```

### payment-service
```http
GET    /api/v1/payments
GET    /api/v1/payments/{id}
POST   /api/v1/payments
PUT    /api/v1/payments/{id}
DELETE /api/v1/payments/{id}
```

---

## 🛠 How to Open & Run in IntelliJ IDEA

### 1. 🧾 Prerequisites

- Java 17
- Maven installed
- IntelliJ IDEA (Ultimate recommended)

### 2. 📥 Import the project

- Open IntelliJ IDEA
- Go to `File > Open`
- Select the root folder `/ecommerce`
- Wait for Maven to finish importing modules

### 3. 🚀 Recommended Startup Order

Run the services in this exact order:

1. **Eureka Server**  
   `com.mreyes.ecommerce.EurekaServerApplication`

2. **Auth Service**  
   `com.mreyes.ecommerce.AuthServiceApplication`

3. **Product Service**  
   `com.mreyes.ecommerce.ProductServiceApplication`

4. **Client Service**  
   `com.mreyes.ecommerce.ClientServiceApplication`

5. **Order Service**  
   `com.mreyes.ecommerce.OrderServiceApplication`

6. **Detail Service**  
   `com.mreyes.ecommerce.DetailServiceApplication`

👉 Each service can be started by right-clicking the main class in IntelliJ and selecting `Run`. You can also use the terminal to run each service with the following command:

```bash
mvn spring-boot:run
```

---

## 🔐 JWT Authentication Flow

### 1. Register
You can register a new user by calling the registration endpoint:
```http
POST /auth/register
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

### 2. Login
The token is obtained by logging in with the registered user credentials:
```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

You will receive a JWT token in response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 3. Call secured endpoints

Pass the token in the `Authorization` header in postman requests to access protected endpoints, You can set the token in the collection variables in Postman for easy access.

```http
Authorization: Bearer <token>
```

---

## 📬 Postman Usage Example

### Step 1: Get Token

- Method: POST
- URL: `http://localhost:8080/auth/login`
- Body:

```json
{
  "username": "admin",
  "password": "admin123"
}
```

### Step 2: Call Protected Endpoint

- Method: GET
- URL: `http://localhost:8082/api/v1/products`
- Headers:

```
Authorization: Bearer <your_token_here>
```

---

## 🗄️ In-Memory H2 Database

This project uses **H2 in-memory database** for persistence in each service. No external DB setup is required.

### Initial Data

The `client-service` module contains a `data.sql` file that loads **100 test clients** at startup.

Path:
```
client-service/src/main/resources/data.sql
```

This facilitates testing and demo usage.

---
