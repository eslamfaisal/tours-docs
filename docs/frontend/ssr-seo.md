# SSR & SEO

## Overview

The platform uses Server-Side Rendering (SSR) and Static Site Generation (SSG) for optimal SEO performance.

---

## Rendering Strategy

| Page Type | Strategy | Reason |
|-----------|----------|--------|
| Home | SSG + ISR | High traffic, semi-dynamic |
| Trip Listings | SSG + ISR | SEO critical, periodic updates |
| Trip Details | SSG + ISR | Individual SEO, content changes |
| Blog | SSG | Static content |
| Booking | SSR | Dynamic, auth required |
| Account | SSR | Personalized, auth required |
| Admin | SSR | Dynamic, secured |

---

## Implementation

### Static Generation (SSG)

```tsx
// app/[locale]/trips/page.tsx
export async function generateStaticParams() {
  const locales = ['en', 'es', 'ar'];
  return locales.map((locale) => ({ locale }));
}

export const revalidate = 3600; // ISR: regenerate every hour

export default async function TripsPage({ params }) {
  const trips = await getTrips(params.locale);
  return <TripsList trips={trips} />;
}
```

### Server-Side Rendering (SSR)

```tsx
// app/[locale]/booking/page.tsx
import { cookies } from 'next/headers';
import { redirect } from 'next/navigation';

export const dynamic = 'force-dynamic';

export default async function BookingPage({ params }) {
  const token = cookies().get('token');
  if (!token) {
    redirect(`/${params.locale}/login?redirect=/booking`);
  }
  
  const bookingData = await getBookingSession(token.value);
  return <BookingWizard data={bookingData} />;
}
```

---

## SEO Implementation

### Meta Tags

```tsx
// app/[locale]/trips/[slug]/page.tsx
import type { Metadata } from 'next';

export async function generateMetadata({ params }): Promise<Metadata> {
  const trip = await getTrip(params.slug, params.locale);
  
  return {
    title: `${trip.title} | Egypt Tours`,
    description: trip.shortDescription,
    keywords: [trip.destination, 'Egypt tour', trip.type],
    openGraph: {
      title: trip.title,
      description: trip.shortDescription,
      images: [trip.thumbnail],
      type: 'website',
      locale: params.locale,
    },
    twitter: {
      card: 'summary_large_image',
      title: trip.title,
      description: trip.shortDescription,
      images: [trip.thumbnail],
    },
    alternates: {
      canonical: `https://egypttours.com/${params.locale}/trips/${params.slug}`,
      languages: {
        en: `https://egypttours.com/en/trips/${params.slug}`,
        es: `https://egypttours.com/es/trips/${params.slug}`,
        ar: `https://egypttours.com/ar/trips/${params.slug}`,
      },
    },
  };
}
```

### Structured Data (JSON-LD)

```tsx
// components/seo/TripSchema.tsx
export function TripSchema({ trip }) {
  const schema = {
    '@context': 'https://schema.org',
    '@type': 'TouristTrip',
    name: trip.title,
    description: trip.description,
    image: trip.images,
    touristType: 'Adventurer',
    itinerary: {
      '@type': 'ItemList',
      itemListElement: trip.itinerary.map((day, index) => ({
        '@type': 'ListItem',
        position: index + 1,
        item: {
          '@type': 'TouristAttraction',
          name: day.title,
          description: day.description,
        },
      })),
    },
    offers: {
      '@type': 'Offer',
      price: trip.price,
      priceCurrency: 'USD',
      availability: 'https://schema.org/InStock',
    },
    aggregateRating: {
      '@type': 'AggregateRating',
      ratingValue: trip.rating,
      reviewCount: trip.reviewCount,
    },
  };

  return (
    <script
      type="application/ld+json"
      dangerouslySetInnerHTML={{ __html: JSON.stringify(schema) }}
    />
  );
}
```

---

## Sitemap

```typescript
// app/sitemap.ts
import { MetadataRoute } from 'next';

export default async function sitemap(): Promise<MetadataRoute.Sitemap> {
  const baseUrl = 'https://egypttours.com';
  const locales = ['en', 'es', 'ar'];
  
  // Get all trips
  const trips = await getAllTrips();
  
  // Get all blog posts
  const posts = await getAllBlogPosts();
  
  const staticPages = ['', '/trips', '/destinations', '/blog', '/contact', '/about'];
  
  const staticUrls = locales.flatMap((locale) =>
    staticPages.map((page) => ({
      url: `${baseUrl}/${locale}${page}`,
      lastModified: new Date(),
      changeFrequency: 'weekly' as const,
      priority: page === '' ? 1 : 0.8,
    }))
  );
  
  const tripUrls = locales.flatMap((locale) =>
    trips.map((trip) => ({
      url: `${baseUrl}/${locale}/trips/${trip.slug}`,
      lastModified: new Date(trip.updatedAt),
      changeFrequency: 'weekly' as const,
      priority: 0.9,
    }))
  );
  
  const blogUrls = locales.flatMap((locale) =>
    posts.map((post) => ({
      url: `${baseUrl}/${locale}/blog/${post.slug}`,
      lastModified: new Date(post.updatedAt),
      changeFrequency: 'monthly' as const,
      priority: 0.6,
    }))
  );
  
  return [...staticUrls, ...tripUrls, ...blogUrls];
}
```

---

## Robots.txt

```typescript
// app/robots.ts
import { MetadataRoute } from 'next';

export default function robots(): MetadataRoute.Robots {
  return {
    rules: [
      {
        userAgent: '*',
        allow: '/',
        disallow: ['/admin/', '/account/', '/booking/', '/api/'],
      },
    ],
    sitemap: 'https://egypttours.com/sitemap.xml',
  };
}
```

---

## Performance Optimization

### Image Optimization

```tsx
import Image from 'next/image';

<Image
  src={trip.thumbnail}
  alt={trip.title}
  width={400}
  height={300}
  priority={isAboveFold}
  placeholder="blur"
  blurDataURL={trip.blurHash}
/>
```

### Loading States

```tsx
// app/[locale]/trips/loading.tsx
export default function Loading() {
  return (
    <div className="grid grid-cols-3 gap-4">
      {[...Array(9)].map((_, i) => (
        <TripCardSkeleton key={i} />
      ))}
    </div>
  );
}
```

---

## Core Web Vitals

| Metric | Target | Strategy |
|--------|--------|----------|
| LCP | < 2.5s | Priority images, SSG |
| FID | < 100ms | Code splitting, minimal JS |
| CLS | < 0.1 | Image dimensions, font loading |
