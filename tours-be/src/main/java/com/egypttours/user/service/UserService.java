package com.egypttours.user.service;

import com.egypttours.common.constants.Messages;
import com.egypttours.common.exception.ResourceAlreadyExistsException;
import com.egypttours.common.exception.ResourceNotFoundException;
import com.egypttours.user.domain.Role;
import com.egypttours.user.domain.User;
import com.egypttours.user.dto.CreateUserRequest;
import com.egypttours.user.dto.UpdateUserRequest;
import com.egypttours.user.dto.UserResponse;
import com.egypttours.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        validateRoleCreation(request.getRole());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException(Messages.Error.EMAIL_EXISTS);
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .active(true)
                .build();

        return mapToResponse(userRepository.save(user));
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Messages.Entity.USER, "ID", id));
        return mapToResponse(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Messages.Entity.USER, "ID", id));

        if (request.getRole() != null) {
            validateRoleCreation(request.getRole());
            user.setRole(request.getRole());
        }

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new ResourceAlreadyExistsException(Messages.Error.EMAIL_EXISTS);
            }
            user.setEmail(request.getEmail());
        }

        if (request.getFirstName() != null)
            user.setFirstName(request.getFirstName());
        if (request.getLastName() != null)
            user.setLastName(request.getLastName());
        if (request.getPassword() != null)
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        if (request.getActive() != null)
            user.setActive(request.getActive());

        return mapToResponse(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(Messages.Entity.USER, "ID", id);
        }
        userRepository.deleteById(id);
    }

    private void validateRoleCreation(Role targetRole) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated())
            return;

        boolean isSuper = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_" + Role.SUPER.name()));
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_" + Role.ADMIN.name()));

        if (isSuper) {
            // Super Admin can create any role (ADMIN, CUSTOMER, etc.)
            return;
        }

        if (isAdmin) {
            // Admin cannot create SUPER or ADMIN
            if (targetRole == Role.SUPER || targetRole == Role.ADMIN) {
                throw new AccessDeniedException(Messages.Error.ADMIN_CREATION_RESTRICTED);
            }
        }
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
