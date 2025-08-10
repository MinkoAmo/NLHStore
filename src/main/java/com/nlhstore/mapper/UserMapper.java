package com.nlhstore.mapper;

import com.nlhstore.dto.internal.UserDTO;
import com.nlhstore.dto.request.UserCreateRequest;
import com.nlhstore.dto.request.UserUpdateRequest;
import com.nlhstore.dto.response.UserResponse;
import com.nlhstore.entity.RoleEntity;
import com.nlhstore.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    UserEntity toEntity(UserDTO User);

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    void update(@MappingTarget UserEntity UserEntity, UserDTO request);

    UserResponse toDTO(UserEntity UserEntity);

    UserDTO toDTO(UserCreateRequest request);

    UserDTO toDTO(UserUpdateRequest request);

    default Set<String> mapRoles(Set<RoleEntity> roles) {
        if (roles == null) return Set.of();
        return roles.stream()
                .map(r -> r.getCode()).collect(Collectors.toSet());
    }
}
