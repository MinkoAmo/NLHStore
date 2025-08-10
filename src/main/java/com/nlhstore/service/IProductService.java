package com.nlhstore.service;

import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.request.ProductCreateRequest;
import com.nlhstore.dto.request.ProductUpdateRequest;
import com.nlhstore.dto.response.ProductResponse;
import com.nlhstore.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    Page<ProductResponse> findAll(Pageable pageable);

    ProductResponse createProduct(ProductCreateRequest request);

    ProductResponse updateProduct(ProductUpdateRequest request);

    void deleteProduct(DeleteRequest<ProductEntity> request);
}
