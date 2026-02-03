# Bookings API

## Customer Endpoints

### Create Booking

```http
POST /api/v1/bookings
Authorization: Bearer {token}
```

**Request:**

```json
{
  "tripId": 1,
  "travelDate": "2026-03-15",
  "adults": 2,
  "children": 1,
  "addOns": [1, 3],
  "travelers": [
    {
      "firstName": "John",
      "lastName": "Doe",
      "nationality": "American",
      "passportNumber": "AB1234567",
      "dateOfBirth": "1985-05-15",
      "travelerType": "ADULT"
    },
    {
      "firstName": "Jane",
      "lastName": "Doe",
      "nationality": "American",
      "passportNumber": "CD7654321",
      "dateOfBirth": "1988-08-20",
      "travelerType": "ADULT"
    },
    {
      "firstName": "Tim",
      "lastName": "Doe",
      "nationality": "American",
      "dateOfBirth": "2018-03-10",
      "travelerType": "CHILD"
    }
  ],
  "specialRequests": "Vegetarian meal preference"
}
```

**Response (201):**

```json
{
  "success": true,
  "data": {
    "id": 501,
    "bookingNumber": "ET-A1B2C3D4",
    "status": "PENDING_PAYMENT",
    "totalPrice": 223.00,
    "trip": {
      "slug": "pyramids-day-tour",
      "title": "Pyramids of Giza Day Tour"
    },
    "travelDate": "2026-03-15",
    "paymentUrl": "https://checkout.stripe.com/c/pay/..."
  }
}
```

---

### Get My Bookings

```http
GET /api/v1/account/bookings
Authorization: Bearer {token}
```

**Query Parameters:**

| Param | Type | Description |
|-------|------|-------------|
| `status` | string | Filter by status |
| `page` | int | Page number |

**Response:**

```json
{
  "success": true,
  "data": [
    {
      "id": 501,
      "bookingNumber": "ET-A1B2C3D4",
      "trip": {
        "slug": "pyramids-day-tour",
        "title": "Pyramids of Giza Day Tour",
        "thumbnail": "..."
      },
      "travelDate": "2026-03-15",
      "travelers": { "adults": 2, "children": 1 },
      "totalPrice": 223.00,
      "status": "CONFIRMED",
      "paymentStatus": "PAID",
      "createdAt": "2026-02-01T10:00:00Z"
    }
  ]
}
```

---

### Get Booking Details

```http
GET /api/v1/account/bookings/{bookingNumber}
Authorization: Bearer {token}
```

**Response:**

```json
{
  "success": true,
  "data": {
    "id": 501,
    "bookingNumber": "ET-A1B2C3D4",
    "trip": {
      "id": 1,
      "slug": "pyramids-day-tour",
      "title": "Pyramids of Giza Day Tour",
      "destination": "Cairo",
      "duration": 1,
      "thumbnail": "..."
    },
    "travelDate": "2026-03-15",
    "travelers": [
      {
        "firstName": "John",
        "lastName": "Doe",
        "travelerType": "ADULT"
      }
    ],
    "addOns": [
      { "name": "Private Guide", "price": 50.00 }
    ],
    "pricing": {
      "subtotal": 178.00,
      "addOnsTotal": 50.00,
      "discount": 5.00,
      "total": 223.00,
      "currency": "USD"
    },
    "status": "CONFIRMED",
    "paymentStatus": "PAID",
    "payments": [
      {
        "amount": 223.00,
        "method": "CREDIT_CARD",
        "status": "COMPLETED",
        "paidAt": "2026-02-01T10:05:00Z"
      }
    ],
    "invoice": {
      "url": "/api/v1/bookings/ET-A1B2C3D4/invoice"
    },
    "specialRequests": "Vegetarian meal preference",
    "createdAt": "2026-02-01T10:00:00Z"
  }
}
```

---

### Cancel Booking

```http
POST /api/v1/account/bookings/{bookingNumber}/cancel
Authorization: Bearer {token}
```

**Request:**

```json
{
  "reason": "Change of travel plans"
}
```

**Response:**

```json
{
  "success": true,
  "data": {
    "bookingNumber": "ET-A1B2C3D4",
    "status": "CANCELLED",
    "refund": {
      "eligible": true,
      "amount": 167.25,
      "percentage": 75,
      "reason": "Cancelled 20 days before travel"
    }
  }
}
```

---

### Download Invoice

```http
GET /api/v1/account/bookings/{bookingNumber}/invoice
Authorization: Bearer {token}
```

**Response:** PDF file

---

## Admin Endpoints

### List All Bookings

```http
GET /api/v1/admin/bookings
Authorization: Bearer {admin_token}
```

### Update Booking Status

```http
PATCH /api/v1/admin/bookings/{id}/status
```

**Request:**

```json
{
  "status": "CONFIRMED"
}
```

### Process Refund

```http
POST /api/v1/admin/bookings/{id}/refund
```

**Request:**

```json
{
  "amount": 167.25,
  "reason": "Customer requested cancellation"
}
```
