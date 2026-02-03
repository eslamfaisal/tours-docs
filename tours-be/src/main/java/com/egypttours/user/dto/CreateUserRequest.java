package com.egypttours.user.dto;

import com.egypttours.common.constants.Messages;
import com.egypttours.user.domain.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = Messages.Validation.FIRST_NAME_REQUIRED)
    @Schema(example = "John")
    private String firstName;

    @NotBlank(message = Messages.Validation.LAST_NAME_REQUIRED)
    @Schema(example = "Doe")
    private String lastName;

    @NotBlank(message = Messages.Validation.EMAIL_REQUIRED)
    @Email(message = Messages.Validation.EMAIL_INVALID)
    @Schema(example = "john.doe@example.com")
    private String email;

    @NotBlank(message = Messages.Validation.PASSWORD_REQUIRED)
    @Size(min = 6, message = Messages.Validation.PASSWORD_TOO_SHORT)
    @Schema(example = "securePass123")
    private String password;

    @NotNull(message = Messages.Validation.ROLE_REQUIRED)
    @Schema(example = "ADMIN")
    private Role role;
}
