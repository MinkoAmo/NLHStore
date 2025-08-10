package com.nlhstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionUpdateRequest {

    Long id;

    @NotBlank(message = "CODE_PERMISSION_BLANK")
    @Size(min = 1, max = 50, message = "CODE_PERMISSION_INVALID")
    String code;

    String description;
}
