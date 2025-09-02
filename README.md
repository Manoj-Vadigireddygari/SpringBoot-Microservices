# SpringBoot-Microservices Demo

A Spring Boot microservices demo project showcasing a complete ecosystem with **API Gateway**, **Service Discovery (Eureka)**, and multiple Spring Boot
services.This project demonstrates patterns like **service discovery, routing, inter-service communication**, and integrates **Resilience4j mechanisms** 
(Circuit Breaker, Retry, Rate Limiter, Bulkhead) to make microservices fault-tolerant.  

It also includes **OAuth2.0 authentication** using **GitHub OAuth** and **Google OAuth** for secure access.

---

##  Project Structure

SpringBoot-Microservices/
├── APIGateway/ ← Spring Cloud Gateway for routing
├── EurekaServer/ ← Netflix Eureka for service discovery
├── Microservice-1/ ← First microservice 
├── Microservice-2/ ← Second microservice 
├── Microservice-3/ ← Third microservice 
└── PostMan-Collection/ ← Collections for testing APIs


##  Tech Stack

- **Java 17+**  - Java
- **Spring Boot** – microservices framework  
- **Spring Cloud Gateway** – centralized API routing , Load Balancing
- **Springboot Actuator** - Exposes endpoints for Health checks & Application Performance
- **Eureka Server** – service registry & discovery  
- **Resilience4j** – fault-tolerance mechanisms:
  - Circuit Breaker  
  - Rate Limiter  
  - Retry Pattern  
  - Bulkhead  
- **Kafka** – event streaming platform for async communication between microservices  
- **OAuth 2.0** – authentication using GitHub OAuth & Google OAuth  
- **Docker** – containerized services  
- **Postman** – API testing & mock flows  
---

##  Getting Started

###  Prerequisites
- Java 17+ (compatible with your Spring Boot version)  
- Maven or Gradle  
- Docker (optional, if you're running containers)

#### ▶️ Run the Project Locally

1. **Clone the repo**
   ```bash
   git clone https://github.com/Manoj-Vadigireddygari/SpringBoot-Microservices.git
   cd SpringBoot-Microservices
2. Start Eureka Server
3. Start Microservices in seperate Terminal
4. Start API Gateway
5. Import the Postman Collection and Test the Endpoints using Postman
