---
description: How to add a new REST endpoint to the PhotozController
---

## Steps

1. Open `photoz-clone/src/main/java/com/jetbrains/marco/photoz/clone/PhotozController.java`

2. Add a new handler method with the appropriate annotation:
   - `@GetMapping("/photos/...")` for read operations
   - `@PostMapping("/photos/...")` for create operations
   - `@DeleteMapping("/photos/...")` for delete operations
   - `@PutMapping("/photos/...")` for update operations

3. For endpoints returning a single resource that may not exist, use:
   ```java
   if (result == null) {
       throw new ResponseStatusException(HttpStatus.NOT_FOUND);
   }
   ```

4. Run tests to verify:
   ```bash
   mvn test
   ```

5. Start the application and test manually:
   ```bash
   mvn spring-boot:run
   ```
