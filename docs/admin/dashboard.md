# Admin Dashboard

## Overview

The admin dashboard provides key metrics and quick actions.

---

## Dashboard Widgets

### Key Metrics

| Metric | Description |
|--------|-------------|
| Total Bookings (Today) | Bookings made today |
| Revenue (This Month) | Current month revenue |
| Pending Bookings | Awaiting confirmation |
| Open Tickets | Unresolved support tickets |

### Charts

- **Booking Trend** — Last 30 days bookings
- **Revenue** — Monthly revenue comparison
- **Popular Trips** — Top 10 booked trips
- **Traffic Sources** — Booking channels

### Quick Actions

- View pending bookings
- Respond to tickets
- View new customers
- Check low availability trips

---

## Dashboard Layout

```
┌─────────────────────────────────────────────────────────────┐
│ ADMIN DASHBOARD                                    [Profile] │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐       │
│  │ Bookings │ │ Revenue  │ │ Pending  │ │ Tickets  │       │
│  │   Today  │ │ $45,230  │ │    12    │ │    5     │       │
│  │    24    │ │ +15% ↑   │ │          │ │          │       │
│  └──────────┘ └──────────┘ └──────────┘ └──────────┘       │
│                                                              │
│  ┌─────────────────────────────────────────────────────┐    │
│  │            Booking Trend (Last 30 Days)              │    │
│  │  [Chart]                                            │    │
│  └─────────────────────────────────────────────────────┘    │
│                                                              │
│  ┌─────────────────────────┐ ┌─────────────────────────┐    │
│  │     Recent Bookings     │ │     Open Tickets        │    │
│  │  • ET-A1B2C3D4 - $599  │ │  • #1234 - Payment...   │    │
│  │  • ET-B2C3D4E5 - $450  │ │  • #1235 - Refund...    │    │
│  └─────────────────────────┘ └─────────────────────────┘    │
│                                                              │
└─────────────────────────────────────────────────────────────┘
```
