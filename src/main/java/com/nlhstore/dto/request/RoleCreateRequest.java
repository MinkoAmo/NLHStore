package com.nlhstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleCreateRequest {

    @NotBlank(message = "CODE_PERMISSION_BLANK")
    @Size(min = 1, max = 50, message = "CODE_PERMISSION_INVALID")
    String code;

    @NotBlank(message = "NAME_BRAND_BLANK")
    @Size(min = 1, max = 50, message = "NAME_BRAND_INVALID")
    String name;

    String description;

    Set<String> permissions;
}
