# Routing

## Overview

Routing is built on Next.js App Router with locale-based path prefixes for i18n support.

---

## Route Structure

```
/[locale]/                          # Home
/[locale]/trips                     # Trip listings
/[locale]/trips/[slug]              # Trip details
/[locale]/destinations              # Destinations
/[locale]/destinations/[slug]       # Destination details
/[locale]/booking                   # Booking flow
/[locale]/blog                      # Blog listing
/[locale]/blog/[slug]               # Blog article
/[locale]/vlogs                     # Video gallery
/[locale]/contact                   # Contact page
/[locale]/about                     # About us
/[locale]/faq                       # FAQ
/[locale]/policies/[type]           # Legal pages

# Authenticated routes
/[locale]/account                   # Dashboard
/[locale]/account/bookings          # Booking history
/[locale]/account/bookings/[id]     # Booking details
/[locale]/account/profile           # Profile settings
/[locale]/account/wishlist          # Saved trips
/[locale]/account/tickets           # Support tickets

# Admin routes
/[locale]/admin                     # Admin dashboard
/[locale]/admin/trips               # Trip management
/[locale]/admin/bookings            # Booking operations
/[locale]/admin/customers           # Customer management
/[locale]/admin/content             # Content management
/[locale]/admin/support             # Support queue
/[locale]/admin/settings            # System settings
```

---

## Route Matrix

| Route | Auth | Roles | SSR | Layout |
|-------|------|-------|-----|--------|
| `/` | No | All | SSG | Public |
| `/trips` | No | All | SSG | Public |
| `/trips/[slug]` | No | All | SSG | Public |
| `/booking` | Yes | Customer+ | SSR | Minimal |
| `/account/*` | Yes | Customer+ | SSR | Dashboard |
| `/admin/*` | Yes | Admin+ | SSR | Admin |

---

## File Structure

```
app/
├── [locale]/
│   ├── layout.tsx              # Root layout with providers
│   ├── page.tsx                # Home page
│   ├── loading.tsx             # Loading UI
│   ├── error.tsx               # Error boundary
│   ├── not-found.tsx           # 404 page
│   │
│   ├── trips/
│   │   ├── page.tsx            # Listings
│   │   └── [slug]/
│   │       └── page.tsx        # Details
│   │
│   ├── booking/
│   │   ├── layout.tsx          # Booking layout
│   │   └── page.tsx            # Booking wizard
│   │
│   ├── account/
│   │   ├── layout.tsx          # Dashboard layout
│   │   ├── page.tsx            # Dashboard home
│   │   ├── bookings/
│   │   └── profile/
│   │
│   └── admin/
│       ├── layout.tsx          # Admin layout
│       ├── page.tsx            # Dashboard
│       └── ...
│
├── middleware.ts               # Auth & locale middleware
└── sitemap.ts                  # Dynamic sitemap
```

---

## Middleware

```typescript
// middleware.ts
import { NextResponse } from 'next/server';
import type { NextRequest } from 'next/server';
import { match } from '@formatjs/intl-localematcher';
import Negotiator from 'negotiator';

const locales = ['en', 'es', 'ar'];
const defaultLocale = 'en';

function getLocale(request: NextRequest): string {
  const negotiator = new Negotiator({
    headers: { 'accept-language': request.headers.get('accept-language') || '' },
  });
  return match(negotiator.languages(), locales, defaultLocale);
}

export function middleware(request: NextRequest) {
  const { pathname } = request.nextUrl;
  
  // Check if path has locale
  const hasLocale = locales.some(
    (locale) => pathname.startsWith(`/${locale}/`) || pathname === `/${locale}`
  );
  
  if (!hasLocale) {
    const locale = getLocale(request);
    return NextResponse.redirect(
      new URL(`/${locale}${pathname}`, request.url)
    );
  }
  
  // Auth check for protected routes
  const protectedPaths = ['/account', '/admin', '/booking'];
  const isProtected = protectedPaths.some((path) =>
    pathname.includes(path)
  );
  
  if (isProtected) {
    const token = request.cookies.get('token');
    if (!token) {
      const locale = pathname.split('/')[1];
      return NextResponse.redirect(
        new URL(`/${locale}/login?redirect=${pathname}`, request.url)
      );
    }
  }
  
  return NextResponse.next();
}

export const config = {
  matcher: ['/((?!api|_next/static|_next/image|favicon.ico).*)'],
};
```

---

## Route Guards

```tsx
// components/auth/ProtectedRoute.tsx
'use client';

import { useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { useAuthStore } from '@/stores/authStore';

interface ProtectedRouteProps {
  children: React.ReactNode;
  roles?: string[];
}

export function ProtectedRoute({ children, roles }: ProtectedRouteProps) {
  const router = useRouter();
  const { user, isAuthenticated, isLoading } = useAuthStore();

  useEffect(() => {
    if (!isLoading && !isAuthenticated) {
      router.push('/login');
    }
    
    if (roles && user && !roles.includes(user.role)) {
      router.push('/unauthorized');
    }
  }, [isAuthenticated, isLoading, user, roles, router]);

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (!isAuthenticated) {
    return null;
  }

  if (roles && user && !roles.includes(user.role)) {
    return null;
  }

  return <>{children}</>;
}
```

---

## Navigation Helpers

```typescript
// lib/navigation.ts
import { useRouter, usePathname } from 'next/navigation';

export function useLocaleRouter() {
  const router = useRouter();
  const pathname = usePathname();
  const currentLocale = pathname.split('/')[1];

  const push = (path: string) => {
    router.push(`/${currentLocale}${path}`);
  };

  const switchLocale = (newLocale: string) => {
    const pathWithoutLocale = pathname.replace(`/${currentLocale}`, '');
    router.push(`/${newLocale}${pathWithoutLocale}`);
  };

  return { push, switchLocale, currentLocale };
}
```
