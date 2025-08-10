package com.nlhstore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

    @NotBlank(message = "USERNAME_BLANK")
    @Size(min = 4, max = 20, message = "USERNAME_INVALID")
    String username;

    @NotBlank(message = "PASSWORD_BLANK")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "PASSWORD_INVALID")
    String password;

    @NotBlank
    @Email(message = "EMAIL_INVALID")
    String email;

    String phoneNumber;
}
