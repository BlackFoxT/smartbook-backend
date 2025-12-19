# 📚 SmartBook

SmartBook is a backend REST API for managing a digital book catalog and personal user libraries.  
Users can browse books, add them to their own library, track reading status, and rate books.

This project is built to demonstrate clean backend architecture using Spring Boot.

---

## 🚀 Features

### 📖 Book Catalog
- List all books
- Filter by genre, author, or title
- Sort and paginate results
- Prevent duplicate book imports by ISBN

### 👤 User Library
- Add books to personal library
- Track reading status (WANT_TO_READ, READING, READ)
- Rate books (1–5)
- Prevent duplicate books in user library

### ⚠️ Error Handling
- Global exception handling
- Meaningful HTTP status codes
- Clean error responses

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security (authentication in progress)
- MySQL
- Hibernate
- Lombok
- Maven

---

## ⚙️ Getting Started

### 1️⃣ Clone the repository

```bash
git clone https://github.com/BlackFoxT/smartbook-backend.git
cd smartbook-backend

```

### 2️⃣ Configure database

Update application.yml:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/smartbook
    username: springstudent
    password: springstudent

```

### 3️⃣ Run the application


```bash
mvn spring-boot:run
```

Application will start at: http://localhost:8080

---


## 🔗 API Endpoints

### 📚 Books

```bash
GET /api/books
GET /api/books/{isbn}
```

### 📘 User Library

```bash
GET    /api/library
POST   /api/library/{isbn}
PUT    /api/library/{isbn}/status
PUT    /api/library/{isbn}/rating
```