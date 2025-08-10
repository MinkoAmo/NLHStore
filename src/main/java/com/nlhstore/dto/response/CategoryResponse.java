package com.nlhstore.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

    Long id;

    String code;

    String name;

    String description;

    String parentCode;

    List<CategoryResponse> subCategories;

    List<ProductResponse> products;
}
