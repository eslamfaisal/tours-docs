package com.egypttours.common.swagger;

/**
 * Centralized JSON examples for Swagger/OpenAPI documentation.
 * Each API endpoint has its own set of request/response examples.
 */
public final class SwaggerExamples {

    private SwaggerExamples() {
        // Utility class - prevent instantiation
    }

    // ==================== COMMON ERROR EXAMPLES ====================

    public static final class Common {

        public static final String VALIDATION_ERROR_RESPONSE = """
                {
                    "status": 400,
                    "error": "Validation Failed",
                    "message": "One or more fields have validation errors",
                    "path": "/api/v1/example",
                    "timestamp": "2024-01-15T10:30:00",
                    "validationErrors": {
                        "fieldName": ["Field error message"]
                    }
                }
                """;

        public static final String UNAUTHORIZED_RESPONSE = """
                {
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "Full authentication is required to access this resource",
                    "path": "/api/v1/example",
                    "timestamp": "2024-01-15T10:30:00"
                }
                """;

        public static final String FORBIDDEN_RESPONSE = """
                {
                    "status": 403,
                    "error": "Forbidden",
                    "message": "You don't have permission to access this resource",
                    "path": "/api/v1/example",
                    "timestamp": "2024-01-15T10:30:00"
                }
                """;

        public static final String INTERNAL_SERVER_ERROR_RESPONSE = """
                {
                    "status": 500,
                    "error": "Internal Server Error",
                    "message": "An unexpected error occurred. Please try again later.",
                    "path": "/api/v1/example",
                    "timestamp": "2024-01-15T10:30:00"
                }
                """;

        private Common() {
        }
    }

    // ==================== AUTH API EXAMPLES ====================

    public static final class Auth {

        public static final String REGISTER_REQUEST = """
                {
                    "email": "john.doe@example.com",
                    "password": "SecureP@ss123",
                    "firstName": "John",
                    "lastName": "Doe"
                }
                """;

        public static final String REGISTER_RESPONSE_SUCCESS = """
                {
                    "success": true,
                    "message": "Success",
                    "data": {
                        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                        "email": "john.doe@example.com",
                        "firstName": "John",
                        "lastName": "Doe",
                        "role": "CUSTOMER"
                    },
                    "timestamp": "2024-01-15T10:30:00"
                }
                """;

        public static final String REGISTER_RESPONSE_EMAIL_EXISTS = """
                {
                    "status": 409,
                    "error": "Conflict",
                    "message": "User already exists with email: 'john.doe@example.com'",
                    "path": "/api/v1/auth/register",
                    "timestamp": "2024-01-15T10:30:00"
                }
                """;

        public static final String LOGIN_REQUEST = """
                {
                    "email": "super@egypttours.com",
                    "password": "super123"
                }
                """;

        public static final String LOGIN_RESPONSE_SUCCESS = """
                {
                    "success": true,
                    "message": "Success",
                    "data": {
                        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                        "email": "john.doe@example.com",
                        "firstName": "John",
                        "lastName": "Doe",
                        "role": "CUSTOMER"
                    },
                    "timestamp": "2024-01-15T10:30:00"
                }
                """;

        public static final String LOGIN_RESPONSE_INVALID_CREDENTIALS = """
                {
                    "status": 401,
                    "error": "Authentication Failed",
                    "message": "Invalid email or password",
                    "path": "/api/v1/auth/login",
                    "timestamp": "2024-01-15T10:30:00"
                }
                """;

        private Auth() {
        }
    }

    // ==================== ADMIN USER API EXAMPLES ====================

    public static final class AdminUser {

        public static final String CREATE_USER_REQUEST = """
                {
                    "firstName": "Jane",
                    "lastName": "Smith",
                    "email": "jane.smith@example.com",
                    "password": "SecurePass456!",
                    "role": "ADMIN"
                }
                """;

        public static final String CREATE_USER_RESPONSE = """
                {
                    "success": true,
                    "message": "User created successfully",
                    "data": {
                        "id": 10,
                        "firstName": "Jane",
                        "lastName": "Smith",
                        "email": "jane.smith@example.com",
                        "role": "ADMIN",
                        "active": true,
                        "createdAt": "2024-02-01T12:00:00",
                        "updatedAt": "2024-02-01T12:00:00"
                    },
                    "timestamp": "2024-02-01T12:00:00"
                }
                """;

