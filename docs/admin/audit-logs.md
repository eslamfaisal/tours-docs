# Audit Logs

## Tracked Actions

| Category | Actions |
|----------|---------|
| Auth | Login, logout, password change |
| Trips | Create, update, delete, publish |
| Bookings | Create, confirm, cancel, refund |
| Users | Create, update role, block/unblock |
| Content | Create, update, delete, publish |

## Log Entry Fields

- Timestamp
- User (who performed action)
- Action type
- Resource type and ID
- Previous/new values (for updates)
- IP address

## Retention

Logs retained for 90 days (configurable).
