# DevOps

This section covers deployment, CI/CD, and infrastructure.

---

## Contents

| Document | Description |
|----------|-------------|
| [Infrastructure](infrastructure.md) | Cloud infrastructure |
| [CI/CD](ci-cd.md) | Pipeline configuration |
| [Monitoring](monitoring.md) | Observability setup |
| [Deployment](deployment.md) | Deployment procedures |
| [Environment Variables](env-vars.md) | Configuration management |

---

## Infrastructure Overview

```mermaid
graph TD
    subgraph CDN
        A[CloudFlare]
    end
    
    subgraph Frontend
        B[Vercel]
    end
    
    subgraph Backend
        C[AWS ECS / Railway]
    end
    
    subgraph Database
        D[PostgreSQL RDS]
        E[Redis Cache]
    end
    
    subgraph Storage
        F[AWS S3 / Cloudinary]
    end
    
    subgraph Services
        G[Stripe]
        H[SendGrid]
        I[OpenAI]
    end
    
    A --> B
    B --> C
    C --> D
    C --> E
    C --> F
    C --> G
    C --> H
    C --> I
```
