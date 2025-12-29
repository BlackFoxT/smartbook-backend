# 📚 SmartBook

SmartBook is a RESTful backend API for managing a digital book catalog and personal user libraries.

Users can browse books, maintain their own reading library, track reading status, rate books, while admins manage users and books.

---

## 🚀 Features

### 📖 Book Catalog
- List all books
- Filter by genre, author, or title
- Pagination & sorting
- Unique ISBN enforcement

### 👤 User Library
- Add/remove books from personal library
- Track reading status (WANT_TO_READ, READING, COMPLETED)
- Rate books (1–5)
- Prevent duplicate entries

### 🛡️ Admin Panel
- Create / update / delete books
- Promote users to admin
- Delete users (safe rules applied)
- View any user's library
- Dashboard statistics:
    - Total users
    - Total books
    - Reading status distribution
    - Average ratings

### ⚠️ Error Handling
- Global exception handling
- Meaningful HTTP status codes
- Clean error responses

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security (JWT)
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
GET    /api/books
GET   /api/books/{isbn}
```

### 🛡️ Admin

```bash
POST   /api/admin/books
PUT    /api/admin/books/{isbn}
DELETE /api/admin/books/{isbn}

GET    /api/admin/users
PUT    /api/admin/users/{id}/promote
DELETE /api/admin/users/{id}
GET    /api/admin/users/{id}/library

GET    /api/admin/dashboard
```
### 📘 User Library

```bash
GET    /api/library
POST   /api/library/{isbn}
PUT    /api/library/{isbn}/status
PUT    /api/library/{isbn}/rating
```