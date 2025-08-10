package com.nlhstore.mapper;

import com.nlhstore.dto.internal.ProductDTO;
import com.nlhstore.dto.request.ProductCreateRequest;
import com.nlhstore.dto.request.ProductUpdateRequest;
import com.nlhstore.dto.response.ProductResponse;
import com.nlhstore.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    ProductEntity toEntity(ProductDTO productDTO);

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    void update(@MappingTarget ProductEntity productEntity, ProductDTO requestDTO);

    @Mapping(source = "category.code", target = "categoryCode")
    @Mapping(source = "brand.code", target = "brandCode")
    ProductResponse toDTO(ProductEntity productEntity);

    ProductDTO toDTO(ProductCreateRequest requestDTO);

    ProductDTO toDTO(ProductUpdateRequest requestDTO);
    
}
