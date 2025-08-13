#Hotel Room API Testing - Rest Assured
## Overview
This project automates the testing of Hotel Room API endpoints using Rest Assured and TestNG.
It validates different API functionalities such as room creation, retrieval, updating, and deletion with proper assertions and response verifications.

## Features
- **Automated API Testing:** Covers all primary endpoints of the Hotel Room API.
- **Rest Assured Integration:** Streamlines HTTP request building and response assertions.
- **TestNG Execution:** Structured and prioritized test management.
- **Reusable Utilities:** Modular helper methods for common API tasks.
- **Centralized Configuration:** Easily manage base URLs and authentication details.
- **JSON Response Validation:** Ensures API responses conform to expected schemas and values.
- **Clear Reporting:** HTML reports generated with TestNG for easy result analysis.

## Tech Stack

- **Java** (JDK 8+)
- **Rest Assured**
- **TestNG**
- **Maven**
- **JSON**

## Project Structure

```
HotelRoomAPITesting/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── (Configuration and utility classes)
│   └── test/
│       └── java/
│           └── (Test cases for API endpoints)
├── pom.xml
├── testng.xml
└── README.md
```

- **src/main/java:** Configuration classes (e.g., base URLs, authentication) and reusable request/response utilities.
- **src/test/java:** Test cases for each major API endpoint.
- **pom.xml:** Maven configuration and dependencies.
- **testng.xml:** TestNG suite definitions.

## Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/ShreetamaDas/HotelRoomAPITesting.git
   cd HotelRoomAPITesting
   ```

2. **Prerequisites**
   - Java 8 or higher ([AdoptOpenJDK](https://adoptopenjdk.net/))
   - Maven ([Download Maven](https://maven.apache.org/download.cgi))

3. **Install Dependencies**
   ```bash
   mvn clean install
   ```

## Configuration

- **Base URLs and Authentication:**
  - Update the configuration file (such as `config.properties` or a Java constants class) with your API's base URL and authentication credentials.
- **Endpoints:**
  - All API endpoint paths are centrally managed for easy modification.

## Running Tests

You can execute the test suite using Maven or directly via your IDE.

**Using Maven:**
```bash
mvn test
```

**Using TestNG (from IDE):**
- Right-click on `testng.xml` and select "Run".

## Test Reporting

- Upon completion, TestNG will generate HTML reports under `target/surefire-reports/emailable-report.html`.
- Open the report in your browser for a detailed view of all test cases, including passed/failed status and failure traces.
