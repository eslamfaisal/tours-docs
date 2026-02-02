# Authentication API

## Endpoints

### Register

```http
POST /api/v1/auth/register
```

**Request:**

```json
{
  "email": "user@example.com",
  "password": "SecureP@ss123",
  "firstName": "John",
  "lastName": "Doe",
  "phone": "+1234567890"
}
```

**Response (201):**

```json
{
  "success": true,
  "message": "Registration successful. Please check your email for verification."
}
```

---

### Login

```http
POST /api/v1/auth/login
```

**Request:**

```json
{
  "email": "user@example.com",
  "password": "SecureP@ss123"
}
```

**Response (200):**

```json
{
  "success": true,
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiIs...",
    "refreshToken": "dGhpcyBpcyBhIHJlZnJlc2...",
    "expiresIn": 3600,
    "user": {
      "id": 12345,
      "email": "user@example.com",
      "firstName": "John",
      "lastName": "Doe",
      "role": "CUSTOMER"
    }
  }
}
```

---

### Refresh Token

```http
POST /api/v1/auth/refresh
```

**Request:**

```json
{
  "refreshToken": "dGhpcyBpcyBhIHJlZnJlc2..."
}
```

**Response (200):**

```json
{
  "success": true,
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiIs...",
    "expiresIn": 3600
  }
}
```

---

### Forgot Password

```http
POST /api/v1/auth/forgot-password
```

**Request:**

```json
{
  "email": "user@example.com"
}
```

**Response (200):**

```json
{
  "success": true,
  "message": "Password reset instructions sent to email."
}
```

---

### Reset Password

```http
POST /api/v1/auth/reset-password
```

**Request:**

```json
{
  "token": "reset_token_from_email",
  "password": "NewSecureP@ss123"
}
```

---

### Social Login - Google

```http
POST /api/v1/auth/google
```

**Request:**

```json
{
  "idToken": "google_id_token_from_client"
}
```

### Social Login - Facebook

```http
POST /api/v1/auth/facebook
```

**Request:**

```json
{
  "accessToken": "facebook_access_token"
}
```

---

### Verify Email

```http
GET /api/v1/auth/verify-email?token={verification_token}
```

---

### Logout

```http
POST /api/v1/auth/logout
Authorization: Bearer {accessToken}
```

---

## Error Codes

| Code | Description |
|------|-------------|
| `INVALID_CREDENTIALS` | Wrong email/password |
| `EMAIL_NOT_VERIFIED` | Email verification required |
| `USER_DISABLED` | Account disabled |
| `TOKEN_EXPIRED` | Access token expired |
| `INVALID_TOKEN` | Token is invalid |
| `EMAIL_ALREADY_EXISTS` | Email already registered |
