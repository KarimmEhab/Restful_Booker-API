# 🧪 API Testing Project

A complete API testing project demonstrating both **Manual API Testing** using **Postman/Newman** and **Automated API Testing** using **Rest Assured** and **TestNG**.  
This project was built during ITI training to showcase industry-level skills in REST API validation, automation frameworks, and reporting.

---

🔧 Tech Stack
Postman – Manual API testing
Newman – Command-line test runner for Postman collections
Rest Assured – Java library for RESTful automation
TestNG – Framework for organizing and running tests
Maven – Project management and dependency handling
Allure Reports – Beautiful test reporting
SLF4J (Logger) – Structured logging
POJOs – Clean request/response handling
Modular Java Structure – Separated tests by module

---

 ✅ Covered Test Scenarios

🔹 Manual Testing (Postman)
- Get all resources (users, products, carts…)
- Create, update, delete operations
- Authentication (login)
- Query parameters (limit, sort, filter)
- Negative test cases (invalid IDs, missing fields)

🔹 Automation Testing (Rest Assured + TestNG)
- Status code verification  
- Response body content validation  
- Response time checks  
- Data-driven tests with POJOs  
- SLF4J logging for better debugging  
- End-to-end flow: create → validate → delete

---

▶️ How to Run Automation Tests
- Install dependencies:
  mvn clean install

- Run tests:
  mvn test

- Generate Allure Report (optional):
  mvn allure:serve

---

  🧰 Tools Used
Category	Tools/Frameworks
Manual API	Postman, Newman
Automation	Rest Assured, TestNG
Build Tool	Maven
Reporting	Allure, Logger (SLF4J)
Language	Java

---

🙌 Credits
Developed as part of the ITI Graduation Project
Guided by mentors and supported by ITI training team


