# Personal Pursuits Blog Project VB

**GITHUB LINK**

[Github Backend Link](https://github.com/rupali-12/java_blogapp_backend)

**DEPLOYED LINK**
[DeployedLink](https://rupali-personal-persuits.vercel.app/)

## Project Overview

This project is a personal blog application that allows users to write, edit, and share blog posts. The application is built using Java Spring Boot for the backend, JPA for database management, and Thymeleaf for the frontend. The application includes user authentication to ensure secure access to blogging features.

## Features

- User Registration and Authentication
- Create, Read, Update, and Delete (CRUD) blog posts

## Technologies Used

### Frontend

- HTML, CSS, Bootstrap

### Backend

- Java Spring Boot
- Spring Data JPA
- Spring Security for authentication
- MongoDB for the database

## Setup Instructions

### Prerequisites

- Java Development Kit (JDK) 8 or later
- MySQL
- Maven
- Git

### Installation

1. **Clone the repository**

   ```sh
   For Frontend:
   git clone https://github.com/rupali-12/Java_blogapp_Frontend.git
   For Backend:
   git clone https://github.com/rupali-12/Java_blogapp_Backend.git

   ```

2. **Backend Setup**

   ```sh
   cd backend
   mvn clean install

   ```

3. **Environment Variables**

- Create a application.properties file in the src/main/resources directory and add the following variables:

```sh
spring.data.mongodb.uri={Your_url}
spring.data.mongodb.database={Your_DB_name}
spring.data.mongodb.auto-index-creation=true
spring.main.allow-circular-references=true
#spring.data.mongodb.username=
#spring.data.mongodb.password=
spring.devtools.restart.enabled=true
spring.devtools.livereload.enabled=true
spring.mvc.cors.allowed-origins={Frontend_URL}
spring.mvc.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS


#CLOUDINARY IMAGE SAVING
cloudinary.cloud_name={Your_cloud_name}
cloudinary.api_key={Your_cloud_key}
cloudinary.api_secret={your_api_secret}
```

4. **Contributors**

- Rupali Sharma
- https://github.com/rupali-12
