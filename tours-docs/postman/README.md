# Egypt Tours API - Postman Collections

Complete API contract for parallel frontend/backend development.

## Quick Start

1. **Import Master Environment**: `environments/Egypt_Tours_Master.postman_environment.json`
2. **Import Collections**:
   - `Egypt_Tours_User_API.postman_collection.json` - Frontend team
   - `Egypt_Tours_Admin_API.postman_collection.json` - Backoffice team
3. **Change `baseUrl`** based on target environment:
   - Dev: `http://localhost:8080/api/v1`
   - Staging: `https://api-staging.egypttours.com/api/v1`
   - Production: `https://api.egypttours.com/api/v1`
4. **Run Login** to auto-populate tokens

---

## Collections

### User API (Frontend)

| Folder | Endpoints | Description |
|--------|-----------|-------------|
| Auth | 9 | Register, login, OAuth, password reset |
| Trips | 4 | Browse, details, reviews, availability |
| Bookings | 5 | Create, view, cancel reservations |
| Account | 3 | Profile management |
| Wishlist | 3 | Saved trips |
| Destinations | 2 | Destination pages |
| Blog | 2 | Travel articles |
| Vlogs | 2 | Travel videos |
| Search | 1 | Global search |
| Contact | 1 | Inquiry form |

**Total: 34 endpoints**

### Admin API (Backoffice)

| Folder | Endpoints | Description |
|--------|-----------|-------------|
| Auth (Admin) | 1 | Admin login |
| Trips Management | 4 | CRUD trips |
| Bookings Management | 4 | View, update, refund |
| Users Management | 4 | View, roles, disable |
| Destinations Management | 2 | CRUD destinations |
| Content Management | 4 | Blog, vlogs |
| Reviews Management | 4 | Moderation |
| Analytics | 3 | Dashboard, reports |

**Total: 27 endpoints (61 total)**

---

## Features

- ✅ **Auto-token management** - Login saves tokens, requests auto-refresh
- ✅ **Test scripts** - Response validation
- ✅ **Example responses** - Success & error cases
- ✅ **Source traceability** - Links to MkDocs API docs

---

## Environment Variables

| Variable | Description |
|----------|-------------|
| `baseUrl` | API base URL (change per env) |
| `environment` | dev, staging, prod, mock |
| `email` / `password` | Test user credentials |
| `adminEmail` / `adminPassword` | Admin credentials |
| `accessToken` / `refreshToken` | JWT tokens (auto) |
| `tripSlug` / `tripId` | Trip identifiers |
| `bookingNumber` / `bookingId` | Booking identifiers |

---

## Documentation Source

All endpoints are generated from:

- `docs/api/auth.md`
- `docs/api/trips.md`
- `docs/api/bookings.md`
- `docs/api/users.md`
- `docs/api/content.md`
- `docs/api/errors.md`
