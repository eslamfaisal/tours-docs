# Error Handling

## Error Response Format

```json
{
  "success": false,
  "error": {
    "code": "ERROR_CODE",
    "message": "Human-readable error message",
    "details": [...],
    "traceId": "trace_abc123"
  },
  "meta": {
    "timestamp": "2026-02-02T22:00:00Z"
  }
}
```

---

## Error Codes

### Authentication Errors (4xx)

| Code | HTTP | Description |
|------|------|-------------|
| `UNAUTHORIZED` | 401 | No token provided |
| `INVALID_TOKEN` | 401 | Token is invalid |
| `TOKEN_EXPIRED` | 401 | Token has expired |
| `INVALID_CREDENTIALS` | 401 | Wrong email/password |
| `FORBIDDEN` | 403 | Insufficient permissions |
| `EMAIL_NOT_VERIFIED` | 403 | Email verification required |

### Validation Errors

| Code | HTTP | Description |
|------|------|-------------|
| `VALIDATION_ERROR` | 400 | Request validation failed |
| `INVALID_FORMAT` | 400 | Invalid data format |
| `MISSING_FIELD` | 400 | Required field missing |

**Validation Error Details:**

```json
{
  "code": "VALIDATION_ERROR",
  "message": "Validation failed",
  "details": [
    { "field": "email", "message": "Invalid email format" },
    { "field": "password", "message": "Must be at least 8 characters" }
  ]
}
```

### Resource Errors

| Code | HTTP | Description |
|------|------|-------------|
| `NOT_FOUND` | 404 | Resource not found |
| `ALREADY_EXISTS` | 409 | Resource already exists |
| `CONFLICT` | 409 | State conflict |

### Business Logic Errors

| Code | HTTP | Description |
|------|------|-------------|
| `TRIP_UNAVAILABLE` | 422 | Trip not available on date |
| `BOOKING_EXPIRED` | 422 | Booking session expired |
| `PAYMENT_FAILED` | 422 | Payment processing failed |
| `CANCELLATION_NOT_ALLOWED` | 422 | Cannot cancel booking |
| `CAPACITY_EXCEEDED` | 422 | Max travelers exceeded |

### Server Errors

| Code | HTTP | Description |
|------|------|-------------|
| `INTERNAL_ERROR` | 500 | Internal server error |
| `SERVICE_UNAVAILABLE` | 503 | Service temporarily down |

---

## Error Handling Implementation

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.of("NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<FieldError> details = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(e -> new FieldError(e.getField(), e.getDefaultMessage()))
            .toList();
            
        return ResponseEntity.badRequest()
            .body(ErrorResponse.of("VALIDATION_ERROR", "Validation failed", details));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Unhandled exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse.of("INTERNAL_ERROR", "An unexpected error occurred"));
    }
}
```
