---
description: How to run tests for the photoz-clone project
---

## Steps

// turbo
1. Run all tests:
   ```bash
   mvn test
   ```

2. Run a specific test class:
   ```bash
   mvn test -Dtest=PhotozCloneApplicationTests
   ```

3. Run tests with verbose output:
   ```bash
   mvn test -X
   ```

## Notes
- Tests are located in `photoz-clone/src/test/java/`
- Currently only a basic Spring Boot context test exists
- Add integration tests for endpoints in `PhotozCloneApplicationTests.java`
