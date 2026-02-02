You are a senior **product + system architect + tech lead**.
Design and document a complete **Egypt-only tours booking web platform** with feature parity inspired by the reference website (features only, not UI), but with **new branding + new design system + new UX/UI**.

Reference site (features inspiration only):
Memphis Tours Egypt: ([memphistours.com](https://www.memphistours.com/egypt))

### 1) Scope & Vision

Build a full platform where tourists can:

* Browse Egypt tours (packages, day tours, cruises) and destinations (Cairo, Luxor, Aswan, Giza, Grand Egyptian Museum, etc.).
* View rich trip pages (itinerary, included/excluded, pricing, gallery, reviews, related trips).
* Book online (support **instant booking** + **request-to-book** modes).
* Create accounts, manage bookings, payments, invoices, and support tickets.
* Consume content: **blogs + vlogs** (videos + images) and past trip media.
* Use an **agentic chat assistant** (LLM + MCP-ready) to answer questions, compare trips, and assist booking using approved data sources.

Tours are **Egypt only**.

### 2) Languages & Localization

Main languages:

* English
* Spanish
* Arabic (RTL)

Requirements:

* Full i18n routing, formatting, RTL for Arabic.
* SEO-friendly localized URLs `/en/`, `/es/`, `/ar/`.

### 3) Tech Stack

Frontend:

* React JS
* Zustand state management
* SEO-first rendering strategy (SSR/SSG recommended)

Backend:

* Java Spring Boot
* Clean Architecture
* JWT auth + Social login
* REST APIs (OpenAPI/Swagger)
* Postman collections + environments

Docs:

* **MkDocs** as the documentation framework
* Docs-as-code workflow (repository + CI/CD build + deployment)

### 4) Required Documentation Output Format (MkDocs)

All documentation MUST be delivered as a full MkDocs-ready structure.

#### A) MkDocs Site Architecture

Generate a proposed `mkdocs.yml` plus a complete navigation tree.

The docs must include:

* `/docs/index.md` (Overview)
* `/docs/product/` (PRD, personas, journeys)
* `/docs/requirements/` (functional + non-functional requirements)
* `/docs/ux/` (user flows, booking flows, role permissions)
* `/docs/frontend/` (architecture, routing, Zustand stores, components, i18n, SSR/SEO)
* `/docs/backend/` (clean architecture structure, modules, auth, security, DB, migrations)
* `/docs/api/` (OpenAPI outlines, endpoints grouped by domain, error format, pagination)
* `/docs/postman/` (collections structure, environments, auth flows, examples)
* `/docs/admin/` (admin modules + operational workflows)
* `/docs/ai/` (agentic chat, MCP tool schemas, RAG design, guardrails)
* `/docs/devops/` (CI/CD, hosting, environments, monitoring, backups)
* `/docs/migration/` (Facebook content organization + import plan)
* `/docs/testing/` (test strategy, QA checklists)
* `/docs/release/` (milestones, release plan, changelog)

#### B) MkDocs Plugins & Standards

Specify a recommended MkDocs stack and why, including:

* `mkdocs-material`
* Search
* Mermaid diagrams (for architecture & flows)
* OpenAPI rendering approach (e.g., Redoc via embedding or plugin)
* Versioning strategy (optional but recommended)
* Markdown conventions and templates

Include a “How to run docs locally” section:

* install commands
* `mkdocs serve`
* build command
* deployment guidance (GitHub Pages / Netlify / internal server)

### 5) Roles & Permissions

Define roles:

* Guest
* Customer
* Admin
* Editor/Content Manager
* Booking Agent/Operations
* Support Agent

Provide RBAC matrix and where it is enforced (frontend route guards + backend policies).

### 6) Modules & Features

#### A) Public Website

* Home, trip listings with filters/sorting, trip details, destinations, blog, vlogs/media, FAQ/policies, search, reviews, contact/lead forms, WhatsApp CTA.

#### B) Booking & Customer Portal

* Auth (email + social), dashboard, wishlist, booking flow, payments (deposit/full), invoices, refunds/cancellation workflow, support tickets.

#### C) Admin Backoffice

* Trips CRUD, pricing rules, availability, destinations CRUD, blog/vlog CRUD, bookings operations, customers management, support queue, analytics, translation workflow, audit logs.

### 7) Data Model & APIs

Provide:

* ERD (Mermaid diagram)
* Entities: User, Role, Trip, Destination, ItineraryDay, PriceRule, Booking, Payment, Review, BlogPost, Vlog, MediaAsset, Ticket, TicketMessage, FAQ, Translation
* API outline grouped by domain + sample payloads
* Auth flows (JWT/refresh) + social login callbacks
* Standard errors, pagination/filter patterns

### 8) Agentic Chat (LLM + MCP-ready)

Design the assistant to:

* Answer only from approved sources (catalog, FAQ, policies).
* Use tools (MCP) like: search trips, fetch details, create lead/ticket, check booking status (authenticated).
* Provide safe behavior: no invented prices, always cite trip ID + last updated timestamp.
  Deliver:
* System prompt + tool prompts
* MCP tool schemas (JSON)
* Conversation flows (anonymous vs logged-in)
* RAG strategy (indexing, chunking, refresh)

### 9) Non-Functional Requirements

Performance, security, SEO, observability, backup/DR, GDPR/cookies, accessibility, rate limiting.

### 10) Deliverables From AntiGravity

Generate these outputs **as MkDocs pages** (with navigation), including:

1. Complete feature checklist (frontend vs backend)
2. Full modules breakdown
3. Data model + ERD + API outline
4. Spring Boot clean architecture plan (folders + layers)
5. React + Zustand structure (routing + stores)
6. Postman collection + environments plan
7. AI chat + MCP plan
8. Milestone roadmap & release plan

Constraints:

* Egypt-only trips.
* New branding + new design system (no UI copying).
* Include a plan for migrating Facebook content into blog/vlog/media library.

Now produce:

* `mkdocs.yml` draft
* full docs navigation tree
* the initial content skeleton for each required page (headings + key points)
* and the feature/spec sections in detail where needed.

