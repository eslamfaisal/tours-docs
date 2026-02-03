# API Documentation

This section covers the REST API specifications and standards.

---

## Contents

| Document | Description |
|----------|-------------|
| [Overview](overview.md) | API design principles |
| [Authentication](auth.md) | Auth endpoints |
| [Trips](trips.md) | Trip API endpoints |
| [Bookings](bookings.md) | Booking API endpoints |
| [Users](users.md) | User/profile endpoints |
| [Content](content.md) | Blog/vlog endpoints |
| [Error Handling](errors.md) | Error response format |
| [Pagination](pagination.md) | Pagination patterns |

---

## API Base URL

| Environment | Base URL |
|-------------|----------|
| Production | `https://api.egypttours.com/api/v1` |
| Staging | `https://api-staging.egypttours.com/api/v1` |
| Development | `http://localhost:8080/api/v1` |

---

## Quick Reference

| Domain | Endpoints |
|--------|-----------|
| Auth | `/auth/login`, `/auth/register`, `/auth/refresh` |
| Trips | `/trips`, `/trips/{slug}`, `/trips/{slug}/reviews` |
| Destinations | `/destinations`, `/destinations/{slug}` |
| Bookings | `/bookings`, `/bookings/{id}` |
| Account | `/account/profile`, `/account/bookings` |
| Content | `/blog`, `/vlogs` |
| Admin | `/admin/trips`, `/admin/bookings`, `/admin/users` |
