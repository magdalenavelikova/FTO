package com.fto.repository;

import com.fto.model.entity.FamilyEntity;
import com.fto.model.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FamilyRepository extends JpaRepository<FamilyEntity, Long> {


    List<FamilyEntity> findAllByUserEmail(String email);



    Optional<FamilyEntity> findByNameAndUserEmail(String name, String email);
}
