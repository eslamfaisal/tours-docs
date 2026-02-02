# Feature Checklist

Complete feature matrix with Frontend (FE) and Backend (BE) implementation tracking.

---

## Legend

| Symbol | Meaning |
|--------|---------|
| ✅ | Implemented |
| 🔄 | In Progress |
| ⏳ | Planned |
| ❌ | Not Planned |

---

## Public Website

| Feature | FE | BE | Priority | Notes |
|---------|----|----|----------|-------|
| Homepage with hero, featured trips | ⏳ | ⏳ | Must | SSR required |
| Trip listings with filters/sorting | ⏳ | ⏳ | Must | Pagination |
| Trip detail pages | ⏳ | ⏳ | Must | SEO critical |
| Destination pages | ⏳ | ⏳ | Must | |
| Blog listing and articles | ⏳ | ⏳ | Must | |
| Vlog/media gallery | ⏳ | ⏳ | Must | |
| Global search | ⏳ | ⏳ | Must | |
| Contact form | ⏳ | ⏳ | Must | |
| FAQ section | ⏳ | ⏳ | Must | |
| Reviews display | ⏳ | ⏳ | Must | |
| WhatsApp CTA | ⏳ | ❌ | Must | FE only |
| Language switcher | ⏳ | ⏳ | Must | EN/ES/AR |
| RTL Arabic layout | ⏳ | ❌ | Must | |

---

## Authentication

| Feature | FE | BE | Priority | Notes |
|---------|----|----|----------|-------|
| Email/password registration | ⏳ | ⏳ | Must | |
| Email verification | ⏳ | ⏳ | Must | |
| Login | ⏳ | ⏳ | Must | JWT |
| Password reset | ⏳ | ⏳ | Must | |
| Google OAuth | ⏳ | ⏳ | Should | |
| Facebook OAuth | ⏳ | ⏳ | Should | |
| Apple Sign-In | ⏳ | ⏳ | Could | |
| Token refresh | ⏳ | ⏳ | Must | |
| Logout | ⏳ | ⏳ | Must | |

---

## Booking System

| Feature | FE | BE | Priority | Notes |
|---------|----|----|----------|-------|
| Date/traveler selection | ⏳ | ⏳ | Must | |
| Booking form | ⏳ | ⏳ | Must | |
| Instant booking flow | ⏳ | ⏳ | Must | |
| Request-to-book flow | ⏳ | ⏳ | Must | |
| Payment integration | ⏳ | ⏳ | Must | Stripe |
| Deposit payment option | ⏳ | ⏳ | Must | |
| Full payment option | ⏳ | ⏳ | Must | |
| Booking confirmation email | ❌ | ⏳ | Must | |
| Invoice generation | ⏳ | ⏳ | Must | PDF |
| Cancellation request | ⏳ | ⏳ | Must | |
| Refund processing | ⏳ | ⏳ | Must | |
| Booking modification | ⏳ | ⏳ | Should | |

---

## Customer Portal

| Feature | FE | BE | Priority | Notes |
|---------|----|----|----------|-------|
| Dashboard overview | ⏳ | ⏳ | Must | |
| Booking history | ⏳ | ⏳ | Must | |
| Booking details | ⏳ | ⏳ | Must | |
| Invoice download | ⏳ | ⏳ | Must | |
| Profile management | ⏳ | ⏳ | Must | |
| Password change | ⏳ | ⏳ | Must | |
| Wishlist | ⏳ | ⏳ | Should | |
| Support tickets | ⏳ | ⏳ | Must | |
| Ticket replies | ⏳ | ⏳ | Must | |

---

## Admin Backoffice

| Feature | FE | BE | Priority | Notes |
|---------|----|----|----------|-------|
| Admin dashboard | ⏳ | ⏳ | Must | |
| Trip CRUD | ⏳ | ⏳ | Must | |
| Itinerary management | ⏳ | ⏳ | Must | |
| Pricing rules | ⏳ | ⏳ | Must | |
| Availability management | ⏳ | ⏳ | Must | |
| Destination CRUD | ⏳ | ⏳ | Must | |
| Blog CRUD | ⏳ | ⏳ | Must | |
| Vlog CRUD | ⏳ | ⏳ | Must | |
| Media library | ⏳ | ⏳ | Should | |
| Booking list | ⏳ | ⏳ | Must | |
| Booking status updates | ⏳ | ⏳ | Must | |
| Customer list | ⏳ | ⏳ | Must | |
| Support ticket queue | ⏳ | ⏳ | Must | |
| Ticket responses | ⏳ | ⏳ | Must | |
| Translation management | ⏳ | ⏳ | Must | |
| Analytics/reports | ⏳ | ⏳ | Should | |
| Audit logs | ⏳ | ⏳ | Should | |
| User/role management | ⏳ | ⏳ | Must | |

---

## AI Assistant

| Feature | FE | BE | Priority | Notes |
|---------|----|----|----------|-------|
| Chat widget | ⏳ | ⏳ | Must | |
| Trip search tool | ⏳ | ⏳ | Must | MCP |
| Trip details fetch | ⏳ | ⏳ | Must | MCP |
| FAQ answering | ⏳ | ⏳ | Must | RAG |
| Lead creation | ⏳ | ⏳ | Should | MCP |
| Booking status (auth) | ⏳ | ⏳ | Should | MCP |
| Multilingual | ⏳ | ⏳ | Could | |

---

## Content Features

| Feature | FE | BE | Priority | Notes |
|---------|----|----|----------|-------|
| Image galleries | ⏳ | ⏳ | Must | Lightbox |
| Video embeds | ⏳ | ❌ | Must | YouTube |
| Reviews/ratings | ⏳ | ⏳ | Must | |
| Social sharing | ⏳ | ❌ | Should | |
| Newsletter signup | ⏳ | ⏳ | Should | |

---

## SEO & Analytics

| Feature | FE | BE | Priority | Notes |
|---------|----|----|----------|-------|
| SSR/SSG | ⏳ | ❌ | Must | Next.js/Nuxt |
| Meta tags | ⏳ | ❌ | Must | |
| Structured data | ⏳ | ❌ | Must | JSON-LD |
| Sitemap | ⏳ | ⏳ | Must | |
| Robots.txt | ⏳ | ❌ | Must | |
| Google Analytics | ⏳ | ❌ | Must | |
| hreflang tags | ⏳ | ❌ | Must | |

---

## Summary by Priority

| Priority | Total Features | FE | BE |
|----------|---------------|----|----|
| Must | ~65 | ~60 | ~55 |
| Should | ~20 | ~18 | ~15 |
| Could | ~5 | ~4 | ~3 |
