package com.nlhstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {

    @NotBlank(message = "CODE_PRODUCT_BLANK")
    @Size(min = 1, max = 50, message = "CODE_PRODUCT_INVALID")
    String code;

    @NotBlank(message = "NAME_PRODUCT_BLANK")
    @Size(min = 1, max = 50, message = "NAME_PRODUCT_INVALID")
    String name;

    String description;

    @NotBlank(message = "PRICE_PRODUCT_BLANK")
    Double price;
}
