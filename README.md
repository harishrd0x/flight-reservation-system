# ✈️ Flight Reservation System

A full-stack Flight Reservation System built with **Spring Boot** (backend) and **React** (frontend). This application supports two user roles — **Admin** and **Customer** — each with distinct features.

---

## 🚀 Features

### 🛠️ Admin
- Login with admin credentials
- Perform CRUD operations on:
  - Flights
  - Airports
  - Airplanes
- View all bookings made by customers
- View all registered users

### 🧑‍💼 Customer
- Register and log in
- Search and book flights
- View current and past bookings
- Manage wallet (create, add money)
- Submit and view flight reviews
- Download tickets (PDF)

---

## 🧰 Tech Stack

| Layer      | Technology         |
|------------|--------------------|
| Backend    | Spring Boot (Java) |
| Frontend   | React              |
| Database   | Oracle             |
| Testing    | Postman            |
| Build Tool | Maven              |

---

## 📁 Project Structure

```
.
├── .gitattributes
├── .gitignore
├── README.md
├── backend
│   ├── .DS_Store
│   ├── .classpath
│   ├── .mvn
│   │   └── wrapper
│   │       └── maven-wrapper.properties
│   ├── .project
│   ├── .settings
│   │   ├── .jsdtscope
│   │   ├── org.eclipse.core.resources.prefs
│   │   ├── org.eclipse.jdt.apt.core.prefs
│   │   ├── org.eclipse.jdt.core.prefs
│   │   ├── org.eclipse.wst.common.component
│   │   ├── org.eclipse.wst.common.project.facet.core.xml
│   │   ├── org.eclipse.wst.jsdt.ui.superType.container
│   │   ├── org.eclipse.wst.jsdt.ui.superType.name
│   │   ├── org.eclipse.wst.validation.prefs
│   │   └── org.springframework.ide.eclipse.prefs
│   ├── HELP.md
│   ├── api-docs.txt
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── version1
│       │   │           └── frs
│       │   │               ├── BackendApplication.java
│       │   │               ├── ServletInitializer.java
│       │   │               ├── config
│       │   │               │   └── SecurityConfig.java
│       │   │               ├── controller
│       │   │               │   ├── AdminController.java
│       │   │               │   ├── AdminFlightController.java
│       │   │               │   ├── AirplaneController.java
│       │   │               │   ├── AirportController.java
│       │   │               │   ├── AuthController.java
│       │   │               │   ├── BookingController.java
│       │   │               │   ├── FlightController.java
│       │   │               │   ├── LoginController.java
│       │   │               │   ├── RegistrationController.java
│       │   │               │   ├── ReviewController.java
│       │   │               │   ├── UserController.java
│       │   │               │   └── WalletController.java
│       │   │               ├── dto
│       │   │               │   ├── .DS_Store
│       │   │               │   ├── AirplaneRequest.java
│       │   │               │   ├── AirportRequest.java
│       │   │               │   ├── AirportResponse.java
│       │   │               │   ├── BookingRequest.java
│       │   │               │   ├── BookingResponse.java
│       │   │               │   ├── FlightRequest.java
│       │   │               │   ├── FlightResponse.java
│       │   │               │   ├── LoginRequest.java
│       │   │               │   ├── LoginResponse.java
│       │   │               │   ├── ReviewRequest.java
│       │   │               │   ├── ReviewResponse.java
│       │   │               │   ├── UserRegistrationRequest.java
│       │   │               │   ├── UserResponse.java
│       │   │               │   ├── WalletRequest.java
│       │   │               │   └── WalletResponse.java
│       │   │               ├── model
│       │   │               │   ├── Airplane.java
│       │   │               │   ├── Airport.java
│       │   │               │   ├── Booking.java
│       │   │               │   ├── Flight.java
│       │   │               │   ├── Review.java
│       │   │               │   ├── User.java
│       │   │               │   └── Wallet.java
│       │   │               ├── repository
│       │   │               │   ├── AirplaneRepository.java
│       │   │               │   ├── AirportRepository.java
│       │   │               │   ├── BookingRepository.java
│       │   │               │   ├── FlightRepository.java
│       │   │               │   ├── ReviewRepository.java
│       │   │               │   ├── UserRepository.java
│       │   │               │   └── WalletRepository.java
│       │   │               ├── security
│       │   │               │   ├── CustomUserDetailsService.java
│       │   │               │   ├── JwtAuthFilter.java
│       │   │               │   └── JwtUtil.java
│       │   │               └── service
│       │   │                   ├── AirplaneService.java
│       │   │                   ├── AirportService.java
│       │   │                   ├── BookingService.java
│       │   │                   ├── FlightService.java
│       │   │                   ├── ReviewService.java
│       │   │                   ├── UserService.java
│       │   │                   ├── WalletService.java
│       │   │                   └── impl
│       │   │                       ├── AirplaneServiceImpl.java
│       │   │                       ├── AirportServiceImpl.java
│       │   │                       ├── BookingServiceImpl.java
│       │   │                       ├── FlightServiceImpl.java
│       │   │                       ├── ReviewServiceImpl.java
│       │   │                       ├── UserServiceImpl.java
│       │   │                       └── WalletServiceImpl.java
│       │   ├── resources
│       │   │   └── application.properties
│       │   └── webapp
│       └── test
│           ├── java
│           │   └── com
│           │       └── version1
│           │           └── frs
│           │               └── BackendApplicationTests.java
│           └── resources
├── frontend
└── project-structure.txt
```

