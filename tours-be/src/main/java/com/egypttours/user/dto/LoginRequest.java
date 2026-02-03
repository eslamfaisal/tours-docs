package com.egypttours.user.dto;

import com.egypttours.common.constants.Messages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = Messages.Validation.EMAIL_REQUIRED)
    @Email(message = Messages.Validation.EMAIL_INVALID)
    private String email;

    @NotBlank(message = Messages.Validation.PASSWORD_REQUIRED)
    private String password;
}
