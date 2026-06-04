# AGENTS.md - Codebase Guide for AI Coding Agents

## Project Overview
**Photoz Clone** is a Spring Boot REST API for managing photos. It's a learning project (Marco Behler's tutorial) demonstrating basic CRUD operations with a RESTful interface.

- **Stack**: Java 21, Spring Boot 3.2.5, Spring Data JDBC, H2 database, Maven
- **Build**: `mvn clean install` or use `mvnw.cmd` wrapper
- **Test**: `mvn test`
- **Run**: `mvn spring-boot:run` or via IDE launcher
- **Entry Point**: `PhotozCloneApplication.main()` prints "Xavier Photoz Clone started"

## Architecture & Data Flow

### Current Implementation (In-Memory)
The application uses a **HashMap in-memory database** (`PhotozController.db`), NOT actual database persistence despite H2/JDBC dependencies being added to pom.xml. This is intentional for learning purposes.

```
Request → PhotozController → HashMap db → Photo model → JSON Response
```

### Business Objects
- **Photo** (`Photo.java`): Simple POJO with `id` (String) and `fileName` (String)
  - No annotations (no JPA/JDBC mapping yet)
  - Basic getters/setters only
  - Note: Inconsistent naming in constructor (`Photo(id, fileName)`) vs field access

### REST Endpoints
All endpoints under `/photos`:

| Method | Endpoint | Handler | Behavior |
|--------|----------|---------|----------|
| GET | `/` | `index()` | Returns "Hello World" health check |
| GET | `/photos` | `get()` | Returns all photos as Collection |
| GET | `/photos/{id}` | `getPhoto(id)` | Returns single photo or HTTP 404 |
| POST | `/photos` | `createPhoto(photo)` | Adds to HashMap, returns photo |
| DELETE | `/photos/{id}` | `deletePhoto(id)` | Removes from HashMap or HTTP 404 |

**Error Handling Pattern**: Uses `ResponseStatusException` (not throwing generic RuntimeException).

## Code Patterns & Conventions

### Spring Boot Conventions
- Single `@RestController` class (no service/repository layers yet)
- No request validation or DTO transformation
- Initial data seeded in HashMap anonymous initialization block
- Commented-out code shows evolution (List → List.of → HashMap iterations)

### Naming Conventions
- Package: `com.jetbrains.marco.photoz.clone`
- No separation of concerns yet (controller handles full CRUD logic)
- Simple resource name `photos` (plural) for collection endpoints

### Testing
- Basic Spring Boot test skeleton exists (`PhotozCloneApplicationTests.java`)
- No endpoint tests yet - integration tests should be added here

## Key Development Workflows

### Add New Feature
1. Extend `Photo.java` with new fields (add getters/setters)
2. Update initial data seeded in `PhotozController.db`
3. Add new endpoint methods to `PhotozController` with `@GetMapping`, `@PostMapping`, or `@DeleteMapping`
4. Response automatically serialized to JSON by Spring

### Future Evolution Path
The presence of Spring Data JDBC dependency (unused) suggests planned next steps:
- Replace HashMap with actual H2 database via JDBC  
- Move CRUD logic to repository layer
- Add proper entity annotations (no `@Table`, `@Column` currently)
- Introduce service layer for business logic

## Integration Points & External Dependencies

### Maven Dependencies
- `spring-boot-starter-web`: REST endpoints and HTTP handling
- `spring-boot-starter-data-jdbc`: Database abstraction (currently unused)
- `h2`: Runtime database (currently unused)
- `spring-boot-starter-test`: Testing framework

### No External APIs
No cross-service integration, third-party APIs, or async processing currently.

## Before Modifying This Codebase

1. **Understand the in-memory approach**: Don't assume data persists across restarts
2. **Respect the learning context**: This is a teaching project; changes should teach concepts clearly
3. **Use ResponseStatusException**: Don't revert to generic RuntimeException
4. **Avoid over-engineering**: Keep patterns simple until requirements demand complexity
5. **Keep JSON serialization simple**: Photo fields auto-serialize; add `@JsonProperty` if renaming needed

## Quick Commands

```bash
# Build and run
mvn clean install
mvn spring-boot:run

# Run tests
mvn test

# Compile only (faster)
mvn compile
```

