# Share A Bite App

## Project Overview

**ShareABite** is a community platform that connects people who want to donate essential food supplies with people 
who need them. Its goal is to reduce food waste and support local communities. The application is also designed with
locality in mind, encouraging people to first offer food within their own region, reducing unnecessary transportation
and contributing to lower CO₂ emissions.

**Share A Bite** is a Spring Boot web application designed to support food donation efforts. Users can create food 
requests, while volunteers can view requests in their region and fulfill them. The project is built with a focus on 
clean architecture, domain-driven design and clear separation of responsibilities. Access to user-related functionality
is protected using Spring Security, ensuring that only authenticated users can create and manage food requests.
Some additional features (such as admin functionality and interactive contact forms) are intentionally not fully
implemented at this stage, as they were not considered essential for the core workflow of the application.
The index page includes a map showing the current service area. In future versions, this map may be extended with pins
representing the partner locations. 

##  Domain Model

The domain consists of the following entities:

- **User** – registered users of the platform
- **FoodRequest** – a request for food items created by a user
- **RequestedItem** – individual food items belonging to a food request
- **Region** – geographical area of the request
- **Status** – status of a request (`OPEN`, `COMPLETED`, `CANCELLED`)
- **FoodItems** – predefined food item categories

The database schema is derived directly from the domain model following a **Model First Approach**.

---

The technologies I use are the following: 

### Backend
- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA (Hibernate)
- Spring Security
- Maven
- Lombok

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
Install and run MySQL Server locally.
Create a MySQL database with the name: shareabiteappbd
Configure the database connection in application.properties.
Environment variables used:
- MYSQL_USER
- MYSQL_PASSWORD

### Build
Use Git Bash or any terminal of your choice and run the following: 
```bash
./mvnw clean package 
```
 ### Run
To start the application:
```bash
./mvnw spring-boot:run
```
The application will be available at: 
http://localhost:8080

## Deployment

The application is intended for local execution for the purposes of the final project.

### Local Deployment Process ###

- Install and run MySQL Server locally.
- Create the database schema (shareabiteappbd).
- Set the required environment variables (MYSQL_USER, MYSQL_PASSWORD).
- Configure the JDBC connection in application.properties.
- Build the application using Maven.
- Run the Spring Boot application locally.

In a production environment, the application could be run as a JAR file on a server with Java installed,
using environment variables for database configuration.

## Author
Eleni Ntoulou

Final Project for Coding Factory 8 (AUEB)
