# E2E Testing

## Playwright Configuration

```typescript
// playwright.config.ts
import { defineConfig } from '@playwright/test';

export default defineConfig({
  testDir: './e2e',
  baseURL: 'http://localhost:3000',
  use: {
    trace: 'on-first-retry',
    screenshot: 'only-on-failure',
  },
  projects: [
    { name: 'chromium', use: { browserName: 'chromium' } },
    { name: 'mobile', use: { viewport: { width: 375, height: 667 } } },
  ],
});
```

## Test Examples

```typescript
// e2e/booking.spec.ts
import { test, expect } from '@playwright/test';

test('complete booking flow', async ({ page }) => {
  await page.goto('/trips/pyramids-day-tour');
  
  await expect(page.getByRole('heading', { name: /pyramids/i })).toBeVisible();
  
  await page.getByRole('button', { name: 'Book Now' }).click();
  await page.fill('[name="date"]', '2026-03-15');
  await page.fill('[name="adults"]', '2');
  await page.getByRole('button', { name: 'Continue' }).click();
  
  await expect(page.getByText('Booking Summary')).toBeVisible();
});
```

## Critical Flows

- [ ] Homepage loads correctly
- [ ] Trip search and filtering
- [ ] Trip detail page loads
- [ ] Complete booking flow
- [ ] User login/register
- [ ] Payment processing
