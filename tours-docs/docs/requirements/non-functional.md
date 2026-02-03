# Non-Functional Requirements

## NFR-1: Performance

| ID | Requirement | Target | Priority |
|----|-------------|--------|----------|
| NFR-1.1 | Page load time (First Contentful Paint) | < 1.5s | Must |
| NFR-1.2 | Time to Interactive | < 3s | Must |
| NFR-1.3 | API response time (P95) | < 300ms | Must |
| NFR-1.4 | Image optimization (WebP, lazy loading) | Implemented | Must |
| NFR-1.5 | CDN for static assets | Implemented | Should |
| NFR-1.6 | Database query time | < 100ms | Should |

---

## NFR-2: Scalability

| ID | Requirement | Target | Priority |
|----|-------------|--------|----------|
| NFR-2.1 | Concurrent users | 1,000+ | Must |
| NFR-2.2 | Horizontal scaling capability | Supported | Should |
| NFR-2.3 | Database connection pooling | Configured | Should |
| NFR-2.4 | Caching layer (Redis) | Implemented | Should |

---

## NFR-3: Security

| ID | Requirement | Target | Priority |
|----|-------------|--------|----------|
| NFR-3.1 | HTTPS everywhere | Enforced | Must |
| NFR-3.2 | JWT token security | Short-lived + refresh | Must |
| NFR-3.3 | Password hashing | bcrypt, cost 12+ | Must |
| NFR-3.4 | SQL injection prevention | Parameterized queries | Must |
| NFR-3.5 | XSS protection | CSP headers, sanitization | Must |
| NFR-3.6 | CSRF protection | Tokens required | Must |
| NFR-3.7 | Rate limiting | Implemented | Must |
| NFR-3.8 | Input validation | Server-side | Must |
| NFR-3.9 | PCI compliance for payments | Via gateway (Stripe) | Must |
| NFR-3.10 | Security headers | HSTS, X-Frame-Options | Should |
| NFR-3.11 | Audit logging | All sensitive operations | Should |
| NFR-3.12 | Penetration testing | Before launch | Should |

---

## NFR-4: SEO

| ID | Requirement | Target | Priority |
|----|-------------|--------|----------|
| NFR-4.1 | Server-side rendering (SSR) | Critical pages | Must |
| NFR-4.2 | Meta tags (title, description) | All pages | Must |
| NFR-4.3 | Structured data (JSON-LD) | Trip pages | Must |
| NFR-4.4 | Sitemap.xml | Auto-generated | Must |
| NFR-4.5 | Robots.txt | Configured | Must |
| NFR-4.6 | Localized URLs (/en/, /es/, /ar/) | Implemented | Must |
| NFR-4.7 | hreflang tags | All pages | Must |
| NFR-4.8 | Canonical URLs | Implemented | Must |
| NFR-4.9 | Open Graph tags | All pages | Should |
| NFR-4.10 | Core Web Vitals compliance | LCP, FID, CLS | Should |

---

## NFR-5: Accessibility

| ID | Requirement | Target | Priority |
|----|-------------|--------|----------|
| NFR-5.1 | WCAG 2.1 Level AA compliance | Implemented | Should |
| NFR-5.2 | Keyboard navigation | Full support | Should |
| NFR-5.3 | Screen reader compatibility | Tested | Should |
| NFR-5.4 | Color contrast ratios | 4.5:1 minimum | Should |
| NFR-5.5 | Alt text for images | All images | Must |
| NFR-5.6 | Form labels and ARIA | Implemented | Should |

---

## NFR-6: Internationalization

| ID | Requirement | Target | Priority |
|----|-------------|--------|----------|
| NFR-6.1 | RTL layout for Arabic | Implemented | Must |
| NFR-6.2 | Date/time localization | Per locale | Must |
| NFR-6.3 | Number formatting | Per locale | Must |
| NFR-6.4 | Currency display | Configurable | Should |
| NFR-6.5 | Translation management | CMS-driven | Must |
| NFR-6.6 | Language detection | Browser-based | Should |

---

## NFR-7: Reliability

| ID | Requirement | Target | Priority |
|----|-------------|--------|----------|
| NFR-7.1 | Uptime SLA | 99.9% | Must |
| NFR-7.2 | Error rate | < 0.1% | Must |
| NFR-7.3 | Graceful degradation | Implemented | Should |
| NFR-7.4 | Health check endpoints | /health, /ready | Must |
| NFR-7.5 | Circuit breaker pattern | External services | Should |

---

## NFR-8: Observability

| ID | Requirement | Target | Priority |
|----|-------------|--------|----------|
| NFR-8.1 | Centralized logging | ELK/CloudWatch | Must |
| NFR-8.2 | Application metrics | Prometheus/Grafana | Should |
| NFR-8.3 | Error tracking | Sentry | Must |
| NFR-8.4 | Distributed tracing | OpenTelemetry | Could |
| NFR-8.5 | Alerting | Critical errors | Must |
| NFR-8.6 | Analytics integration | GA4 | Must |

---

## NFR-9: Backup & Disaster Recovery

| ID | Requirement | Target | Priority |
|----|-------------|--------|----------|
| NFR-9.1 | Database backups | Daily automated | Must |
| NFR-9.2 | Point-in-time recovery | 7-day retention | Should |
| NFR-9.3 | Backup testing | Monthly | Should |
| NFR-9.4 | Recovery Time Objective (RTO) | < 4 hours | Should |
| NFR-9.5 | Recovery Point Objective (RPO) | < 1 hour | Should |

---

## NFR-10: Compliance

| ID | Requirement | Target | Priority |
|----|-------------|--------|----------|
| NFR-10.1 | GDPR compliance | Data handling, consent | Must |
| NFR-10.2 | Cookie consent banner | Implemented | Must |
| NFR-10.3 | Data export (user request) | Supported | Should |
| NFR-10.4 | Data deletion (right to be forgotten) | Supported | Should |
| NFR-10.5 | Privacy policy | Published | Must |
| NFR-10.6 | Terms of service | Published | Must |

---

## NFR-11: Development & Operations

| ID | Requirement | Target | Priority |
|----|-------------|--------|----------|
| NFR-11.1 | CI/CD pipeline | Automated | Must |
| NFR-11.2 | Staging environment | Mirrors production | Must |
| NFR-11.3 | Feature flags | Supported | Should |
| NFR-11.4 | Blue-green deployments | Configured | Could |
| NFR-11.5 | Infrastructure as Code | Terraform/CDK | Should |
| NFR-11.6 | API documentation | OpenAPI/Swagger | Must |
