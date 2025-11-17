# BookingMx

Minimal vanilla JS + Spring Boot project to practice unit tests, improve code quality, and strengthen documentation processes.

---

# 📘 Project Overview

BookingMx is a minimal learning-oriented project designed to practice:

- Backend development with **Spring Boot**
- Frontend logic using **vanilla JavaScript**
- Unit testing in **Java (JUnit + JaCoCo)** and **JavaScript (Jest + Babel)**
- Clean architecture, documentation, and test-driven development practices

The project includes two main modules:

### 🔵 **Reservations Module (Java Backend)**
A simple reservation system supporting:
- Create a reservation
- Update reservation details
- Cancel a reservation
- Validate dates and prevent invalid inputs

### 🟢 **City Graph Module (JavaScript Frontend)**
A graph structure representing nearby cities and distances:
- Add cities and connections
- Validate datasets
- Build graph structures
- Get nearby cities sorted by distance

Both modules were expanded with fully automated unit tests as part of the project’s quality assurance workflow.

---

# ⚙️ Installation & Setup

This project has **two independent environments**:


## 🟦 Backend (Java + Spring Boot)

### Requirements
- Java 17+
- Maven 3+
- IDE (IntelliJ, VS Code, or Eclipse)

### Run Backend
```bash
cd backend
mvn spring-boot:run
```

Backend will start at:
```
http://localhost:8080
```


## 🟩 Frontend (JavaScript + Node.js)

### Requirements
- Node.js 18+
- NPM 9+

### Run Frontend
```bash
cd frontend
npm install
npm run serve
```

Runs on:
```
http://localhost:5173/
```

---

# 🧪 Automated Tests

The project contains **two independent test suites**, one for each module.


## ✔️ **Java Tests (JUnit + JaCoCo)**

### Location
```
backend/src/test/java/...
```

### Run Tests
```bash
cd backend
mvn test
```

### Coverage Report
After running tests:
```
backend/target/site/jacoco/index.html
```

### Highlights
- Fully tested ReservationService  
- Positive + negative cases  
- Date validation logic  
- Exception handling  
- In-memory repository behavior  
- Achieved **>90%** coverage

### Evidence
```
backend/screenshots/
 ├── jacoco_coverage_report.png
 ├── jacoco_folder.png
 ├── tests_success_2.png
 └── tests_success.png
```

---

## ✔️ **JavaScript Tests (Jest + Babel)**

### Location
```
frontend/tests/
```

### Run Tests
```bash
cd frontend
npm test
```

### Coverage Report
```
frontend/coverage/lcov-report/index.html
```

### Highlights
- Full Graph class coverage
- getNearbyCities() behavior + sorting
- validateGraphData edge cases
- buildGraph structure testing
- Achieved **100% statements / 100% lines / 95% branches**

### Evidence
```
frontend/screenshots/
 ├── coverage_report.png
 └── tests_success.png
```

---

# 📚 Code Documentation (Javadoc / JsDoc)

Both modules include structured documentation to help developers understand the logic clearly.

### ✔ Java (Javadoc)
- ReservationService
- ReservationRepository
- DTOs & Exceptions

### ✔ JavaScript (JsDoc)
- Graph class (addCity, addEdge, neighbors)
- validateGraphData()
- buildGraph()
- getNearbyCities()
- sampleData

Documentation includes:
- Purpose of the method  
- Parameter descriptions  
- Return values  
- Error conditions  
- Important decisions  

---

# 🗂 Project Structure
```
BookingMx-testing/
 ├── backend/
 │   ├── src/main/java/com/bookingmx/reservations/...
 │   ├── src/test/java/com/bookingmx/reservations/...
 │   ├── screenshots/
 │   └── pom.xml
 │
 ├── frontend/
 │   ├── js/
 │   ├── tests/
 │   ├── screenshots/
 │   ├── babel.config.js
 │   ├── jest.config.js
 │   └── package.json
 │
 ├── TEST_LOG.md
 ├── TECH_LOG_JS.md
 └── README.md
```

 
  
   
    
---

## 📐 System Architecture & Diagrams

This project includes three diagrams that describe the internal architecture, backend structure, and frontend module relationships of the BookingMx system. All diagrams are available in PDF format inside the `diagrams/` folder.


### **1. System Architecture Diagram**
📄`diagrams/SystemArchitecture_diagram.pdf`

Illustrates how the JavaScript frontend interacts with the Spring Boot backend, which manages data through an in-memory repository. Includes request/response flow and internal data interactions.

### **2. Java Class Diagram**
📄`diagrams/JavaClass_diagram.pdf`

Represents the backend structure including:
- Reservation entity
- DTOs
- Service layer
- Repository layer
- Custom exceptions
- Status enum

This diagram highlights the relationships between backend components.

### **3. JavaScript Module Diagram**

📄 `diagrams/JSModule_diagram.pdf`

Describes how the graph utilities on the frontend are structured:
- Graph class
- buildGraph()
- validateGraphData()
- getNearbyCities()
- sampleData

Shows how these modules interact to perform graph-related operations.