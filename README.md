# DriveAndDeliver

## Overview
**DriveAndDeliver** is a Spring Boot application designed to manage delivery services using a flexible and efficient delivery system. It leverages Java 21, Spring Boot 3.3.1, and incorporates various features such as Docker integration, H2 in-memory database, Swagger UI for API documentation, and basic authentication for security.

## Features
- Java 21
- Spring Boot 3.3.1
- Spring WebFlux
- Dockerfile for containerization
- Swagger UI for API documentation
- H2 in-memory database
- H2 console for database management
- Basic authentication with Spring Security
- SQL scripts for schema creation and data initialization

## Requirements
- Java 21
- Docker
- Maven

## Setup and Run

### Running Locally

1. **Clone the repository**
    ```bash
    git clone https://github.com/yahyamql/DriveAndDeliver.git
    cd DriveAndDeliver
    ```

2. **Build the project**
    ```bash
    ./mvnw clean install
    ```

3. **Run the application**
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the application**
    - Swagger UI: [http://localhost:8080/webjars/swagger-ui/index.html](http://localhost:8080/webjars/swagger-ui/index.html)
    - H2 Console: [http://localhost:8082/login.jsp](http://localhost:8082/login.jsp)
        - Username: `admin`
        - Password: `123456`

### Running with Docker

1. **Build the Docker image**
    ```bash
    docker build -t drive-and-deliver .
    ```

2. **Run the Docker container**
    ```bash
    docker run -p 8080:8080 -p 8082:8082 drive-and-deliver
    ```

3. **Access the application**
    - Swagger UI: [http://localhost:8080/webjars/swagger-ui/index.html](http://localhost:8080/webjars/swagger-ui/index.html)
    - H2 Console: [http://localhost:8082/login.jsp](http://localhost:8082/login.jsp)
        - Username: `admin`
        - Password: `123456`

## Database

This application uses an H2 in-memory database. The database schema and initial data are set up using two SQL scripts on startup:

- `schema-script.sql`: Defines the database schema.
- `data-script.sql`: Populates the database with initial data.

## Security

Basic authentication is enabled using Spring Security with the following credentials:
- Username: `admin`
- Password: `123456`

## Contact
For any questions or inquiries, please contact me at yahya.nehli@gmail.com.