# Pagination

## Pagination Format

All list endpoints support pagination with consistent response structure.

---

## Request Parameters

| Param | Type | Default | Max | Description |
|-------|------|---------|-----|-------------|
| `page` | int | 1 | - | Page number (1-indexed) |
| `pageSize` | int | 20 | 50 | Items per page |
| `sort` | string | - | - | Sort field |
| `order` | string | asc | - | Sort order (asc/desc) |

---

## Response Format

```json
{
  "success": true,
  "data": [...],
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

---

## Sorting

### Available Sort Fields by Endpoint

| Endpoint | Sort Fields |
|----------|-------------|
| `/trips` | price, rating, duration, createdAt, popularity |
| `/blog` | publishedAt, views |
| `/bookings` | createdAt, travelDate, totalPrice |
| `/reviews` | createdAt, rating |

### Example

```http
GET /api/v1/trips?sort=price&order=asc&page=1&pageSize=10
```

---

## Filtering

Filters are applied via query parameters:

```http
GET /api/v1/trips?destination=cairo&minPrice=50&maxPrice=200&type=DAY_TOUR
```

---

## Implementation

### Controller

```java
@GetMapping
public ResponseEntity<PagedResponse<TripListDTO>> getTrips(
        @Valid TripFilterRequest filter,
        @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
    return ResponseEntity.ok(tripService.findTrips(filter, pageable));
}
```

### Response Builder

```java
public static <T> PagedResponse<T> of(Page<T> page) {
    return PagedResponse.<T>builder()
        .data(page.getContent())
        .pagination(Pagination.builder()
            .page(page.getNumber() + 1)
            .pageSize(page.getSize())
            .totalItems(page.getTotalElements())
            .totalPages(page.getTotalPages())
            .hasNext(page.hasNext())
            .hasPrev(page.hasPrevious())
            .build())
        .build();
}
```
