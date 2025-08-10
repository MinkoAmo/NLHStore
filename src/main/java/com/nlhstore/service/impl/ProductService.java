package com.nlhstore.service.impl;

import com.nlhstore.dto.internal.ProductDTO;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.request.ProductCreateRequest;
import com.nlhstore.dto.request.ProductUpdateRequest;
import com.nlhstore.dto.response.ProductResponse;
import com.nlhstore.entity.ProductEntity;
import com.nlhstore.exception.AppException;
import com.nlhstore.exception.ErrorCode;
import com.nlhstore.mapper.ProductMapper;
import com.nlhstore.repository.ProductRepository;
import com.nlhstore.service.IProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductService implements IProductService {
    
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    ProductMapper productMapper;
    
    @Override
    public Page<ProductResponse> findAll(Pageable pageable) {
        Page<ProductEntity> productEntities = productRepository.findAll(pageable);
        List<ProductResponse> dtoList = productEntities.stream().map(x -> productMapper.toDTO(x)).toList();
        return new PageImpl<>(dtoList, pageable, productEntities.getTotalElements());
    }

    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {
        ProductDTO productDTO = productMapper.toDTO(request);
        boolean isCodeExist = productRepository.existsByCode(productDTO.getCode());
        boolean isNameExist = productRepository.existsByName(productDTO.getName());

        if (isCodeExist) {
            throw new AppException(ErrorCode.CODE_PRODUCT_EXISTED);
        }
        if (isNameExist) {
            throw new AppException(ErrorCode.NAME_PRODUCT_EXISTED);
        }
        ProductEntity productEntity = productMapper.toEntity(productDTO);
        productEntity = productRepository.save(productEntity);
        return productMapper.toDTO(productEntity);
    }

    @Override
    public ProductResponse updateProduct(ProductUpdateRequest request) {
        ProductDTO productDTO = productMapper.toDTO(request);
        boolean isCodeExist = productRepository.existsByCodeAndIdNot(productDTO.getCode(), productDTO.getId());
        boolean isNameExist = productRepository.existsByNameAndIdNot(productDTO.getName(), productDTO.getId());

        if (isCodeExist) {
            throw new AppException(ErrorCode.CODE_PRODUCT_EXISTED);
        }
        if (isNameExist) {
            throw new AppException(ErrorCode.NAME_PRODUCT_EXISTED);
        }

        ProductEntity productEntity = productRepository.findById(productDTO.getId()).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        productMapper.update(productEntity, productDTO);
        productRepository.save(productEntity);
        return productMapper.toDTO(productEntity);
    }

    @Override
    public void deleteProduct(DeleteRequest<ProductEntity> request) {
        for (Long id : request.getIds()) {
            productRepository.deleteById(id);
        }
    }
}
