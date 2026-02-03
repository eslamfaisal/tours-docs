# Trip Management

## Features

- Create, edit, and delete trips
- Manage itineraries
- Set pricing rules
- Control availability
- Upload images
- Manage translations

---

## Trip CRUD

### Create Trip Flow

1. Enter basic info (title, slug, destination)
2. Set duration and type
3. Configure pricing
4. Add itinerary days
5. Upload images
6. Add translations
7. Publish or save as draft

### Trip Status

| Status | Description | Public |
|--------|-------------|--------|
| DRAFT | Work in progress | No |
| PUBLISHED | Live and bookable | Yes |
| ARCHIVED | Hidden from listings | No |

---

## Itinerary Management

| Field | Description |
|-------|-------------|
| Day Number | Sequential day (1, 2, 3...) |
| Title | Day title |
| Description | Detailed activities |
| Locations | List of stops/attractions |

---

## Pricing Rules

| Rule Type | Description |
|-----------|-------------|
| Default | Base price |
| Seasonal | Higher rates for peak season |
| Group | Discounts for larger groups |
| Early Bird | Advance booking discount |

---

## Translation Workflow

1. Create trip in default language (English)
2. Add translations for Spanish and Arabic
3. Mark translation status (Draft, Ready, Published)
4. Translations sync with trip status
