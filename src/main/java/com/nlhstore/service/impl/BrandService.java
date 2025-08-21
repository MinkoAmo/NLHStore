package com.nlhstore.service.impl;

import com.nlhstore.dto.internal.BrandDTO;
import com.nlhstore.dto.request.BrandCreateRequest;
import com.nlhstore.dto.request.BrandUpdateRequest;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.response.BrandResponse;
import com.nlhstore.entity.BrandEntity;
import com.nlhstore.exception.AppException;
import com.nlhstore.exception.ErrorCode;
import com.nlhstore.mapper.BrandMapper;
import com.nlhstore.repository.BrandRepository;
import com.nlhstore.service.IBrandService;
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
public class BrandService implements IBrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    BrandMapper brandMapper;

    @Override
    public Page<BrandResponse> findAll(Pageable pageable) {
        Page<BrandEntity> brandEntities = brandRepository.findAll(pageable);
        List<BrandResponse> dtoList = brandEntities.stream().map(x -> brandMapper.toDTO(x)).toList();
        return new PageImpl<>(dtoList, pageable, brandEntities.getTotalElements());
    }

    @Override
    public BrandResponse createBrand(BrandCreateRequest request) {
        BrandDTO brandDTO = brandMapper.toDTO(request);

        boolean isCodeExist = brandRepository.existsByCode(brandDTO.getCode());
        boolean isNameExist = brandRepository.existsByName(brandDTO.getName());
        if (isCodeExist) {
            throw new AppException(ErrorCode.CODE_BRAND_EXISTED);
        }
        if (isNameExist) {
            throw new AppException(ErrorCode.NAME_BRAND_EXISTED);
        }

        BrandEntity brandEntity = brandRepository.save(brandMapper.toEntity(brandDTO));
        return brandMapper.toDTO(brandEntity);
    }

    @Override
    public BrandResponse updateBrand(BrandUpdateRequest request) {
        BrandDTO brandDTO = brandMapper.toDTO(request);
        boolean isCodeExist = brandRepository.existsByCodeAndIdNot(brandDTO.getCode(), brandDTO.getId());
        boolean isNameExist = brandRepository.existsByNameAndIdNot(brandDTO.getName(), brandDTO.getId());

        if (isCodeExist) {
            throw new AppException(ErrorCode.CODE_BRAND_EXISTED);
        }
        if (isNameExist) {
            throw new AppException(ErrorCode.NAME_BRAND_EXISTED);
        }

        BrandEntity brandEntity = brandRepository.findById(brandDTO.getId()).orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));
        brandMapper.update(brandEntity, brandDTO);
        brandRepository.save(brandEntity);
        return brandMapper.toDTO(brandEntity);
    }

    @Override
    public void deleteBrand(DeleteRequest<BrandEntity> request) {
        for (Long id : request.getIds()) {
            if (brandRepository.existsById(id)) {
                brandRepository.deleteById(id);
            } else throw new AppException(ErrorCode.BRAND_NOT_EXISTED);
        }
    }
}
