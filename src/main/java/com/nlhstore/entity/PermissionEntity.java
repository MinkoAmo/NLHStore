package com.nlhstore.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permission")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    String code;

    @Column
    String description;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "permissions")
    Set<RoleEntity> roles  = new HashSet<RoleEntity>();
}
