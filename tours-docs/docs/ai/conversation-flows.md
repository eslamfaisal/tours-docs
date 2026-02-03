# Conversation Flows

Standard workflows for MCP-enabled documentation interactions.

---

## Overview

All AI interactions follow documentation-driven workflows:

```mermaid
stateDiagram-v2
    [*] --> ReceiveRequest
    ReceiveRequest --> QueryDocumentation
    QueryDocumentation --> CheckCoverage
    CheckCoverage --> BuildContext: Sufficient
    CheckCoverage --> ReportGap: Insufficient
    BuildContext --> Generate
    Generate --> Validate
    Validate --> CitedOutput: Pass
    Validate --> Iterate: Fail
    Iterate --> Generate
    CitedOutput --> [*]
    ReportGap --> [*]
```

---

## Standard Flows

### 1. Feature Implementation Request

For requests like "Implement the booking confirmation email":

```mermaid
sequenceDiagram
    participant D as Developer
    participant A as AI Agent
    participant M as MCP Tools
    participant L as LLM

    D->>A: Implement booking confirmation email
    
    A->>M: docs_search("booking confirmation email")
    M-->>A: [requirements/functional.md, api/bookings.md]
    
    A->>M: docs_read("requirements/functional.md")
    M-->>A: FR-EMAIL-001: Booking confirmation...
    
    A->>M: docs_read("api/bookings.md")
    M-->>A: GET /bookings/{id} returns...
    
    A->>M: context_build(task="booking email")
    M-->>A: Complete context map
    
    A->>L: Generate with context
    L-->>A: Implementation draft
    
    A->>A: Validate citations
    A->>A: Check compliance
    
    A-->>D: Implementation with references
```

---

### 2. API Endpoint Query

For requests like "What endpoints exist for trips?":

```mermaid
sequenceDiagram
    participant U as User
    participant A as AI Agent
    participant M as MCP Tools

    U->>A: What endpoints exist for trips?
    
    A->>M: docs_list(section="api")
    M-->>A: [auth.md, trips.md, bookings.md...]
    
    A->>M: docs_read("api/trips.md")
    M-->>A: Full endpoint documentation
    
    A-->>U: Listed endpoints with citations
```

---

### 3. Documentation Gap Handling

When documentation is missing:

```mermaid
sequenceDiagram
    participant D as Developer
    participant A as AI Agent
    participant M as MCP Tools

    D->>A: Implement payment refund logic
    
    A->>M: docs_search("payment refund")
    M-->>A: No results
    
    A->>M: docs_search("payments")
    M-->>A: No results
    
    A->>M: docs_list(section="backend")
    M-->>A: [auth.md, security.md, database.md...]
    
    Note over A: No payment documentation found
    
    A-->>D: DOCUMENTATION_GAP detected
    A-->>D: Missing: backend/payments.md
    A-->>D: Required: Refund policies and API specs
```

---

### 4. Customer Chat Inquiry

For tourist inquiries in the chat widget:

```mermaid
sequenceDiagram
    participant C as Customer
    participant W as Chat Widget
    participant S as Chat Service
    participant M as MCP Tools
    participant L as LLM

    C->>W: What Luxor tours are available next week?
    W->>S: Process query
    
    S->>M: docs_search("Luxor tours")
    M-->>S: Product catalog info
    
    S->>M: API call: search_trips(destination=Luxor)
    M-->>S: Available trips
    
    S->>L: Generate response with context
    L-->>S: Formatted recommendations
    
    S->>S: Validate against guardrails
    S-->>W: Response with trip cards
    W-->>C: Display recommendations
```

---

### 5. Validation and Review Flow

For code review against documentation:

```mermaid
sequenceDiagram
    participant D as Developer
    participant A as AI Agent
    participant M as MCP Tools

    D->>A: Review this booking endpoint implementation
    
    A->>M: docs_read("api/bookings.md")
    M-->>A: Documented specification
    
    A->>M: api_validate(endpoint="/bookings", method="POST", impl={...})
    M-->>A: Validation results
    
    alt Matches Documentation
        A-->>D: ✅ Implementation matches spec
    else Deviations Found
        A-->>D: ⚠️ Deviations found:
        A-->>D: - Field X missing from response
        A-->>D: - Status code should be 201 not 200
    end
```

---

## Error Handling Flows

### Insufficient Context

```mermaid
graph TD
    A[Low Confidence] --> B{Can request more?}
    B -->|Yes| C[Query additional sections]
    B -->|No| D{Partial answer possible?}
    D -->|Yes| E[Provide partial + note gaps]
    D -->|No| F[Request clarification from user]
```

### Conflicting Documentation

```mermaid
graph TD
    A[Conflict Detected] --> B[Identify conflicting sources]
    B --> C[Flag to user]
    C --> D{User resolves?}
    D -->|Yes| E[Proceed with clarified requirement]
    D -->|No| F[Document conflict for team review]
```

---

## Context Persistence

### Session State

```json
{
  "session_id": "sess_abc123",
  "user_id": "user_456",
  "message_history": [...],
  "retrieved_context": {
    "docs/api/trips.md": { "cached_at": "...", "sections": [...] },
    "docs/requirements/functional.md": { "cached_at": "...", "sections": [...] }
  },
  "active_tasks": [],
  "escalation_status": null
}
```

### Context Reuse

Within a session, relevant documentation is cached:

```mermaid
graph LR
    A[New Query] --> B{Related to cached context?}
    B -->|Yes| C[Reuse + Augment]
    B -->|No| D[Fresh retrieval]
    C --> E[Generate response]
    D --> E
```

---

## Flow Metrics

| Flow Type | Avg Response Time | Doc Queries | Success Rate |
|-----------|-------------------|-------------|--------------|
| Feature Implementation | 5-10s | 3-5 | 95% |
| API Query | 2-3s | 1-2 | 99% |
| Gap Detection | 3-5s | 2-4 | 100% |
| Customer Chat | 2-4s | 1-3 | 92% |
| Validation | 4-6s | 2-3 | 98% |
