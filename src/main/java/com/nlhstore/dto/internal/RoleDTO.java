package com.nlhstore.dto.internal;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDTO extends BaseDTO{

    String code;

    String name;

    String description;

    Set<UserDTO> users = new HashSet<>();

    Set<PermissionDTO> permissions = new HashSet<>();
}
