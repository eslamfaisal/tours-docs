# Component Library

## Overview

A comprehensive set of reusable UI components built with accessibility and i18n in mind.

---

## Design Tokens

```css
/* styles/variables.css */
:root {
  /* Colors */
  --color-primary: #e65100;
  --color-primary-light: #ff833a;
  --color-primary-dark: #ac1900;
  --color-secondary: #ffc107;
  --color-success: #4caf50;
  --color-error: #f44336;
  --color-warning: #ff9800;
  
  /* Neutral */
  --color-gray-50: #fafafa;
  --color-gray-100: #f5f5f5;
  --color-gray-900: #212121;
  
  /* Typography */
  --font-sans: 'Inter', system-ui, sans-serif;
  --font-arabic: 'Noto Sans Arabic', sans-serif;
  
  /* Spacing */
  --space-xs: 0.25rem;
  --space-sm: 0.5rem;
  --space-md: 1rem;
  --space-lg: 1.5rem;
  --space-xl: 2rem;
  
  /* Radius */
  --radius-sm: 0.25rem;
  --radius-md: 0.5rem;
  --radius-lg: 1rem;
  --radius-full: 9999px;
  
  /* Shadows */
  --shadow-sm: 0 1px 2px rgba(0,0,0,0.05);
  --shadow-md: 0 4px 6px rgba(0,0,0,0.1);
  --shadow-lg: 0 10px 15px rgba(0,0,0,0.1);
}
```

---

## Component Catalog

### Button

```tsx
interface ButtonProps {
  variant?: 'primary' | 'secondary' | 'outline' | 'ghost';
  size?: 'sm' | 'md' | 'lg';
  disabled?: boolean;
  loading?: boolean;
  fullWidth?: boolean;
  as?: 'button' | 'link';
  href?: string;
  children: React.ReactNode;
  onClick?: () => void;
}

// Usage
<Button variant="primary" size="lg">Book Now</Button>
<Button variant="outline" loading>Processing...</Button>
<Button as="link" href="/trips">View Tours</Button>
```

### Card

```tsx
interface CardProps {
  children: React.ReactNode;
  variant?: 'elevated' | 'outlined' | 'filled';
  padding?: 'none' | 'sm' | 'md' | 'lg';
  hoverable?: boolean;
}

// Usage
<Card hoverable>
  <CardImage src="..." />
  <CardContent>...</CardContent>
  <CardFooter>...</CardFooter>
</Card>
```

### Input

```tsx
interface InputProps {
  type?: 'text' | 'email' | 'password' | 'tel' | 'number';
  label: string;
  placeholder?: string;
  error?: string;
  helperText?: string;
  required?: boolean;
  disabled?: boolean;
}

// Usage
<Input
  label="Email"
  type="email"
  error={errors.email}
  required
/>
```

### Modal

```tsx
interface ModalProps {
  open: boolean;
  onClose: () => void;
  title?: string;
  size?: 'sm' | 'md' | 'lg' | 'full';
  children: React.ReactNode;
}

// Usage
<Modal open={isOpen} onClose={handleClose} title="Login">
  <LoginForm />
</Modal>
```

### Rating

```tsx
interface RatingProps {
  value: number;
  max?: number;
  count?: number;
  readonly?: boolean;
  onChange?: (value: number) => void;
}

// Usage
<Rating value={4.5} count={128} readonly />
<Rating value={rating} onChange={setRating} />
```

### Price

```tsx
interface PriceProps {
  amount: number;
  currency?: string;
  locale?: string;
  originalAmount?: number; // For discounts
  size?: 'sm' | 'md' | 'lg';
}

// Usage
<Price amount={599} currency="USD" />
<Price amount={499} originalAmount={599} />
```

---

## Complex Components

### TripCard

```tsx
<TripCard
  trip={trip}
  variant="default"
  showWishlist
  onWishlistClick={handleWishlist}
/>
```

### DatePicker

```tsx
<DatePicker
  value={date}
  onChange={setDate}
  minDate={new Date()}
  availableDates={availableDates}
  locale={locale}
/>
```

### SearchBar

```tsx
<SearchBar
  placeholder={t('search.placeholder')}
  onSearch={handleSearch}
  suggestions={suggestions}
  loading={isLoading}
/>
```

### Pagination

```tsx
<Pagination
  currentPage={page}
  totalPages={totalPages}
  onPageChange={setPage}
  showFirstLast
/>
```

---

## Layout Components

### Container

```tsx
<Container size="lg" padding>
  {/* Max-width: 1200px, centered */}
</Container>
```

### Grid

```tsx
<Grid cols={{ sm: 1, md: 2, lg: 3 }} gap="lg">
  {trips.map(trip => <TripCard key={trip.id} trip={trip} />)}
</Grid>
```

### Stack

```tsx
<Stack direction="vertical" spacing="md" align="center">
  {/* Vertical stack with gap */}
</Stack>
```

---

## Accessibility

All components follow WCAG 2.1 AA guidelines:

| Feature | Implementation |
|---------|----------------|
| Keyboard nav | Tab, Enter, Escape, Arrow keys |
| ARIA labels | All interactive elements |
| Focus states | Visible focus rings |
| Color contrast | 4.5:1 minimum |
| Screen reader | Proper announcements |

```tsx
// Example: Accessible button
<button
  type="button"
  aria-label={loading ? 'Loading...' : 'Submit form'}
  aria-disabled={disabled}
  aria-busy={loading}
  disabled={disabled || loading}
>
  {loading ? <Spinner aria-hidden /> : null}
  {children}
</button>
```
