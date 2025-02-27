# CRUD Todo Application

A simple Todo application built with Spring Boot using Java 23 and MongoDB. This project demonstrates basic CRUD operations through a RESTful API while integrating with MongoDB as the data store.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Project Structure](#project-structure)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Future Improvements](#future-improvements)
- [License](#license)

## Overview

This project implements a simple Todo application that allows you to create, retrieve, update, and delete Todo items using a RESTful interface. It leverages Spring Boot with Spring Data MongoDB for data access and uses a cloud-hosted MongoDB instance.

## Features

- **CRUD Operations:** Create, Read, Update, and Delete Todo items.
- **RESTful API:** Simple endpoints to manage Todo items.
- **MongoDB Integration:** Uses MongoDB Atlas for data persistence.
- **Modern Java:** Built with Java 23 and Spring Boot.

## Technologies Used

- **Java 23**
- **Spring Boot 3.4.3**
- **Spring Data MongoDB**
- **Gradle Build Tool**
- **MongoDB Atlas**

## Prerequisites

- **Java 23 JDK** – Ensure that Java 23 is installed on your system.
- **Gradle** – The project uses Gradle; ensure you have it installed or use the Gradle wrapper.
- **MongoDB Atlas Account** – For hosting your MongoDB database (or you can modify the connection URI for a local instance).

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/crud-todo-app.git
   cd crud-todo-app
   ```

2. **Build the Project:**

   If using the Gradle wrapper, run:

   ```bash
   ./gradlew clean build
   ```

## Configuration

### application.properties

Make sure to update your `src/main/resources/application.properties` with your MongoDB credentials:

```properties
spring.application.name=crud
spring.application.mongodburi=mongodb+srv://amankriet:<db_password>@crud.mp7t2.mongodb.net/
spring.application.dbname=todos
```

Replace `<db_password>` with your actual MongoDB Atlas password.

### build.gradle

Your `build.gradle` is configured to use Java 23 and includes the required dependencies:

```groovy
plugins {
	id 'java'
	id 'org.springframework.boot' version '3.4.3'
	id 'io.spring.dependency-management' version '1.1.7'
}

group = 'com.amankriet'
version = '0.0.1-SNAPSHOT'

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

tasks.named('test') {
	useJUnitPlatform()
}
```

## Project Structure

The file structure is organized as follows:

```
crud-todo-app/
├── build.gradle
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── amankriet/
│   │   │           └── crud/
│   │   │               ├── controllers/        // REST controllers
│   │   │               ├── models/             // Domain models (e.g., Todo.java)
│   │   │               ├── repositories/       // MongoDB repositories
│   │   │               ├── services/           // Business logic and services
│   │   │               └── CrudApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── amankriet/
│                   └── crud/
│                       └── CrudApplicationTests.java
└── README.md
```

## Running the Application

To start the application, run:

```bash
./gradlew bootRun
```

The application will start on [http://localhost:8080](http://localhost:8080).

## API Endpoints

Here are some example endpoints for the Todo application:

- **GET /todos** – Retrieve all Todo items.
- **POST /todos** – Create a new Todo item.
- **GET /todos/{id}** – Retrieve a Todo item by its ID.
- **PUT /todos/{id}** – Update an existing Todo item.
- **DELETE /todos/{id}** – Delete a Todo item.

You can test these endpoints using tools like [Postman](https://www.postman.com/) or `curl`.

## Future Improvements

- **Authentication & Authorization:** Secure the API endpoints.
- **External Database:** Switch from MongoDB Atlas to a locally managed or alternative external database.
- **Enhanced Error Handling:** Add comprehensive error handling and validation.
- **Frontend Integration:** Build a simple user interface with frameworks like React or Angular.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.