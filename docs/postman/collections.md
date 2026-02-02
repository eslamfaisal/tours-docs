# Postman Collections

## Collection Files

| File | Description | Endpoints |
|------|-------------|-----------|
| `Egypt_Tours_User_API.postman_collection.json` | Frontend user-facing API | 34 |
| `Egypt_Tours_Admin_API.postman_collection.json` | Admin backoffice API | 27 |

**Total: 61 endpoints**

---

## User API Collection

Frontend-facing endpoints organized by feature folders.

### Folder Structure

| Folder | Endpoints |
|--------|-----------|
| Auth | Register, Login, Refresh Token, Forgot/Reset Password, Google/Facebook OAuth, Verify Email, Logout |
| Trips | List Trips, Get Details, Get Reviews, Check Availability |
| Bookings | Create, Get My Bookings, Get Details, Cancel, Download Invoice |
| Account | Get Profile, Update Profile, Change Password |
| Wishlist | Get Wishlist, Add, Remove |
| Destinations | List, Get Details |
| Blog | List Posts, Get Post |
| Vlogs | List Vlogs, Get Vlog |
| Search | Global Search |
| Contact | Submit Inquiry |

---

## Admin API Collection

Backoffice management endpoints requiring admin role.

### Folder Structure

| Folder | Endpoints |
|--------|-----------|
| Auth (Admin) | Admin Login |
| Trips Management | Create, Update, Delete, List All |
| Bookings Management | List All, Get Details, Update Status, Process Refund |
| Users Management | List, Get Details, Update Role, Disable |
| Destinations Management | Create, Update |
| Content Management | Create/Update/Delete Blog, Create Vlog |
| Reviews Management | List Pending, Approve, Reject, Reply |
| Analytics | Dashboard Stats, Revenue Report, Popular Trips |

---

## Features

- **Auto-token management** - Login saves tokens, requests auto-refresh when expired
- **Test scripts** - Response validation for status codes and schemas
- **Example responses** - Success and error cases for each endpoint
- **Source traceability** - Each endpoint references its MkDocs source file

---

## Quick Start

```bash
# 1. Import environment
postman/environments/Egypt_Tours_Master.postman_environment.json

# 2. Import collections
postman/collections/Egypt_Tours_User_API.postman_collection.json
postman/collections/Egypt_Tours_Admin_API.postman_collection.json

# 3. Select environment, run Login
```

---

## Collection JSON Structure

```json
{
  "info": {
    "name": "Egypt Tours - User API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "auth": {
    "type": "bearer",
    "bearer": [{ "key": "token", "value": "{{accessToken}}" }]
  },
  "item": [
    {
      "name": "Auth",
      "item": [
        {
          "name": "Login",
          "request": {
            "method": "POST",
            "url": "{{baseUrl}}/auth/login",
            "body": { "mode": "raw", "raw": "{...}" }
          },
          "event": [
            {
              "listen": "test",
              "script": {
                "exec": [
                  "if (pm.response.code === 200) {",
                  "    pm.environment.set('accessToken', pm.response.json().data.accessToken);",
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
