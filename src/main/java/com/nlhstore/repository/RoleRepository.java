package com.nlhstore.repository;

import com.nlhstore.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository  extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByCode(String code);
}
