package com.egypttours.common.constants;

/**
 * Centralized message constants for the application.
 * Use these constants instead of hardcoded strings for consistency and i18n
 * readiness.
 */
public final class Messages {

    private Messages() {
        // Utility class - prevent instantiation
    }

    // ==================== SUCCESS MESSAGES ====================

    public static final class Success {
        public static final String DEFAULT = "Success";
        public static final String CREATED = "Created successfully";
        public static final String UPDATED = "Updated successfully";
        public static final String DELETED = "Deleted successfully";

        // Auth
        public static final String REGISTRATION = "Registration successful";
        public static final String LOGIN = "Login successful";
        public static final String LOGOUT = "Logout successful";
        public static final String PASSWORD_RESET = "Password reset email sent";
        public static final String PASSWORD_CHANGED = "Password changed successfully";

        // Trip
        public static final String TRIP_CREATED = "Trip created successfully";
        public static final String TRIP_UPDATED = "Trip updated successfully";
        public static final String TRIP_DELETED = "Trip deleted successfully";
        public static final String TRIP_PUBLISHED = "Trip published successfully";
        public static final String TRIP_ARCHIVED = "Trip archived successfully";

        // Booking
        public static final String BOOKING_CREATED = "Booking created successfully";
        public static final String BOOKING_CONFIRMED = "Booking confirmed successfully";
        public static final String BOOKING_CANCELLED = "Booking cancelled successfully";

        // User
        public static final String USER_UPDATED = "User updated successfully";
        public static final String USER_ACTIVATED = "User activated successfully";
        public static final String USER_DEACTIVATED = "User deactivated successfully";

        private Success() {
        }
    }

    // ==================== ERROR MESSAGES ====================

    public static final class Error {
        // General
        public static final String INTERNAL_SERVER = "An unexpected error occurred. Please try again later.";
        public static final String BAD_REQUEST = "Invalid request";
        public static final String VALIDATION_FAILED = "One or more fields have validation errors";

        // Auth
        public static final String INVALID_CREDENTIALS = "Invalid email or password";
        public static final String UNAUTHORIZED = "Unauthorized access";
        public static final String FORBIDDEN = "You don't have permission to access this resource";
        public static final String TOKEN_EXPIRED = "Token has expired";
        public static final String TOKEN_INVALID = "Invalid token";
        public static final String USER_NOT_FOUND = "User not found";
        public static final String EMAIL_EXISTS = "Email already exists";
        public static final String ADMIN_CREATION_RESTRICTED = "Admins cannot create or modify SUPER or ADMIN users";

        // Resource
        public static final String RESOURCE_NOT_FOUND = "Resource not found";
        public static final String RESOURCE_EXISTS = "Resource already exists";

        // Trip
        public static final String TRIP_NOT_FOUND = "Trip not found";
        public static final String TRIP_NOT_AVAILABLE = "Trip is not available for booking";
        public static final String TRIP_FULL = "Trip is fully booked";

        // Booking
        public static final String BOOKING_NOT_FOUND = "Booking not found";
        public static final String BOOKING_ALREADY_CANCELLED = "Booking is already cancelled";
        public static final String BOOKING_CANNOT_CANCEL = "Booking cannot be cancelled";

        // Payment
        public static final String PAYMENT_FAILED = "Payment processing failed";
        public static final String REFUND_FAILED = "Refund processing failed";

        private Error() {
        }
    }

    // ==================== VALIDATION MESSAGES ====================

    public static final class Validation {
        // Common
        public static final String REQUIRED = "This field is required";
        public static final String INVALID_FORMAT = "Invalid format";
        public static final String TOO_SHORT = "Value is too short";
        public static final String TOO_LONG = "Value is too long";

        // Email
        public static final String EMAIL_REQUIRED = "Email is required";
        public static final String EMAIL_INVALID = "Invalid email format";

        // Password
        public static final String PASSWORD_REQUIRED = "Password is required";
        public static final String PASSWORD_TOO_SHORT = "Password must be at least 6 characters";
        public static final String PASSWORD_TOO_WEAK = "Password is too weak";

        // Name
        public static final String FIRST_NAME_REQUIRED = "First name is required";
        public static final String LAST_NAME_REQUIRED = "Last name is required";
        public static final String ROLE_REQUIRED = "Role is required";

        // Admin

        // Trip
        public static final String TITLE_REQUIRED = "Title is required";
        public static final String DESCRIPTION_REQUIRED = "Description is required";
        public static final String DESTINATION_REQUIRED = "Destination is required";
        public static final String DURATION_REQUIRED = "Duration is required";
        public static final String DURATION_MIN = "Duration must be at least 1 day";
        public static final String PRICE_REQUIRED = "Price is required";
        public static final String PRICE_POSITIVE = "Price must be positive";
        public static final String START_DATE_REQUIRED = "Start date is required";
        public static final String END_DATE_REQUIRED = "End date is required";
        public static final String MAX_TRAVELERS_REQUIRED = "Max travelers is required";
        public static final String MAX_TRAVELERS_MIN = "Max travelers must be at least 1";

        // Date
        public static final String DATE_INVALID = "Invalid date";
        public static final String DATE_IN_PAST = "Date cannot be in the past";
        public static final String END_BEFORE_START = "End date must be after start date";

        private Validation() {
        }
    }

    // ==================== ENTITY NAMES (for dynamic messages) ====================

    public static final class Entity {
        public static final String USER = "User";
        public static final String TRIP = "Trip";
        public static final String BOOKING = "Booking";
        public static final String PAYMENT = "Payment";
        public static final String REVIEW = "Review";
        public static final String DESTINATION = "Destination";

        private Entity() {
        }
    }

    // ==================== TEMPLATE MESSAGES ====================

    /**
     * Template messages with placeholders.
     * Use String.format() to replace placeholders.
     */
    public static final class Template {
        public static final String NOT_FOUND_WITH_ID = "%s not found with id: '%s'";
        public static final String NOT_FOUND_WITH_FIELD = "%s not found with %s: '%s'";
        public static final String ALREADY_EXISTS_WITH_FIELD = "%s already exists with %s: '%s'";
        public static final String PARAMETER_TYPE_MISMATCH = "Parameter '%s' should be of type '%s'";

        private Template() {
        }
    }
}
