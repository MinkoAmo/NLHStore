package com.nlhstore.service.impl;

import com.nlhstore.dto.internal.UserDTO;
import com.nlhstore.dto.request.UserCreateRequest;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.request.UserUpdateRequest;
import com.nlhstore.dto.response.UserResponse;
import com.nlhstore.entity.RoleEntity;
import com.nlhstore.entity.UserEntity;
import com.nlhstore.exception.AppException;
import com.nlhstore.exception.ErrorCode;
import com.nlhstore.mapper.UserMapper;
import com.nlhstore.repository.RoleRepository;
import com.nlhstore.repository.UserRepository;
import com.nlhstore.service.IUserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<UserEntity> userEntities = userRepository.findAll(pageable);
        List<UserResponse> dtoList = userEntities.stream().map(x -> userMapper.toDTO(x)).toList();
        return new PageImpl<>(dtoList, pageable, userEntities.getTotalElements());
    }

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        UserDTO userDTO = userMapper.toDTO(request);

        boolean isUsernameExist = userRepository.existsByUsername(userDTO.getUsername());
        boolean isEmailExist = userRepository.existsByEmail(userDTO.getEmail());
        if (isUsernameExist) {
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        }
        if (isEmailExist) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        UserEntity userEntity = userMapper.toEntity(userDTO);
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        assignRoleToUser(userEntity, "USER");
        return userMapper.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserResponse updateUser(UserUpdateRequest request) {
        UserDTO userDTO = userMapper.toDTO(request);
        boolean isEmailExist = userRepository.existsByEmail(userDTO.getEmail());
        if (isEmailExist) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        UserEntity userEntity = userRepository.findById(userDTO.getId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.update(userEntity, userDTO);
        return userMapper.toDTO(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(DeleteRequest<UserEntity> request) {
        for (Long id : request.getIds()) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public UserResponse getMyInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toDTO(userEntity);
    }

    public void assignRoleToUser(UserEntity userEntity, String roleCode) {
        RoleEntity roleEntity = roleRepository.findByCode(roleCode).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        if (userEntity.getRoles() != null) {
            userEntity.getRoles().add(roleEntity);
        } else {
            userEntity.setRoles(Set.of(roleEntity));
        }
    }
}
