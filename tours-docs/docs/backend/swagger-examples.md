# Swagger Examples Convention

## Overview

This project uses a centralized approach for Swagger/OpenAPI documentation examples. All JSON examples are stored in a static utility class to ensure consistency and maintainability.

---

## Location

All Swagger examples are stored in:

```
src/main/java/com/egypttours/common/swagger/SwaggerExamples.java
```

---

## Structure

The `SwaggerExamples` class uses nested static classes to organize examples by feature/endpoint:

```java
public final class SwaggerExamples {

    // Auth API examples
    public static final class Auth {
        public static final String REGISTER_REQUEST = """...""";
        public static final String REGISTER_RESPONSE_SUCCESS = """...""";
        public static final String LOGIN_REQUEST = """...""";
        // etc.
    }

    // Admin Trip API examples
    public static final class AdminTrip {
        public static final String CREATE_TRIP_REQUEST = """...""";
        public static final String CREATE_TRIP_RESPONSE_SUCCESS = """...""";
        // etc.
    }

    // Public Trip API examples
    public static final class Trip {
        public static final String TRIP_LIST_RESPONSE = """...""";
        // etc.
    }
}
```

---

## Usage in Controllers

### Request Body Example

```java
@io.swagger.v3.oas.annotations.parameters.RequestBody(
    description = "Registration details",
    required = true,
    content = @Content(
        mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = RegisterRequest.class),
        examples = @ExampleObject(
            name = "Register Request",
            value = SwaggerExamples.Auth.REGISTER_REQUEST
        )
    )
)
public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
    // ...
}
```

### Response Examples (Multiple Cases)

```java
@ApiResponses(value = {
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "200",
        description = "Success",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            examples = @ExampleObject(
                name = "Success",
                value = SwaggerExamples.Auth.LOGIN_RESPONSE_SUCCESS
            )
        )
    ),
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "401",
        description = "Invalid credentials",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            examples = @ExampleObject(
                name = "Invalid Credentials",
                value = SwaggerExamples.Auth.LOGIN_RESPONSE_INVALID_CREDENTIALS
            )
        )
    )
})
```

---

## Best Practices

1. **Group by Feature**: Use nested classes to group examples by feature module
2. **Naming Convention**: Use descriptive names that indicate the scenario:
   - `*_REQUEST` - Request body examples
   - `*_RESPONSE_SUCCESS` - Successful response
   - `*_RESPONSE_*` - Specific error scenarios (e.g., `RESPONSE_NOT_FOUND`)
3. **Include All Cases**: Document success AND error responses
4. **Use Text Blocks**: Java 15+ text blocks (`"""..."""`) for readable JSON
5. **Keep Updated**: When DTOs change, update the examples accordingly

---

## Required Imports

```java
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
```

---

## Adding New Examples

When adding a new API endpoint:

1. Add a new nested class in `SwaggerExamples.java` if it's a new feature
2. Add request example constant
3. Add success response example constant
4. Add error response example constants for each possible error
5. Apply the examples in your controller using `@ExampleObject`

---

## Viewing Documentation

Access the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```
