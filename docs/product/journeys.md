# Customer Journeys

## Overview

This document maps end-to-end customer journeys through the Egypt Tours Platform.

---

## Journey 1: Discovery to Booking (New Customer)

```mermaid
journey
    title New Customer Booking Journey
    section Discovery
      Search "Egypt tours": 5: Customer
      Land on website: 5: Customer
      Browse trip listings: 4: Customer
    section Exploration
      View trip details: 5: Customer
      Check itinerary: 4: Customer
      Read reviews: 4: Customer
    section Booking
      Select date & options: 4: Customer
      Create account: 3: Customer
      Enter details: 3: Customer
      Make payment: 4: Customer
    section Confirmation
      Receive confirmation: 5: Customer
      Get invoice: 5: Customer
      Access dashboard: 4: Customer
```

### Touchpoints

| Stage | Action | System Response |
|-------|--------|-----------------|
| Discovery | Search engine query | SEO-optimized landing |
| Browse | View listings | Fast, filtered results |
| Details | Click trip | Rich trip page |
| Book | Select options | Real-time pricing |
| Pay | Complete payment | Secure gateway |
| Confirm | Booking complete | Email + dashboard update |

---

## Journey 2: AI-Assisted Booking

```mermaid
journey
    title AI-Assisted Booking Journey
    section Initiation
      Open chat widget: 5: Customer
      Ask "best Luxor tour": 4: Customer
    section Recommendation
      AI suggests options: 5: AI
      Customer asks follow-up: 4: Customer
      AI provides details: 5: AI
    section Conversion
      Customer requests booking: 4: Customer
      AI guides to trip page: 5: AI
      Complete booking: 4: Customer
```

### Chat Flow Example

```
Customer: "I want a 3-day tour to Luxor and Aswan"
AI: "I found 5 tours matching your criteria. Here are the top picks:
     1. Classic Luxor & Aswan (★4.8) - $450
     2. Nile Cruise Adventure (★4.9) - $680
     Would you like details on any of these?"
Customer: "Tell me about the Nile Cruise"
AI: "The Nile Cruise Adventure includes:
     • 3 nights on a 5-star cruise
     • Valley of the Kings, Karnak Temple
     • All meals included
     [View Full Details →]"
```

---

## Journey 3: Returning Customer

```mermaid
journey
    title Returning Customer Journey
    section Login
      Visit website: 5: Customer
      Login to account: 5: Customer
    section Dashboard
      View past bookings: 5: Customer
      Check wishlist: 4: Customer
    section New Booking
      Book new trip: 4: Customer
      Apply loyalty discount: 5: Customer
    section Support
      Create support ticket: 4: Customer
      Receive resolution: 5: Customer
```

---

## Journey 4: Content Engagement

```mermaid
journey
    title Content to Booking Journey
    section Content
      Read blog article: 5: Customer
      Watch vlog video: 5: Customer
    section Interest
      Click related tour CTA: 4: Customer
      Explore trip page: 4: Customer
    section Conversion
      Add to wishlist: 4: Customer
      Book later: 4: Customer
```

---

## Journey 5: Request-to-Book Flow

```mermaid
sequenceDiagram
    participant C as Customer
    participant W as Website
    participant A as Admin
    participant E as Email

    C->>W: Submit booking request
    W->>A: Notify new request
    A->>A: Check availability
    alt Available
        A->>W: Confirm booking
        W->>E: Send confirmation
        E->>C: Booking confirmed
    else Not Available
        A->>W: Suggest alternatives
        W->>E: Send alternatives
        E->>C: Alternative options
    end
```

---

## Key Metrics by Journey

| Journey | Primary KPI | Target |
|---------|-------------|--------|
| Discovery to Booking | Conversion Rate | > 3% |
| AI-Assisted | Chat Engagement | > 40% |
| Returning Customer | Repeat Booking | > 25% |
| Content Engagement | Content-to-Book | > 5% |
| Request-to-Book | Response Time | < 2 hrs |
