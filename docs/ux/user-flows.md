# User Flows

## Overview

This document defines the primary user flows through the Egypt Tours Platform.

---

## Flow 1: Trip Discovery

```mermaid
flowchart TD
    A[User lands on Homepage] --> B{Browse or Search?}
    B -->|Browse| C[View Trip Listings]
    B -->|Search| D[Enter Search Query]
    
    C --> E[Apply Filters]
    E --> F[View Filtered Results]
    D --> F
    
    F --> G[Click Trip Card]
    G --> H[View Trip Details]
    
    H --> I{Interested?}
    I -->|Yes| J[Start Booking]
    I -->|Maybe| K[Add to Wishlist]
    I -->|No| F
    
    K --> L[Login Required?]
    L -->|Yes| M[Login/Register]
    M --> K
    L -->|No| N[Saved to Wishlist]
```

### Key States

| State | Description | Actions Available |
|-------|-------------|-------------------|
| Listings | Grid/list of trips | Filter, sort, paginate |
| Trip Card | Preview card | View details, quick wishlist |
| Trip Details | Full information | Book, wishlist, share |

---

## Flow 2: User Registration

```mermaid
flowchart TD
    A[Click Register] --> B[Registration Form]
    B --> C[Enter Email/Password]
    C --> D[Accept Terms]
    D --> E[Submit]
    E --> F{Validation}
    
    F -->|Valid| G[Send Verification Email]
    F -->|Invalid| H[Show Errors]
    H --> B
    
    G --> I[Check Email]
    I --> J[Click Verification Link]
    J --> K[Account Activated]
    K --> L[Redirect to Dashboard]
```

### Alternative: Social Login

```mermaid
flowchart TD
    A[Click Social Login] --> B{Provider}
    B -->|Google| C[Google OAuth]
    B -->|Facebook| D[Facebook OAuth]
    
    C --> E[Authorize]
    D --> E
    
    E --> F{Existing Account?}
    F -->|Yes| G[Login & Redirect]
    F -->|No| H[Create Account]
    H --> G
```

---

## Flow 3: Customer Portal

```mermaid
flowchart TD
    A[Login] --> B[Dashboard]
    
    B --> C[My Bookings]
    B --> D[My Profile]
    B --> E[My Wishlist]
    B --> F[Support Tickets]
    
    C --> G[Booking Details]
    G --> H[Download Invoice]
    G --> I[Request Cancellation]
    G --> J[View Itinerary]
    
    F --> K[View Ticket]
    K --> L[Reply to Ticket]
    F --> M[Create New Ticket]
```

---

## Flow 4: Content Consumption

```mermaid
flowchart TD
    A[Homepage] --> B{Content Type}
    B -->|Blog| C[Blog Listing]
    B -->|Vlogs| D[Vlog Gallery]
    B -->|Destinations| E[Destination Pages]
    
    C --> F[Article Detail]
    F --> G[Related Trips CTA]
    G --> H[Trip Details]
    
    D --> I[Video Player]
    I --> G
    
    E --> J[Destination Info]
    J --> K[Trips in Destination]
    K --> H
```

---

## Flow 5: AI Assistant

```mermaid
flowchart TD
    A[Click Chat Widget] --> B[Chat Opens]
    B --> C[User Types Question]
    C --> D[AI Processes]
    
    D --> E{Query Type}
    E -->|Trip Search| F[Search Trips Tool]
    E -->|FAQ| G[RAG Knowledge Base]
    E -->|Booking Status| H{Authenticated?}
    E -->|General| I[General Response]
    
    F --> J[Show Trip Cards]
    J --> K[User Clicks Trip]
    K --> L[Navigate to Trip]
    
    G --> M[Show Answer]
    
    H -->|Yes| N[Fetch Status Tool]
    H -->|No| O[Request Login]
    N --> P[Show Booking Info]
```

---

## Flow 6: Contact/Lead

```mermaid
flowchart TD
    A[Contact Page] --> B[Contact Form]
    B --> C[Enter Details]
    C --> D[Select Inquiry Type]
    D --> E[Submit]
    E --> F[Thank You Message]
    F --> G[Email Notification to Team]
    
    A --> H[WhatsApp CTA]
    H --> I[Open WhatsApp]
```

---

## Error Handling Flows

### 404 Page

```mermaid
flowchart TD
    A[Invalid URL] --> B[404 Page]
    B --> C[Search Box]
    B --> D[Popular Trips]
    B --> E[Homepage Link]
```

### Session Expired

```mermaid
flowchart TD
    A[API Returns 401] --> B[Show Login Modal]
    B --> C[Re-login]
    C --> D[Restore Previous State]
```
