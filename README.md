<!-- ============================================  TITLE ======================================================  -->
# A RESTful API Service for Library Management System

<img src="https://www.pixel-studios.com/blog/wp-content/uploads/2018/12/012-1200x600.jpg" width="100%">

<!-- ============================================  DETAILS ======================================================  -->

An Automated Library Management System that will maintain records of all books, users and perform basic CRUD operations such as
add, delete, upadate, issue, return a book and calculate fines for any late returns

<!-- ============================================  FEATURES ======================================================  -->
## Features

* Book Features:
    * Add, Update, Delete Books from database
    * Get list of all books
    * Check availability of books
    * Issue books
    * Caluculate fines for late submission
    * Get all borrowed books list
    * Check due dates of books

* User Features:
    * Register a new user
    * Delete a user account
    * Borrow and Return books
    * View list of borrowed books

<!-- ============================================  TECH STACK ======================================================  -->

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Postman

<!-- ============================================  MODULES ======================================================  -->

## Modules

* Book Module
* User Module

<!-- ============================================  API ROOT ENDPOINTS ======================================================  -->

# API Root Endpoint

* General API  `https://localhost:8080/`

* Add Book API  `http://localhost:8080/book/`
<img src="https://www.linkpicture.com/q/Screenshot_20230203_112855.png" width="100%">

* Get all Borrowed Book `http://localhost:8080/book/allBorrowedBookList`
<img src="https://i.postimg.cc/WNFnzT9X/Screenshot-20230203-113856.png" width="100%">


* Add User API  `http://localhost:8080/user/`

* User Borrowed Books `http://localhost:8080/user//userBorrowedList/{userId}`

* Issue Book API `http://localhost:8080/user/borrow/{userId}/{bookId}`

* Return Book API `http://localhost:8080/user/return/{userId}/{bookId}/{borrowId}`

<!-- ============================================  INSTALLATION AND RUN ======================================================  -->

## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](/LibrarayManagementSystem/src/main/resources/application.properties) file. 
* Update the port number, username and password as per your local database config.

```
#changing the server port 
server.port=8080

#db specific properties 
spring.datasource.url=jdbc:mysql://localhost:3306/LibraryManagementSystem
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=IMCoder@0566
```
