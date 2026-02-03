# Egypt Tours Platform - Implementation Plan

## 1. Architecture Overview
Based on `docs/backend/index.md` and `docs/frontend/index.md`:

### Frontend (`tours-frontend`)
- **Framework**: Next.js 14+ (App Router)
- **Language**: TypeScript
- **State**: Zustand
- **Styling**: Tailwind CSS + Shadcn/UI
- **I18n**: next-intl (Arabic RTL support)
- **Data**: TanStack Query (React Query)

### Backend (`tours-be`)
- **Framework**: Spring Boot 3.3+
- **Language**: Java 21
- **Build**: Gradle (Kotlin DSL recommended for modern setups)
- **Architecture**: Clean Architecture (Domain, Application, Infrastructure, API)
- **Auth**: Spring Security + JWT
- **Docs**: OpenAPI (Swagger)

### AI Integration (`mcp-server`)
- **Model**: MCP (Model Context Protocol)
- **Role**: The Application will act as an MCP Client or host an MCP bridge to query the `mcp-server` for documentation and tour data rag.

## 2. Implementation Phases

### Phase 1: Foundation & Setup
- Initialize Monorepo structure.
- **Backend**: Spring Boot scaffolding (Gradle, Docker Compose for Postgres/Redis).
- **Frontend**: Next.js scaffolding (Tailwind, Fonts, Icons).
- **DevOps**: Basic CI/CD workflow.

### Phase 2: Backend Core (The Engine)
- **Domain**: User, Role entities.
- **Infrastructure**: PostgreSQL config, Flyway/Liquibase Migrations.
- **Security**: JWT Filter, AuthController (Login/Register).
- **API**: Global Exception Handling, Response Wrapping.

### Phase 3: Frontend Core (The Shell)
- **UI**: Design System implementation (Colors, Typography).
- **Layouts**: Main Layout (Navbar, Footer), Dashboard Layout.
- **Auth**: Login/Register Pages, Protected Routes.
- **I18n**: Language Switcher (Ar/En).

### Phase 4: Business Domains (Tours & Booking)
- **Backend API**: CRUD for `Tours`, `Destinations`, `Bookings`.
- **Search**: Advanced filtering (Price, Date, Location).
- **Frontend**: Tour Listing, Tour Details, Booking Form.

### Phase 5: AI & Polishing
- **Chatbot**: Floating UI widget.
- **Integration**: Connect Chatbot to MCP Server context.
- **Optimization**: SEO Metadata, Sitemap, Performance tuning.

---
**Status**: Starting Phase 1...
