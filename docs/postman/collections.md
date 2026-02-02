# Postman Collections

## Collection Files

| File | Description |
|------|-------------|
| `Egypt_Tours_API.postman_collection.json` | Main API collection |
| `Egypt_Tours_Admin.postman_collection.json` | Admin-only endpoints |

---

## Collection JSON Structure

```json
{
  "info": {
    "name": "Egypt Tours API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "variable": [
    { "key": "baseUrl", "value": "{{baseUrl}}" }
  ],
  "item": [
    {
      "name": "Auth",
      "item": [
        {
          "name": "Login",
          "request": {
            "method": "POST",
            "header": [
              { "key": "Content-Type", "value": "application/json" }
            ],
            "body": {
              "mode": "raw",
              "raw": "{ \"email\": \"{{email}}\", \"password\": \"{{password}}\" }"
            },
            "url": "{{baseUrl}}/auth/login"
          },
          "event": [
            {
              "listen": "test",
              "script": {
                "exec": [
                  "if (pm.response.code === 200) {",
                  "    var jsonData = pm.response.json();",
                  "    pm.environment.set('accessToken', jsonData.data.accessToken);",
                  "    pm.environment.set('refreshToken', jsonData.data.refreshToken);",
                  "}"
                ]
              }
            }
          ]
        }
      ]
    }
  ]
}
```

---

## Folder Structure

### Auth Folder

- Register
- Login
- Refresh Token
- Forgot Password
- Reset Password
- Google OAuth
- Facebook OAuth
- Logout

### Trips Folder

- List Trips (with query params)
- Get Trip Details
- Get Trip Reviews
- Check Availability

### Bookings Folder

- Create Booking
- Get My Bookings
- Get Booking Details
- Cancel Booking
- Download Invoice

### Account Folder

- Get Profile
- Update Profile
- Change Password
- Get Wishlist
- Add to Wishlist
- Remove from Wishlist

### Support Folder

- Create Ticket
- Get My Tickets
- Get Ticket Details
- Reply to Ticket
