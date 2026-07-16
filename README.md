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

## Endpoints

| Method | URL                       | Body         |
|--------|---------------------------|--------------|
| POST   | /api/categories           | { "name" }   |
| GET    | /api/categories           | -            |
| GET    | /api/categories/{id}      | -            |
| PUT    | /api/categories/{id}      | { "name" }   |
| DELETE | /api/categories/{id}      | -            |

## API Response Format

Every endpoint returns the same JSON structure:

```json
{
  "statusCode": 200,
  "message": "Category retrieved",
  "data": { "id": 1, "name": "Fast Food" },
  "timestamp": "2026-06-18T08:42:11"
}

## Auth Endpoints

| Method | Path                  | Who Can Call It      |
|--------|-----------------------|-----------------------|
| POST   | /api/auth/register    | Public (anyone)       |
| POST   | /api/auth/login       | Public (anyone)       |
| GET    | /api/categories/**    | Public (anyone)       |
| GET    | /api/menu/**          | Public (anyone)       |
| GET    | /api/reviews/**       | Public (anyone)       |
| POST   | /api/categories/**    | ADMIN only            |
| PUT    | /api/categories/**    | ADMIN only            |
| DELETE | /api/categories/**    | ADMIN only            |
| POST   | /api/menu/**          | ADMIN only            |
| PUT    | /api/menu/**          | ADMIN only            |
| DELETE | /api/menu/**          | ADMIN only            |
| *      | Everything else       | Any authenticated user |

## Security Rules Summary

- **Public** — no token required: register, login, and all read (GET) operations on categories, menu, and reviews.
- **Customer** (any authenticated user) — can access their own profile, cart, and orders (upcoming). Cannot create, update, or delete categories or menu items.
- **Admin** — full write access to categories and menu items (create, update, delete), in addition to all customer-level permissions.

Unauthenticated requests to protected endpoints return `401 Unauthorized`. Authenticated requests without the required role return `403 Forbidden`. Both use the app's standard `Response<T>` error shape.

## Promoting a User to ADMIN

New users are always registered with the `CUSTOMER` role by default — there is no way for a client to self-assign `ADMIN`. To promote an existing user to `ADMIN`, run the following SQL directly in MySQL Workbench:

\`\`\`sql
UPDATE users_roles
SET role_id = (SELECT id FROM roles WHERE name = 'ADMIN')
WHERE user_id = (SELECT id FROM users WHERE email = 'the.users.email@example.com');
\`\`\`

Replace the email with the user you want to promote. Roles are always looked up by name, never hardcoded by ID, so this works regardless of environment.

## How to Authenticate in Postman

1. Send a `POST /api/auth/login` request with a valid email and password. On success, a post-response script automatically saves the returned token into the `adminToken` or `customerToken` environment variable (based on the user's role), and always also into `authToken`.
2. Set the collection-level Authorization to **Bearer Token** with the value `{{authToken}}` (or reference `{{adminToken}}` / `{{customerToken}}` directly on individual requests when you need to test role-specific behavior).
3. Individual requests inherit the token automatically — no need to copy-paste it manually. To test an endpoint without auth, override that request's Authorization tab to **No Auth**.