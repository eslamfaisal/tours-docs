# Monitoring

## Tools

| Tool | Purpose |
|------|---------|
| Datadog / Grafana | Metrics, dashboards |
| Sentry | Error tracking |
| AWS CloudWatch | Logs, alarms |
| Uptime Robot | Uptime monitoring |

---

## Key Metrics

| Metric | Target |
|--------|--------|
| API Response Time (p95) | < 200ms |
| Error Rate | < 0.1% |
| Uptime | 99.9% |
| Apdex Score | > 0.95 |

---

## Alerts

| Alert | Condition | Channel |
|-------|-----------|---------|
| High Error Rate | > 1% for 5min | Slack, PagerDuty |
| High Latency | p95 > 500ms | Slack |
| Database Slow | Queries > 1s | Slack |
| Site Down | No response | PagerDuty |

---

## Logging

```yaml
# Structured logging format
{
  "timestamp": "2026-02-02T22:00:00Z",
  "level": "INFO",
  "service": "api",
  "traceId": "abc123",
  "message": "Booking created",
  "bookingId": 501
}
```
