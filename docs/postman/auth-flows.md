# Postman Auth Flows

## Auto-Token Management

Login request automatically saves tokens to environment variables.

### Login Pre-request Script

```javascript
// Clear existing tokens before login
pm.environment.unset("accessToken");
pm.environment.unset("refreshToken");
```

### Login Test Script

```javascript
if (pm.response.code === 200) {
    var jsonData = pm.response.json();
    pm.environment.set('accessToken', jsonData.data.accessToken);
    pm.environment.set('refreshToken', jsonData.data.refreshToken);
    console.log('Tokens saved to environment');
}
```

---

## Authorization Header

In Collection Settings > Authorization:

- Type: Bearer Token
- Token: `{{accessToken}}`

All requests inherit this authorization.

---

## Token Refresh

### Auto-Refresh Pre-request Script

```javascript
// Add to collection pre-request script
const tokenExpiry = pm.environment.get("tokenExpiry");
const now = Date.now();

if (tokenExpiry && now > tokenExpiry - 60000) {
    // Token expires in less than 1 minute, refresh
    pm.sendRequest({
        url: pm.environment.get("baseUrl") + "/auth/refresh",
        method: 'POST',
        header: { 'Content-Type': 'application/json' },
        body: {
            mode: 'raw',
            raw: JSON.stringify({ 
                refreshToken: pm.environment.get("refreshToken") 
            })
        }
    }, function (err, res) {
        if (res.code === 200) {
            var data = res.json();
            pm.environment.set("accessToken", data.data.accessToken);
            pm.environment.set("tokenExpiry", Date.now() + 3600000);
        }
    });
}
```
