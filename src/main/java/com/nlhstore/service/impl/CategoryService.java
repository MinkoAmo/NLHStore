package com.nlhstore.service.impl;

import com.nlhstore.dto.internal.CategoryDTO;
import com.nlhstore.dto.request.CategoryCreateRequest;
import com.nlhstore.dto.request.CategoryUpdateRequest;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.response.CategoryResponse;
import com.nlhstore.entity.CategoryEntity;
import com.nlhstore.exception.AppException;
import com.nlhstore.exception.ErrorCode;
import com.nlhstore.mapper.CategoryMapper;
import com.nlhstore.repository.CategoryRepository;
import com.nlhstore.service.ICategoryService;
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
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        CategoryDTO categoryDTO = categoryMapper.toDTO(request);
        boolean isCodeExist = categoryRepository.existsByCode(categoryDTO.getCode());
        boolean isNameExist = categoryRepository.existsByName(categoryDTO.getName());

        if (isCodeExist) {
            throw new AppException(ErrorCode.CODE_CATEGORY_EXISTED);
        }
        if (isNameExist) {
            throw new AppException(ErrorCode.NAME_CATEGORY_EXISTED);
        }
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDTO);
        categoryEntity = categoryRepository.save(categoryEntity);
        return categoryMapper.toDTO(categoryEntity);
    }

    @Override
    public CategoryResponse updateCategory(CategoryUpdateRequest request) {
        CategoryDTO categoryDTO = categoryMapper.toDTO(request);
        boolean isCodeExist = categoryRepository.existsByCodeAndIdNot(categoryDTO.getCode(), categoryDTO.getId());
        boolean isNameExist = categoryRepository.existsByNameAndIdNot(categoryDTO.getName(), categoryDTO.getId());

        if (isCodeExist) {
            throw new AppException(ErrorCode.CODE_CATEGORY_EXISTED);
        }
        if (isNameExist) {
            throw new AppException(ErrorCode.NAME_CATEGORY_EXISTED);
        }

        CategoryEntity categoryEntity = categoryRepository.findById(categoryDTO.getId()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        categoryMapper.update(categoryEntity, categoryDTO);
        categoryRepository.save(categoryEntity);
        return categoryMapper.toDTO(categoryEntity);
    }

    @Override
    public void deleteCategory(DeleteRequest<CategoryEntity> request) {
        for (Long id : request.getIds()) {
            if (categoryRepository.existsById(id)) {
                categoryRepository.deleteById(id);
            } else throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
    }

    @Override
    public Page<CategoryResponse> findAll(Pageable pageable) {
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
        List<CategoryResponse> dtoList = categoryEntities.stream().map(x -> categoryMapper.toDTO(x)).toList();
        return new PageImpl<>(dtoList, pageable, categoryEntities.getTotalElements());
    }

    @Override
    public List<CategoryResponse> findByParentCategory() {
        return categoryRepository.findByParentCategoryIsNull().stream().map(x -> categoryMapper.toDTO(x)).toList();
    }

    @Override
    public List<CategoryResponse> findByHasProducts() {
        return categoryRepository.findByProductsIsNotNull().stream().map(x -> categoryMapper.toDTO(x)).toList();
    }

    @Override
    public CategoryResponse findById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        return categoryMapper.toDTO(categoryEntity);
    }
}
