# Share A Bite App

## Project Overview

**ShareABite** is a community platform that connects people who want to donate essential food supplies with people who need them. Its goal is to reduce food waste and support local communities.
The application is also designed with locality in mind, encouraging people to first offer food within their own region, reducing unnecessary transportation and contributing to lower CO₂ emissions.

**Share A Bite** is a Spring Boot web application designed to support food donation efforts. Users can create food requests, while volunteers can view requests in their region and fulfill them. 
The project is built with a focus on clean architecture, domain-driven design and clear separation of responsibilities. Access to user-related functionality is protected using Spring Security, ensuring that only authenticated users can create and manage food requests.
Some additional features (such as admin functionality) are intentionally not fully implemented at this stage, as they were not considered essential for the core workflow of the application.
The index page includes a map showing the current service area, in future versions, this map may be extended with pins representing partner locations. As for the contact page, interactive communication features will be added in future versions of the application.

##  Domain Model

The domain consists of the following entities:

- **User** – registered users of the platform
- **FoodRequest** – a request for food items created by a user
- **RequestedItem** – individual food items belonging to a food request
- **Region** – geographical area of the request
- **Status** – status of a request (`OPEN`, `COMPLETED`, `CANCELLED`)
- **FoodItems** – predefined food item categories

The database schema is derived directly from the domain model following a Model First Approach.

---

The technologies I use are the following: 
---
### Backend
- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA (Hibernate)
- Spring Security
- Maven
- Lombok
---
### Frontend
- Thymeleaf
- Bootstrap 5
- HTML and CSS

### Database
- MySQL
---
## Build & Run Instructions

### Requirements
- Java 17 or higher
- Maven
- MySQL
- An IDE (I used IntelliJ IDEA)

### Database Setup
Create a MySQL database and configure the connection in `application.properties`
Environment variables used:
- MYSQL_USER
- MYSQL_PASSWORD

### Build
Use Gitbash or any terminal of your choice and run the following: 
```bash
./mvnw clean package 
./mvnw spring-boot:run
```
The application will be available at: 
http://localhost:8080

The name of the database is: shareabiteappbd

## Author
Eleni Ntoulou
Final Project for Coding Factory 8 (AUEB)
