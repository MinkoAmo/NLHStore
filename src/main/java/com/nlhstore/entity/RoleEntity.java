package com.nlhstore.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    String code;

    @Column(unique = true, nullable = false)
    String name;

    @Column
    String description;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "roles")
    Set<UserEntity> users  = new HashSet<UserEntity>();

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    Set<PermissionEntity> permissions  = new HashSet<PermissionEntity>();
}
