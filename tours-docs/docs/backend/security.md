# Security

## Overview

Security implementation following OWASP best practices.

---

## Security Configuration

```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/trips/**").permitAll()
                .requestMatchers("/api/v1/destinations/**").permitAll()
                .requestMatchers("/api/v1/blog/**").permitAll()
                .requestMatchers("/api/v1/search/**").permitAll()
                .requestMatchers("/api/v1/chat/**").permitAll()
                // Authenticated endpoints
                .requestMatchers("/api/v1/bookings/**").authenticated()
                .requestMatchers("/api/v1/account/**").authenticated()
                .requestMatchers("/api/v1/tickets/**").authenticated()
                // Admin endpoints
                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(authEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
            )
            .build();
    }
}
```

---

## Security Measures

### Password Security

```java
@Configuration
public class SecurityBeans {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // Cost factor 12
    }
}
```

### Input Validation

```java
@PostMapping
public ResponseEntity<?> createBooking(
        @Valid @RequestBody CreateBookingRequest request) {
    // @Valid triggers validation
}

// Request DTO with validation
public class CreateBookingRequest {
    @NotNull
    private Long tripId;

    @NotNull
    @Future
    private LocalDate date;

    @Min(1) @Max(20)
    private int adults;

    @Min(0) @Max(10)
    private int children;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$")
    private String phone;
}
```

### SQL Injection Prevention

```java
// Spring Data JPA uses parameterized queries by default
@Query("SELECT t FROM Trip t WHERE t.destination.slug = :slug AND t.status = :status")
List<Trip> findByDestinationAndStatus(
    @Param("slug") String slug, 
    @Param("status") TripStatus status);
```

### XSS Prevention

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<XssFilter> xssFilter() {
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/api/*");
        return registration;
    }
}
```

---

## CORS Configuration

```java
@Bean
CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of(
        "https://egypttours.com",
        "https://admin.egypttours.com"
    ));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);
    configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/api/**", configuration);
    return source;
}
```

---

## Rate Limiting

```java
@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final RateLimiter rateLimiter;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {

        String clientIp = getClientIp(request);

        if (!rateLimiter.tryAcquire(clientIp)) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate limit exceeded");
            return;
        }

        chain.doFilter(request, response);
    }
}
```

### Rate Limits

| Endpoint Type | Limit |
|---------------|-------|
| Public API | 100 requests/minute |
| Auth endpoints | 10 requests/minute |
| Booking creation | 5 requests/minute |
| Admin API | 200 requests/minute |

---

## Security Headers

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .headers(headers -> headers
            .contentSecurityPolicy(csp -> 
                csp.policyDirectives("default-src 'self'"))
            .frameOptions(frame -> frame.deny())
            .xssProtection(xss -> xss.disable()) // CSP handles this
            .contentTypeOptions(Customizer.withDefaults())
            .httpStrictTransportSecurity(hsts -> hsts
                .maxAgeInSeconds(31536000)
                .includeSubDomains(true))
        )
        // ... rest of config
        .build();
}
```

---

## Audit Logging

```java
@Aspect
@Component
public class AuditAspect {

    private final AuditLogService auditLogService;

    @AfterReturning(
        pointcut = "@annotation(Audited)",
        returning = "result")
    public void logAudit(JoinPoint joinPoint, Object result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String action = getActionName(joinPoint);
        
        auditLogService.log(AuditLog.builder()
            .userId(getCurrentUserId())
            .action(action)
            .entityType(getEntityType(joinPoint))
            .entityId(getEntityId(result))
            .timestamp(Instant.now())
            .build());
    }
}

// Usage
@Audited
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteTrip(@PathVariable Long id) { ... }
```

---

## Data Protection

### Sensitive Data Handling

```java
// Never log sensitive data
@Slf4j
public class AuthService {
    public LoginResponse login(LoginRequest request) {
        log.info("Login attempt for user: {}", request.getEmail());
        // Never log: password, tokens, credit card info
    }
}

// Mask sensitive data in responses
@JsonIgnore
private String password;

@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private String creditCardNumber;
```

### Encryption at Rest

```yaml
# application.yml
spring:
  datasource:
    # Use encrypted connection
    url: jdbc:postgresql://db:5432/egypttours?ssl=true
```

---

## Access Control (RBAC)

We use **Role-Based Access Control** to secure endpoints. Authorities are checked using:

1.  **Security Filter Chain**: URL pattern matching in `SecurityConfig`.
2.  **Method Security**: `@PreAuthorize` annotations on controllers.

### Roles
- `ROLE_CUSTOMER`: Default role for registered users.
- `ROLE_ADMIN`: Superusers with full system access.

### Configuration

```java
@Configuration
@EnableMethodSecurity(securedEnabled = true) // Enables @PreAuthorize
public class SecurityConfig {
    // ...
    .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
    // ...
}
```

### Usage in Controllers

```java
@RestController
@RequestMapping("/api/v1/admin/trips")
@PreAuthorize("hasRole('ADMIN')") // Secures all methods in class
public class AdminTripController {
    
    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')") // Can also be per-method
    public ResponseEntity createTrip(...) { ... }
}
```

---

## Admin Data Seeder

To bootstrap the system, an **Admin Data Seeder** runs on startup to ensure a super-admin account exists.

- **Class**: `com.egypttours.config.AdminDataSeeder`
- **Trigger**: Implements `CommandLineRunner`
- **Logic**: Checks if `admin@egypttours.com` exists; if not, creates it with default credentials.

**Default Credentials:**
- **Email**: `admin@egypttours.com`
- **Password**: `admin123` (Change immediately in production!)

