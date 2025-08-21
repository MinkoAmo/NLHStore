package com.nlhstore.service;

import com.nlhstore.dto.request.BrandUpdateRequest;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.request.PermissionCreateRequest;
import com.nlhstore.dto.request.PermissionUpdateRequest;
import com.nlhstore.dto.response.BrandResponse;
import com.nlhstore.dto.response.PermissionResponse;
import com.nlhstore.entity.PermissionEntity;

import java.util.List;

public interface IPermissionService {

    List<PermissionResponse> findAll();

    PermissionResponse findById(Long id);

    PermissionResponse createPermission(PermissionCreateRequest request);

    PermissionResponse updatePermission(PermissionUpdateRequest request);

    void deletePermission(DeleteRequest<PermissionEntity> request);
}
