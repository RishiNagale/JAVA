# Java Project with JWT Authentication

A Spring Boot application demonstrating JWT authentication with custom filters for secure user management.

## Overview

This project is a Spring Boot application that demonstrates JWT (JSON Web Token) authentication for securing RESTful endpoints. It includes user management features such as registration, login, and CRUD operations for user data. The application also integrates custom filters for logging requests and transactions.

## Key Features

1. **User Authentication and Authorization:** Uses JWT tokens for secure user authentication and authorization.
2. **User Management:** Provides endpoints for creating, retrieving, updating, and deleting user records.
3. **In-Memory Database:** Utilizes H2 as an in-memory database for simplicity and ease of testing.
4. **Custom Filters:** Includes custom filters for logging transaction details and request/response data.

## Technologies Used

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- H2 Database

## Application Structure

### Security Configuration
- **SecurityFilterConfig:** Configures HTTP security, including JWT authentication filter and DaoAuthenticationProvider for user details and password encoding.

### JWT Authentication
- JWT (JSON Web Token) authentication is used to secure endpoints in this application. It involves generating, validating, and parsing JWT tokens to ensure that only authenticated users can access certain resources.
  - **JwtAuthenticationFilter:** A filter that intercepts requests to validate JWT tokens and sets authentication in the security context.
  - **JwtHelper:** Utility class for creating and validating JWT tokens.

### Custom Filters
- Custom filters in this application are used to log details of transactions and requests/responses, which aids in monitoring and debugging. Filters operates on Servlet level.
- Filters implemented in this application:
  - **TransactionLoggerFilter:** Logs the start and end of each request, including the HTTP method and path.
  - **RequestResponseLogFilter:** Logs details of incoming requests and outgoing responses.

### Interceptors
- Interceptors in Spring are used to process requests before they reach the controller and responses before they reach the client. They can be used for a variety of tasks such as logging, authentication, and modifying request/response objects.
- Interceptor implemented in this application:
    - **LoggingInterceptor:** Logs request details for monitoring and debugging purposes.

### Endpoints
1. **Authentication:** POST /welcome/login for user login.
2. **User Management:**
   - POST /auth/create-user for creating new users.
   - GET /auth/getall for retrieving all users.
   - GET /auth/user/{id} for retrieving a user by ID.
   - PUT /auth/update/{id} for updating a user by ID.
   - DELETE /auth/delete/{id} for deleting a user by ID.

### Setup and Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/RishiNagale/JAVA-Application.git
   cd JAVA-Application
   ```

2. **Build the project:**

   ```bash
   ./mvnw clean install
   ```

3. **Run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```
4. **Access H2 console:**

- URL: http://localhost:8081/h2-console
- Default credentials:
  - Username: sa
  - Password: 

### Configuration

Configure application properties in src/main/resources/application.properties as needed.

### Logging

Custom filters provide detailed logging of transactions and request/response cycles to facilitate debugging and monitoring.

## Flow of the application

1. **Client Request:**

- The client sends an HTTP request to the application.
- The request can be for any endpoint, e.g., /welcome/login, /auth/resource, etc.

2. **JwtAuthenticationFilter:**

- The first filter to intercept the request as it is a Spring Security Filter.
- Intercepts the request to check for JWT token in the Authorization header.
- If a JWT token is present, extracts and validates it.
- If the token is valid, sets the authentication in the SecurityContextHolder.
- If the token is invalid, logs the appropriate error and proceeds without setting the authentication.
- Passes the request to the next filter or endpoint in the chain.

3. **TransactionLoggerFilter:**

Logs the start of the request, including the HTTP method and path.
Passes the request to the next filter in the chain.

4. **RequestResponseLogFilter:**

Logs details of the incoming request, including the request URI.
Passes the request to the next filter in the chain.

5. **Security Configuration (SecurityFilterConfig):**

- The configurations are invoked at the application startup.
- Determines the access control for the request based on configured security rules.
- Permits access to certain endpoints like /welcome/login.
- Ensures other endpoints are secured and require authentication.
- Handles exceptions such as unauthorized access attempts.

6. **Interceptors:**

- If any interceptors are configured, they will intercept the request before it reaches the controller.
- Interceptors can be used for tasks such as logging, modifying requests, or performing pre-processing.
- After the controller processes the request, interceptors can also modify the response or perform post-processing tasks.
  
6. **Controller/Endpoint:**

- If the request passes through the filters and security checks, it reaches the appropriate controller or endpoint.
- The controller processes the request, performs necessary business logic, and returns a response.

7. **Response:**

- The response generated by the controller is passed back through the filter chain.
- RequestResponseLogFilter logs details of the outgoing response.
- TransactionLoggerFilter logs the completion of the request, including the HTTP method and path.

8. **Client Receives Response:**

- The response is sent back to the client.
- The client processes the response, such as displaying data to the user or handling errors.
