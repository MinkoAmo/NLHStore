package com.nlhstore.repository;

import com.nlhstore.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    List<PermissionEntity> findAllByCodeIn(List<String> codes);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, Long id);
}
