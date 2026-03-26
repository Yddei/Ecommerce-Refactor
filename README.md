# E-Bookshop: Architecture Refactor

## How to Start
- **Requirements:** Maven, Docker
- **Steps:**
    - rename ```.env.example``` to ```.env```, set ```DB_USERNAME``` and ```DB_ROOT_PASSWORD``` values.
    - run ```docker-compose up --build -d``` in terminal, at root directory.

## Overview
This project aims to convert a typical schoolwork using Jakarta EE Monolith into a modern Microservices architecture.

## Phases of this webserver
- **Phase 1 (Legacy):** School Project IM2073 Jakarta EE (Servlets/JDBC) running on Tomcat.
- **Phase 2 (Refactor):** Node.js Gateway + Spring Boot 3.x Backend (In Progress).
  - [x] Containerisation (Docker)
  - [ ] Implement authenticaation system
  - [x] API Gateway
  - [ ] Implement CORS and configure SecurityConfig.java
  - [ ] Service Decoupling
      - [x] Auth
      - [ ] Shop
      - [ ] Cart
      - [ ] Checkout

## Key Technical Shifts
- **Language:** Migrating from Pure Java to a Java + Node.js.
- **Infrastructure:** Moving from manual server installs to Docker Containerization.

