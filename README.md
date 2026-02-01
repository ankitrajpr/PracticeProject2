# Spring Boot 3.4 + Java 21 Demo Project

A complete REST API application demonstrating Spring Boot 3.4 with Java 21 features.

## Features

- **JPA Entities**: Employee and Department with bidirectional relationships
- **DTOs**: Java Records for type-safe data transfer
- **MapStruct**: Automatic mapping between entities and DTOs
- **Error Handling**: ResponseStatusException for consistent error responses
- **CRUD REST API**: Full CRUD operations with proper HTTP status codes
- **H2 Database**: In-memory database with sample data

## Project Structure

```
employee-management-system
└── src
    └── main
        ├── java
        │   └── com.example.PracticeProject2
        │       ├── controller
        │       ├── service
        │       ├── repository
        │       ├── entity
        │       ├── dto
        │       ├── mapper
        │       └── EmployeeMgmtSystemApplication.java
        └── resources
            └── application.properties
└── pom.xml

```

## Requirements

- Java 21
- Maven 3.13

## Running the Application

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Departments

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/departments` | Get all departments |
| GET | `/api/departments/{id}` | Get department by ID |
| GET | `/api/departments/search?name={name}` | Get department by name |
| POST | `/api/departments` | Create new department |
| PUT | `/api/departments/{id}` | Update department |
| DELETE | `/api/departments/{id}` | Delete department |

### Employees

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| POST | `/api/employees` | Create new employee |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |

## H2 Console

Access the H2 database console at: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:demodb`
- Username: `sa`
- Password: (empty)


## Example Requests

### Create a Department
```bash
curl -X POST http://localhost:8080/api/departments \
  -H "Content-Type: application/json" \
  -d '{"name":"IT Support","description":"Technical support team","location":"Building C"}'
```

### Create an Employee
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "firstName":"Alice",
    "lastName":"Wonder",
    "email":"alice.wonder@company.com",
    "phoneNumber":"555-9999",
    "jobTitle":"System Administrator",
    "salary":70000,
    "hireDate":"2024-01-15",
    "departmentId":1
  }'
```

### Get All Employees
```bash
curl http://localhost:8080/api/employees
```

### Update an Employee
```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName":"John",
    "lastName":"Smith",
    "email":"john.smith@company.com",
    "jobTitle":"Principal Engineer",
    "salary":110000
  }'
```

### Delete an Employee
```bash
curl -X DELETE http://localhost:8080/api/employees/1
```

## HTTP Status Codes

- `200 OK` - Request successful
- `201 Created` - Resource created successfully
- `204 No Content` - Resource deleted successfully
- `400 Bad Request` - Invalid request data
- `404 Not Found` - Resource not found
- `409 Conflict` - Resource already exists
- `500 Internal Server Error` - Unexpected server error

## 💡 Key Design Decisions

### Why Java Records for DTOs?
- Immutable by default
- Less boilerplate (no getters, setters, equals, hashCode)
- Modern Java feature (Java 16+)

### Why MapStruct?
- Compile-time code generation (no reflection)
- Type-safe mapping
- Easy to customize

### Why ResponseStatusException?
- No custom exception classes needed
- No global exception handler needed
- Clean, maintainable code

### Why DTOs instead of direct Entities?
- Hide internal database structure
- Prevent infinite recursion in JSON serialization
- Control what data clients receive

## 📊 Database Schema

```sql
-- Departments
CREATE TABLE departments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    location VARCHAR(255)
);

-- Employees
CREATE TABLE employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    salary DOUBLE,
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);
```

## 📄 License

This project is open source and available under the MIT License.
