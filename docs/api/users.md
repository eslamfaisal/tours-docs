# Users API

## Profile Endpoints

### Get Profile

```http
GET /api/v1/account/profile
Authorization: Bearer {token}
```

**Response:**

```json
{
  "success": true,
  "data": {
    "id": 12345,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phone": "+1234567890",
    "nationality": "American",
    "role": "CUSTOMER",
    "emailVerified": true,
    "createdAt": "2026-01-01T00:00:00Z"
  }
}
```

### Update Profile

```http
PUT /api/v1/account/profile
Authorization: Bearer {token}
```

**Request:**

```json
{
  "firstName": "John",
  "lastName": "Smith",
  "phone": "+1987654321",
  "nationality": "British"
}
```

### Change Password

```http
POST /api/v1/account/change-password
Authorization: Bearer {token}
```

**Request:**

```json
{
  "currentPassword": "OldP@ssword123",
  "newPassword": "NewP@ssword456"
}
```

---

## Wishlist Endpoints

### Get Wishlist

```http
GET /api/v1/account/wishlist
Authorization: Bearer {token}
```

### Add to Wishlist

```http
POST /api/v1/account/wishlist
```

**Request:**

```json
{
  "tripId": 1
}
```

### Remove from Wishlist

```http
DELETE /api/v1/account/wishlist/{tripId}
```

---

## Admin User Management

### List Users

```http
GET /api/v1/admin/users
Authorization: Bearer {admin_token}
```

### Get User Details

```http
GET /api/v1/admin/users/{id}
```

### Update User Role

```http
PATCH /api/v1/admin/users/{id}/role
```

**Request:**

```json
{
  "role": "EDITOR"
}
```
