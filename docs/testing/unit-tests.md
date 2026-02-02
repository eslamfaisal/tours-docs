# Unit Testing

## Frontend

### Component Testing

```javascript
// TripCard.test.jsx
import { render, screen } from '@testing-library/react';
import { TripCard } from './TripCard';

describe('TripCard', () => {
  const mockTrip = {
    slug: 'pyramids-tour',
    title: 'Pyramids Day Tour',
    price: 89,
    rating: 4.8,
  };

  it('renders trip title', () => {
    render(<TripCard trip={mockTrip} />);
    expect(screen.getByText('Pyramids Day Tour')).toBeInTheDocument();
  });

  it('displays price correctly', () => {
    render(<TripCard trip={mockTrip} />);
    expect(screen.getByText('$89')).toBeInTheDocument();
  });
});
```

### Store Testing

```javascript
// stores/bookingStore.test.js
import { useBookingStore } from './bookingStore';

describe('bookingStore', () => {
  it('sets travel date', () => {
    const { setDate } = useBookingStore.getState();
    setDate('2026-03-15');
    expect(useBookingStore.getState().date).toBe('2026-03-15');
  });
});
```

---

## Backend

### Service Testing

```java
@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    @Mock TripRepository tripRepository;
    @Mock TripMapper tripMapper;
    @InjectMocks TripServiceImpl tripService;

    @Test
    void findBySlug_returnsTrip() {
        Trip trip = new Trip();
        trip.setSlug("pyramids-tour");
        
        when(tripRepository.findBySlugAndStatus(eq("pyramids-tour"), any()))
            .thenReturn(Optional.of(trip));
        when(tripMapper.toDTO(trip)).thenReturn(new TripDTO());

        TripDTO result = tripService.findBySlug("pyramids-tour");
        
        assertNotNull(result);
        verify(tripRepository).findBySlugAndStatus("pyramids-tour", TripStatus.PUBLISHED);
    }
}
```
