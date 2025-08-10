package com.nlhstore.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brand")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    String code;

    @Column(nullable = false)
    String name;

    @Column
    String logoUrl;

    @Column
    String description;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<ProductEntity> products  = new HashSet<ProductEntity>();
}
