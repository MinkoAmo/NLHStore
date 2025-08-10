package com.nlhstore.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Long id;

    String username;

    String email;

    String phoneNumber;

    Integer loginAttempts;

    LocalDateTime lastLoginAt;

    Set<String> roles;
}
