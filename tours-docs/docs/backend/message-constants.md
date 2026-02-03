# Message Constants Convention

## Overview

This project uses a centralized approach for message constants. All user-facing messages (success, error, validation) are stored in a static utility class to ensure consistency, maintainability, and i18n readiness.

---

## Location

All message constants are stored in:

```
src/main/java/com/egypttours/common/constants/Messages.java
```

---

## Structure

The `Messages` class uses nested static classes to organize messages by category:

```java
public final class Messages {

    // Success messages
    public static final class Success {
        public static final String TRIP_CREATED = "Trip created successfully";
        public static final String LOGIN = "Login successful";
        // etc.
    }

    // Error messages
    public static final class Error {
        public static final String INVALID_CREDENTIALS = "Invalid email or password";
        public static final String FORBIDDEN = "You don't have permission to access this resource";
        // etc.
    }

    // Validation messages (for @Valid annotations)
    public static final class Validation {
        public static final String EMAIL_REQUIRED = "Email is required";
        public static final String PASSWORD_TOO_SHORT = "Password must be at least 6 characters";
        // etc.
    }

    // Entity names for dynamic messages
    public static final class Entity {
        public static final String USER = "User";
        public static final String TRIP = "Trip";
        // etc.
    }

    // Template messages with placeholders
    public static final class Template {
        public static final String NOT_FOUND_WITH_ID = "%s not found with id: '%s'";
        // etc.
    }
}
```

---

## Usage Examples

### In Controllers (Success Messages)

```java
import com.egypttours.common.constants.Messages;

@PostMapping
public ResponseEntity<ApiResponse<TripResponse>> createTrip(@RequestBody CreateTripRequest request) {
    TripResponse trip = tripService.createTrip(request);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(Messages.Success.TRIP_CREATED, trip));
}
```

### In Validation Annotations

```java
import com.egypttours.common.constants.Messages;

public class RegisterRequest {

    @NotBlank(message = Messages.Validation.EMAIL_REQUIRED)
    @Email(message = Messages.Validation.EMAIL_INVALID)
    private String email;

    @NotBlank(message = Messages.Validation.PASSWORD_REQUIRED)
    @Size(min = 8, message = Messages.Validation.PASSWORD_TOO_SHORT)
    private String password;
}
```

### In Exception Handlers

```java
import com.egypttours.common.constants.Messages;

@ExceptionHandler(BadCredentialsException.class)
public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse.of(401, "Authentication Failed", Messages.Error.INVALID_CREDENTIALS, path));
}
```

### With Template Messages

```java
import com.egypttours.common.constants.Messages;

// Dynamic message with placeholders
String message = String.format(Messages.Template.NOT_FOUND_WITH_ID, Messages.Entity.TRIP, tripId);
// Result: "Trip not found with id: '123'"

throw new ResourceNotFoundException(message);
```

---

## Message Categories

### Success Messages (`Messages.Success`)

| Constant | Value |
|----------|-------|
| `DEFAULT` | "Success" |
| `CREATED` | "Created successfully" |
| `UPDATED` | "Updated successfully" |
| `DELETED` | "Deleted successfully" |
| `TRIP_CREATED` | "Trip created successfully" |
| `TRIP_UPDATED` | "Trip updated successfully" |
| `BOOKING_CREATED` | "Booking created successfully" |
| `LOGIN` | "Login successful" |
| `REGISTRATION` | "Registration successful" |

### Error Messages (`Messages.Error`)

| Constant | Value |
|----------|-------|
| `INTERNAL_SERVER` | "An unexpected error occurred. Please try again later." |
| `INVALID_CREDENTIALS` | "Invalid email or password" |
| `UNAUTHORIZED` | "Unauthorized access" |
| `FORBIDDEN` | "You don't have permission to access this resource" |
| `RESOURCE_NOT_FOUND` | "Resource not found" |
| `EMAIL_EXISTS` | "Email already exists" |
| `TRIP_NOT_FOUND` | "Trip not found" |

### Validation Messages (`Messages.Validation`)

| Constant | Value |
|----------|-------|
| `REQUIRED` | "This field is required" |
| `EMAIL_REQUIRED` | "Email is required" |
| `EMAIL_INVALID` | "Invalid email format" |
| `PASSWORD_REQUIRED` | "Password is required" |
| `PASSWORD_TOO_SHORT` | "Password must be at least 6 characters" |
| `TITLE_REQUIRED` | "Title is required" |
| `PRICE_POSITIVE` | "Price must be positive" |

### Template Messages (`Messages.Template`)

| Constant | Value | Usage |
|----------|-------|-------|
| `NOT_FOUND_WITH_ID` | "%s not found with id: '%s'" | `String.format(Messages.Template.NOT_FOUND_WITH_ID, "Trip", 123)` |
| `ALREADY_EXISTS_WITH_FIELD` | "%s already exists with %s: '%s'" | `String.format(Messages.Template.ALREADY_EXISTS_WITH_FIELD, "User", "email", "test@example.com")` |

---

## Best Practices

1. **Never hardcode messages** - Always use constants from `Messages` class
2. **Group by category** - Use the appropriate nested class (`Success`, `Error`, `Validation`, etc.)
3. **Use templates for dynamic messages** - Use `String.format()` with `Messages.Template.*`
4. **Consistent naming** - Use `ENTITY_ACTION` format (e.g., `TRIP_CREATED`, `BOOKING_CANCELLED`)
5. **Keep messages user-friendly** - Messages should be clear and actionable

---

## Adding New Messages

When adding a new message:

1. Identify the correct category (`Success`, `Error`, `Validation`, `Template`)
2. Add the constant to the appropriate nested class
3. Use descriptive constant names
4. Use the constant wherever the message is needed

```java
// In Messages.java
public static final class Success {
    public static final String PAYMENT_PROCESSED = "Payment processed successfully";
}

// Usage
ApiResponse.success(Messages.Success.PAYMENT_PROCESSED, paymentData);
```

---

## i18n Readiness

This structure makes it easy to externalize messages for internationalization:

1. Replace constant values with keys
2. Load values from `.properties` files based on locale
3. The code using constants remains unchanged

```properties
# messages_en.properties
success.trip.created=Trip created successfully

# messages_ar.properties
success.trip.created=تم إنشاء الرحلة بنجاح
```
