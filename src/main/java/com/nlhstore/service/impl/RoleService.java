package com.nlhstore.service.impl;

import com.nlhstore.dto.internal.RoleDTO;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.request.RoleCreateRequest;
import com.nlhstore.dto.request.RoleUpdateRequest;
import com.nlhstore.dto.response.RoleResponse;
import com.nlhstore.entity.PermissionEntity;
import com.nlhstore.entity.RoleEntity;
import com.nlhstore.exception.AppException;
import com.nlhstore.exception.ErrorCode;
import com.nlhstore.mapper.RoleMapper;
import com.nlhstore.repository.PermissionRepository;
import com.nlhstore.repository.RoleRepository;
import com.nlhstore.service.IRoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleService implements IRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleMapper mapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public RoleResponse createRole(RoleCreateRequest request) {
        RoleDTO roleDTO = roleMapper.toDTO(request);


        boolean isCodeExist = roleRepository.existsByCode(roleDTO.getCode());
        boolean isNameExist = roleRepository.existsByName(roleDTO.getName());
        if (isCodeExist) {
            throw new AppException(ErrorCode.CODE_ROLE_EXISTED);
        }
        if (isNameExist) {
            throw new AppException(ErrorCode.NAME_ROLE_EXISTED);
        }

        RoleEntity roleEntity = mapper.toEntity(roleDTO);
        Set<PermissionEntity> permissions = permissionRepository.findAllByCodeIn(request.getPermissions().stream().toList()).stream().collect(Collectors.toSet());
        roleEntity.setPermissions(permissions);

        return roleMapper.toDTO(roleRepository.save(roleEntity));
    }

    @Override
    public List<RoleResponse> findAll() {
        List<RoleEntity> roles = roleRepository.findAll();
        return roles.stream().map(r -> roleMapper.toDTO(r)).toList();
    }

    @Override
    public RoleResponse updateRole(RoleUpdateRequest request) {
        RoleDTO roleDTO = roleMapper.toDTO(request);

        boolean isCodeExist = roleRepository.existsByCodeAndIdNot(roleDTO.getCode(), roleDTO.getId());
        boolean isNameExist = roleRepository.existsByNameAndIdNot(roleDTO.getName(), roleDTO.getId());

        if (isCodeExist) {
            throw new AppException(ErrorCode.CODE_ROLE_EXISTED);
        }
        if (isNameExist) {
            throw new AppException(ErrorCode.NAME_ROLE_EXISTED);
        }

        RoleEntity roleEntity = roleRepository.findById(roleDTO.getId()).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        roleMapper.update(roleEntity, roleDTO);
        return roleMapper.toDTO(roleRepository.save(roleEntity));
    }

    @Override
    public void deleteRole(DeleteRequest<RoleEntity> request) {
        for (Long id : request.getIds()) {
            if (roleRepository.existsById(id)){
                roleRepository.deleteById(id);
            } else throw new AppException(ErrorCode.ROLE_NOT_EXISTED);
        }
    }
}
