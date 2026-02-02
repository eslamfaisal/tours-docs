# Database Design

## Overview

PostgreSQL database with JPA/Hibernate ORM.

---

## Entity Relationship Diagram

```mermaid
erDiagram
    USER ||--o{ BOOKING : makes
    USER ||--o{ REVIEW : writes
    USER ||--o{ TICKET : creates
    USER ||--o{ WISHLIST_ITEM : saves
    
    TRIP ||--o{ BOOKING : "booked as"
    TRIP ||--o{ REVIEW : receives
    TRIP ||--o{ ITINERARY_DAY : contains
    TRIP ||--o{ PRICE_RULE : has
    TRIP ||--o{ TRIP_IMAGE : has
    TRIP ||--o{ TRIP_TRANSLATION : "translated to"
    TRIP }o--|| DESTINATION : "located in"
    
    BOOKING ||--o{ TRAVELER : includes
    BOOKING ||--o{ BOOKING_ADDON : has
    BOOKING ||--|{ PAYMENT : "paid by"
    BOOKING ||--o| INVOICE : generates
    
    DESTINATION ||--o{ DESTINATION_TRANSLATION : "translated to"
    
    TICKET ||--o{ TICKET_MESSAGE : contains
    
    BLOG_POST ||--o{ BLOG_TRANSLATION : "translated to"
    
    USER {
        bigint id PK
        string email UK
        string password
        string first_name
        string last_name
        string phone
        enum role
        boolean email_verified
        enum auth_provider
        timestamp created_at
    }
    
    TRIP {
        bigint id PK
        string slug UK
        bigint destination_id FK
        integer duration_days
        enum trip_type
        enum status
        decimal base_price
        integer max_capacity
        timestamp created_at
    }
    
    TRIP_TRANSLATION {
        bigint id PK
        bigint trip_id FK
        string locale
        string title
        text description
        text short_description
    }
    
    ITINERARY_DAY {
        bigint id PK
        bigint trip_id FK
        integer day_number
        string title
        text description
        string locations
    }
    
    PRICE_RULE {
        bigint id PK
        bigint trip_id FK
        string name
        decimal price
        date valid_from
        date valid_to
        integer min_travelers
    }
    
    DESTINATION {
        bigint id PK
        string slug UK
        string country
        decimal latitude
        decimal longitude
    }
    
    BOOKING {
        bigint id PK
        string booking_number UK
        bigint user_id FK
        bigint trip_id FK
        date travel_date
        integer adults
        integer children
        decimal total_price
        enum status
        enum payment_status
        timestamp created_at
    }
    
    TRAVELER {
        bigint id PK
        bigint booking_id FK
        string first_name
        string last_name
        string nationality
        string passport_number
        date date_of_birth
        enum traveler_type
    }
    
    PAYMENT {
        bigint id PK
        bigint booking_id FK
        string transaction_id UK
        decimal amount
        string currency
        enum status
        enum payment_method
        timestamp created_at
    }
    
    REVIEW {
        bigint id PK
        bigint user_id FK
        bigint trip_id FK
        integer rating
        text comment
        boolean verified
        timestamp created_at
    }
    
    TICKET {
        bigint id PK
        bigint user_id FK
        bigint booking_id FK
        string subject
        enum status
        enum priority
        timestamp created_at
    }
    
    BLOG_POST {
        bigint id PK
        string slug UK
        bigint author_id FK
        enum status
        string featured_image
        timestamp published_at
    }
```

---

## Entity Definitions

### User Entity

```java
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    private String phone;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.CUSTOMER;
    
    @Column(name = "email_verified")
    private boolean emailVerified = false;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider")
    private AuthProvider authProvider = AuthProvider.LOCAL;
    
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();
}
```

### Trip Entity

```java
@Entity
@Table(name = "trips")
public class Trip extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String slug;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private Destination destination;
    
    @Column(name = "duration_days")
    private Integer durationDays;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trip_type")
    private TripType tripType;
    
    @Enumerated(EnumType.STRING)
    private TripStatus status = TripStatus.DRAFT;
    
    @Column(name = "base_price", precision = 10, scale = 2)
    private BigDecimal basePrice;
    
    @Column(name = "max_capacity")
    private Integer maxCapacity;
    
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripTranslation> translations = new ArrayList<>();
    
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dayNumber ASC")
    private List<ItineraryDay> itinerary = new ArrayList<>();
    
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<PriceRule> priceRules = new ArrayList<>();
    
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    @OrderBy("sortOrder ASC")
    private List<TripImage> images = new ArrayList<>();
}
```

### Booking Entity

```java
@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "booking_number", unique = true, nullable = false)
    private String bookingNumber;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    
    @Column(name = "travel_date", nullable = false)
    private LocalDate travelDate;
    
    private Integer adults = 1;
    private Integer children = 0;
    
    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Traveler> travelers = new ArrayList<>();
    
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();
    
    @PrePersist
    public void generateBookingNumber() {
        this.bookingNumber = "ET-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
```

---

## Indexes

```sql
-- Performance indexes
CREATE INDEX idx_trips_destination ON trips(destination_id);
CREATE INDEX idx_trips_status ON trips(status);
CREATE INDEX idx_trips_slug ON trips(slug);

CREATE INDEX idx_bookings_user ON bookings(user_id);
CREATE INDEX idx_bookings_status ON bookings(status);
CREATE INDEX idx_bookings_date ON bookings(travel_date);

CREATE INDEX idx_reviews_trip ON reviews(trip_id);
CREATE INDEX idx_reviews_user ON reviews(user_id);

-- Composite indexes
CREATE INDEX idx_trips_dest_status ON trips(destination_id, status);
CREATE INDEX idx_bookings_user_status ON bookings(user_id, status);
```

---

## Enums

```java
public enum Role {
    GUEST, CUSTOMER, EDITOR, BOOKING_AGENT, SUPPORT_AGENT, ADMIN
}

public enum TripStatus {
    DRAFT, PUBLISHED, ARCHIVED
}

public enum TripType {
    PACKAGE, DAY_TOUR, CRUISE, PRIVATE
}

public enum BookingStatus {
    PENDING, CONFIRMED, CANCELLED, COMPLETED, REFUNDED
}

public enum PaymentStatus {
    PENDING, PARTIAL, PAID, REFUNDED
}
```
