# Postman Environments

## Master Environment File

Use the **single master environment** and change `baseUrl` per target:

```
postman/environments/Egypt_Tours_Master.postman_environment.json
```

---

## Environment URLs

| Environment | baseUrl Value |
|-------------|---------------|
| **Development** | `http://localhost:8080/api/v1` |
| **Staging** | `https://api-staging.egypttours.com/api/v1` |
| **Production** | `https://api.egypttours.com/api/v1` |
| **Mock** | `https://mock.egypttours.com/api/v1` |

---

## Variables

| Variable | Description | Type |
|----------|-------------|------|
| `baseUrl` | API base URL | Default |
| `environment` | Current env (dev/staging/prod/mock) | Default |
| `version` | API version (v1) | Default |
| `language` | Content locale (en/es/ar) | Default |
| `email` | Test user email | Default |
| `password` | Test user password | Secret |
| `accessToken` | JWT access token | Auto |
| `refreshToken` | Refresh token | Auto |
| `tokenExpiry` | Token expiry timestamp | Auto |
| `adminEmail` | Admin user email | Default |
| `adminPassword` | Admin password | Secret |
| `tripSlug` | Sample trip slug | Default |
| `tripId` | Trip ID | Auto |
| `bookingNumber` | Booking number | Auto |
| `bookingId` | Booking ID (admin) | Auto |
| `userId` | User ID (admin) | Auto |
| `reviewId` | Review ID (admin) | Auto |

---

## Quick Start

1. Import `Egypt_Tours_Master.postman_environment.json`
2. Set `baseUrl` to your target environment
3. Set `email`/`password` for test user
4. Set `adminEmail`/`adminPassword` for admin testing
5. Run Login → tokens auto-populate

---

## JSON Structure

```json
{
  "name": "Egypt Tours - All Environments",
  "values": [
    { "key": "baseUrl", "value": "http://localhost:8080/api/v1", "enabled": true },
    { "key": "email", "value": "test@example.com", "enabled": true },
    { "key": "accessToken", "value": "", "enabled": true },
    { "key": "adminEmail", "value": "admin@egypttours.com", "enabled": true }
  ]
}
```
