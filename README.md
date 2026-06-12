# Food Ordering System

A Spring Boot project for the Jumpstart programme.

## Research Questions

### 1. What is Spring Boot?
Spring Boot is a Java framework that helps developers build web applications quickly. It removes the need for a lot of manual configuration by providing sensible defaults and built-in features out of the box.

### 2. What is Maven?
Maven is a build and dependency management tool for Java projects. It automatically downloads the libraries your project needs and handles how the project is built and packaged.

### 3. What is the purpose of pom.xml?
The pom.xml file is the heart of a Maven project. It contains project information, lists all dependencies the project needs, and defines how the project should be built.

### 4. What is the purpose of application.properties?
application.properties is a configuration file where you store settings for your application such as database connection details, server port, and other environment-specific values.

### 5. What does @SpringBootApplication do?
It marks the main class of a Spring Boot application. It enables three things at once: auto-configuration, component scanning, and Spring Boot setup, so the application knows how to start itself.

### 6. Why do developers use dependency management tools such as Maven?
Maven saves time by automatically downloading and managing all the libraries a project needs. Without it, developers would have to manually find, download, and manage every library and its version.

### 7. What is a REST API?
A REST API is a way for applications to communicate with each other over the internet using HTTP methods such as GET, POST, PUT, and DELETE to send and receive data.

### 8. What is JSON?
JSON stands for JavaScript Object Notation. It is a lightweight text format used to exchange data between a server and a client. It is easy for both humans and machines to read and write.

### 9. What is Dependency Injection?
Dependency Injection is a design pattern where Spring automatically creates and provides the objects a class needs, instead of the class creating them manually. This makes code cleaner and easier to test.

## Package Structure

| Package | Purpose |
|---------|---------|
| controller | Handles incoming HTTP requests and returns responses |
| service | Contains the business logic of the application |
| repository | Communicates with the database using JPA |
| entity | Represents database tables as Java classes |
| dto | Transfers data between layers without exposing entities |
| config | Stores configuration classes for the application |
| exception | Handles errors and custom exceptions globally |