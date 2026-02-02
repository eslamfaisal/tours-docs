# API Overview

## Design Principles

The API follows RESTful conventions with:

- **Resource-based URLs** — `/trips`, `/bookings`, `/users`
- **HTTP methods** — GET (read), POST (create), PUT (update), DELETE (remove)
- **JSON responses** — All responses in JSON format
- **Consistent error handling** — Standard error response structure
- **Pagination** — Cursor or offset-based for collections
- **Filtering** — Query parameters for filtering/sorting

---

## Request Headers

| Header | Required | Description |
|--------|----------|-------------|
| `Authorization` | Conditional | `Bearer {token}` for protected endpoints |
| `Content-Type` | Yes (POST/PUT) | `application/json` |
| `Accept-Language` | No | Locale for translated content (`en`, `es`, `ar`) |
| `X-Request-ID` | No | Client-generated request ID for tracing |

---

## Response Format

### Success Response

```json
{
  "success": true,
  "data": { ... },
  "meta": {
    "timestamp": "2026-02-02T22:00:00Z",
    "requestId": "req_abc123"
  }
}
```

### Paginated Response

```json
{
  "success": true,
  "data": [ ... ],
  "pagination": {
    "page": 1,
    "pageSize": 20,
    "totalItems": 156,
    "totalPages": 8,
    "hasNext": true,
    "hasPrev": false
  }
}
```

### Error Response

```json
{
  "success": false,
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Invalid request data",
    "details": [
      { "field": "email", "message": "Invalid email format" }
    ]
  },
  "meta": {
    "timestamp": "2026-02-02T22:00:00Z",
    "requestId": "req_abc123"
  }
}
```

---

## HTTP Status Codes

| Code | Meaning | Usage |
|------|---------|-------|
| 200 | OK | Successful GET, PUT |
| 201 | Created | Successful POST |
| 204 | No Content | Successful DELETE |
| 400 | Bad Request | Validation errors |
| 401 | Unauthorized | Missing/invalid token |
| 403 | Forbidden | Insufficient permissions |
| 404 | Not Found | Resource doesn't exist |
| 409 | Conflict | Duplicate resource |
| 422 | Unprocessable | Business logic error |
| 429 | Too Many Requests | Rate limit exceeded |
| 500 | Server Error | Internal error |

---

## Versioning

API versioning is path-based: `/api/v1/...`

Breaking changes require a new version (`/api/v2/...`).

---

## Rate Limiting

| Tier | Limit | Window |
|------|-------|--------|
| Anonymous | 100 requests | 1 minute |
| Authenticated | 500 requests | 1 minute |
| Admin | 1000 requests | 1 minute |

Rate limit headers:

```
X-RateLimit-Limit: 100
X-RateLimit-Remaining: 95
X-RateLimit-Reset: 1707012345
```

---

## OpenAPI Specification

Full API documentation available at:

- Swagger UI: `/api/docs`
- OpenAPI JSON: `/api/docs/openapi.json`
