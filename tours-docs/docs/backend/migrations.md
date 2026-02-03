# Database Migrations

## Overview

Database schema migrations using Flyway with version control.

---

## Migration Configuration

```yaml
# application.yml
spring:
  flyway:
    enabled: true
    locations: classpath:db/migration
    baseline-on-migrate: true
    validate-on-migrate: true
```

---

## Migration Naming

```
V{version}__{description}.sql

Examples:
V1__create_users_table.sql
V2__create_trips_table.sql
V3__create_bookings_table.sql
V4__add_trip_translations.sql
```

---

## Initial Migrations

### V1__create_users_table.sql

```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    role VARCHAR(20) NOT NULL DEFAULT 'CUSTOMER',
    email_verified BOOLEAN NOT NULL DEFAULT FALSE,
    auth_provider VARCHAR(20) NOT NULL DEFAULT 'LOCAL',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_role ON users(role);
```

### V2__create_destinations_table.sql

```sql
CREATE TABLE destinations (
    id BIGSERIAL PRIMARY KEY,
    slug VARCHAR(255) NOT NULL UNIQUE,
    country VARCHAR(100) NOT NULL DEFAULT 'Egypt',
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE destination_translations (
    id BIGSERIAL PRIMARY KEY,
    destination_id BIGINT NOT NULL REFERENCES destinations(id) ON DELETE CASCADE,
    locale VARCHAR(5) NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    UNIQUE(destination_id, locale)
);
```

### V3__create_trips_table.sql

```sql
CREATE TABLE trips (
    id BIGSERIAL PRIMARY KEY,
    slug VARCHAR(255) NOT NULL UNIQUE,
    destination_id BIGINT REFERENCES destinations(id),
    duration_days INTEGER NOT NULL,
    trip_type VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
    base_price DECIMAL(10, 2) NOT NULL,
    max_capacity INTEGER,
    booking_mode VARCHAR(20) NOT NULL DEFAULT 'INSTANT',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE trip_translations (
    id BIGSERIAL PRIMARY KEY,
    trip_id BIGINT NOT NULL REFERENCES trips(id) ON DELETE CASCADE,
    locale VARCHAR(5) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    short_description VARCHAR(500),
    included TEXT,
    excluded TEXT,
    UNIQUE(trip_id, locale)
);

CREATE TABLE itinerary_days (
    id BIGSERIAL PRIMARY KEY,
    trip_id BIGINT NOT NULL REFERENCES trips(id) ON DELETE CASCADE,
    day_number INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    locations VARCHAR(500),
    UNIQUE(trip_id, day_number)
);

CREATE TABLE price_rules (
    id BIGSERIAL PRIMARY KEY,
    trip_id BIGINT NOT NULL REFERENCES trips(id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    valid_from DATE,
    valid_to DATE,
    min_travelers INTEGER DEFAULT 1,
    is_default BOOLEAN DEFAULT FALSE
);

CREATE TABLE trip_images (
    id BIGSERIAL PRIMARY KEY,
    trip_id BIGINT NOT NULL REFERENCES trips(id) ON DELETE CASCADE,
    url VARCHAR(500) NOT NULL,
    alt_text VARCHAR(255),
    sort_order INTEGER DEFAULT 0,
    is_thumbnail BOOLEAN DEFAULT FALSE
);

CREATE INDEX idx_trips_destination ON trips(destination_id);
CREATE INDEX idx_trips_status ON trips(status);
```

### V4__create_bookings_table.sql

```sql
CREATE TABLE bookings (
    id BIGSERIAL PRIMARY KEY,
    booking_number VARCHAR(20) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL REFERENCES users(id),
    trip_id BIGINT NOT NULL REFERENCES trips(id),
    travel_date DATE NOT NULL,
    adults INTEGER NOT NULL DEFAULT 1,
    children INTEGER NOT NULL DEFAULT 0,
    infants INTEGER NOT NULL DEFAULT 0,
    total_price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    payment_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    special_requests TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE travelers (
    id BIGSERIAL PRIMARY KEY,
    booking_id BIGINT NOT NULL REFERENCES bookings(id) ON DELETE CASCADE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    nationality VARCHAR(100),
    passport_number VARCHAR(50),
    date_of_birth DATE,
    traveler_type VARCHAR(20) NOT NULL DEFAULT 'ADULT'
);

CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    booking_id BIGINT NOT NULL REFERENCES bookings(id),
    transaction_id VARCHAR(255) UNIQUE,
    amount DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL DEFAULT 'USD',
    status VARCHAR(20) NOT NULL,
    payment_method VARCHAR(20),
    provider_response TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_bookings_user ON bookings(user_id);
CREATE INDEX idx_bookings_status ON bookings(status);
CREATE INDEX idx_bookings_date ON bookings(travel_date);
```

---

## Migration Best Practices

| Practice | Description |
|----------|-------------|
| Never modify | Once deployed, never modify a migration |
| Backwards compatible | Ensure migrations don't break running app |
| Test rollback | Have rollback scripts ready |
| Small changes | One logical change per migration |
| Descriptive names | Clear description in filename |

---

## Rollback Strategy

```sql
-- V5__add_phone_verified.sql
ALTER TABLE users ADD COLUMN phone_verified BOOLEAN DEFAULT FALSE;

-- V5_1__rollback_add_phone_verified.sql (manual rollback)
ALTER TABLE users DROP COLUMN phone_verified;
```
