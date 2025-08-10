package com.nlhstore.service;

import com.nlhstore.dto.request.UserCreateRequest;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.request.UserUpdateRequest;
import com.nlhstore.dto.response.UserResponse;
import com.nlhstore.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    Page<UserResponse> findAll(Pageable pageable);

    UserResponse createUser(UserCreateRequest request);

    UserResponse updateUser(UserUpdateRequest request);

    void deleteUser(DeleteRequest<UserEntity> request);

    UserResponse getMyInfo();
}
