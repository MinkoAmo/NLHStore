package com.nlhstore.api;

import com.nlhstore.dto.request.*;
import com.nlhstore.dto.response.ApiResponse;
import com.nlhstore.dto.response.RoleResponse;
import com.nlhstore.entity.RoleEntity;
import com.nlhstore.service.impl.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRoleById(@PathVariable Long id) {
        DeleteRequest<RoleEntity> request = new DeleteRequest<>();
        request.setIds(List.of(id));
        roleService.deleteRole(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .build();
    }

    @DeleteMapping()
    public ApiResponse<Void> deleteRoles(@RequestBody DeleteRequest<RoleEntity> request) {
        roleService.deleteRole(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .build();
    }
}
