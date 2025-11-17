 # BookingTrainsTicket — Backend

This repository contains the backend service for a Train Ticket Booking system implemented with Spring Boot. The application exposes RESTful HTTP APIs to manage users, trains, schedules, seats and bookings, and integrates with PAYOS for payment processing.

**Key features:**
- **Authentication & Authorization:** Spring Security-based user authentication (JWT/passwords).
- **Train & Schedule management:** CRUD operations for trains, coaches, schedules and schedule batches.
- **Seat & Ticket management:** Seat availability, booking, cancellation and ticket generation.
- **Order & Payment processing:** Checkout and integration with PAYOS payment provider.
- **Thymeleaf templates:** Simple HTML views for payment success/cancel pages.

**Tech stack:**
- **Language:** Java 17
- **Frameworks:** Spring Boot (Web, Data JPA, Security, WebFlux), Thymeleaf
- **Persistence:** MySQL (via `mysql-connector-j` and Spring Data JPA / Hibernate)
- **Build:** Maven (wrapper provided: `mvnw` / `mvnw.cmd`)
- **API docs:** springdoc OpenAPI (UI available when enabled)
- **Other:** Lombok, Gson, PAYOS Java SDK

**Repository layout (important paths):**
- `src/main/java/com/DevJavaMinh` — application sources
- `src/main/resources/application.properties` — runtime configuration
- `src/main/resources/templates` — Thymeleaf views (`index.html`, `success.html`, `cancel.html`)

**Prerequisites**
- Java 17 or newer
- Maven (wrapper included; not strictly required)
- MySQL server (or compatible RDBMS)

**Configuration**
Copy or update `src/main/resources/application.properties` with environment-specific values. Important properties:
- `spring.datasource.url` — JDBC URL for your MySQL database
- `spring.datasource.username` and `spring.datasource.password` — DB credentials
- `server.port` — application port (default in this project: `8081`)
- PAYOS integration keys:
  - `PAYOS_CLIENT_ID`
  - `PAYOS_API_KEY`
  - `PAYOS_CHECKSUM_KEY`

Sensitive values should be injected via environment variables or a secrets manager in production.

**Build & Run (local, Windows PowerShell)**
1. Build with the included Maven wrapper:

```powershell
.\mvnw.cmd clean package -DskipTests
```

2. Run the application (after build):

```powershell
java -jar .\target\BookingTrainsTicket-0.0.1-SNAPSHOT.jar
```

Alternatively run directly with the wrapper during development:

```powershell
.\mvnw.cmd spring-boot:run
```

**API overview (high level)**
The project exposes REST controllers located under `src/main/java/com/DevJavaMinh/controller`. Primary controllers include:
- `AuthController` — user registration/login and token endpoints
- `UserController` — user profile and management
- `TrainController` — CRUD for trains
- `CoachController` — coach configuration per train
- `ScheduleController` / `ScheduleTrainController` — schedule management and batch creation
- `SeatController` — seat queries and status
- `TicketController` — ticket creation and retrieval
- `OrderController` / `CheckoutController` — checkout and order flow
- `PaymentController` — PAYOS payment callbacks and handling

For full endpoint details, enable the OpenAPI UI (`springdoc-openapi`) and visit `/swagger-ui.html` or `/swagger-ui/index.html` (depending on configuration) after starting the application.

**Database**
By default `spring.jpa.hibernate.ddl-auto=update` is set in `application.properties`. For production, prefer running controlled schema migrations (Flyway or Liquibase) and set `ddl-auto` to `validate` or remove it.

**Testing**
- Unit and integration tests can be run with Maven:

```powershell
.\mvnw.cmd test
```

**Development notes & suggestions**
- Move secrets out of `application.properties` into environment variables or profiles-based configuration.
- Add API documentation examples or Postman collection to `docs/`.
- Consider adding database migration scripts (Flyway/Liquibase) for production stability.

**Contributing**
If you want to contribute, please open an issue or a pull request. Include a clear description and tests for code changes.

**License**
This repository does not contain an explicit license. Add a `LICENSE` file if you intend to make this project open source.


