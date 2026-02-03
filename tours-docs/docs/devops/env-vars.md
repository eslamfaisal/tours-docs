# Environment Variables

## Frontend

| Variable | Description |
|----------|-------------|
| `NEXT_PUBLIC_API_URL` | Backend API URL |
| `NEXT_PUBLIC_STRIPE_KEY` | Stripe publishable key |
| `NEXT_PUBLIC_GA_ID` | Google Analytics ID |
| `NEXT_PUBLIC_SENTRY_DSN` | Sentry DSN |

## Backend

| Variable | Description |
|----------|-------------|
| `DATABASE_URL` | PostgreSQL connection |
| `REDIS_URL` | Redis connection |
| `JWT_SECRET` | JWT signing secret |
| `STRIPE_SECRET_KEY` | Stripe secret key |
| `SENDGRID_API_KEY` | Email API key |
| `OPENAI_API_KEY` | AI API key |
| `S3_BUCKET` | Storage bucket |

## Secrets Management

Secrets stored in:

- GitHub Secrets (CI/CD)
- AWS Secrets Manager (Production)
- Vercel Environment Variables (Frontend)
