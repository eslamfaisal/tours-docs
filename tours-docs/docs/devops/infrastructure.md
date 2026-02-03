# Infrastructure

## Cloud Architecture

| Component | Service | Purpose |
|-----------|---------|---------|
| Frontend | Vercel | Next.js hosting with edge |
| CDN | CloudFlare | Caching, DDoS protection |
| Backend | AWS ECS / Railway | Spring Boot containers |
| Database | PostgreSQL (RDS) | Primary data store |
| Cache | Redis (ElastiCache) | Session, caching |
| Storage | S3 / Cloudinary | Images, files |
| Email | SendGrid | Transactional emails |
| Payments | Stripe | Payment processing |
| AI | OpenAI | Chat assistant |

---

## Environments

| Environment | Purpose | URL |
|-------------|---------|-----|
| Development | Local development | localhost |
| Staging | Pre-production testing | staging.egypttours.com |
| Production | Live system | egypttours.com |

---

## Scaling Strategy

| Component | Strategy |
|-----------|----------|
| Frontend | Edge functions, global CDN |
| Backend | Horizontal scaling, auto-scaling groups |
| Database | Read replicas, connection pooling |
| Cache | Redis cluster |
