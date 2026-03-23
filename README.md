# E-Bookshop: Architecture Refactor

## How to Start
- **Requirements:** Maven, Docker
- **Steps:**
    - cd to /backend-java-springboot
    - run ```mvn clean package```
    - run ```docker-compose up --build -d```
    
## Overview
This project aims to convert a typical schoolwork using Jakarta EE Monolith into a modern Microservices architecture.

## Phases of this webserver
- **Phase 1 (Legacy):** IM2073 Jakarta EE (Servlets/JDBC) running on Tomcat.
- **Phase 2 (Refactor):** Node.js Gateway + Spring Boot 3.x Backend (In Progress).

## Key Technical Shifts
- **Language:** Migrating from Pure Java to a Java + Node.js.
- **Infrastructure:** Moving from manual server installs to Docker Containerization.