---

## ⚙️ Getting Started

### 🔧 Backend Setup

1. Navigate to the backend folder:
   ```bash
   cd backend
   ```

2. Configure the database in `src/main/resources/application.properties`:
   ```properties
   server.port=1212
   spring.datasource.url=jdbc:oracle:thin:@localhost:1522:xe
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
   spring.jpa.show-sql=true
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Run the backend application:
   ```bash
   ./mvnw spring-boot:run
   ```

---

### 🌐 Frontend Setup

1. Navigate to the frontend folder:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the React app:
   ```bash
   npm start
   ```

---

## 📬 Sample API Endpoints

| Method | Endpoint                     | Description                    |
|--------|------------------------------|--------------------------------|
| POST   | `/api/register`              | Customer registration          |
| POST   | `/api/login`                 | Login and receive JWT token    |
| GET    | `/api/flights`              | List all flights               |
| POST   | `/api/flights`              | Add a new flight (admin)       |
| POST   | `/api/bookings`             | Book a flight (customer)       |
| GET    | `/api/bookings/user/{id}`   | View user's bookings           |
| GET    | `/api/admin/bookings`       | View all bookings (admin)      |
| POST   | `/api/wallets/create`       | Create wallet (customer)       |
| POST   | `/api/wallets/add`          | Add money to wallet            |
| GET    | `/api/wallets/{userId}`     | View wallet balance            |
| POST   | `/api/reviews`              | Submit a review                |
| GET    | `/api/reviews`              | All reviews (home display)     |
| GET    | `/api/reviews/flight/{id}`  | Reviews for a specific flight  |
| GET    | `/api/users`                | Get all registered users       |

> Use **Postman** to test these endpoints. Pre-configured collections are available.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## 🙋‍♂️ Authors

- [Anubhav](https://github.com/anubhavrouth)
- [Harish](https://github.com/harishrd0x)
- [Harsha](https://github.com/harsha24-tech)
- [Irfan](https://github.com/sheik-irfan)
- [Karthik](https://github.com/kunni-n)
- [Subhendu](https://github.com/subhendu-1)
- [Suhas](https://github.com/hssuhasgowda)
- [Tejas](https://github.com/tejasmehta07)
- [Venu](https://github.com/venupathange)
- [Yukta](https://github.com/Yukku-21)

