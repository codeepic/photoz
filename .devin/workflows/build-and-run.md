---
description: How to build and run the photoz-clone application
---

## Build

// turbo
1. Clean and build the project:
   ```bash
   mvn clean install
   ```

## Run

2. Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

3. Application starts on `http://localhost:8080`

## Quick Test Endpoints

4. Test the health check:
   ```bash
   curl http://localhost:8080/
   ```

5. Get all photos:
   ```bash
   curl http://localhost:8080/photos
   ```

6. Create a new photo:
   ```bash
   curl -X POST http://localhost:8080/photos -H "Content-Type: application/json" -d "{\"id\":\"3\",\"fileName\":\"test.jpg\"}"
   ```
