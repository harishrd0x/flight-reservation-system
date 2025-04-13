# 📬 API Endpoints Documentation (For Frontend Devs)

All backend endpoints of the Flight Reservation System, grouped by module.

---

## 🔐 Auth

| Method | Endpoint        | Description              |
|--------|------------------|--------------------------|
| POST   | `/api/register`  | Register new user        |
| POST   | `/api/login`     | Login user (returns JWT) |

---

## 👤 User (Admin)

| Method | Endpoint           | Description        |
|--------|--------------------|--------------------|
| GET    | `/api/users`       | List all users     |

---

## ✈️ Flights

| Method | Endpoint                | Description                    |
|--------|-------------------------|--------------------------------|
| POST   | `/api/flights`          | Add new flight (admin only)    |
| PUT    | `/api/flights/{id}`     | Update flight (admin only)     |
| DELETE | `/api/flights/{id}`     | Delete flight (admin only)     |
| GET    | `/api/flights`          | Get all flights                |
| GET    | `/api/flights/search`   | Search flights by src/dest/date|
| GET    | `/api/flights/{id}`     | Get flight by ID               |

---

## 📍 Airports

| Method | Endpoint              | Description               |
|--------|-----------------------|---------------------------|
| POST   | `/api/airports`       | Add airport (admin only)  |
| PUT    | `/api/airports/{id}`  | Update airport (admin)    |
| DELETE | `/api/airports/{id}`  | Delete airport (admin)    |
| GET    | `/api/airports`       | List all airports         |
| GET    | `/api/airports/{id}`  | Get airport by ID         |

---

## 🛩️ Airplanes

| Method | Endpoint               | Description                 |
|--------|------------------------|-----------------------------|
| POST   | `/api/airplanes`       | Add airplane (admin only)   |
| PUT    | `/api/airplanes/{id}`  | Update airplane (admin)     |
| DELETE | `/api/airplanes/{id}`  | Delete airplane (admin)     |
| GET    | `/api/airplanes`       | List all airplanes          |
| GET    | `/api/airplanes/{id}`  | Get airplane by ID          |

---

## 📦 Bookings

| Method | Endpoint                          | Description                        |
|--------|-----------------------------------|------------------------------------|
| POST   | `/api/bookings`                   | Book a flight (customer only)      |
| GET    | `/api/bookings/user/{userId}`     | View customer's bookings           |
| GET    | `/api/admin/bookings`             | View all bookings (admin only)     |

---

## 💰 Wallet

| Method | Endpoint                 | Description                        |
|--------|--------------------------|------------------------------------|
| POST   | `/api/wallets/create`    | Create wallet (once per customer) |
| GET    | `/api/wallets/{userId}`  | Get wallet balance                 |
| POST   | `/api/wallets/add`       | Add money to wallet                |
| POST   | `/api/wallets/pay`       | Make payment (internal use)       |

---

## ⭐ Reviews

| Method | Endpoint                       | Description                          |
|--------|--------------------------------|--------------------------------------|
| POST   | `/api/reviews`                 | Post a review (customer only)        |
| GET    | `/api/reviews/flight/{id}`     | Get reviews for specific flight      |
| GET    | `/api/reviews`                 | List all reviews (for homepage)      |

---

## 🔧 Headers

All authenticated routes require this header:
```http
Authorization: Bearer <your-jwt-token>
Content-Type: application/json
```

---

## 🔄 Status Codes

| Code | Meaning            |
|------|--------------------|
| 200  | Success            |
| 201  | Created            |
| 400  | Bad request        |
| 401  | Unauthorized       |
| 403  | Forbidden          |
| 404  | Not found          |
| 500  | Server error       |

