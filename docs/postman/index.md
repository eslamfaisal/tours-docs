# Postman Documentation

## Contents

| Document | Description |
|----------|-------------|
| [Collections](collections.md) | Collection structure |
| [Environments](environments.md) | Environment variables |
| [Auth Flows](auth-flows.md) | Authentication setup |
| [Examples](examples.md) | Request/response examples |

---

## Overview

Postman collections are provided to facilitate API testing and integration.

### Collection Structure

```
Egypt Tours API/
├── Auth/
│   ├── Register
│   ├── Login
│   ├── Refresh Token
│   └── Password Reset
├── Trips/
│   ├── List Trips
│   ├── Get Trip Details
│   └── Check Availability
├── Bookings/
│   ├── Create Booking
│   ├── Get My Bookings
│   └── Cancel Booking
├── Account/
│   ├── Get Profile
│   ├── Update Profile
│   └── Wishlist
├── Content/
│   ├── Blog
│   ├── Vlogs
│   └── Destinations
└── Admin/
    ├── Trips Management
    ├── Bookings Management
    └── Users Management
```

---

## Quick Start

1. Import the collection: `Egypt_Tours_API.postman_collection.json`
2. Import the environment: `Egypt_Tours_[Environment].postman_environment.json`
3. Set the active environment
4. Run "Login" to get tokens (auto-saved to environment)
5. Start testing endpoints
