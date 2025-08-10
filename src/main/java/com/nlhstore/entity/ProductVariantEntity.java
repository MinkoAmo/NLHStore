package com.nlhstore.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_variant", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "size", "color"})})
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantEntity extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column
    String imageUrl;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String color;

    @Column
    Integer quantity;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    Boolean active;
}