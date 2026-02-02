# Trips API

## Public Endpoints

### List Trips

```http
GET /api/v1/trips
```

**Query Parameters:**

| Param | Type | Description |
|-------|------|-------------|
| `destination` | string | Filter by destination slug |
| `type` | string | Filter by trip type (PACKAGE, DAY_TOUR, CRUISE) |
| `minDuration` | int | Minimum duration in days |
| `maxDuration` | int | Maximum duration in days |
| `minPrice` | decimal | Minimum price |
| `maxPrice` | decimal | Maximum price |
| `minRating` | decimal | Minimum rating (1-5) |
| `sort` | string | Sort field (price, rating, duration, popularity) |
| `order` | string | Sort order (asc, desc) |
| `page` | int | Page number (default: 1) |
| `pageSize` | int | Items per page (default: 20, max: 50) |
| `locale` | string | Content locale (en, es, ar) |

**Response:**

```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "slug": "pyramids-day-tour",
      "title": "Pyramids of Giza Day Tour",
      "shortDescription": "Explore the ancient wonders...",
      "thumbnail": "https://cdn.egypttours.com/trips/pyramids-thumb.jpg",
      "destination": {
        "slug": "cairo",
        "name": "Cairo"
      },
      "duration": 1,
      "tripType": "DAY_TOUR",
      "price": 89.00,
      "rating": 4.8,
      "reviewCount": 156,
      "bookingMode": "INSTANT"
    }
  ],
  "pagination": {
    "page": 1,
    "pageSize": 20,
    "totalItems": 45,
    "totalPages": 3
  }
}
```

---

### Get Trip Details

```http
GET /api/v1/trips/{slug}
```

**Response:**

```json
{
  "success": true,
  "data": {
    "id": 1,
    "slug": "pyramids-day-tour",
    "title": "Pyramids of Giza Day Tour",
    "description": "Full markdown description...",
    "shortDescription": "Explore the ancient wonders...",
    "destination": {
      "slug": "cairo",
      "name": "Cairo",
      "country": "Egypt"
    },
    "duration": 1,
    "tripType": "DAY_TOUR",
    "bookingMode": "INSTANT",
    "maxCapacity": 20,
    "pricing": {
      "basePrice": 89.00,
      "childPrice": 45.00,
      "currency": "USD",
      "rules": [
        {
          "name": "Peak Season",
          "price": 99.00,
          "validFrom": "2026-06-01",
          "validTo": "2026-08-31"
        }
      ]
    },
    "itinerary": [
      {
        "day": 1,
        "title": "Cairo Exploration",
        "description": "Start your day with hotel pickup...",
        "locations": ["Giza Pyramids", "Sphinx", "Egyptian Museum"]
      }
    ],
    "included": [
      "Hotel pickup and drop-off",
      "Professional Egyptologist guide",
      "Entrance fees",
      "Lunch"
    ],
    "excluded": [
      "Tips and gratuities",
      "Personal expenses"
    ],
    "images": [
      {
        "url": "https://cdn.egypttours.com/trips/pyramids-1.jpg",
        "alt": "Pyramids of Giza",
        "isThumbnail": true
      }
    ],
    "rating": 4.8,
    "reviewCount": 156,
    "relatedTrips": [
      { "slug": "luxor-day-trip", "title": "Luxor Day Trip" }
    ],
    "updatedAt": "2026-01-15T10:00:00Z"
  }
}
```

---

### Get Trip Reviews

```http
GET /api/v1/trips/{slug}/reviews
```

**Query Parameters:**

| Param | Type | Description |
|-------|------|-------------|
| `sort` | string | Sort by (recent, rating) |
| `rating` | int | Filter by rating (1-5) |
| `page` | int | Page number |

**Response:**

```json
{
  "success": true,
  "data": [
    {
      "id": 101,
      "rating": 5,
      "comment": "Amazing experience! Our guide was fantastic...",
      "author": {
        "firstName": "Sarah",
        "lastName": "M."
      },
      "verified": true,
      "createdAt": "2026-01-20T14:30:00Z",
      "response": {
        "comment": "Thank you for your kind words!",
        "respondedAt": "2026-01-21T09:00:00Z"
      }
    }
  ]
}
```

---

### Check Trip Availability

```http
GET /api/v1/trips/{slug}/availability
```

**Query Parameters:**

| Param | Type | Description |
|-------|------|-------------|
| `from` | date | Start date (YYYY-MM-DD) |
| `to` | date | End date (YYYY-MM-DD) |

**Response:**

```json
{
  "success": true,
  "data": {
    "available": [
      { "date": "2026-03-01", "spots": 15, "price": 89.00 },
      { "date": "2026-03-02", "spots": 20, "price": 89.00 }
    ],
    "unavailable": [
      { "date": "2026-03-05", "reason": "SOLD_OUT" }
    ]
  }
}
```

---

## Admin Endpoints

### Create Trip

```http
POST /api/v1/admin/trips
Authorization: Bearer {admin_token}
```

**Request:**

```json
{
  "slug": "new-cairo-tour",
  "destinationId": 1,
  "duration": 1,
  "tripType": "DAY_TOUR",
  "basePrice": 99.00,
  "maxCapacity": 20,
  "bookingMode": "INSTANT",
  "translations": [
    {
      "locale": "en",
      "title": "New Cairo Tour",
      "description": "...",
      "shortDescription": "..."
    }
  ],
  "itinerary": [...],
  "included": [...],
  "excluded": [...]
}
```

### Update Trip

```http
PUT /api/v1/admin/trips/{id}
```

### Delete Trip

```http
DELETE /api/v1/admin/trips/{id}
```
