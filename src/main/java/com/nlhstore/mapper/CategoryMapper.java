package com.nlhstore.mapper;

import com.nlhstore.dto.internal.CategoryDTO;
import com.nlhstore.dto.request.CategoryCreateRequest;
import com.nlhstore.dto.request.CategoryUpdateRequest;
import com.nlhstore.dto.response.CategoryResponse;
import com.nlhstore.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    CategoryEntity toEntity(CategoryDTO categoryDTO);

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    void update(@MappingTarget CategoryEntity categoryEntity, CategoryDTO requestDTO);

    @Mapping(source = "parentCategory.code", target = "parentCode")
    CategoryResponse toDTO(CategoryEntity categoryEntity);

    CategoryDTO toDTO(CategoryCreateRequest requestDTO);

    CategoryDTO toDTO(CategoryUpdateRequest requestDTO);

}
