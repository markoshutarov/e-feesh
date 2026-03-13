# 🐟 E-Feesh Aquarium Store

This is my first real project — a fully functional REST API for an online aquarium store, 
built with Java and Spring Boot. I built this to apply and deepen my backend development 
skills by working with real-world concepts like authentication, relationships, 
pagination, and order management.

## 🛠️ Tech Stack
- Java 17
- Spring Boot 3
- Spring Security + JWT
- PostgreSQL
- JPA / Hibernate
- Maven

## ✨ Features
- JWT based registration and login
- Product management with pagination and category filtering
- Order placement with stock validation
- Automatic stock reduction on order
- Price snapshot at time of purchase
- Global exception handling
- Input validation

## 📦 API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/register | Register a new user |
| POST | /auth/login | Login and get JWT token |

### Products
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /products | Get all products (paginated) |
| GET | /products/{id} | Get product by id |
| GET | /products/category/{category} | Get products by category |
| POST | /products | Create a product |
| PUT | /products/{id} | Update a product |
| DELETE | /products/{id} | Delete a product |

### Orders
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /orders | Place an order |
| GET | /orders | Get my orders |
| GET | /orders/{id} | Get order by id |

## 🚀 How to Run Locally
1. Clone the repository
```bash
git clone https://github.com/markoshutarov/e-feesh.git
```
2. Create a PostgreSQL database named `aquarium_store`
3. Set environment variables:
```
DB_PASSWORD=your_db_password
JWT_SECRET=your_base64_secret
```
4. Run the project in IntelliJ or with:
```bash
./mvnw spring-boot:run
```
5. API is available at `http://localhost:8080`
