package com.nlhstore.dto.response;

import com.nlhstore.entity.BrandEntity;
import com.nlhstore.entity.CategoryEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    Long id;

    String code;

    String name;

    String description;

    Double price;

    String categoryCode;

    String brandCode;
}
