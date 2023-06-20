# Parkee-POS

Design: https://www.figma.com/file/EVlaHXBtvKVv0mehXPnSIM/Parkee-POS?type=design&node-id=0%3A1&t=8CZzYpRS8sPrX9e3-1

| Service  | Tech Stack|
|----------|-----------|
| Backend  | `Java, Spring Boot, PostgreSQL, Flyway` |
| Frontend | `React, SCSS` |

## Preview: 

https://github.com/VL-037/parkee-pos/assets/68309124/6443db27-ca0b-48b1-b9c3-65a9f6b1aaf1

## Features

- Parking Check In
- Parking Check Out (w/ voucher discount)
- [SQL Migration](backend/src/main/resources/db/migration)
- [JAR file](backend/target/vincentlow.parkee-backend-0.0.1-SNAPSHOT.jar)

## How to run:

- Backend
  - run `mvn spring-boot:run` on `/backend` OR
  - run `java -jar target/vincentlow.parkee-backend-0.0.1-SNAPSHOT.jar` on `/backend`
  - APIs will be served on http://localhost:8080

  
- Frontend
  - run these commands on `/frontend`
    ```
    npm install
    npm start
    ```
  - react-app can be access on http://localhost:3000
