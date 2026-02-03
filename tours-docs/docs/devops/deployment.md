# Deployment

## Deployment Checklist

- [ ] All tests passing
- [ ] Database migrations reviewed
- [ ] Environment variables updated
- [ ] Feature flags configured
- [ ] Rollback plan ready

---

## Rolling Deployment

1. Deploy new version to staging
2. Run E2E tests
3. Deploy to production (rolling)
4. Monitor metrics
5. Rollback if issues detected

---

## Rollback Procedure

```bash
# Vercel (Frontend)
vercel rollback

# ECS (Backend)
aws ecs update-service --cluster prod --service api \
  --task-definition api:previous-version

# Database
flyway undo
```
