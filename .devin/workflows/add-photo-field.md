---
description: How to add a new field to the Photo model
---

## Steps

1. Open `photoz-clone/src/main/java/com/jetbrains/marco/photoz/clone/Photo.java`

2. Add the new private field:
   ```java
   private String newField;
   ```

3. Add getter and setter:
   ```java
   public String getNewField() {
       return newField;
   }

   public void setNewField(String newField) {
       this.newField = newField;
   }
   ```

4. Update the constructor if needed (optional - current pattern uses setters)

5. Update seed data in `PhotozController.java` if the field should have initial values

6. Run tests:
   ```bash
   mvn test
   ```

## Notes
- Fields auto-serialize to JSON (no `@JsonProperty` needed unless renaming)
- No database mapping required (in-memory HashMap storage)
