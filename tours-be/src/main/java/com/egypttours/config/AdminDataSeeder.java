package com.egypttours.config;

import com.egypttours.user.domain.Role;
import com.egypttours.user.domain.User;
import com.egypttours.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Seeds the database with an initial Admin user if none exists.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AdminDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail("super@egypttours.com")) {
            User superAdmin = User.builder()
                    .firstName("Super")
                    .lastName("Admin")
                    .email("super@egypttours.com")
                    .password(passwordEncoder.encode("super123"))
                    .role(Role.SUPER)
                    .active(true)
                    .build();
            superAdmin.setCreatedBy("system");
            userRepository.save(superAdmin);
            log.info("Verified Super Admin user created: super@egypttours.com / super123");
        } else {
            log.info("Super Admin user already exists.");
        }
    }
}
