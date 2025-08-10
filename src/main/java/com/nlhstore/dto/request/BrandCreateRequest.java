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
public class BrandCreateRequest {

    @NotBlank(message = "CODE_BRAND_BLANK")
    @Size(min = 1, max = 50, message = "CODE_BRAND_INVALID")
    String code;

    @NotBlank(message = "NAME_BRAND_BLANK")
    @Size(min = 1, max = 50, message = "NAME_BRAND_INVALID")
    String name;

    String logoUrl;

    String description;
}
