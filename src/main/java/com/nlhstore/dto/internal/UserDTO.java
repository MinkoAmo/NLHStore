package com.nlhstore.dto.internal;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO extends BaseDTO{

    String username;

    String password;

    String email;

    String phoneNumber;

    Boolean enabled;

    Boolean locked;

    Integer loginAttempts;

    LocalDateTime lastLoginAt;

    Set<RoleDTO> roles;
}
