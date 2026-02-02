# Egypt Tours API - Postman Collections

Complete Postman-ready API contract for the Egypt Tours Booking Platform.

## Quick Start

1. **Import Environments**: Import one of the environment files based on your target:
   - `environments/Egypt_Tours_Dev.postman_environment.json` - Local development
   - `environments/Egypt_Tours_Staging.postman_environment.json` - Staging server
   - `environments/Egypt_Tours_Prod.postman_environment.json` - Production
   - `environments/Egypt_Tours_Mock.postman_environment.json` - Mock server (frontend dev)

2. **Import Collections**: Import all collection files from `collections/`

3. **Set Active Environment**: Select your imported environment in Postman

4. **Start with Login**: Run `Auth > Login` to auto-populate tokens

---

## Collections Overview

| Collection | Endpoints | Description |
|------------|-----------|-------------|
| **Auth** | 9 | Register, login, OAuth, password reset |
| **Trips** | 5 | Browse trips, details, reviews, availability |
| **Bookings** | 5 | Create, manage, cancel bookings |
| **Users** | 6 | Profile & wishlist management |
| **Content** | 9 | Blog, vlogs, destinations, search |
| **Admin** | 9 | Trip/booking/user management |

**Total: 43 endpoints**

---

## Token Management

All collections include automatic token management:

- **Login** auto-saves `accessToken` and `refreshToken` to environment
- **Protected requests** auto-refresh tokens when expired
- **Logout** clears tokens from environment

---

## Documentation Mapping

Every endpoint includes source traceability:

| Endpoint | Documentation Source |
|----------|---------------------|
| `POST /auth/register` | `docs/api/auth.md` |
| `POST /auth/login` | `docs/api/auth.md` |
| `GET /trips` | `docs/api/trips.md` |
| `GET /trips/{slug}` | `docs/api/trips.md` |
| `POST /bookings` | `docs/api/bookings.md` |
| `GET /account/profile` | `docs/api/users.md` |
| `GET /blog` | `docs/api/content.md` |
| `POST /admin/trips` | `docs/api/trips.md` |

---

## For Frontend Team

Use the **Mock Environment** for parallel development:

- Pre-filled tokens (no login required)
- All example responses included
- Can work before backend is ready

## For Backend Team

Implement endpoints to match:

- Request schemas
- Response formats
- Error codes from `docs/api/errors.md`

## For QA Team

Each request includes test scripts for:

- Status code validation
- Response schema validation
- Required field checks

---

## Environment Variables

| Variable | Description |
|----------|-------------|
| `baseUrl` | API base URL |
| `email` | Test user email |
| `password` | Test user password |
| `accessToken` | JWT access token (auto-populated) |
| `refreshToken` | Refresh token (auto-populated) |
| `tokenExpiry` | Token expiry timestamp |
| `adminEmail` | Admin user email |
| `adminPassword` | Admin password |
| `language` | Content locale (en, es, ar) |
| `bookingNumber` | Last created booking (auto-populated) |