        public static final String LIST_USERS_RESPONSE = """
                {
                    "success": true,
                    "message": "Success",
                    "data": [
                        {
                            "id": 1,
                            "firstName": "Super",
                            "lastName": "Admin",
                            "email": "super@egypttours.com",
                            "role": "SUPER",
                            "active": true
                        },
                        {
                            "id": 10,
                            "firstName": "Jane",
                            "lastName": "Smith",
                            "email": "jane.smith@example.com",
                            "role": "ADMIN",
                            "active": true
                        }
                    ],
                    "timestamp": "2024-02-01T12:00:00"
                }
                """;

        public static final String USER_NOT_FOUND_RESPONSE = """
                {
                    "status": 404,
                    "error": "Not Found",
                    "message": "User not found with id: '999'",
                    "path": "/api/v1/admin/users/999",
                    "timestamp": "2024-02-01T12:00:00"
                }
                """;

        private AdminUser() {
        }
    }

    // ==================== ADMIN TRIP API EXAMPLES ====================

    public static final class AdminTrip {

        public static final String CREATE_TRIP_REQUEST = """
                {
                    "title": "Pyramids of Giza Adventure",
                    "description": "Explore the magnificent Pyramids of Giza and the Sphinx on this unforgettable 3-day adventure.",
                    "destination": "Giza, Egypt",
                    "durationInDays": 3,
                    "price": 499.99,
                    "startDate": "2024-03-15",
                    "endDate": "2024-03-17",
                    "maxTravelers": 20
                }
                """;

        public static final String CREATE_TRIP_RESPONSE_SUCCESS = """
                {
                    "success": true,
                    "message": "Trip created successfully",
                    "data": {
                        "id": 1,
                        "title": "Pyramids of Giza Adventure",
                        "description": "Explore the magnificent Pyramids of Giza...",
                        "destination": "Giza, Egypt",
                        "durationInDays": 3,
                        "price": 499.99,
                        "startDate": "2024-03-15",
                        "endDate": "2024-03-17",
                        "maxTravelers": 20,
                        "status": "DRAFT",
                        "featured": false
                    },
                    "timestamp": "2024-01-15T10:30:00"
                }
                """;

        public static final String UPDATE_TRIP_REQUEST = """
                {
                    "title": "Pyramids of Giza Deluxe Adventure",
                    "description": "Updated description with more details...",
                    "destination": "Giza, Egypt",
                    "durationInDays": 4,
                    "price": 599.99,
                    "startDate": "2024-03-15",
                    "endDate": "2024-03-18",
                    "maxTravelers": 15,
                    "status": "PUBLISHED",
                    "featured": true
                }
                """;

        public static final String TRIP_NOT_FOUND_RESPONSE = """
                {
                    "status": 404,
                    "error": "Not Found",
                    "message": "Trip not found with id: '999'",
                    "path": "/api/v1/admin/trips/999",
                    "timestamp": "2024-01-15T10:30:00"
                }
                """;

        private AdminTrip() {
        }
    }

    // ==================== PUBLIC TRIP API EXAMPLES ====================

    public static final class Trip {

        public static final String TRIP_LIST_RESPONSE = """
                {
                    "success": true,
                    "message": "Success",
                    "data": [
                        {
                            "id": 1,
                            "title": "Pyramids of Giza Adventure",
                            "destination": "Giza, Egypt",
                            "durationInDays": 3,
                            "price": 499.99,
                            "startDate": "2024-03-15",
                            "status": "PUBLISHED",
                            "featured": true
                        },
                        {
                            "id": 2,
                            "title": "Luxor Temple Experience",
                            "destination": "Luxor, Egypt",
                            "durationInDays": 2,
                            "price": 299.99,
                            "startDate": "2024-04-01",
                            "status": "PUBLISHED",
                            "featured": false
                        }
                    ],
                    "timestamp": "2024-01-15T10:30:00"
                }
                """;

        public static final String TRIP_DETAIL_RESPONSE = """
                {
                    "success": true,
                    "message": "Success",
                    "data": {
                        "id": 1,
                        "title": "Pyramids of Giza Adventure",
                        "description": "Explore the magnificent Pyramids of Giza and the Sphinx...",
                        "destination": "Giza, Egypt",
                        "durationInDays": 3,
                        "price": 499.99,
                        "startDate": "2024-03-15",
                        "endDate": "2024-03-17",
                        "maxTravelers": 20,
                        "status": "PUBLISHED",
                        "featured": true
                    },
                    "timestamp": "2024-01-15T10:30:00"
                }
                """;

        private Trip() {
        }
    }
}
