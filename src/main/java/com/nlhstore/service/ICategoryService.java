package com.nlhstore.service;

import com.nlhstore.dto.request.CategoryCreateRequest;
import com.nlhstore.dto.request.CategoryUpdateRequest;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.response.CategoryResponse;
import com.nlhstore.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {

    Page<CategoryResponse> findAll(Pageable pageable);

    List<CategoryResponse> findByParentCategory();

    List<CategoryResponse> findByHasProducts();

    CategoryResponse findById(Long id);

    CategoryResponse createCategory(CategoryCreateRequest request);

    CategoryResponse updateCategory(CategoryUpdateRequest request);

    void deleteCategory(DeleteRequest<CategoryEntity> request);
}
