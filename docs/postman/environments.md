# Postman Environments

## Environment Files

| Environment | File |
|-------------|------|
| Development | `Egypt_Tours_Dev.postman_environment.json` |
| Staging | `Egypt_Tours_Staging.postman_environment.json` |
| Production | `Egypt_Tours_Prod.postman_environment.json` |

---

## Variables

| Variable | Description | Example |
|----------|-------------|---------|
| `baseUrl` | API base URL | `http://localhost:8080/api/v1` |
| `email` | Test user email | `test@example.com` |
| `password` | Test user password | `TestP@ss123` |
| `accessToken` | JWT access token | (auto-populated) |
| `refreshToken` | JWT refresh token | (auto-populated) |
| `adminEmail` | Admin user email | `admin@egypttours.com` |
| `adminPassword` | Admin password | - |

---

## Environment JSON

```json
{
  "id": "dev-env",
  "name": "Egypt Tours Dev",
  "values": [
    {
      "key": "baseUrl",
      "value": "http://localhost:8080/api/v1",
      "enabled": true
    },
    {
      "key": "email",
      "value": "test@example.com",
      "enabled": true
    },
    {
      "key": "password",
      "value": "TestP@ss123",
      "enabled": true
    },
    {
      "key": "accessToken",
      "value": "",
      "enabled": true
    },
    {
      "key": "refreshToken",
      "value": "",
      "enabled": true
    }
  ]
}
```

---

## Environment URLs

| Environment | Base URL |
|-------------|----------|
| Development | `http://localhost:8080/api/v1` |
| Staging | `https://api-staging.egypttours.com/api/v1` |
| Production | `https://api.egypttours.com/api/v1` |
