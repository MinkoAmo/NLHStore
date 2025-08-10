package com.nlhstore.mapper;

import com.nlhstore.dto.internal.BrandDTO;
import com.nlhstore.dto.request.BrandCreateRequest;
import com.nlhstore.dto.request.BrandUpdateRequest;
import com.nlhstore.dto.response.BrandResponse;
import com.nlhstore.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    BrandEntity toEntity(BrandDTO brandDTO);

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    void update(@MappingTarget BrandEntity brandEntity, BrandDTO requestDTO);

    BrandResponse toDTO(BrandEntity brandEntity);

    BrandDTO toDTO(BrandCreateRequest request);

    BrandDTO toDTO(BrandUpdateRequest requestDTO);
}
