package com.egypttours.user.dto;

import com.egypttours.common.constants.Messages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = Messages.Validation.FIRST_NAME_REQUIRED)
    private String firstName;

    @NotBlank(message = Messages.Validation.LAST_NAME_REQUIRED)
    private String lastName;

    @NotBlank(message = Messages.Validation.EMAIL_REQUIRED)
    @Email(message = Messages.Validation.EMAIL_INVALID)
    private String email;

    @NotBlank(message = Messages.Validation.PASSWORD_REQUIRED)
    @Size(min = 8, message = Messages.Validation.PASSWORD_TOO_SHORT)
    private String password;
}
