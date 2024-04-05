package com.fto.repository;

import com.fto.model.entity.FamilyEntity;
import com.fto.model.entity.FamilyMemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface FamilyMemberRepository extends JpaRepository<FamilyMemberEntity,Long> {
    Optional<FamilyMemberEntity> findByNameAndFamily_Name(String name, String email);
}
