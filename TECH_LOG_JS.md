# 🧪 JavaScript Technical Log – Graph Module Testing  
BookingMx – Testing & Quality Assurance (Frontend)

This document summarizes the main technical difficulties encountered while implementing unit tests for the **graph visualization module** using **Jest**, along with the solutions applied and lessons learned during the process.

---

## ✅ 1. Jest Environment Setup Issues

### **Issue 1 — Jest could not run ES Module (import/export) code**
Initial test execution failed with:

```bash
Jest encountered an unexpected 
SyntaxError: Cannot use import statement outside a module
```
**Cause:**  
Jest does not natively support ES Modules unless properly configured.

**Solution:**  
- Installed Babel-related packages:
  - `babel-jest`
  - `@babel/core`
  - `@babel/preset-env`
- Created `babel.config.js` with preset configuration.
- Updated `jest.config.js` to include:

    ```js
    transform: {
    "^.+\\.js$": "babel-jest",
    }
    ```

**Result:**
Jest successfully processed ES Modules and test files.

## ✅ 2. Syntax Error in Test Suite
### Issue 2 — “Unexpected token” in `graph.test.js`

Test run failed due to:
```java
Unexpected token (151:0)
```

**Cause:**

The test file was cut off mid-line during copy/paste, leaving a stray fragment:

```lua
exp
```

**Solution:**
- Inspected the test at the reported line.
- Removed the incomplete fragment.
- Restored the full `getNearbyCities()` test block.

**Result:**
Test suite parsed successfully, all tests executed correctly.

## ❗ 3. Managing Frontend Build Artifacts in Git
### Issue 3 — `coverage/` and `node_modules/` were not ignored

Jest generated a `coverage/` folder, and installing dependencies created `node_modules/`.

Both were initially untracked but needed to be properly ignored to avoid accidental commits.

**Solution:**
Updated root `.gitignore` to include:

- `frontend/node_modules/`
- `frontend/coverage/`
- `frontend/dist/`


**Result:**
Repository remains clean and lightweight, with no generated files committed.

## 🧪 4. Testing Logic & Edge Cases
### Issue 4 — Ensuring full coverage (90%+)

To meet the requirement, tests had to cover:
- Normal behavior
- Edge cases
- Error scenarios
- Invalid input types
- Negative paths
- Graph connectivity errors

Some functions (like `validateGraphData()`) required creating many combinations of invalid inputs.

**Solution:**
Designed a complete test plan covering:
- `addCity()`
- `addEdge()`
- `neighbors()`
- `validateGraphData()`
- `buildGraph()`
- `getNearbyCities()`
- sampleData validation

**Result**

Coverage achieved:
```
Statements: 100%
Branches:   95.34%
Functions:  100%
Lines:      100%
```

## 📎 5. Evidence

Screenshots of test execution and coverage report are located in: 
`frontend/screenshots/`


Files included:
- `coverage_report.png`
- `tests_success.png`
