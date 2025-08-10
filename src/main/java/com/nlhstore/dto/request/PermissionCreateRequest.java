package com.nlhstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionCreateRequest {

    @NotBlank(message = "CODE_PERMISSION_BLANK")
    @Size(min = 1, max = 50, message = "CODE_PERMISSION_INVALID")
    String code;

    String description;
}
