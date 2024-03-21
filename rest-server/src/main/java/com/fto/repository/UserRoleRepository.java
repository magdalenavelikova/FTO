package com.fto.repository;

import com.fto.model.entity.UserRoleEntity;
import com.fto.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {
    Optional<UserRoleEntity> findUserRoleEntitiesByRole(UserRoleEnum role);
}
