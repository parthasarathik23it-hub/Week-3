# Library Book Inventory System — Week 3

Week 3 extends the Week 2 command-line Library Book Inventory System with JUnit 5 unit tests and a documented debugging exercise.

## Technology
- Java 17+
- JUnit 5
- Maven
- ArrayList / Java Collections Framework

## Project Structure
```text
library-book-inventory/
├── src/
│   ├── Book.java
│   ├── BookManager.java
│   └── Main.java
├── test/
│   └── BookManagerTest.java
├── debugging/
│   └── BUG_REPRODUCTION.md
├── docs/
│   └── Week_3_Testing_Debugging_Report.docx
├── pom.xml
├── README.md
└── SAMPLE_RUN.txt
```

## Run Unit Tests
From the project root:

```bash
mvn test
```

Maven downloads the JUnit 5 dependency and executes `BookManagerTest` through the Surefire plugin.

## Test Coverage
The test suite covers:
- Adding one and multiple books
- Duplicate ISBN rejection
- Empty inventory behavior
- Defensive copy returned by `getAllBooks()`
- Existing and missing ISBN searches
- Case-insensitive ISBN matching
- Updating existing and missing books
- Deleting existing and missing books
- Deleting one book without affecting other books
- Book string representation
- Book setters/getters
- ISBN preservation during update

## Debugging Exercise
Three controlled defects were intentionally introduced and then resolved:
1. Duplicate ISBN validation removed.
2. Incorrect ISBN lookup during update.
3. Missing removal statement during delete.

See `debugging/BUG_REPRODUCTION.md` and the Week 3 report for the debugging process.

## Week 2 Relationship
The production classes are the Week 2 implementation. Week 3 adds automated unit tests without changing the application's core CLI behavior.
