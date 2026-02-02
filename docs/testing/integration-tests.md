# Integration Tests

## Backend API Tests

```java
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
class TripControllerIT {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired TestRestTemplate restTemplate;

    @Test
    void getTrips_returnsPagedResponse() {
        ResponseEntity<PagedResponse<TripListDTO>> response = 
            restTemplate.exchange(
                "/api/v1/trips",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
            );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getData());
    }
}
```

## Database Tests

```java
@DataJpaTest
class TripRepositoryIT {

    @Autowired TripRepository tripRepository;

    @Test
    void findBySlug_returnsTrip() {
        Trip trip = Trip.builder().slug("test-trip").build();
        tripRepository.save(trip);

        Optional<Trip> found = tripRepository.findBySlug("test-trip");
        
        assertTrue(found.isPresent());
    }
}
```
