package com.nlhstore.api.admin;

import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.request.PermissionCreateRequest;
import com.nlhstore.dto.response.ApiResponse;
import com.nlhstore.dto.response.PermissionResponse;
import com.nlhstore.entity.PermissionEntity;
import com.nlhstore.service.impl.PermissionService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/permission")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionAPI {

    @Autowired
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionCreateRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .code(200)
                .result(permissionService.createPermission(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> findAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(200)
                .result(permissionService.findAll())
                .build();
    }

    @DeleteMapping()
    public ApiResponse<Void> deleteUser(@RequestBody DeleteRequest<PermissionEntity> request) {
        permissionService.deletePermission(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .build();
    }
}
