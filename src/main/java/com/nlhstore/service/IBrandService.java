package com.nlhstore.service;

import com.nlhstore.dto.request.BrandCreateRequest;
import com.nlhstore.dto.request.BrandUpdateRequest;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.response.BrandResponse;
import com.nlhstore.entity.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandService {

    Page<BrandResponse> findAll(Pageable pageable);

    BrandResponse createBrand(BrandCreateRequest request);

    BrandResponse updateBrand(BrandUpdateRequest request);

    void deleteBrand(DeleteRequest<BrandEntity> request);
}
