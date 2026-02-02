# Postman Documentation

## Overview

Complete API contract for the Egypt Tours Booking Platform. Use these collections for parallel frontend/backend development.

---

## Files

### Collections (in `/postman/collections/`)

| File | Target Team | Endpoints |
|------|-------------|-----------|
| `Egypt_Tours_User_API.postman_collection.json` | Frontend | 34 |
| `Egypt_Tours_Admin_API.postman_collection.json` | Backoffice | 27 |

### Environment (in `/postman/environments/`)

| File | Description |
|------|-------------|
| `Egypt_Tours_Master.postman_environment.json` | Single env, change `baseUrl` per target |

---

## Quick Start

1. Import `Egypt_Tours_Master.postman_environment.json`
2. Import the collection you need (User or Admin)
3. Set `baseUrl` in environment:
   - Dev: `http://localhost:8080/api/v1`
   - Staging: `https://api-staging.egypttours.com/api/v1`
   - Prod: `https://api.egypttours.com/api/v1`
4. Run **Login** → tokens auto-populate
5. Start testing endpoints

---

## Collection Structure

### User API

```
Auth/          (9) → Login, Register, OAuth, Password
Trips/         (4) → Browse trips
Bookings/      (5) → Reservations
Account/       (3) → Profile
Wishlist/      (3) → Saved trips
Destinations/  (2) → Locations
Blog/          (2) → Articles
Vlogs/         (2) → Videos
Search/        (1) → Global search
Contact/       (1) → Inquiries
```

### Admin API

```
Auth (Admin)/           (1) → Admin login
Trips Management/       (4) → CRUD trips
Bookings Management/    (4) → View, status, refund
Users Management/       (4) → Roles, disable
Destinations Management/(2) → CRUD
Content Management/     (4) → Blog, vlogs
Reviews Management/     (4) → Moderation
Analytics/              (3) → Reports
```

---

## Documentation

| Document | Description |
|----------|-------------|
| [Collections](collections.md) | Collection structure details |
| [Environments](environments.md) | Environment variables |
| [Auth Flows](auth-flows.md) | Token management scripts |
| [Examples](examples.md) | Request/response examples |
