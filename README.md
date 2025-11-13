# BookingMx

Minimal vanilla JS + Spring Boot project to practice unit tests.

## Run backend
```bash
cd backend
mvn spring-boot:run
```

## Run frontend
```bash
cd frontend
npm i
npm run serve
# http://localhost:5173
```
---

## 🏨 Project Overview

This repository includes backend and frontend components designed to practice:
- Java unit testing
- JavaScript unit testing
- Code validation
- Documentation
- Code coverage analysis

The backend contains a small reservations module built with Spring Boot, while the frontend uses vanilla JavaScript.

The main goal is to ensure reliable functionality through automated tests and proper documentation.

## 🧰 Testing & Quality Assurance

The Java backend incorporates robust unit tests for the ReservationService, covering:

✔ Reservation creation

✔ Reservation update

✔ Reservation cancellation

✔ Date validation logic

✔ Handling of invalid input

✔ Exception scenarios

- BadRequestException
- NotFoundException

These tests ensure correctness, consistency, and safe error handling across the module.

## 🧪 Running Tests

Navigate to the backend folder:

```bash
cd backend
mvn clean test
```

### View Code Coverage (JaCoCo)
After tests run, open:
```bash
backend/target/site/jacoco/index.html
```

You will find:

- Total project coverage
- Class-level coverage
- Line-by-line analysis

## 📂 Project Structure
```bash
BookingMx-testing/
 ├── backend/
 │   ├── src/main/java/com/bookingmx/reservations/...
 │   ├── src/test/java/com/bookingmx/reservations/service/ReservationServiceTest.java
 │   ├── screenshots/
 │   │    ├── test_success_2.png
 │   │    └── jacoco_coverage_report.png
 │   └── pom.xml
 │
 ├── frontend/
 │   ├── index.html
 │   ├── js/
 │   └── package.json
 │
 ├── TEST_LOG.md
 └── README.md
```

## 🖼 Evidence & Documentation

- Test execution screenshot
- Coverage report screenshot
- Log of issues found and how they were resolved (TEST_LOG.md)

You can find the evidence inside:
```
backend/screenshots/
```

## 🧹 Repository Hygiene

This project uses an enhanced .gitignore to keep the repository clean and professional by ignoring:

- Maven target/ directory
- IDE-specific files
- JaCoCo output files
- Node node_modules/
- OS/system files

Previously tracked build artifacts were removed using:

```bash
git rm -r --cached backend/target
```

This ensures that only relevant source code and documentation remain in the repository.