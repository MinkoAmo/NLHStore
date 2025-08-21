package com.nlhstore.service;

import com.nlhstore.dto.request.*;
import com.nlhstore.dto.response.PermissionResponse;
import com.nlhstore.dto.response.RoleResponse;
import com.nlhstore.entity.PermissionEntity;
import com.nlhstore.entity.RoleEntity;

import java.util.List;

public interface IRoleService {

    List<RoleResponse> findAll();

    RoleResponse createRole(RoleCreateRequest request);

    RoleResponse updateRole(RoleUpdateRequest request);

    void deleteRole(DeleteRequest<RoleEntity> request);
}
