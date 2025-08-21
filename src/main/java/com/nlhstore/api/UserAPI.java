package com.nlhstore.api;

import com.nlhstore.dto.request.UserCreateRequest;
import com.nlhstore.dto.request.DeleteRequest;
import com.nlhstore.dto.request.UserUpdateRequest;
import com.nlhstore.dto.response.ApiResponse;
import com.nlhstore.dto.response.UserResponse;
import com.nlhstore.entity.UserEntity;
import com.nlhstore.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAPI {

    private static final Logger log = LoggerFactory.getLogger(UserAPI.class);
    @Autowired
    UserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<UserResponse>> findAll(@RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "0") int page) {
        Page<UserResponse> responsePage = userService.findAll(PageRequest.of(page, size));

        return ApiResponse.<List<UserResponse>>builder()
                .code(200)
                .result(responsePage.getContent())
                .build();
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .result(userService.getMyInfo())
                .build();
    }

    @PostMapping()
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        UserResponse response = userService.createUser(request);
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .result(response)
                .build();
    }

    @PostMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@RequestBody @Valid UserUpdateRequest request) {
        UserResponse response = userService.updateUser(request);
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .result(response)
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> deleteUserById(@PathVariable(required = false) Long id) {
        DeleteRequest<UserEntity> request = new DeleteRequest<>();
        request.setIds(List.of(id));
        userService.deleteUser(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .build();
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> deleteUsers(@RequestBody(required = false) DeleteRequest<UserEntity> request) {

        userService.deleteUser(request);
        return ApiResponse.<Void>builder()
                .code(200)
                .build();
    }
}
