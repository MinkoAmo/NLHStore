package com.nlhstore.dto.internal;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseDTO {

    Long id;

    String createdBy;

    LocalDateTime createdDate;

    String modifiedBy;

    LocalDateTime modifiedDate;
}
