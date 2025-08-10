package com.nlhstore.dto.request;

import com.nlhstore.exception.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCreateRequest {

    @NotBlank(message = "CODE_CATEGORY_BLANK")
    @Size(min = 1, max = 50, message = "CODE_CATEGORY_INVALID")
    String code;

    @NotBlank(message = "NAME_CATEGORY_BLANK")
    @Size(min = 1, max = 50, message = "NAME_CATEGORY_INVALID")
    String name;

    String description;
}
