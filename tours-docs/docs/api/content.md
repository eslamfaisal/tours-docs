# Content API

## Blog Endpoints

### List Blog Posts

```http
GET /api/v1/blog
```

**Query Parameters:**

| Param | Type | Description |
|-------|------|-------------|
| `category` | string | Filter by category |
| `tag` | string | Filter by tag |
| `search` | string | Search in title/content |
| `page` | int | Page number |

### Get Blog Post

```http
GET /api/v1/blog/{slug}
```

---

## Vlog Endpoints

### List Vlogs

```http
GET /api/v1/vlogs
```

### Get Vlog

```http
GET /api/v1/vlogs/{slug}
```

---

## Destinations

### List Destinations

```http
GET /api/v1/destinations
```

### Get Destination

```http
GET /api/v1/destinations/{slug}
```

---

## Search

### Global Search

```http
GET /api/v1/search
```

**Query Parameters:**

| Param | Type | Description |
|-------|------|-------------|
| `q` | string | Search query |
| `type` | string | Filter by type (trips, blog, destinations) |

**Response:**

```json
{
  "success": true,
  "data": {
    "trips": [...],
    "blog": [...],
    "destinations": [...]
  }
}
```

---

## Contact/Leads

### Submit Contact Form

```http
POST /api/v1/contact
```

**Request:**

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "+1234567890",
  "subject": "Trip Inquiry",
  "message": "I would like to know more about...",
  "tripSlug": "pyramids-day-tour"
}
```
