# Booking Operations

## Booking Queue

### Status Filters

- All Bookings
- Pending Confirmation (Request-to-Book)
- Confirmed
- Pending Payment
- Cancelled
- Completed

### Bulk Actions

- Export to CSV
- Send reminder emails
- Update status (batch)

---

## Booking Actions

| Action | Description |
|--------|-------------|
| Confirm | Approve request-to-book |
| Decline | Reject with reason |
| Cancel | Cancel with refund options |
| Modify | Change date/travelers |
| Refund | Process partial/full refund |
| Resend Confirmation | Email to customer |

---

## Request-to-Book Flow

1. Customer submits booking request
2. Admin receives notification
3. Check availability
4. Confirm or decline
5. Customer receives payment link (if confirmed)
6. Customer completes payment

---

## Refund Processing

| Criteria | Refund % |
|----------|----------|
| 30+ days before | 100% |
| 15-29 days | 75% |
| 7-14 days | 50% |
| < 7 days | 0% |

Admin can override policy for special cases.
