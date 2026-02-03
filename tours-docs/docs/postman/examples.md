# Postman Examples

## Complete Booking Flow

### 1. Login

```http
POST {{baseUrl}}/auth/login
{
  "email": "customer@example.com",
  "password": "Password123"
}
```

### 2. Browse Trips

```http
GET {{baseUrl}}/trips?destination=cairo&type=DAY_TOUR
```

### 3. Get Trip Details

```http
GET {{baseUrl}}/trips/pyramids-day-tour
```

### 4. Check Availability

```http
GET {{baseUrl}}/trips/pyramids-day-tour/availability?from=2026-03-01&to=2026-03-31
```

### 5. Create Booking

```http
POST {{baseUrl}}/bookings
{
  "tripId": 1,
  "travelDate": "2026-03-15",
  "adults": 2,
  "travelers": [...]
}
```

### 6. View Booking

```http
GET {{baseUrl}}/account/bookings/ET-A1B2C3D4
```

---

## Admin Flow Example

### 1. Admin Login

```http
POST {{baseUrl}}/auth/login
{
  "email": "admin@egypttours.com",
  "password": "AdminPass123"
}
```

### 2. Create New Trip

```http
POST {{baseUrl}}/admin/trips
{
  "slug": "luxor-adventure",
  "destinationId": 2,
  "duration": 3,
  ...
}
```

### 3. View All Bookings

```http
GET {{baseUrl}}/admin/bookings?status=PENDING
```
