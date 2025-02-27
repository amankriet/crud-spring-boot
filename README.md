# Spring Boot Todo API

A simple RESTful API for managing Todo items built with Spring Boot and MongoDB. This project demonstrates CRUD operations using Spring Data MongoDB with integration to MongoDB Atlas, along with a secure and flexible configuration approach that externalizes sensitive data.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [File Structure](#file-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
   - [MongoDB Connection & Secrets](#mongodb-connection--secrets)
   - [Global CORS Configuration](#global-cors-configuration)
- [API Endpoints](#api-endpoints)
- [Running the Application](#running-the-application)
- [Future Improvements](#future-improvements)
- [License](#license)

## Overview

This Spring Boot Todo API provides endpoints for creating, reading, updating, and deleting Todo items stored in a MongoDB database. It is designed with a clean architecture, separating concerns for better maintainability. The application integrates with MongoDB Atlas and includes global CORS configuration to support frontend applications (e.g., React apps running on `http://localhost:3000`).

## Features

- **CRUD Operations:**  
  Create, retrieve, update, and delete Todo items.
- **MongoDB Integration:**  
  Utilizes Spring Data MongoDB for persistence with a MongoDB Atlas cluster.
- **Secure Configuration:**  
  Sensitive properties (like your MongoDB URI) are externalized in a separate `application-secret.properties` file.
- **Global CORS Configuration:**  
  Enables cross-origin requests from designated origins.
- **Exception Handling:**  
  Custom exceptions and global exception handlers return meaningful HTTP responses.

## Technologies Used

- **Java 23**
- **Spring Boot (3.x)**
- **Spring Data MongoDB**
- **MongoDB Atlas**
- **Gradle** (or Maven)

## File Structure

```
spring-boot-todo-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── amankriet/
│   │   │           └── crud/
│   │   │               ├── config/
│   │   │               │   └── CorsConfig.java        // Global CORS configuration
│   │   │               ├── controllers/
│   │   │               │   └── TodoController.java
│   │   │               ├── exception/
│   │   │               │   └── ResourceNotFoundException.java
│   │   │               ├── models/
│   │   │               │   └── Todo.java
│   │   │               ├── repositories/
│   │   │               │   └── TodoRepository.java
│   │   │               ├── services/
│   │   │               │   └── TodoService.java
│   │   │               └── CrudApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── amankriet/
│                   └── crud/
│                       └── CrudApplicationTests.java
├── application-secret.properties    // Contains sensitive properties (not committed to version control)
├── build.gradle (or pom.xml)
└── README.md
```

## Prerequisites

- **Java 23 JDK**
- **MongoDB Atlas Account:**  
  Set up your cluster and ensure your IP is whitelisted.
- **Gradle or Maven:**  
  Use your preferred build tool.
- **IDE or Text Editor**

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/spring-boot-todo-api.git
   cd spring-boot-todo-api
   ```

2. **Build the Project:**

   - **Gradle:**

     ```bash
     ./gradlew clean build
     ```

   - **Maven:**

     ```bash
     mvn clean install
     ```

## Configuration

### MongoDB Connection & Secrets

Your main configuration file (`src/main/resources/application.properties`) imports sensitive properties from an external file located in the project root. This keeps your sensitive data (like the MongoDB URI) out of the source control.

**application.properties:**

```properties
spring.application.name=crud
# Import the secret properties file (optional so the app still runs if file is missing)
spring.config.import=optional:file:./application-secret.properties
# Use the value from the secret file
spring.data.mongodb.uri=${mongodb.uri}
spring.application.dbname=todos
```

**application-secret.properties:**

Place this file in the project root (at the same level as your build file) and add it to your `.gitignore`:

```properties
mongodb.uri=mongodb+srv://amankriet:<db_password>@crud.mp7t2.mongodb.net/<dbname>?retryWrites=true&w=majority
```

Replace `<db_password>` and `<dbname>` with your actual MongoDB Atlas password and database name.

### Global CORS Configuration

To allow cross-origin requests (e.g., from `http://localhost:3000`), a global CORS configuration is implemented in `CorsConfig.java`:

```java
// src/main/java/com/amankriet/crud/config/CorsConfig.java

package com.amankriet.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
         return new WebMvcConfigurer() {
             @Override
             public void addCorsMappings(CorsRegistry registry) {
                 registry.addMapping("/**")
                         .allowedOrigins("http://localhost:3000")
                         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                         .allowedHeaders("*");
             }
         };
    }
}
```

This ensures that every endpoint in your application will accept requests from your frontend running at `http://localhost:3000`.

## API Endpoints

- **GET /todos**  
  Retrieves all Todo items.

- **GET /todos/{id}**  
  Retrieves a specific Todo item by its ID.  
  _Returns 404 Not Found if the Todo does not exist._

- **POST /todos**  
  Creates a new Todo item.  
  _Example Request Body:_
  ```json
  {
    "task": "Buy groceries",
    "completed": false
  }
  ```

- **PUT /todos/{id}**  
  Updates an existing Todo item.  
  _Example Request Body:_
  ```json
  {
    "task": "Buy groceries and cook dinner",
    "completed": true
  }
  ```

- **DELETE /todos/{id}**  
  Deletes a Todo item by its ID.

## Running the Application

You can run the application in several ways:

- **Using Gradle:**

  ```bash
  ./gradlew bootRun
  ```

- **Using Maven:**

  ```bash
  mvn spring-boot:run
  ```

- **Using your IDE:**  
  Run the `main` method in `CrudApplication.java`.

Once running, the API will be available at `http://localhost:8080/todos`.

## Future Improvements

- **Enhanced Error Handling:**  
  Implement a global exception handler using `@ControllerAdvice` for centralized error responses.
- **Security:**  
  Integrate Spring Security for authentication and authorization.
- **Testing:**  
  Add comprehensive unit and integration tests.
- **API Documentation:**  
  Use Swagger/OpenAPI for automatic API documentation.
- **Deployment:**  
  Prepare for cloud deployment and configure additional profiles.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.