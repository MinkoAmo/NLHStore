package com.nlhstore.mapper;

import com.nlhstore.dto.internal.RoleDTO;
import com.nlhstore.dto.request.RoleCreateRequest;
import com.nlhstore.dto.request.RoleUpdateRequest;
import com.nlhstore.dto.response.RoleResponse;
import com.nlhstore.entity.PermissionEntity;
import com.nlhstore.entity.RoleEntity;
import com.nlhstore.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    RoleEntity toEntity(RoleDTO RoleDTO);

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    void update(@MappingTarget RoleEntity RoleEntity, RoleDTO requestDTO);

    RoleResponse toDTO(RoleEntity RoleEntity);

    @Mapping(target = "permissions", ignore = true)
    RoleDTO toDTO(RoleCreateRequest request);

    RoleDTO toDTO(RoleUpdateRequest requestDTO);

    default Set<String> mapUsers(Set<UserEntity> users) {
        if (users == null) return Set.of();
        return users.stream()
                .map(UserEntity::getUsername).collect(Collectors.toSet());
    }

    default Set<String> mapPermissions(Set<PermissionEntity> permissions) {
        if (permissions == null) return Set.of();
        return permissions.stream()
                .map(PermissionEntity::getCode).collect(Collectors.toSet());
    }
}
