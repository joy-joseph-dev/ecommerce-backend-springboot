# Ecommerce Backend – Spring Boot

A backend REST API for an e-commerce application built using Spring Boot, following a clean layered architecture.

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Hibernate

## Features
- Product management (CRUD)
- Product search, pagination and sorting
- User cart management
- Order placement from cart
- Order history per user
- Global exception handling

## API Endpoints
### Product APIs
- POST /product – Create a product
- GET /products – Get all products
- GET /product/{id} – Get product by id
- PUT /products – Update product
- DELETE /product/{id} – Delete product
- GET /products/search?keyword= – Search products
- GET /products/pagination?page=&size=&sortBy= – Pagination and sorting

### Cart APIs
- POST /cart/add?userId=&productId=&quantity= – Add product to cart
- GET /cart/{userId} – View cart by user
- PUT /cart/item/{cartItemId}?quantity= – Update cart item quantity
- DELETE /cart/item/{cartItemId} – Remove item from cart

### Order APIs
- POST /orders/place/{userId} – Place order
- GET /orders/user/{userId} – Get user order history

## How to Run
1. Clone the repository
2. Configure MySQL database in application.properties
3. Run the application using: mvn spring-boot:run
4. Test APIs using Postman