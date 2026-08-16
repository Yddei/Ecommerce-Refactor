# E-Bookshop: Architecture Refactor

## How to Start
- **Requirements:** Maven, Docker
- **Steps:**
    - rename ```.env.example``` to ```.env```, set ```DB_USERNAME``` and ```DB_ROOT_PASSWORD``` values.
    - run ```docker-compose up --build -d``` in terminal, at root directory.
    - test API via Postman

## Overview
This project aims to convert a typical schoolwork using Jakarta EE Monolith into a modern Microservices architecture.

## Phases of this webserver
- **Legacy:** School Project IM2073 Jakarta EE (Servlets/JDBC) running on Tomcat.
- **Refactored:** Node.js Gateway + Spring Boot 3.x Backend (In Progress).
  - [x] Containerisation (Docker)
  - [x] Implement authentication system
  - [x] API Gateway
  - [x] ~~Implement CORS and~~ configure SecurityConfig.java
  - [x] Service Decoupling
    - [x] Auth
    - [x] Shop
    - [ ] ~~Cart~~
    - [ ] ~~Checkout~~

- 16 Aug 2026: Due to time constraints, some of the above parts will be skipped. Project will be marked as done as I achieved my goals with this project (mainly to set up Docker). No site/GUI for some parts, mostly done via Postman.

## Key Technical Shifts
- **Language:** Migrating from Pure Java to a Java + Node.js.
- **Infrastructure:** Moving from manual server installs to Docker Containerization.

