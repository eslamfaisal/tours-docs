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

Base URL: `/api/v1/admin/users`

### 1. Get All Users

Retrieve a list of all registered users.

- **URL**: `/`
- **Method**: `GET`
- **Response**: `200 OK`

### 2. Create User

Create a new user with a specific role.

- **URL**: `/`
- **Method**: `POST`
- **Headers**: `Content-Type: application/json`

**Request Body**

```json
{
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane.smith@example.com",
    "password": "SecurePass456!",
    "role": "ADMIN"
}
```

**Note**:
- `SUPER` admins can create any role.
- `ADMIN` can create `CUSTOMER`, `GUIDE`, `SUPPORT`, but NOT `ADMIN` or `SUPER`.

### 3. Update User

Update details of an existing user.

- **URL**: `/{id}`
- **Method**: `PUT`
- **Headers**: `Content-Type: application/json`

**Request Body** (All fields optional)

```json
{
    "firstName": "Jane Updated",
    "role": "CUSTOMER"
}
```

### 4. Delete User

Permanently delete a user.

- **URL**: `/{id}`
- **Method**: `DELETE`
- **Response**: `200 OK`

