package com.nlhstore.api;

import com.nlhstore.dto.request.CategoryCreateRequest;
import com.nlhstore.dto.request.CategoryUpdateRequest;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.response.ApiResponse;
import com.nlhstore.dto.response.CategoryResponse;
import com.nlhstore.entity.CategoryEntity;
import com.nlhstore.service.impl.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/category")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryAPI {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public ApiResponse<List<CategoryResponse>> findAll(@RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "0") int page) {
        Page<CategoryResponse> responsePage = categoryService.findAll(PageRequest.of(page, size));

        return ApiResponse.<List<CategoryResponse>>builder()
                .code(200)
                .result(responsePage.getContent())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> findById(@PathVariable Long id) {
        CategoryResponse response = categoryService.findById(id);

        return ApiResponse.<CategoryResponse>builder()
                .code(200)
                .result(response)
                .build();
    }

    @PostMapping()
    public ApiResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryCreateRequest request) {
        CategoryResponse response = categoryService.createCategory(request);
        return ApiResponse.<CategoryResponse>builder()
                .code(200)
                .result(response)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> updateCategory(@RequestBody @Valid CategoryUpdateRequest request) {
        CategoryResponse response = categoryService.updateCategory(request);
        return ApiResponse.<CategoryResponse>builder()
                .code(200)
                .result(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategoryById(@PathVariable Long id) {
        DeleteRequest<CategoryEntity> request = new DeleteRequest<>();
        request.setIds(List.of(id));
        categoryService.deleteCategory(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .build();
    }

    @DeleteMapping()
    public ApiResponse<Void> deleteCategories(@RequestBody DeleteRequest<CategoryEntity> request) {
        categoryService.deleteCategory(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .build();
    }
}
