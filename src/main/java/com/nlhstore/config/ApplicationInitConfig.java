package com.nlhstore.config;

import com.nlhstore.entity.RoleEntity;
import com.nlhstore.entity.UserEntity;
import com.nlhstore.exception.AppException;
import com.nlhstore.exception.ErrorCode;
import com.nlhstore.repository.RoleRepository;
import com.nlhstore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration

public class ApplicationInitConfig {

    private static final Logger log = LoggerFactory.getLogger(ApplicationInitConfig.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                UserEntity userEntity = UserEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .email("admin@admin.com")
                        .build();
                RoleEntity roleEntity = roleRepository.findByCode("ADMIN").orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
                userEntity.setRoles(Set.of(roleEntity));
                userRepository.save(userEntity);
                log.warn("Người dùng 'admin' đã được tạo với mật khẩu mặc định là 'admin'. Vui lòng đổi mật khẩu");
            }
        };
    }
}
