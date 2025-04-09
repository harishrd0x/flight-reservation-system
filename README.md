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

### 🧑‍💼 Customer
- Register and log in
- Search and book flights
- View current and past bookings
- Manage wallet (add money)
- Download tickets (PDF)

---

## 🧰 Tech Stack

| Layer      | Technology         |
|------------|--------------------|
| Backend    | Spring Boot (Java) |
| Frontend   | React              |
| Database   | MySQL              |
| Testing    | Postman            |
| Build Tool | Maven              |

---

## 📁 Project Structure

```
flight-reservation-system/
├── backend/         # Spring Boot backend
│   ├── src/
│   ├── pom.xml
├── frontend/        # React frontend
│   ├── src/
│   ├── package.json
├── .gitignore
├── .gitattributes
└── README.md
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
   server.port=8080
   spring.datasource.url=jdbc:mysql://localhost:3306/flightdb
   spring.datasource.username=root
   spring.datasource.password=yourpassword
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

| Method | Endpoint                 | Description               |
|--------|--------------------------|---------------------------|
| GET    | `/api/flights`           | List all flights          |
| POST   | `/api/flights`           | Add a new flight (admin)  |
| POST   | `/api/bookings`          | Book a flight (customer)  |
| GET    | `/api/bookings/{id}`     | View booking by ID        |
| POST   | `/api/wallet/add`        | Add money to wallet       |
| GET    | `/api/wallet/{userId}`   | View wallet balance       |

> Use **Postman** to test these endpoints.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## 🙋‍♂️ Authors

- [Anubhav](https://github.com/your-username)
- [Harish](https://github.com/your-username)
- [Harsha](https://github.com/your-username)
- [Irfan](https://github.com/your-username)
- [Karthik](https://github.com/your-username)
- [Subhendu](https://github.com/your-username)
- [Suhas](https://github.com/your-username)
- [Tejas](https://github.com/your-username)
- [Venu](https://github.com/your-username)
- [Yukta](https://github.com/your-username)
