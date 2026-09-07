# Employee Management System -- SIMPLE CRUD APIs

A simple Employee Management System built using **Spring Boot** and **Spring Data JPA**.

## Features

* Create Employee
* Get Employee by ID
* Get All Employees
* Update Employee
* Delete Employee
* DTO and Entity mapping using MapStruct
* Exception handling for employee not found
* Unit testing using JUnit 5 and Mockito

## Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* MapStruct
* Maven
* Junit 5
* Mockito

## Project Structure

```text
src/main/java
└── employee_management
    ├── controller
    ├── service
    ├── repository
    ├── entity
    ├── dto
    ├── mapper
    └── exception
```

## API Endpoints

| Method | Endpoint          | Description        |
| ------ | ----------------- | ------------------ |
| POST   | `/employees`      | Create Employee    |
| GET    | `/employees`      | Get All Employees  |
| GET    | `/employees/{id}` | Get Employee by ID |
| PUT    | `/employees/{id}` | Update Employee    |
| DELETE | `/employees/{id}` | Delete Employee    |
