package com.nlhstore.api.admin;

import com.nlhstore.dto.request.*;
import com.nlhstore.dto.response.ApiResponse;
import com.nlhstore.dto.response.CategoryResponse;
import com.nlhstore.dto.response.PermissionResponse;
import com.nlhstore.dto.response.RoleResponse;
import com.nlhstore.entity.CategoryEntity;
import com.nlhstore.entity.PermissionEntity;
import com.nlhstore.entity.RoleEntity;
import com.nlhstore.service.impl.CategoryService;
import com.nlhstore.service.impl.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleAPI {

    @Autowired
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleCreateRequest request){
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .result(roleService.createRole(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> findAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .code(200)
                .result(roleService.findAll())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<RoleResponse> updatePermission(@RequestBody @Valid RoleUpdateRequest request) {
        RoleResponse response = roleService.updateRole(request);
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .result(response)
                .build();
    }

    @DeleteMapping()
    public ApiResponse<Void> deleteRole(@RequestBody DeleteRequest<RoleEntity> request) {
        roleService.deleteRole(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .build();
    }
}
