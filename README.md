# Library Management Application

## Overview

This is a simple library management application built with Spring Boot. It allows users to perform CRUD operations on books in a library.

## Features

- List all books in the library
- View details of a specific book
- Create a new book entry
- Edit existing book information
- Delete a book from the library

## Prerequisites

- Docker installed on your system

## Setup Instructions

### 1. Clone the Repository

```bash
git clone [repository_url]
cd library-management-app
2. Set up MySQL Database
Create a file named init.sql in the root directory of the project with the following content:

sql
Copy code
CREATE DATABASE IF NOT EXISTS librarydb;
3. Build and Run the Application
bash
Copy code
docker-compose up --build
4. Access the Application
Open your web browser and go to http://localhost:8080 to access the library management application.

Default Login Credentials
Username: user
Password: password
Controller Usage
List all Books
GET /api/books/list

View a Book
GET /api/books/view/{id}

Create a Book
POST /api/books/create

Request Body:

json
Copy code
{
  "title": "Sample Book",
  "author": "John Doe",
  "isbn": "978-3-16-148410-0"
}
Edit a Book
GET /api/books/edit/{id}

Update a Book
POST /api/books/update/{id}

Request Body:

json
Copy code
{
  "title": "Updated Book Title",
  "author": "Jane Doe",
  "isbn": "978-3-16-148410-1"
}
Delete a Book
GET /api/books/delete/{id}

Technologies Used
Spring Boot
Docker
MySQL
Thymeleaf (for server-side rendering)
HTML/CSS (for frontend)
License
This project is licensed under the MIT License - see the LICENSE.md file for details.

Acknowledgements
Thanks to Spring Boot for providing an excellent framework for building Java applications.
Thanks to Docker for simplifying the deployment process with containerization.


This README.md file provides comprehensive setup instructions, usage details for the controller endpoints, information about the technologies used, license details, and acknowledgments. Feel free to customize it further according to your specific requirements and project details!
