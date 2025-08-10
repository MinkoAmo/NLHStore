package com.nlhstore.mapper;

import com.nlhstore.dto.internal.PermissionDTO;
import com.nlhstore.dto.request.PermissionCreateRequest;
import com.nlhstore.dto.request.PermissionUpdateRequest;
import com.nlhstore.dto.response.PermissionResponse;
import com.nlhstore.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    PermissionEntity toEntity(PermissionDTO permissionDTO);

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    void update(@MappingTarget PermissionEntity permissionEntity, PermissionDTO requestDTO);

    PermissionResponse toDTO(PermissionEntity permissionEntity);

    PermissionDTO toDTO(PermissionCreateRequest request);

    PermissionDTO toDTO(PermissionUpdateRequest requestDTO);
}
