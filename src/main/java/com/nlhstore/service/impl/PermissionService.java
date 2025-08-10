package com.nlhstore.service.impl;

import com.nlhstore.dto.internal.PermissionDTO;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.request.PermissionCreateRequest;
import com.nlhstore.dto.request.PermissionUpdateRequest;
import com.nlhstore.dto.response.PermissionResponse;
import com.nlhstore.entity.BrandEntity;
import com.nlhstore.entity.PermissionEntity;
import com.nlhstore.exception.AppException;
import com.nlhstore.exception.ErrorCode;
import com.nlhstore.mapper.PermissionMapper;
import com.nlhstore.repository.PermissionRepository;
import com.nlhstore.service.IPermissionService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionService implements IPermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse createPermission(PermissionCreateRequest request) {
        PermissionDTO permissionDTO = permissionMapper.toDTO(request);

        boolean isCodeExist = permissionRepository.existsByCode(permissionDTO.getCode());
        if (isCodeExist) {
            throw new AppException(ErrorCode.CODE_PERMISSION_EXISTED);
        }
        PermissionEntity permissionEntity = permissionRepository.save(permissionMapper.toEntity(permissionDTO));
        return permissionMapper.toDTO(permissionEntity);
    }

    @Override
    public List<PermissionResponse> findAll() {
        List<PermissionEntity> permissionEntities = permissionRepository.findAll();
        return permissionEntities.stream().map(x -> permissionMapper.toDTO(x)).toList();
    }

    @Override
    public PermissionResponse updatePermission(PermissionUpdateRequest request) {
        PermissionDTO permissionDTO = permissionMapper.toDTO(request);
        boolean isCodeExist = permissionRepository.existsByCodeAndIdNot(permissionDTO.getCode(), permissionDTO.getId());

        if (isCodeExist) {
            throw new AppException(ErrorCode.CODE_PERMISSION_EXISTED);
        }

        PermissionEntity permissionEntity = permissionRepository.findById(permissionDTO.getId()).orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_EXISTED));
        permissionMapper.update(permissionEntity, permissionDTO);
        return permissionMapper.toDTO(permissionRepository.save(permissionEntity));
    }

    @Override
    public void deletePermission(DeleteRequest<PermissionEntity> request){
        for (Long id : request.getIds()) {
            permissionRepository.deleteById(id);
        }
    }
}
