# 🧪 TEST LOG
BookingMx – Reservations Module (Java)

This document summarizes the issues found, solutions applied, and learning outcomes during the development of unit tests for **ReservationService**

---

## ✅ 1. Environment Setup Issues

### **Issue 1 — Repository was being tracked by Git**
- When running tests, Maven generated many files inside `backend/target/`.
- These files appeared in `git status` and polluted the repository.

**Solution:**
- Updated `.gitignore` to exclude all Java/Maven/JaCoCo build paths.
- Removed previously tracked build files using:  
  `git rm -r --cached backend/target/`

**Result:**  
Repository is now clean, and build artifacts are no longer tracked.

---

## ❗ 2. Testing Logic Issues Encountered

### **Issue 2 — Mockito could not mock the ReservationRepository**
- Mockito threw:  
  *“Cannot mock this class… underlying exception…”*
- Reason: the repository is a concrete class with internal state, and Java 25 restricts inline mocking.

**Solution:**
- Removed Mockito entirely.
- Used the **real in-memory ReservationRepository**, which behaves like a test double.
- Injected it using reflection into `ReservationService`.

**Result:**  
Tests run successfully with no mocking framework needed.

### ⚠️ Issue 3 — No constructor in ReservationRequest
- Initial attempts assumed a constructor like:  
  `new ReservationRequest("guest", "hotel", date1, date2)`
- The class does not provide such a constructor.

**Solution:**  
Used setter-based initialization:
```java
ReservationRequest req = new ReservationRequest();
req.setGuestName(...);
req.setHotelName(...);
req.setCheckIn(...);
req.setCheckOut(...);
```
---

## 🧪 3. Test Execution & Results
✔ Created tests for:

- `create()` — valid and invalid reservations
- `update()` — success, not found, canceled reservation
- `cancel()` — success, not found
- `validateDates()` — null dates, past dates, invalid ranges

✔ All 9 tests passed successfully:
```bash
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

✔ Achieved high coverage using JaCoCo _(see screenshot in `/backend/screenshots/`)_

---

## 📘 4. Lessons Learned

Mocking is not always the correct approach — real in-memory repositories can simplify testing. 
Test classes must align with the actual structure of DTOs and models (constructor assumptions cause errors).

Achieving **high coverage** requires testing:

- normal paths
- negative paths
- exception handling
- validation logic

A clean `.gitignore` is critical to maintain a professional repository. 
Reflection can be used safely to inject dependencies for testing private fields when DI is not implemented.

---

## 📎 5. Evidence

All screenshots (test execution + JaCoCo coverage) are located in:

```bash
backend/screenshots/
        ├─ test_success_2.png
        └─ jacoco_coverage_report.png
```